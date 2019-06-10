var username = "Carlo";
var BASE_URI = "http://147.83.7.205:8080/dsaApp/";

$(document).ready(function(){
    console.log("Va cojones");
    $('#login').on('submit', function(e){
        user = $("#loginUsername").val();
        var myObj = {
            username: $("#loginUsername").val(),
            password: $("#loginPassword").val()
        };
        e.preventDefault();
        console.log("Me cago en dios");
        $.ajax({
            type: 'POST',
            url: 'http://147.83.7.205:8080/dsaApp/auth/login',
            data: JSON.stringify(myObj),
            success: function(data) {
                location.href = "http://http://147.83.7.205:8080/Home.html";
            },
            error: function (xhr, ajaxOptions, thrownError) {
                if(xhr.status===500){
                    alert("Password not match");
                }
                else{
                    alert("User not found");
                }
            },
            contentType: "application/json",
            dataType: 'json'
        });
    });
    $('#register').on('submit', function(e){
        var myObj = {
            username: $("#registerUsername").val(),
            password: $("#registerPassword").val()
        };
        e.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'http://147.83.7.205:8080/dsaApp/auth/register',
            data: JSON.stringify(myObj),
            success: function(data) {
                var username = data.username;
                var password = data.password;
                document.getElementById('registerTextUsername').innerHTML = "Username: "+ username.toString();
                document.getElementById('registerTextPassword').innerHTML = "Password: "+ password.toString();
                var container = document.getElementById('secondRow');
                var registerContainer = document.getElementById('registerResponse');
                container.style.display="block";
                registerContainer.style.display="block";
            },
            error: function (xhr, ajaxOptions, thrownError) {
                alert("Existant user");
            },
            contentType: "application/json",
            dataType: 'json'
        });
    });
});