let contact = new Object();
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
    table = $('#contactTable').DataTable({
        filter: true,
        orderMulti: true,
        ajax: {
            url: "/contact/get-all",
            datatype: "json",
            dataSrc: ""
        },
        columns: [
            {
                data: "contactId", name: "Contact Id", autoWidth: true
            },
            {
                data: "phone", name: "Phone", autoWidth: true
            },
            {
                data: "linkedin", name: "Linked In", autoWidth: true
            },
            {
                render: (data, type, row, meta) => {
                    return `
                        <button 
                            class='btn btn-sm btn-primary'
                            data-toggle="modal" 
                            data-target="#contactModal"
                            onclick="getById('${row.contactId}')"
                        >
                            <i class='fa fa-sm fa-pencil'></i>
                        </button>
                        <button class='btn btn-sm btn-danger' onclick='deleteById(${row.contactId})'>
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
        url: `/contact/${id}`,
        type: 'GET',
        success: (res) => {
            setForm(res);
        }
    });
}

function create() {
    contact = {
        contactId: $("#contactId").val(),
        phone: $("#phone").val(),
        linkedin: $("#linkedin").val()
    };

    $.ajax({
        url: "/contact",
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(contact),
        success: (res) => {
            table.ajax.reload();
            successAlert("Contact Created");
            $("#contactModal").modal("hide");
        },
        error: (err) => {
            errorAlert("Contact Failed Created");
        }
    });
}

function update(id) {
    contact = {
        contactId: $("#contactId").val(),
        phone: $("#phone").val(),
        linkedin: $("#linkedin").val()
    };

    $.ajax({
        url: `/contact/${this.id}`,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(contact),
        success: (res) => {
            table.ajax.reload();
            successAlert("Contact Updated");
            $("#contactModal").modal("hide");
        },
        error: (err) => {
            errorAlert("Contact Failed Updated");
        }
    });
}

function deleteById(id) {
    questionAlert("Are you sure?", "Do you want to delete this data?", () => {
        $.ajax({
            url: `/contact/${this.id}`,
            type: 'DELETE',
            success: (res) => {
                successAlert("Contact Deleted");
            }
        });
    });
}

function setForm(data) {
    $("#contactId").val(data.contactId);
    $("#phone").val(data.phone);
    $("#linkedin").val(data.linkedin);
}

//Clear form after edit the data
function clearForm() {
    idContact = 0;
    $("#contactId").val("");
    $("#phone").val("");
    $("#linkedin").val("");
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
