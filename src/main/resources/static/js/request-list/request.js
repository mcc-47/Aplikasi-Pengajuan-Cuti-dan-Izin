

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
                data: "startDate", name: "Start Date", autoWidth: true,
                render: function (data, type, row, meta) {
                      return moment(new Date(data).toString()).format('DD MMM YYYY');}
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
        leaveDuration: $("#leaveDuration").val(),
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
//            requestMail();
            tableA.ajax.reload();
            $("#requestEmp").modal("hide");
            document.getElementById("reqLeaveForm").reset();
        },
        error: function (err) {
            errorAlert();
        }
    });
}

function setDuration(){
    console.log("masuk fungsi");
    if( document.getElementById("reqType").value === "1" ) {
        $("#leaveDuration").val(1);
        $("#leaveDuration").removeAttr('readonly');
        $("#leaveDuration").attr('max', '3');
        $("#leaveDuration").attr('min', '1');
        console.log("oke masuk if isinya 1");
        }
    if( document.getElementById("reqType").value === "2" ) {
        $("#leaveDuration").val(1);
        $("#leaveDuration").removeAttr('readonly');
        $("#leaveDuration").attr('min', '1');
        if (validasiProfil.totalLeave >= 5) {
        $("#leaveDuration").attr('max', '5');
        }
        if (validasiProfil.totalLeave === 4) {
        $("#leaveDuration").attr('max', '4');
        }
        if (validasiProfil.totalLeave === 3) {
        $("#leaveDuration").attr('max', '3');
        }
        if (validasiProfil.totalLeave === 2) {
        $("#leaveDuration").attr('max', '2');
        }
        if (validasiProfil.totalLeave === 1) {
        $("#leaveDuration").attr('max', '1');
        }
        console.log("oke masuk if isinya 2");
        }
    if( document.getElementById("reqType").value === "3" ) {
        $("#leaveDuration").val(90);
        $("#leaveDuration").attr('readonly', true);
        console.log("oke masuk if isinya 3");
        }
    if( document.getElementById("reqType").value === "4" ) {
        $("#leaveDuration").val(3);
        $("#leaveDuration").attr('readonly', true);
        console.log("oke masuk if isinya 4");
        }
    if( document.getElementById("reqType").value === "5" ) {
        $("#leaveDuration").val(30);
        $("#leaveDuration").attr('readonly', true);
        console.log("oke masuk if isinya 5");
        }
    if( document.getElementById("reqType").value === "6" ) {
        $("#leaveDuration").val(2);
        $("#leaveDuration").attr('readonly', true);
        console.log("oke masuk if isinya 6");
        }
    if( document.getElementById("reqType").value === "7" ) {
        $("#leaveDuration").val(2);
        $("#leaveDuration").attr('readonly', true);
        console.log("oke masuk if isinya 7");
        }
    
}

function setLeaveValidation(){
    
    const picker = document.getElementById('startDate');
    picker.addEventListener('input', function(e){
        let day = new Date(this.value).getUTCDay();
        if([6,0].includes(day) ){
            e.preventDefault();
            this.value = '';
            errorPickDate();
        }
        
        ambilHoliday(this.value,e);
        
        
    });
    
    $("#cutiMelahirkan").removeAttr('hidden');
    $("#cutiHaji").removeAttr('hidden');
    $("#cutiBiasa").removeAttr('hidden');
    if (validasiProfil.gender === "Male") {
        $("#cutiMelahirkan").attr('hidden', true);
    }
    if (validasiProfil.religion !== "Islam") {
        $("#cutiHaji").attr('hidden', true);
    }
    if (validasiProfil.totalLeave === 0) {
        $("#cutiBiasa").attr('hidden', true);
    }
    setDuration();
    
}

//GET BY ID
function ambilHoliday(inputan,e) {
    console.log("masuk ambil holidays");
    $.ajax({
        url: `/employee/get-holidays`,
        datatype: "json",
        type: 'GET',
        success: (res) => {
            let arrayHolidays = new Object();
            arrayHolidays = res;
            console.log("dapat respon list holiday");
            console.log(arrayHolidays);
            let pickDate = moment(new Date(inputan)).format('YYYY-MM-DD');
            console.log("lala");
            console.log(pickDate);
            if (arrayHolidays.includes(pickDate)) {
                e.preventDefault();
                $("#startDate").val("");
                errorPickDate();
            }
        }
    });
}

//Sent Request Mail
//function requestMail(){
//    $.ajax({
//        url: `/message/request`,
//        type: 'GET',
//        success: (res) => {
//            console.log(res);
//        }
//    });
//}