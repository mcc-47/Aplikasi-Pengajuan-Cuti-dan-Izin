

let request = new Object();
let tableA = null;

$(document).ready(() => {
    console.log("di request");
    getAllReqEmp();
    
    $("#reqLeaveForm").submit(e => {
        e.preventDefault();
        validationForm(createRequest);
    });
    
});


function getAllReqEmp() {
    tableA = $('#reqListTab').DataTable({
        filter: true,
        orderMulti: true,
        ajax: {
            url: "/request/by-empId",
            datatype: "json",
            dataSrc: ""
        },
        columns: [
            {
                data: "reqId", name: "ID", autoWidth: true
            },
            {
                data: "leaveName", name: "Request Type", autoWidth: true
            },
            {
                data: "leaveDuration", name: "Duration", autoWidth: true
            },
            {
                data: "startDate", name: "Start Date", autoWidth: true
            },
            {
                data: "reasons", name: "Reasons", autoWidth: true
            },
            {
                data: "manager", name: "Manager", autoWidth: true
            },
            {
                data: "statusName", name: "Status", autoWidth: true
            },
        {render: (data, type, row, meta) => {
                    return `
                        <button 
                            class='btn btn-sm btn-primary'
                            data-toggle="modal" style="align:center"
                            data-target="#RequestEdit" 
                            onclick="getreqById('${row.reqId}')">
                            <i class='fa fa-sm fa-circle-o-notch'></i>
                        </button>
                    `;
                }
            }
        ]
    });
}
//GET BY ID
function getreqById(id) {
    console.log(id);
    $.ajax({
        url: `/manager/${id}`,
        type: 'GET',
        success: (res) => {
            console.log(res);
            console.log("yg diambil diatas")
           setRequestForm(res);
        }
    }); 
}

function setRequestForm(mdl) {
    $("#req_Id").val(mdl.reqId);
    $("#noteDet").val(mdl.note);
    $("#appDate").val(moment(mdl.approvementDate).format('YYYY[-]MM[-]DD'));
    }
    
    
//CREATE REQUEST
function createRequest() {
    request = {
        leaveId: $("#reqType").val(),
        reasons: $("#reasons").val(),
        startDate: $("#startDate").val()
    };
    console.log(request);
    
    $.ajax({
        url: `/request`,
        type: 'POST',
        data: JSON.stringify(request),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: (res) => {
            createSuccessAlert();
            console.log("Success");
            tableA.ajax.reload();
            $("#requestEmp").modal("hide");
            document.getElementById("reqLeaveForm").reset();
        },
        error: function (err) {
            errorAlert();
        }
    });
}