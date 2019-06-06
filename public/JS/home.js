var BASE_URI = "http://localhost:8080/dsaApp";

function myfunction(id) {
    var btn = document.getElementById(id);
    btn.innerHTML='<button id=id type="button" class="btn btn-buya" onclick="buy(this.id);myfunction(id)" disabled>BUY</button>';
}
function buy(id){
    console.log("ou mama");
    console.log(id,": puta id");
        var myObj = {
            nombre: id,
        };
        console.log(myObj,": Me cago en dios");
        $.ajax({
            type: 'POST',
            url: '/dsaApp/user/buy',
            data: JSON.stringify(myObj),
            success: function(data) {
                location.href = "http://localhost:8080/Inventory.html";
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
}
function titulo(){
    var container = document.getElementById("titulo");
    container.innerHTML='<h1>EscapeRoom</h1>'+'<p>Escapa si puedes Mario</p>';
}

$(document).ready(function(){
    $('[data-toggle="popover"]').popover();
    $('[data-toggle="popover1"]').popover();
    $('[data-toggle="popover2"]').popover();

    // Add smooth scrolling to all links in navbar + footer link
    $(".navbar a, footer a[href='#myPage']").on('click', function(event) {
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
            }, 900, function(){

                // Add hash (#) to URL when done scrolling (default click behavior)
                window.location.hash = hash;
            });
        } // End if
    });
    $(window).scroll(function() {
        $(".slideanim").each(function(){
            var pos = $(this).offset().top;

            var winTop = $(window).scrollTop();
            if (pos < winTop + 600) {
                $(this).addClass("slide");
            }
        });
    });
    $("#inventory_button").click(function () {
        location.href = "http://localhost:8080/Inventory.html";
    })
    titulo();
    $.get("http://localhost:8080/dsaApp/user/profile", function (data) {
        var username = data.username;
        var password = data.password;
        var name = data.name;
        var surname = data.surname;
        var mail = data.mail;
        var age = data.age;
        var username_text = "username";
        var password_text = "password";
        var name_text = "name";
        var surname_text = "surname";
        var mail_text ="mail";
        var age_text ="age";
    console.log("Profile:",data);
        var insertion = "<tr><td>" + username_text + "</td><td>" + username + "</td></tr><tr><td>" + password_text + "</td><td>" + password + "</td></tr><tr><td>" + name_text+"</td>><td>" + name + "</td></tr><tr><td>" + surname_text + "</td><td>" + surname + "</td></tr><tr><td>" + mail_text + "</td><td>" + mail + "</td></tr><tr><td>" + age_text + "</td><td>" + age + "</td></tr>";
        $("#mytabla tbody").append(insertion);
}, "json");
    $.get("http://localhost:8080/dsaApp/user/statistics", function (data) {
        var partidasjugadas = data.partidasjugadas;
        var enemigosmatados = data.enemigosmatados;
        var monedasconseguidas = data.monedasconseguidas;
        var tiempototal = data.minutostotales;
        var partidasjugadas_text = "Partidas";
        var enemigosmatados_text = "Enemigos asesinados";
        var monedasconseguidas_text = "Monedas conseguidas";
        var tiempototal_text = "Tiempo total";
        console.log("Profile:",data);
        var insertion = "<tr><td>" + partidasjugadas_text + "</td><td>" + partidasjugadas + "</td></tr><tr><td>" + tiempototal_text + "</td><td>" + tiempototal + "</td></tr><tr><td>" + enemigosmatados_text +"</td>><td>" + enemigosmatados + "</td></tr><tr><td>" + monedasconseguidas_text + "</td><td>" + monedasconseguidas + "</td></tr>";
        $("#statistics_tabla tbody").append(insertion);
    }, "json");
    $.get("http://localhost:8080/dsaApp/user/inventoryHome", function (data) {
        console.log("Data:",data);
        for (let i = 0; i<data.lista.length; i++)
        {
            myfunction(data.lista[i]);
        }
    }, "json");
})