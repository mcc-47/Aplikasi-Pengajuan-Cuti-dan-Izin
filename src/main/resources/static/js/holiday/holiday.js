

let holiday = new Object();
let tableHol = null;

$(document).ready(() => {
    console.log("di holiday");
    getAll();
    
    $("#createForm").submit(e => {
        e.preventDefault();
        validationForm(createHoliday);
    });
    
    $("#updateHol").submit(e => {
        e.preventDefault();
        validationForm(updateHoliday);
    });
    
    
});


function getAll() {
//    $.fn.dataTable.moment('DD/MM/YYYY');
    tableHol = $('#holidayTable').DataTable({
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
                data: "holidayDate", name: "Holiday Date", autoWidth: true,
                render: function (data, type, row, meta) {
                      return moment(new Date(data).toString()).format('DD MMM YYYY');}
//                ,render: $.fn.dataTable.render.moment('YYYY/MM/DD', 'Do MMM YY')
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
                            onclick='onClickDeleteH(${row.id})'>
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
            setFormH(res);
        }
    }); 
}

function setFormH(hdy) {
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
            tableHol.ajax.reload();
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
            tableHol.ajax.reload();
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
            tableHol.ajax.reload();
            deleteSuccessAlert();
        },
        error: function (err) {
            errorAlert();
        }
    });
}

//DELETE BUTTON
function onClickDeleteH(id){
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText:'Yes, delete it!'
        }).then((result) => {
            if (result.isConfirmed) {
                deleteHoliday(id);
                }
        });
}

