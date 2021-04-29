let holiday = new Object();
let table = null;

$(document).ready(() => {
    
    getAll();
    
    $("#createForm").submit(e => {
        e.preventDefault();
        validationForm(createHoliday);
    });
    
    $("#update").submit(e => {
        e.preventDefault();
        validationForm(updateHoliday);
    });
    
});


function getAll() {
    table = $('#holidayTable').DataTable({
        filter: true,
        orderMulti: true,
        ajax: {
            url: "/holiday/get-all",
            datatype: "json",
            dataSrc: ""
        },
        columns: [
            {
                data: "id", name: "Holiday Id", autoWidth: true
            },
            {
                data: "name", name: "Holiday", autoWidth: true
            },
            {
                data: "holidayDate", name: "Holiday Date", autoWidth: true
            },
            {
                render: (data, type, row, meta) => {
                    return `
                        <button 
                            class='btn btn-sm btn-primary'
                            data-toggle="modal" 
                            data-target="#holidayEdit" 
                            onclick="getById('${row.id}')">
                            <i class='fa fa-sm fa-pencil'></i>
                        </button>
                        <button 
                            class='btn btn-sm btn-danger' 
                            onclick='onClickDelete(${row.id})'>
                            <i class='fa fa-sm fa-trash'></i>
                        </button>
                    `;
                }
            }
        ]
    });
}


//GET BY ID
function getById(id) {
    console.log(id);
    $.ajax({
        url: `/holiday/${id}`,
        type: 'GET',
        success: (res) => {
            console.log(res);
            setForm(res);
        }
    }); 
}

function setForm(hdy) {
    $("#idU").val(hdy.id);
    $("#nameU").val(hdy.name);
    $("#holidayDateU").val(moment(hdy.holidayDate).format('YYYY[-]MM[-]DD'));
}

//CREATE EMPLOYEE
function createHoliday() {
    holiday = {
        name: $("#name").val(),
        holidayDate: $("#holidayDate").val()
    };
    console.log(holiday);
    
    $.ajax({
        url: `/holiday`,
        type: 'POST',
        data: JSON.stringify(holiday),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: (res) => {
            createSuccessAlert();
            console.log("Success");
            table.ajax.reload();
            $("#holidayModal").modal("hide");
            document.getElementById("createForm").reset();
        },
        error: function (err) {
            errorAlert();
        }
    });
}

//UPDATE EMPLOYEE
function updateHoliday(){
    holiday = {
        id: $("#idU").val(),
        name: $("#nameU").val(),
        holidayDate: $("#holidayDateU").val()
    };
    console.log(holiday);
    
    $.ajax({
        url: `/holiday/${holiday.id}`,
        type: 'PUT',
        data: JSON.stringify(holiday),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: (res) => {
            console.log("Success");
            table.ajax.reload();
            updateSuccessAlert();
            $("#holidayEdit").modal("hide");
        },
        error: function (err) {
            errorAlert();
        }
    });
}

//DELETE EMPLOYEE
function deleteHoliday(id) {
    $.ajax({
        url: `/holiday/${id}`,
        type: 'DELETE',
        success: (res) => {
            table.ajax.reload();
            deleteSuccessAlert();
        },
        error: function (err) {
            errorAlert();
        }
    });
}
