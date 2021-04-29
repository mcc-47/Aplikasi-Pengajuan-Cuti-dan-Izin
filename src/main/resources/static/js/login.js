
$(document).ready(() => {
    $("#loginForm").submit(e => {
        e.preventDefault();
//        login();
        validationForm(login);
    });
    
});

//LOGIN BUTTON
function login() {
    auth = {
        username: $("#username").val(),
        password: $("#password").val()
    };
    console.log(auth);
    
    $.ajax({
        url: `/login`,
        type: 'POST',
        data: JSON.stringify(auth),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: (res) => {
            console.log(res);
            if (res===true) {
            loginSuccess();
            console.log("Success");
            window.location.replace("/dashboard");
            } else {
            errorAlert();
            window.location.replace("/login?error");
            }
        },
        error: function (err) {
            errorAlert();
            window.location.replace("/login?error");
        }
    });
}


//LOGIN SUCCESS
function loginSuccess(){
    const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
    })

    Toast.fire({
      icon: 'success',
      title: 'Signed In successfully'
    })
}

// ERROR
function errorAlert(){
    const Toast = Swal.mixin({
                toast: true,
                position: 'top-end',
                showConfirmButton: false,
                timer: 3000,
                timerProgressBar: true,
                didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                }
                })
    Toast.fire({
        icon: 'error',
        title: 'failed'
    })
}

//LOGOUT BUTTON
$(document).ready(function () {
    $("#logoutButton").on("click", function () {
        Swal.fire({
        position : 'top-end',
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText:'Log out'
        }).then((result) => {
            if (result.isConfirmed) {
                const Toast = Swal.mixin({
                toast: true,
                position: 'top-end',
                showConfirmButton: false,
                timer: 3000,
                timerProgressBar: true,
                didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                }
                })

                Toast.fire({
                    icon: 'success',
                    title: 'Signed Out successfully'
                })
                $('#logoutform').submit();
            }
        });
    });
});