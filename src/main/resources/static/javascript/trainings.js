$(document).ready(function() {
    $('[data-toggle="modal"]').click(function() {
        var target = $(this).data('target');
        $(target).modal('show');
    });
});


$(document).ready(function() {
    $('#potvrditBtn').click(function() {
        var selectedDay = $('#selectDay').val();
        var selectedTime = $('#selectTime').val();
        var text3 = $('#inputText3').val();

        if (selectedDay === "" || selectedTime === "" || text3 === ""){
            $('#myModal').modal('hide');
            alert("Neboli zadane udaje den,cas alebo popis treningu");
            window.location.href = "/error_page";
        }else {
            $.ajax({
                type: 'POST',
                url: '/addTraining',
                contentType: 'application/json',
                data: JSON.stringify({
                    day: selectedDay,
                    time: selectedTime,
                    description: text3
                }),
                success: function(response) {
                    console.log('Backend odpoveď:', response);
                    window.location.reload();
                },
                error: function(error) {
                    console.error('Chyba pri volaní backendu:', error);
                }
            });
            $('#myModal').modal('hide');
        }
    });
});

function deleteTraining(button) {
    var trainingId = $(button).data('id');
    $.ajax({
        type: 'POST',
        url: '/deleteTraining/' + trainingId,
        success: function(response) {
            console.log('Backend odpoveď:', response);
            window.location.reload();
        },
        error: function(error) {
            console.error('Chyba pri volaní backendu:', error);
        }
    });
}