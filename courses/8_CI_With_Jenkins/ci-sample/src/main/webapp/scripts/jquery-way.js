$(document).ready(function(){
    var userInput = $('#userInput');
    var display = $('#display');

    userInput.keyup(function(){
        display.text(userInput.val());
    });

    $.ajax({
        url: '/api/pets',
        success: function(pets){
            for (var i in pets) {
                $('ul#pets').append('<li>' + pets[i].name + '</li>');
            }
        }
    });
});