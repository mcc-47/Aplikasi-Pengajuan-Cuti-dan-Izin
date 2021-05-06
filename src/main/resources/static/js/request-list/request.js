

let request = new Object();
let tableA = null;

$(document).ready(() => {
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
                data: "startDate", name: "Start Date", autoWidth: true,
                render: function (data, type, row, meta) {
                      return moment(new Date(data).toString()).format('DD MMM YYYY');}
            },
            {
                data: "endDate", name: "End Date", autoWidth: true,
                render: function (data, type, row, meta) {
                      return moment(new Date(data).toString()).format('DD MMM YYYY');}
            },
            {
                data: "leaveDuration", name: "Duration", autoWidth: true
            },
            {
                render: (data, type, row, meta) => {
                    return `
                        <button 
                            position='center' sty id='detailsButton'
                            class='btn btn-sm btn-info'
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
    $.ajax({
        url: `/request/${id}`,
        type: 'GET',
        success: (res) => {
           setRequestForm(res);
        }
    }); 
}

function setRequestForm(mdl) {
    $("#leaveTypeDet").val(mdl.leaveName);
    $("#reasonsDet").val(mdl.reasons);
    $("#statusDet").val(mdl.statusName.toUpperCase());
    $("#managerDet").val(mdl.managerName);
    $("#noteDet").val(mdl.note);
    $("#appDate").val(moment(mdl.approvalDate).format('YYYY[-]MM[-]DD'));
    }
    
    
//CREATE REQUEST
function createRequest() {
    request = {
        leaveId: $("#reqType").val(),
        leaveDuration: $("#leaveDuration").val(),
        reasons: $("#reasons").val(),
        startDate: $("#startDate").val(),
        endDate: $("#endDate").val()
    };
    
    $.ajax({
        url: `/request`,
        type: 'POST',
        data: JSON.stringify(request),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: (res) => {
            createSuccessAlert();
//            requestMail();
            tableA.ajax.reload();
            $("#requestEmp").modal("hide");
            document.getElementById("reqLeaveForm").reset();
        },
        error: function (err) {
//            errorAlert();
        }
    });
}

function setDuration(){
    console.log("masuk fungsi");
    if( $("#reqType").val() === "1" ) {
        
        
        $("#leaveDuration").val(1);
        $("#leaveDuration").removeAttr('readonly');
        $("#leaveDuration").attr('max', '3');
        $("#leaveDuration").attr('min', '1');
        console.log("oke masuk if isinya 1");
        $("#leaveDuration").on('change', function (e) {
            if ($("#leaveDuration").val() > 3 ) {
                $("#leaveDuration").val(3);
            } else if ($("#leaveDuration").val() < 1 ) {
                $("#leaveDuration").val(1);
            }

        });
        }
        
    if( $("#reqType").val() === "2" ) {
        $("#leaveDuration").val(1);
        $("#leaveDuration").removeAttr('readonly');
        $("#leaveDuration").attr('min', '1');
        
        if (validasiProfil.totalLeave >= 5) {
        $("#leaveDuration").attr('max', '5');
        $("#leaveDuration").on('change', function (e) {
            if ($("#leaveDuration").val() > 5) {
                $("#leaveDuration").val(5);
            } else if ($("#leaveDuration").val() < 1 ) {
                $("#leaveDuration").val(1);
            }
        });
        }
        if (validasiProfil.totalLeave === 4) {
        $("#leaveDuration").attr('max', '4');
        $("#leaveDuration").on('change', function (e) {
            if ($("#leaveDuration").val() > 4) {
                $("#leaveDuration").val(4);
            } else if ($("#leaveDuration").val() < 1 ) {
                $("#leaveDuration").val(1);
            }
        });
        }
        if (validasiProfil.totalLeave === 3) {
        $("#leaveDuration").attr('max', '3');
        $("#leaveDuration").on('change', function (e) {
            if ($("#leaveDuration").val() > 3) {
                $("#leaveDuration").val(3);
            } else if ($("#leaveDuration").val() < 1 ) {
                $("#leaveDuration").val(1);
            }
        });
        }
        if (validasiProfil.totalLeave === 2) {
        $("#leaveDuration").attr('max', '2');
        $("#leaveDuration").on('change', function (e) {
            if ($("#leaveDuration").val() > 2) {
                $("#leaveDuration").val(2);
            } else if ($("#leaveDuration").val() < 1 ) {
                $("#leaveDuration").val(1);
            }
        });
        }
        if (validasiProfil.totalLeave === 1) {
        $("#leaveDuration").attr('max', '1');
        $("#leaveDuration").on('change', function (e) {
            if ($("#leaveDuration").val() > 1 || $("#leaveDuration").val() < 1) {
                $("#leaveDuration").val(1);
            } 
        });
        }
        console.log("oke masuk if isinya 2");
        }
        
    if( $("#reqType").val() === "3" ) {
        $("#leaveDuration").val(90);
        $("#leaveDuration").attr('readonly', true);
        console.log("oke masuk if isinya 3");
        }
    if( $("#reqType").val() === "4" ) {
        $("#leaveDuration").val(3);
        $("#leaveDuration").attr('readonly', true);
        console.log("oke masuk if isinya 4");
        }
    if( $("#reqType").val() === "5" ) {
        $("#leaveDuration").val(30);
        $("#leaveDuration").attr('readonly', true);
        console.log("oke masuk if isinya 5");
        }
    if( $("#reqType").val() === "6" ) {
        $("#leaveDuration").val(2);
        $("#leaveDuration").attr('readonly', true);
        console.log("oke masuk if isinya 6");
        }
    if( $("#reqType").val() === "7" ) {
        $("#leaveDuration").val(2);
        $("#leaveDuration").attr('readonly', true);
        console.log("oke masuk if isinya 7");
        }
    
}

function setLeaveValidation(){
    let dateToday = new Date(); 
    $(function() {
        $( "#startDate" ).datepicker({
            numberOfMonths: 3,
            showButtonPanel: true,
            minDate: dateToday
        });
    });

    
    
    $("#startDate").on('input', function(e){
        let day = new Date(this.value).getUTCDay();
        if([6,0].includes(day) ){
            e.preventDefault();
            this.value = '';
            errorPickDate();
        }
        ambilHoliday(this.value,e);
    });
    
    $("#startDate").on('input',function (e) {
        let maxDate = new Date($("#endDate").attr('max', function (e) {
            let maxEnd = new Date($("#startDate").val());
            maxEnd.setDate(maxEnd.getDate()+2);
            console.log("batasi end date");
            console.log(maxEnd);
            return moment(maxEnd).format('YYYY[-]MM[-]DD');
        }));    
        console.log(maxDate);
    });
    
    $("#endDate").on('input',function (e) {
        if (!$("#startDate").val() || [6,0].includes(new Date($("#endDatels")).getUTCDay())) {
            e.preventDefault();
            this.value = '';
            errorPickDate();
        }
//        if ($("#endDate").val() > $("#endDate").attr('max').val()) {
//            console.log("melebihi btas");
//        }
    });
    
    
    $("#cutiMelahirkan").removeAttr('hidden');
    $("#cutiSunatAnak").removeAttr('hidden');
    $("#cutiHaji").removeAttr('hidden');
    $("#cutiBiasa").removeAttr('hidden');
    if (validasiProfil.gender === "Male") {
        $("#cutiMelahirkan").attr('hidden', true);
    }
    if (validasiProfil.maritalStatus === "Single") {
        $("#cutiSunatAnak").attr('hidden', true);
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
