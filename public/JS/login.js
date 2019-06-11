var username = "marioboto";
var BASE_URI = "http://147.83.7.205:8080/dsaApp/";

function pasarVariables(pagina, nombres) {
    pagina +="?";
    nomVec = nombres.split(",");
    for (i=0; i<nomVec.length; i++)
        pagina += nomVec[i] + "=" + escape(eval(nomVec[i]))+"&";
    pagina = pagina.substring(0,pagina.length-1);
    location.href=pagina;
}
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
                window.location="http://147.83.7.205:8080/Home.html?username="+ $("#loginUsername").val();
                //location.href="http://147.83.7.205:8080/Home.html";
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
        var myObj2 = {
            username: $("#registerUsername").val(),
            password: $("#registerPassword").val(),
            name: $("#registerName").val(),
            surname: $("#registerSurname").val(),
            mail: $("#registerMail").val(),
            age: $("#registerAge").val()
        };
        e.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'http://147.83.7.205:8080/dsaApp/auth/register',
            data: JSON.stringify(myObj2),
            success: function(data) {
                location.href = "http://147.83.7.205:8080/Home.html";
            },
            error: function (xhr, ajaxOptions, thrownError) {
                if(xhr.status===500) {
                    alert("Password not match");
                }
                if(xhr.status===201) {
                    location.href = "http://147.83.7.205:8080/Home.html";
                }
                else {
                alert("Existant user");
                }},
            contentType: "application/json",
            dataType: 'json'
        });
    });
});