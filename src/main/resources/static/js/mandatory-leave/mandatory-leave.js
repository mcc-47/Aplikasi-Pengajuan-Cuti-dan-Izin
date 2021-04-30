let mandatoryLeave = new Object();
let tableMdl = null;

$(document).ready(() => {
    
    console.log("di mandatory leave")
    
    getAllMandatory();
    
    $("#createMandatoryForm").submit(e => {
        e.preventDefault();
        validationForm(createMandatoryLeave);
    });
    
    $("#updateMandatoryForm").submit(e => {
        e.preventDefault();
        validationForm(updateMandatoryLeave);
    });
    
});


function getAllMandatory() {
    tableMdl = $('#mandatoryTest').DataTable({
        filter: true,
        orderMulti: true,
        ajax: {
            url: "/mandatory-leave/get-all",
            datatype: "json",
            dataSrc: ""
        },
        columns: [
            {
                data: "id", name: "Mandatory Leave Id", autoWidth: true
            },
            {
                data: "name", name: "Name", autoWidth: true
            },
            {
                data: "startDate", name: "Mandatory Leave Date", autoWidth: true
            },
            {
                data: "duration", name: "Duration", autoWidth: true
            },
            {
                render: (data, type, row, meta) => {
                    return `
                        <button 
                            class='btn btn-sm btn-primary'
                            data-toggle="modal" 
                            data-target="#mandatoryLeaveEdit" 
                            onclick="getMandatoryById('${row.id}')">
                            <i class='fa fa-sm fa-pencil'></i>
                        </button>
                        <button 
                            class='btn btn-sm btn-danger' 
                            onclick='onClickDeleteML(${row.id})'>
                            <i class='fa fa-sm fa-trash'></i>
                        </button>
                    `;
                }
            }
        ]
    });
}


//GET BY ID
function getMandatoryById(id) {
    console.log(id);
    $.ajax({
        url: `/mandatory-leave/${id}`,
        type: 'GET',
        success: (res) => {
            console.log(res);
            setMandatoryForm(res);
        }
    }); 
}

function setMandatoryForm(mdl) {
    $("#idU").val(mdl.id);
    $("#nameU").val(mdl.name);
    $("#startDateU").val(moment(mdl.startDate).format('YYYY[-]MM[-]DD'));
    $("#durationU").val(mdl.duration);
}

//CREATE EMPLOYEE
function createMandatoryLeave() {
    mandatoryLeave = {
        name: $("#name").val(),
        startDate: $("#startDate").val(),
        duration: $("#duration").val()
    };
    console.log(mandatoryLeave);
    
    $.ajax({
        url: `/mandatory-leave`,
        type: 'POST',
        data: JSON.stringify(mandatoryLeave),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: (res) => {
            createSuccessAlert();
            console.log("Success");
            tableMdl.ajax.reload();
            $("#mandatoryLeaveModal").modal("hide");
            document.getElementById("createMandatoryForm").reset();
        },
        error: function (err) {
            errorAlert();
        }
    });
}

//UPDATE EMPLOYEE
function updateMandatoryLeave(){
    mandatoryLeave = {
        id: $("#idU").val(),
        name: $("#nameU").val(),
        startDate: $("#startDateU").val(),
        duration: $("#durationU").val()
    };
    console.log(mandatoryLeave);
    
    $.ajax({
        url: `/mandatory-leave/${mandatoryLeave.id}`,
        type: 'PUT',
        data: JSON.stringify(mandatoryLeave),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: (res) => {
            console.log("Success");
            tableMdl.ajax.reload();
            updateSuccessAlert();
            $("#mandatoryLeaveEdit").modal("hide");
        },
        error: function (err) {
            errorAlert();
        }
    });
}
//
////DELETE EMPLOYEE
function deleteMandatoryLeave(id) {
    $.ajax({
        url: `/mandatory-leave/${id}`,
        type: 'DELETE',
        success: (res) => {
            tableMdl.ajax.reload();
            deleteSuccessAlert();
        },
        error: function (err) {
            errorAlert();
        }
    });
}

//DELETE BUTTON
function onClickDeleteML(id){
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
                deleteMandatoryLeave(id);
                }
        });
}