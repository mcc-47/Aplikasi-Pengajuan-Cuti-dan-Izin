let manager = new Object;
let tableMgr = null;

$(document).ready(() => {
    
    getAllManager();
    
    $("#accReq").on('click',e => {
        e.preventDefault();
        validationForm(updateAccept);
    });
    
    $("#denReq").on('click',e => {
        e.preventDefault();
        validationForm(updateDeny);
    });
    
});


function getAllManager() {
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
                render: (data, type, row, meta) => {
                    return `
                        <button 
                            class='btn btn-sm ' id="${row.reqId}"
                            data-toggle="modal" onload="changeColour('${row.reqId}')"
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


function changeColour(id) {
    console.log(`/manager/${id}`);
    $.ajax({
        url: `/manager/${id}`,
        type: 'GET',
        success: (res) => {
            console.log(res.statusName);
            switch (res.statusName) {
                case "on progress":
                    $(this).addClass("btn-primary");
                    console.log("jalan");
                    break;
                case "accepted":
                    $(this).addClass("btn-success");
                    console.log("terima");
                    break;
                case "denied":
                    $(this).addClass("btn-danger");
                    console.log("tolak");
                    let idnya = document.getElementById('${id}')
                    console.log(idnya);
                    break;
                
            }
            
        }
    }); 
    
}


//GET BY ID
function getManagerById(id) {
    $.ajax({
        url: `/manager/${id}`,
        type: 'GET',
        success: (res) => {
            setManagerForm(res);
        }
    }); 
}

function setManagerForm(mdl) {
    $("#reqIdReq").val(mdl.reqId);
    $("#employeeNameReq").val(mdl.employeeName);
    $("#totalLeaveReq").val(mdl.totalLeave);
    $("#startDateReq").val(moment(mdl.startDate).format('YYYY[-]MM[-]DD'));
    $("#endDateReq").val(moment(mdl.endDate).format('YYYY[-]MM[-]DD'));
    $("#leaveDurationReq").val(mdl.leaveDuration);
    $("#note").val(mdl.note);
    }

//update manager 
function updateAccept(){
    manager = {
        reqId: $("#reqIdReq").val(),
        statusId: $("#accReq").val(),
        note: $("#noteReq").val()
    };
    let linke = `/manager/${manager.reqId}`
    $.ajax({
        url: `/manager/${manager.reqId}`,
        type: 'PUT',
        data: JSON.stringify(manager),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: (res) => {
            tableMgr.ajax.reload();
            updateSuccessAlert();
            $("#ManagerEdit").modal("hide");
        },
        error: function (err) {
            errorAlert();
        }
    });
}
function updateDeny(){
    manager = {
        reqId: $("#reqIdReq").val(),
        statusId: $("#denReq").val(),
        note: $("#noteReq").val()
    };
    let linke = `/manager/${manager.reqId}`
    $.ajax({
        url: `/manager/${manager.reqId}`,
        type: 'PUT',
        data: JSON.stringify(manager),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: (res) => {
            tableMgr.ajax.reload();
            updateSuccessAlert();
            $("#ManagerEdit").modal("hide");
        },
        error: function (err) {
            errorAlert();
        }
    });
}

