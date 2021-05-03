let manager = new Object;
let tableMgr = null;

$(document).ready(() => {
    
    console.log("test")
    
    getAllManager();
    
    $("#updateManager").submit(e => {
        e.preventDefault();
        validationForm(updateManager);
    });
    
});


function getAllManager() {
    console.log("masuk get all manager")
    tableMgr = $('#managerTest').DataTable({
        filter: true,
        orderMulti: true,
        ajax: {
            url: "/manager/by-mgrId",
            datatype: "json",
            dataSrc: ""
        },
        columns: [
            {
                data: "reqId", name: "ID", autoWidth: true
            },
            {
                data: "employeeName", name: "Employee", autoWidth: true
            },
            { 
                data: "leaveName", name: "Leave Type", autoWidth: true
            },
            {
                data: "startDate", name: "Start Date", autoWidth: true,
                render: function (data, type, row, meta) {
                      return moment(new Date(data).toString()).format('DD MMM YYYY');}
            },
            {
                data: "duration", name: "Duration", autoWidth: true
            },
            {
                data: "reasons", name: "Reasons", autoWidth: true
            },
            {
                render: (data, type, row, meta) => {
                    return `
                        <button 
                            class='btn btn-sm btn-primary'
                            data-toggle="modal" style="align:center"
                            data-target="#ManagerEdit" 
                            onclick="getManagerById('${row.reqId}')">
                            <i class='fa fa-sm fa-circle-o-notch'></i>
                        </button>
                    `;
                }
            }
        ]
    });
}


//GET BY ID
function getManagerById(id) {
    console.log(id);
    $.ajax({
        url: `/manager/${id}`,
        type: 'GET',
        success: (res) => {
            console.log(res);
            setManagerForm(res);
        }
    }); 
}

function setManagerForm(mdl) {
    $("#reqId").val(mdl.reqId);
    $("#status").val(mdl.statusId.statusId);
    $("#note").val(mdl.note);
    }

//update manager 
function updateManager(){
    manager = {
        reqId: $("#reqId").val(),
        statusId: $("#status").val(),
        note: $("#note").val(),
    };
    console.log(manager);
    let linke = `/manager/${manager.reqId}`
    console.log(linke);
    $.ajax({
        url: `/manager/${manager.reqId}`,
        type: 'PUT',
        data: JSON.stringify(manager),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: (res) => {
            console.log("Success");
            tableMgr.ajax.reload();
            updateSuccessAlert();
            $("#ManagerEdit").modal("hide");
        },
        error: function (err) {
            errorAlert();
        }
    });
}

