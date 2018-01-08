

$(document).ready(function () {

    $('#submit').click(function (e) {

        e.preventDefault();

        $('input').next().remove();

        $.ajax({
            type: 'POST',
            url: 'organizations/createOrganization',
            data: $('form[name=organization]').serialize(),
            success: function (response) {

                if (response.validated) {
            
                    $('#resultContainer').show();
                    addOrganizationToTable(response);

                } else {
               
                    $.each(response.errorMessages, function (key, value) {
                        $('input[name=' + key + ']').after('<span class="error">' + value + '</span>');
                    });
                }
            }
        })
    });
});

function addOrganizationToTable(response) {

    var obj = JSON.stringify(response.org.name);
    var name = JSON.parse(obj);
    var desc = JSON.stringify(response.org.description);
    var description = JSON.parse(desc);
    var id = JSON.stringify(response.org.organizationId);

    var orgRows = $('#jj');

    var view = '<a href="displayOrganizationDetails?organizationId=';
    var edit = '<a href="displayEditOrganizationForm?organizationId=';
    var deleteO = '<a href="deleteOrganization?organizationId=';
    var end = '</a>';
    
    var row = '<tr>';
    row += '<td>' + view + id + '">' + name + end + '</td>';
    row += '<td>' + description + '</td>';
    row += '<td>' + edit + id + '">' + "Edit" + end + '</td>';
    row += '<td>' + deleteO + id + '">' + "Delete" + end + '</td>';
    row += '</tr>';

    orgRows.append(row);
};




















//var data = {};
//$(document).ready(function() {
//  $('input[type="submit"]').on('click', function() {
//      resetErrors();
//      var url = '/createSuper';
//      $.each($('form input, form select'), function(i, v) {
//          if (v.type !== 'submit') {
//              data[v.name] = v.value;
//          }
//      }); //end each
//      $.ajax({
//          dataType: 'json',
//          type: 'POST',
//          url: url,
//          data: data,
//          success: function(resp) {
//              if (resp === true) {
//                  	//successful validation
//                      $('form').submit();
//                  	return false;
//              } else {
//                  $.each(resp, function(i, v) {
//	        console.log(i + " => " + v); // view in console for error messages
//                      var msg = '<label class="error" for="'+i+'">'+v+'</label>';
//                      $('input[name="' + i + '"], select[name="' + i + '"]').addClass('inputTxtError').after(msg);
//                  });
//                  var keys = Object.keys(resp);
//                  $('input[name="'+keys[0]+'"]').focus();
//              }
//              return false;
//          },
//          error: function() {
//              console.log('there was a problem checking the fields');
//          }
//      });
//      return false;
//  });
//});
//function resetErrors() {
//    $('form input, form select').removeClass('inputTxtError');
//    $('label.error').remove();
//}


//$(document).ready(function () {
//   
//     $.ajax({
//        type: 'GET',
//        url: 'SuperheroSightings/displaySuperheroPage',
//        success: function(superArray) {
//            // get a reference to the 'allContacts' div
//            var superDiv = $('#contentRows');
//
//            // go through each of the returned contacts and append the info to the
//            //contactsDiv
//            $.each(superArray, function(index, superList) {
//                var superInfo = '<p>';
//                superInfo += 'Name: ' + superList.name + '<br>';
//                superInfo += 'Description: ' + superList.name + '<br>';
//                superInfo += '<hr>';
//
//                superDiv.append(superInfo);
//            });
//        },
//        error: function() {
//            alert('FAILURE!');
//        }
//    });
//    });
////
//  
//
////        $.ajax({
////            type: 'GET',
////            url: 'SuperheroSightings/createSuper',
////            date: JSON.stringify({
////                name: $('#validateName').val(),
////                description: $('#valdiateDescription').val()
////            }),
////            headers: {
////                'Accept': 'application/json',
////                'Content-Type': 'application/json'
////            },
////            'dataType': 'json',
////            success: function (data) {
////                fillSuperTable(data);
////            },
////            error: function (data) {
////                fillSuperTable(data);
//////                    $('#errorMessages')
//////                            .append($('<li>')
//////                                    .attr({class: 'list-group-item list-group-item-danger'})
//////                                    .text('Error calling web service.  Please try again later.'));
////            }
////        });
////
////    });
//
//function fillSuperTable(data) {
//    
//    var contentRows=$('#contentRows');
//    
//    
//    $.each(data, function (index, superList) {
//        var name = superList.name;
//        var description = superList.description;
//      
//
//        var row = '<tr>';
//        row += '<td>' + name + '</td>';
//        row += '<td>' + description + '</td>';
//        row += '</tr>';
//        contentRows.append(row);
//    });
//}
//
//
