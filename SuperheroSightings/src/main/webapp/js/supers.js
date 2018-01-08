/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    $('#submit').click(function (e) {

        e.preventDefault();

        $('input').next().remove();

        $.ajax({
            type: 'POST',
            url: 'supers/createSuper',
            data: $('form[name=supers]').serialize(),
            success: function (response) {

                if (response.validated) {
                
                    $('#resultContainer').show();
                    addSuperToTable(response);

                } else {
                 
                    $.each(response.errorMessages, function (key, value) {
                        $('input[name=' + key + ']').after('<span class="error">' + value + '</span>');
                    });
                }
            }
        })
    });
});

function addSuperToTable(response) {


    var obj = JSON.stringify(response.supers.name);
    var name = JSON.parse(obj);
    var desc = JSON.stringify(response.supers.description);
    var description = JSON.parse(desc);
    var id = JSON.stringify(response.supers.superId);

    var superRows = $('#j');

    var view = '<a href="displaySupersDetails?superId=';
    var edit = '<a href="displayEditSuperForm?superId=';
    var deleteS = '<a href="deleteSuper?superId=';

    var end = '</a>';
    var row = '<tr>';
    row += '<td>' + view + id + '">' + name + end + '</td>';
    row += '<td>' + description + '</td>';
    row += '<td>' + edit + id + '">' + "Edit" + end + '</td>';
    row += '<td>' + deleteS + id + '">' + "Delete" + end + '</td>';
    row += '</tr>';

    superRows.append(row);
};



