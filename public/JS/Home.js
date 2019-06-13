var BASE_URI = "http://147.83.7.205:8080";
var username = null;
var inventario_nulo=0;

function myfunction(id) {
    var btn = document.getElementById(id);
    btn.innerHTML='<button id=id type="button" class="btn btn-buya" onclick="buy(this.id);myfunction(id)" disabled>BUY</button>';
}
function buy(id){
        var myObj = {
            nombre:id
        };
        $.ajax({
            type: 'POST',
            url: '/dsaApp/user/buy/'+ username,
            data: JSON.stringify(myObj),
            success: function(data) {
                window.location="http://147.83.7.205:8080/Inventory.html?username="+ username;
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
    var paramstr = window.location.search.substr(1);
    var paramarr = paramstr.split ("&");
    var params = {};

    for ( var i = 0; i < paramarr.length; i++) {
        var tmparr = paramarr[i].split("=");
        params[tmparr[0]] = tmparr[1];
    }
    if (params['username']) {
        console.log('El valor del parámetro variable es: '+params['username']);
        username=params['username'];
    } else {
        console.log('No se envió el parámetro variable');
    }
    $("#inventory_button").click(function () {
        if(inventario_nulo==1) {
            alert("You not have any object");
        }else {
            window.location = "http://147.83.7.205:8080/Inventory.html?username=" + params['username'];
        }
    })
    titulo();
    $.get("http://147.83.7.205:8080/dsaApp/user/profile/"+params['username'], function (data) {
        var username = params['username'];
        var password = data.password;
        var name = data.name;
        var surname = data.surname;
        var mail = data.mail;
        var age = data.age;
        var username_text = 'username';
        var password_text = "password";
        var name_text = "name";
        var surname_text = "surname";
        var mail_text ="mail";
        var age_text ="age";
    console.log("Profile:",data);
        var insertion = "<tr><td>" + username_text + "</td><td>" + username + "</td></tr><tr><td>" + password_text + "</td><td>" + password + "</td></tr><tr><td>" + name_text+"</td>><td>" + name + "</td></tr><tr><td>" + surname_text + "</td><td>" + surname + "</td></tr><tr><td>" + mail_text + "</td><td>" + mail + "</td></tr><tr><td>" + age_text + "</td><td>" + age + "</td></tr>";
        $("#mytabla tbody").append(insertion);
}, "json");
    $.get("http://147.83.7.205:8080/dsaApp/user/statistics/"+params['username'], function (data) {
        var currentEnemiesKilled = data.currentEnemiesKilled;
        var currentTime = data.currentTime;
        var playedGames = data.playedGames;
        var currentShield = data.currentShield;
        var currentSword = data.currentSword;
        var partidasjugadas_text = "playedGames";
        var enemigosmatados_text = "currentEnemiesKilled";
        var tiempototal_text = "currentTime";
        var currentShieldtext = "currentShield";
        var currentSwordtext = "currentSword";
        console.log("Profile:",data);
        var insertion = "<tr><td>" + partidasjugadas_text + "</td><td>" + playedGames + "</td></tr><tr><td>" + tiempototal_text + "</td><td>" + currentTime + "</td></tr><tr><td>" + enemigosmatados_text +"</td><td>" + currentEnemiesKilled + "</td></tr><tr><td><tr><td>" + currentShieldtext + "</td><td>" + currentShield + "</td></tr><tr><td>" + currentSwordtext + "</td><td>" + currentSword + "</td></tr>";
        $("#statistics_tabla tbody").append(insertion);
    }, "json");
    $.get("http://147.83.7.205:8080/dsaApp/user/inventory/"+params['username'], function (data) {
        console.log("Data:",data.lista[0].nombre);
        if(data.lista[0]==null)
        {inventario_nulo=1;}
        for (let i = 0; i<data.lista.length; i++)
        {
            if(data.lista[i].nombre=="woodSword"||data.lista[i].nombre=="ironSword"||data.lista[i].nombre=="goldSword"||data.lista[i].nombre=="woodShield"||data.lista[i].nombre=="ironShield"||data.lista[i].nombre=="goldShield") {
                myfunction(data.lista[i].nombre);
            }
        }
    }, "json");
})