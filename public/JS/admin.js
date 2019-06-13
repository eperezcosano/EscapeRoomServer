
function ejecutar(id)
{ console.log("id",id);
    user = $("#userIdholder").val();
    $.ajax({
        type: 'DELETE',
        url: '/dsaApp/admin/admin/deleteUser/'+ user,
        success: function(data) {
            alert("PERFECT");
        },
        error: function (xhr, ajaxOptions, thrownError) {
            if(xhr.status===500){
                alert("Password not match");
            }
            else{
                alert("User not found");
            }
        },
        contentType: "application/json"
    });
}

function ejecutarObject(id)
{ console.log("id",id);
    object = $("#objectIdholder").val();
    $.ajax({
        type: 'DELETE',
        url: '/dsaApp/admin/admin/deleteObject/'+ object,
        success: function(data) {
            alert("PERFECT");
        },
        error: function (xhr, ajaxOptions, thrownError) {
            if(xhr.status===500){
                alert("Password not match");
            }
            else{
                alert("User not found");
            }
        },
        contentType: "application/json"
    });
}

$(document).ready(function() {
    $(".navbar a, footer a[href='#myPage']").on('click', function (event) {
        // Make sure this.hash has a value before overriding default behavior
        if (this.hash !== "") {
            // Prevent default anchor click behavior
            event.preventDefault();

            // Store hash
            var hash = this.hash;

            // Using jQuery's animate() method to add smooth page scroll
            // The optional number (900) specifies the number of milliseconds it takes to scroll to the specified area
            $('html, body').animate({
                scrollTop: $(hash).offset().top
            }, 900, function () {

                // Add hash (#) to URL when done scrolling (default click behavior)
                window.location.hash = hash;
            });
        } // End if
    });
    $(window).scroll(function () {
        $(".slideanim").each(function () {
            var pos = $(this).offset().top;

            var winTop = $(window).scrollTop();
            if (pos < winTop + 600) {
                $(this).addClass("slide");
            }
        });
    });
}
