$(document).ready(function() {
    setInterval(function() {
        updateNews();
    }, 5000);

    function updateNews() {
        $.ajax({
            type: 'GET',
            url: '/newsList',
            success: function(data) {
                $('.container-cards').empty().html(data);
            },
            error: function(error) {
                console.log('Chyba pri aktualizácii príspevkov: ', error);
            }
        });
    }
});