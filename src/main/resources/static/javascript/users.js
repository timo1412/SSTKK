function editUser(userId, login, email, password) {
    console.log(userId);
    console.log(login);
    console.log( email);
    console.log( password);

    $(document).ready(function() {
        $('[data-toggle="modal"]').click(function() {
            var target = $(this).data('target');
            console.log("zobrazenie")
            $(target).modal('show');
        });

        $('#editModal').on('show.bs.modal', function (event) {
            var modal = $(this);
            modal.find('#editId').val(userId);
            modal.find('#editLogin').val(login);
            modal.find('#editEmail').val(email);
            modal.find('#editPassword').val(password);
        });
    });
}


function saveChanges() {
    var id = $('#editId').val();
    var login = $('#editLogin').val();
    var email = $('#editEmail').val();
    var password = $('#editPassword').val();

    if (login === "" || email === "" || password === ""){
        $('#editModal').modal('hide')
        alert("Niektore udaje zostali sprazdne je potrebne aby boli vsetky udaje vyplnene");
        window.location.href = "/error_page";
    }
    console.log(id);
    console.log(login);
    console.log(email);
    console.log(password);

    var userData = {
        id: id,
        login: login,
        email: email,
        password: password
    };

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: '/user/updateUser',
        data: JSON.stringify(userData),
        success: function(response) {
            console.log('Úspech', response);
            $('#editModal').modal('hide');
            window.location.reload();
        },
        error: function(error) {
            console.error('Chyba', error);
        }
    });
    $('#editModal').modal('hide');
}

function deleteUser(button) {
    var  userId = $(button).data('id');
    $.ajax({
        type: 'POST',
        url: '/user/deleteUser/' + userId,
        success: function(response) {
            console.log('Backend odpoveď:', response);
            window.location.reload();
        },
        error: function(error) {
            console.error('Chyba pri volaní backendu:', error);
        }
    });
}