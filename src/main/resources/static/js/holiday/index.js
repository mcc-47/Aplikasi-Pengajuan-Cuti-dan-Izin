let holiday = new Object();
let table = null;
let id = 0;

$(document).ready(() => {
    getAll();
    $("#formModal").submit((e) => {
        e.preventDefault();
        formValidation(this.id ? update : create);
    });
});


function getAll() {
    table = $('#HolidayTable').DataTable({
        filter: true,
        orderMulti: true,
        ajax: {
            url: "/holiday/get-all",
            datatype: "json",
            dataSrc: ""
        },
        columns: [
            {
                data: "holidayId", name: "Holiday Id", autoWidth: true
            },
            {
                data: "holidayName", name: "Holiday", autoWidth: true
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
                            data-target="#holidayModal" 
                            onclick="getById('${row.holidayId}')"
                        >
                            <i class='fa fa-sm fa-pencil'></i>
                        </button>
                        <button class='btn btn-sm btn-danger' onclick='deleteById(${row.holidayId})'>
                            <i class='fa fa-sm fa-trash'></i>
                        </button>
                    `;
                }
            }
        ]
    });
}

function getById(id) {
    this.id = id;
    $.ajax({
        url: `/holiday/${id}`,
        type: 'GET',
        success: (res) => {
            setForm(res);
        }
    });
}

function create() {
    contact = {
        holidayId: $("#holidayId").val(),
        holidayName: $("#holidayName").val(),
        holidayDate: $("#holidayDate").val()
    };

    $.ajax({
        url: "/holiday",
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(holiday),
        success: (res) => {
            table.ajax.reload();
            successAlert("Date Added");
            $("#holidayModal").modal("hide");
        },
        error: (err) => {
            errorAlert("Date is Failed to Added");
        }
    });
}

function update(id) {
    contact = {
        holidayId: $("#holidayId").val(),
        holidayName: $("#holidayName").val(),
        holidayDate: $("#holidayDate").val()
    };

    $.ajax({
        url: `/holiday/${this.id}`,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(holiday),
        success: (res) => {
            table.ajax.reload();
            successAlert("Date Updated");
            $("#holidayModal").modal("hide");
        },
        error: (err) => {
            errorAlert("Date is Failed to Updated");
        }
    });
}

function deleteById(id) {
    questionAlert("Are you sure?", "Do you want to delete this data?", () => {
        $.ajax({
            url: `/holiday/${this.id}`,
            type: 'DELETE',
            success: (res) => {
                successAlert("Date Deleted");
            }
        });
    });
}

function setForm(data) {
    $("#holidayId").val(data.contactId);
    $("#holidayName").val(data.holidayName);
    $("#holidayDate").val(data.holidayDate);
}

//Clear form after edit the data
function clearForm() {
    idHoliday = 0;
    $("#holidayId").val("");
    $("#holidayName").val("");
    $("#holidayDate").val("");
}

$(document).ready(function () {
    $("#loginForm").click(function () { // hides all element H1
        var username = $("#username").val();
        var password = $("#password").val();

        if (username == '' || password == '') {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Something went wrong!',
                footer: '<a href>Why do I have this issue?</a>'
            })
        } else {
            Swal.fire({
                icon: 'success',
                title: 'Okay',
                text: 'Login Success',
                footer: '<a href>Why do I have this issue?</a>'
            })
        }
    });
});

$(document).ready(function () {
    $("#logoutButton").on("click", function () {
        Swal.fire({
            title: 'Are you sure to logout?',
            text: "You have to log in again!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, I am sure!'
        }).then((result) => {
            if (result.value === true) {
                $('#logoutform').submit();
            }
        });
    });
});
