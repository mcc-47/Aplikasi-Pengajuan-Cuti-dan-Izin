

let request = new Object();
let tableA = null;

$(document).ready(() => {
    getAllReqEmp();
    setLeaveValidation();
    
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



////////////////////////////////////////////////////////////////////////////////
function setDuration(){
    if( $("#reqType").val() === "1" ) {
        $('#endDate').removeAttr('readonly');
        $('#startDate').val(null);
        $("#endDate").val(null);
        $("#startDate").on('input', function(e){
        $('#endDate').val(null);
            //max end date
            let maxAkhir = new Date($("#startDate").val());
            let bulannya = maxAkhir.getMonth() + 1;
            let id = 2;
            let numDay = new Date(this.value).getDay();
            console.log(numDay);
            if ( numDay > 3) {
                id = 4;
            }
            let harinya = maxAkhir.getDate() + id;
            let tahunnya = maxAkhir.getFullYear();
            if(bulannya < 10)
                bulannya = '0' + bulannya.toString();
            if(harinya < 10)
                harinya = '0' + harinya.toString();
            let maxDate = tahunnya + '-' + bulannya + '-' + harinya;
            $("#endDate").attr('max', maxDate);
            $("#endDate").on('input', function () {
                let date1 = new Date($("#startDate").val());
                let date2 = new Date($("#endDate").val());
                let diff = date2.getTime() - date1.getTime();
                let msInDay = 1000 * 3600 * 24;
                $("#leaveDuration").val(diff/msInDay + 1 );
            });
        });
        }
    ////////////////////////////////////////////////////////////////////////////
    if( $("#reqType").val() === "2" ) {
        $('#endDate').removeAttr('readonly');
        $('#startDate').val(null);
        $('#endDate').val(null);
        if (validasiProfil.totalLeave >= 5) {
            $("#startDate").on('input', function(e){
            $('#endDate').val(null);
            //max end date
            let maxAkhir = new Date($("#startDate").val());
            let bulannya = maxAkhir.getMonth() + 1;
            let id = 4;
            let numDay = new Date(this.value).getDay();
            console.log(numDay);
            if ( numDay > 1) {
                id = 6;
            }
            let harinya = maxAkhir.getDate() + id;
            let tahunnya = maxAkhir.getFullYear();
            if(bulannya < 10)
                bulannya = '0' + bulannya.toString();
            if(harinya < 10)
                harinya = '0' + harinya.toString();
            let maxDate = tahunnya + '-' + bulannya + '-' + harinya;
            $('#endDate').attr('max', maxDate);
            $("#endDate").on('input', function () {
                let date1 = new Date($("#startDate").val());
                let date2 = new Date($("#endDate").val());
                let diff = date2.getTime() - date1.getTime();
                let msInDay = 1000 * 3600 * 24;
                $("#leaveDuration").val(diff/msInDay + 1 );
            });
        });
        }
        if (validasiProfil.totalLeave === 4) {
        $("#startDate").on('input', function(e){
            $('#endDate').val(null);
            //max end date
            let maxAkhir = new Date($("#startDate").val());
            let bulannya = maxAkhir.getMonth() + 1;
            let id = 3;
            let numDay = new Date(this.value).getDay();
            console.log(numDay);
            if ( numDay > 2) {
                id = 5;
            }
            let harinya = maxAkhir.getDate() + id;
            let tahunnya = maxAkhir.getFullYear();
            if(bulannya < 10)
                bulannya = '0' + bulannya.toString();
            if(harinya < 10)
                harinya = '0' + harinya.toString();
            let maxDate = tahunnya + '-' + bulannya + '-' + harinya;
            $('#endDate').attr('max', maxDate);
            $("#endDate").on('input', function () {
                let date1 = new Date($("#startDate").val());
                let date2 = new Date($("#endDate").val());
                let diff = date2.getTime() - date1.getTime();
                let msInDay = 1000 * 3600 * 24;
                $("#leaveDuration").val(diff/msInDay + 1 );
            });
        });
        }
        if (validasiProfil.totalLeave === 3) {
        $("#startDate").on('input', function(e){
            $('#endDate').val(null);
            //max end date
            let maxAkhir = new Date($("#startDate").val());
            let bulannya = maxAkhir.getMonth() + 1;
            let id = 2;
            let numDay = new Date(this.value).getDay();
            if ( numDay > 3) {
                id = 4;
            }
            let harinya = maxAkhir.getDate() + id;
            let tahunnya = maxAkhir.getFullYear();
            if(bulannya < 10)
                bulannya = '0' + bulannya.toString();
            if(harinya < 10)
                harinya = '0' + harinya.toString();
            let maxDate = tahunnya + '-' + bulannya + '-' + harinya;
            $('#endDate').attr('max', maxDate);
            $("#endDate").on('input', function () {
                let date1 = new Date($("#startDate").val());
                let date2 = new Date($("#endDate").val());
                let diff = date2.getTime() - date1.getTime();
                let msInDay = 1000 * 3600 * 24;
                $("#leaveDuration").val(diff/msInDay + 1 );
            });
        });
        }
        if (validasiProfil.totalLeave === 2) {
        $("#startDate").on('input', function(e){
            $('#endDate').val(null);
            //max end date
            let maxAkhir = new Date($("#startDate").val());
            let bulannya = maxAkhir.getMonth() + 1;
            let id = 1;
            let numDay = new Date(this.value).getDay();
            console.log(numDay);
            if ( numDay > 2) {
                id = 3;
            }
            let harinya = maxAkhir.getDate() + id;
            let tahunnya = maxAkhir.getFullYear();
            if(bulannya < 10)
                bulannya = '0' + bulannya.toString();
            if(harinya < 10)
                harinya = '0' + harinya.toString();
            let maxDate = tahunnya + '-' + bulannya + '-' + harinya;
            $('#endDate').attr('max', maxDate);
            $("#endDate").on('input', function () {
                let date1 = new Date($("#startDate").val());
                let date2 = new Date($("#endDate").val());
                let diff = date2.getTime() - date1.getTime();
                let msInDay = 1000 * 3600 * 24;
                $("#leaveDuration").val(diff/msInDay + 1 );
            });
        });
        }
        if (validasiProfil.totalLeave === 1) {
        $("#startDate").on('input', function(e){
            $('#endDate').val(null);
            //max end date
            let maxAkhir = new Date($("#startDate").val());
            let bulannya = maxAkhir.getMonth() + 1;
            let harinya = maxAkhir.getDate();
            let tahunnya = maxAkhir.getFullYear();
            if(bulannya < 10)
                bulannya = '0' + bulannya.toString();
            if(harinya < 10)
                harinya = '0' + harinya.toString();
            let maxDate = tahunnya + '-' + bulannya + '-' + harinya;
            $('#endDate').attr('max', maxDate);
            $("#leaveDuration").val(1);
        });
        }
    }
    /////////////////////////////////////////////////////////////////////////////

    if( $("#reqType").val() === "3" ) {
        $('#endDate').attr('readonly','readonly');
        $('#startDate').val(null);
        $('#endDate').val(null);
        $("#startDate").on('input', function(e){
            $('#endDate').val(null);
            //max end date
            let maxAkhir = new Date($("#startDate").val());
            let bulannya = maxAkhir.getMonth() + 4;
            let harinya = maxAkhir.getDate();
            let tahunnya = maxAkhir.getFullYear();
            if(bulannya < 10)
                bulannya = '0' + bulannya.toString();
            if(harinya < 10)
                harinya = '0' + harinya.toString();
            let maxDate = tahunnya + '-' + bulannya + '-' + harinya;
            $('#endDate').val(maxDate);
            $("#leaveDuration").val(90);
        });
        }
        
    if( $("#reqType").val() === "4" ) {
        $('#endDate').attr('readonly','readonly');
        $('#startDate').val(null);
        $('#endDate').val(null);
        $("#startDate").on('input', function(e){
            $('#endDate').val(null);
            //max end date
            let maxAkhir = new Date($("#startDate").val());
            let bulannya = maxAkhir.getMonth() + 1;
            let id = 2;
            let numDay = new Date(this.value).getDay();
            if ( numDay > 3) {
                id = 4;
            }
            let harinya = maxAkhir.getDate() + id;
            let tahunnya = maxAkhir.getFullYear();
            if(bulannya < 10)
                bulannya = '0' + bulannya.toString();
            if(harinya < 10)
                harinya = '0' + harinya.toString();
            let maxDate = tahunnya + '-' + bulannya + '-' + harinya;
            $('#endDate').val(maxDate);
            $("#leaveDuration").val(3);
        });
        }
        
    if( $("#reqType").val() === "5" ) {
        $('#endDate').attr('readonly','readonly');
        $('#startDate').val(null);
        $('#endDate').val(null);
        $("#startDate").on('input', function(e){
            $('#endDate').val(null);
            //max end date
            let maxAkhir = new Date($("#startDate").val());
            let bulannya = maxAkhir.getMonth() + 2;
            let harinya = maxAkhir.getDate();
            let tahunnya = maxAkhir.getFullYear();
            if(bulannya < 10)
                bulannya = '0' + bulannya.toString();
            if(harinya < 10)
                harinya = '0' + harinya.toString();
            let maxDate = tahunnya + '-' + bulannya + '-' + harinya;
            $('#endDate').val(maxDate);
            $("#leaveDuration").val(30);
        });
        }
        
    if( $("#reqType").val() === "6" ) {
        $('#endDate').attr('readonly','readonly');
        $('#startDate').val(null);
        $('#endDate').val(null);
        $("#startDate").on('input', function(e){
            $('#endDate').val(null);
            //max end date
            let maxAkhir = new Date($("#startDate").val());
            let bulannya = maxAkhir.getMonth() + 1;
            let id = 1;
            let numDay = new Date(this.value).getDay();
            if ( numDay > 4) {
                id = 3;
            }
            let harinya = maxAkhir.getDate() + id;
            let tahunnya = maxAkhir.getFullYear();
            if(bulannya < 10)
                bulannya = '0' + bulannya.toString();
            if(harinya < 10)
                harinya = '0' + harinya.toString();
            let maxDate = tahunnya + '-' + bulannya + '-' + harinya;
            $('#endDate').val(maxDate);
            $("#leaveDuration").val(2);
        });
        }
        
    if( $("#reqType").val() === "7" ) {
        $('#endDate').attr('readonly','readonly');
        $('#startDate').val(null);
        $('#endDate').val(null);
        $("#startDate").on('input', function(e){
            $('#endDate').val(null);
            //max end date
            let maxAkhir = new Date($("#startDate").val());
            let bulannya = maxAkhir.getMonth() + 1;
            let id = 1;
            let numDay = new Date(this.value).getDay();
            if ( numDay > 4) {
                id = 3;
            }
            let harinya = maxAkhir.getDate() + id;
            let tahunnya = maxAkhir.getFullYear();
            if(bulannya < 10)
                bulannya = '0' + bulannya.toString();
            if(harinya < 10)
                harinya = '0' + harinya.toString();
            let maxDate = tahunnya + '-' + bulannya + '-' + harinya;
            $('#endDate').val(maxDate);
            $("#leaveDuration").val(2);
        });
        }
        
}


////////////////////////////////////////////////////////////////////////////////
function setLeaveValidation(){
    console.log("set leave valid pls");
    
    //ini buat gaboleh milih masa lalu
    $(function(){
        //min start date
        let hariIni = new Date();
        let bulannya = hariIni.getMonth() + 1;
        let harinya = hariIni.getDate();
        let tahunnya = hariIni.getFullYear();
        if(bulannya < 10)
            bulannya = '0' + bulannya.toString();
        if(harinya < 10)
            harinya = '0' + harinya.toString();

        let maxDate = tahunnya + '-' + bulannya + '-' + harinya;
        $('#startDate').attr('min', maxDate);
        $('#endDate').attr('min', maxDate);
    });
    
    // gabisa milih weekend dan holiday
    $("#startDate").on('input', function(e){
        let day = new Date(this.value).getUTCDay();
        if([6,0].includes(day) ){
            e.preventDefault();
            this.value = '';
            errorPickDate();
        }
        ambilHoliday(this.value,e);
        
        //min - end date
        let awalnyaLo = new Date(this.value);
        let bulan = awalnyaLo.getMonth() + 1;
        let hari = awalnyaLo.getDate();
        let tahun = awalnyaLo.getFullYear();
        if(bulan < 10)
            bulan = '0' + bulan.toString();
        if(hari < 10)
            hari = '0' + hari.toString();
        let minDate = tahun + '-' + bulan + '-' + hari;
        $('#endDate').attr('min', minDate);
    });
    
    
    // end date input gabisa weekend dan holiday
    $("#endDate").on('input',function (e) {
        if (!$("#startDate").val()) {
            e.preventDefault();
            this.value = '';
            errorPickDate();
        }
        let day = new Date(this.value).getUTCDay();
        if([6,0].includes(day) ){
            e.preventDefault();
            this.value = '';
            errorPickDate();
        }
        ambilHoliday(this.value,e);
    });
    
}

//validasi data diri
function validasiprofil() {
    //hide sesuai profile
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
}


//gabisa holiday ////////////////////////////////////////////////////////////////////
function ambilHoliday(inputan,e) {
    $.ajax({
        url: `/employee/get-holidays`,
        datatype: "json",
        type: 'GET',
        success: (res) => {
            arrayHolidays = res;
            let pickDate = moment(new Date(inputan)).format('YYYY-MM-DD');
            if (arrayHolidays.includes(pickDate)) {
                e.preventDefault();
                $("#startDate").val("");
                $("#endDate").val(null);
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
