/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $('#timepicker').timepicker({
        'disableTextInput': true,
        'timeFormat': 'H:i:s',
        'step': 1,
        'scrollDefault': 'now'
    });
    $('#datepicker').glDatePicker({
        onClick: function (target, cell, date, data) {
            target.val(date.getFullYear() + '-' + (date.getMonth() < 9 ? "0" : "") +
                    (date.getMonth() + 1) + '-' + (date.getDate() < 10 ? "0" : "") +
                    date.getDate());

            if (data != null) {
                alert(data.message + '\n' + date);
            }
        }
    });

});

$(document).ready(function () {
    $('#submit').click(function (e) {

        e.preventDefault();
        $('input').next().remove();

        $.ajax({
            type: 'POST',
            url: 'sightings/createSighting',
            data: $('form[name=sighting]').serialize(),
            success: function (response) {

                if (response.validated) {

                    $('#resultContainer').show();
                    addSightingToTable(response);

                } else {                
                    $.each(response.errorMessages, function (key, value) {
                        $('input[name=' + key + ']').after('<span class="error">' + value + '</span>');
                    });
                }
            }
        });
    });
});

function addSightingToTable(response) {

    var obj = JSON.stringify(response.sl.name);
    var name = JSON.parse(obj);
    var desc = JSON.stringify(response.sl.description);
    var description = JSON.parse(desc);
    var id = JSON.stringify(response.sl.sightingId);

    var sightingRows = $('#newSights');

    var view = '<a href="displaySightingDetails?sightingId=';
    var edit = '<a href="displayEditSightingForm?sightingId=';
    var deleteS = '<a href="deleteSighting?sightingId=';

    var end = '</a>';
    var row = '<tr>';
    row += '<td>' + view + id + '">' + name + end + '</td>';
    row += '<td>' + description + '</td>';
    row += '<td>' + edit + id + '">' + "Edit" + end + '</td>';
    row += '<td>' + deleteS + id + '">' + "Delete" + end + '</td>';
    row += '</tr>';

    sightingRows.append(row);
}
;



