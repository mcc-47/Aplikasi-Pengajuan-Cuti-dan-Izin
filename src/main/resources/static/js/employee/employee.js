
let validasiProfil = new Object();
let auth = new Object();

$(document).ready(() => {
    console.log("di employee");
    getByUsername();
    
});

//GET EMPLOYEE PROFILE
function getByUsername() {
    auth = {
        username: $("#employeeName").val(),
    };
    console.log(auth);
    
    $.ajax({
        url: `/employee/by-username`,
        type: 'POST',
        data: JSON.stringify(auth),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: (res) => {
            console.log(res);
            validasiProfil = {
                gender: res.gender,
                religion: res.religion,
                totalLeave: res.totalLeave,
            };
            console.log(validasiProfil);
            setFormP(res);
        },
        error: function (err) {
//            errorAlert();
        }
    });
}

function setFormP(emp) {
    $("#employeeName").val(emp.employeeName);
    $("#gender").val(emp.gender);
    $("#religion").val(emp.religion);
    $("#email").val(emp.email);
    $("#jobTitle").val(emp.jobTitle);
    $("#totalLeave").val(emp.totalLeave);
    $("#entryDate").val(moment(emp.entryDate).format('YYYY[-]MM[-]DD'));
    $("#managerId").val(emp.managerId);
}

