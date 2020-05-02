$('document').ready(function() {

    $('.table .btn-warning').on('click',function(event){

        event.preventDefault();

        var href = $(this).attr('href');

        $.get(href, function (user, status) {

            $('#IdEdit').val(user.id);
            $('#nameEdit').val(user.name);
            $('#loginEdit').val(user.login);
            $('#passwordEdit').val(user.password);
            $('#roleEdit').val(user.role);

        });

        $('#editModal').modal();
    });

    $('.table #deleteButton').on('click',function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteModal #delRef').attr('href', href);
        $('#deleteModal').modal();
    });


});