var contWoodSword =0;
var contIronSword =0;
var contGoldSword =0;
var contWoodShield =0;
var contIronShield =0;
var contGoldShield =0;
var contclueyellow=0;
var contclueblue=0;
var contcluered=0;
var contkeyyellow=0;
var contkeyblue=0;
var contkeyred=0;
var username = null;

function volver(id)
{ console.log("id",id);
    window.location="http://147.83.7.205:8080/Home.html?username="+ username;
}
function titulo(){
    var container = document.getElementById("titul");
    container.innerHTML='<h1>EscapeRoom</h1>'+'<p>Escapa si puedes</p>' + username ;
}
$(document).ready(function() {
    // Add smooth scrolling to all links in navbar + footer link
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
    titulo();
    console.log("EEEEEEEE");
    var i=0;
    $.get("http://147.83.7.205:8080/dsaApp/user/inventory/"+username, function (data) {
            console.log("Data:",data);
            while(i<data.lista.length) {
                console.log("AA:",data.lista[i]);
                if (data.lista[i].nombre == "woodSword") {
                    contWoodSword = 1;
                }
                if (data.lista[i].nombre == "goldSword") {
                    contGoldSword = 1;
                }
                if (data.lista[i].nombre == "ironSword") {
                    contIronSword = 1;
                }
                if (data.lista[i].nombre == "woodShield") {
                    contWoodShield = 1;
                }
                if (data.lista[i].nombre == "ironShield") {
                    contIronShield = 1;
                }
                if (data.lista[i].nombre == "goldShield") {
                    contGoldShield = 1;
                }
                if (data.lista[i].nombre == "llaveB") {
                    contkeyblue=data.lista[i].amount;
                }
                if (data.lista[i].nombre == "llaveR") {
                    contkeyred=data.lista[i].amount;
                }
                if (data.lista[i].nombre == "llaveY") {
                    contkeyyellow=data.lista[i].amount;
                }
                if (data.lista[i].nombre == "pistaB") {
                    contclueblue=data.lista[i].amount;
                }
                if (data.lista[i].nombre == "pistaR") {
                    contcluered=data.lista[i].amount;
                }
                if (data.lista[i].nombre == "pistaY") {
                    contclueyellow=data.lista[i].amount;
                }
                i++;
            }
            console.log("yellow:",contkeyyellow);
        if (contWoodSword == 0) {
            console.log("Hola",1);
            document.getElementById('woodSword').src = "https://pngimage.net/wp-content/uploads/2018/05/candados-png-6.png";
            var conte = document.getElementById('contenedor_woodSword');
            conte.style.display = "none";
        } else {
            console.log("Hola",2);
            document.getElementById('woodSword').src = "https://www.sccpre.cat/mypng/detail/70-705224_espada-de-madera-cross.png";
            var conte = document.getElementById('contenedor_woodSword');
            conte.style.display = "block";
            contWoodSword=0;
        }
        if (contIronSword == 0) {
            console.log("Hola",3);
            document.getElementById('ironSword').src = "https://pngimage.net/wp-content/uploads/2018/05/candados-png-6.png";
            var conte = document.getElementById('contenedor_ironSword');
            conte.style.display = "none";
        } else {
            console.log("Hola",4);
            document.getElementById('ironSword').src = "https://i.pinimg.com/originals/55/40/4a/55404a52fb3359bdeaff4394d7cb81d0.png";
            var conte = document.getElementById('contenedor_ironSword');
            conte.style.display = "block";
            contIronSword=0;
        }
        if (contGoldSword == 0) {
            console.log("Hola",5);
            document.getElementById('goldSword').src = "https://pngimage.net/wp-content/uploads/2018/05/candados-png-6.png";
            var conte = document.getElementById('contenedor_goldSword');
            conte.style.display = "none";
        }
        else{
            console.log("Hola",6);
            document.getElementById('goldSword').src ="https://vignette.wikia.nocookie.net/horadeaventura/images/7/7f/Espadador.png/revision/latest?cb=20121124030750&path-prefix=es";
            var conte = document.getElementById('contenedor_goldSword');
            conte.style.display = "block";
            contGoldSword=0;
        }
        if (contWoodShield == 0) {
            console.log("Hola",20);
            document.getElementById('woodShield').src = "https://pngimage.net/wp-content/uploads/2018/05/candados-png-6.png";
            var conte = document.getElementById('contenedor_woodShield');
            conte.style.display = "none";
        } else {
            console.log("Hola",21);
            document.getElementById('woodShield').src = "https://previews.123rf.com/images/andreykuzmin/andreykuzmin1204/andreykuzmin120400030/13103962-lat%C3%B3n-envejecido-o-un-escudo-de-metal-de-bronce-aislado-en-blanco.jpg";
            var conte = document.getElementById('contenedor_woodShield');
            conte.style.display = "block";
            contWoodSword=0;
        }
        if (contIronShield == 0) {
            console.log("Hola",3);
            document.getElementById('ironShield').src = "https://pngimage.net/wp-content/uploads/2018/05/candados-png-6.png";
            var conte = document.getElementById('contenedor_ironShield');
            conte.style.display = "none";
        } else {
            console.log("Hola",4);
            document.getElementById('ironShield').src = "https://i0.pngocean.com/files/201/17/292/metal-shield-stock-photography-stock-illustration-icon-silver-shield.jpg";
            var conte = document.getElementById('contenedor_ironShield');
            conte.style.display = "block";
            contIronSword=0;
        }
        if (contGoldShield == 0) {
            console.log("Hola",5);
            document.getElementById('goldShield').src = "https://pngimage.net/wp-content/uploads/2018/05/candados-png-6.png";
            var conte = document.getElementById('contenedor_goldShield');
            conte.style.display = "none";
        }
        else{
            console.log("Hola",6);
            document.getElementById('goldShield').src ="https://st2.depositphotos.com/1011833/5645/i/950/depositphotos_56451727-stock-photo-gold-or-bronze-metal-medieval.jpg";
            var conte = document.getElementById('contenedor_goldShield');
            conte.style.display = "block";
            contGoldSword=0;
        }
        if(contclueyellow==0) {
            console.log("Hola",7);
            document.getElementById('pistaY').src = "https://pngimage.net/wp-content/uploads/2018/05/candados-png-6.png";
            var conte = document.getElementById('contenedor_yellow');
            conte.style.display = "none";
            var conta = document.getElementById('contiyellow');
            conta.style.display = "none";
        }
        else{
            console.log("Hola",8);
            document.getElementById('pistaY').src ="https://i.pinimg.com/originals/d3/c8/60/d3c860c48ad9f9bb48346528f74d3f48.png";
            var conte = document.getElementById('contenedor_yellow');
            conte.style.display = "block";
            document.getElementById('contiyellow').innerHTML=contclueyellow.toString();
            var conta = document.getElementById('contiyellow');
            conta.style.display = "block";
            contclueyellow=0;
        }
        if(contclueblue==0) {
            console.log("Hola",9);
            document.getElementById('pistaB').src ="https://pngimage.net/wp-content/uploads/2018/05/candados-png-6.png";
            var conte = document.getElementById('contenedor_blue');
            conte.style.display = "none";
            var conta = document.getElementById('contiblue');
            conta.style.display = "none";
        }
        else{
            console.log("Hola",10);
            console.log("Contador:",contclueblue);
            document.getElementById('pistaB').src ="https://4.bp.blogspot.com/-w6rNRWPnMFU/UmFae6uZLyI/AAAAAAAABBM/-R1T3m-Pm2I/s640/preguntas.png";
            var conte = document.getElementById('contenedor_blue');
            conte.style.display = "block";
            document.getElementById('contiblue').innerHTML=contclueblue.toString();
            var conta = document.getElementById('contiblue');
            conta.style.display = "block";
            contclueblue=0;
        }
        if(contcluered==0) {
            console.log("Hola",11);
            document.getElementById('pistaR').src ="https://pngimage.net/wp-content/uploads/2018/05/candados-png-6.png";
            var conte = document.getElementById('contenedor_red');
            conte.style.display = "none";
            var conta = document.getElementById('contired');
            conta.style.display = "none";
        }
        else{
            console.log("Hola",12);
            document.getElementById('pistaR').src ="https://pngimage.net/wp-content/uploads/2018/06/interrogantes-png-6.png";
            var conte = document.getElementById('contenedor_red');
            conte.style.display = "block";
            document.getElementById('contired').innerHTML=contcluered.toString();
            var conta = document.getElementById('contired');
            conta.style.display = "block";
            contcluered=0;
        }
        if(contkeyyellow==0) {
            console.log("Hola",13);
            document.getElementById('llaveY').src ="https://pngimage.net/wp-content/uploads/2018/05/candados-png-6.png";
            document.getElementById('llaveY').width ="250";
            document.getElementById('llaveY').height ="165";
            var conte = document.getElementById('contenedorkey_yellow');
            conte.style.display = "none";
            var conta = document.getElementById('contadorkey_yellow');
            conta.style.display = "none";
        }
        else{
            console.log("Hola",14);
            document.getElementById('llaveY').src ="https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/59627072_2381151755270864_7056317532851929088_n.jpg?_nc_cat=111&_nc_ht=scontent-mad1-1.xx&oh=2b3a0a634a078c0fdd3e487fccfac39d&oe=5D5EBFF3";
            var conte = document.getElementById('contenedorkey_yellow');
            conte.style.display = "block";
            document.getElementById('contadorkey_yellow').innerHTML=contkeyyellow.toString();
            var conta = document.getElementById('contadorkey_yellow');
            conta.style.display = "block";
            contkeyyellow=0;
        }
        if(contkeyblue==0) {
            console.log("Hola",15);
            document.getElementById('llaveB').src ="https://pngimage.net/wp-content/uploads/2018/05/candados-png-6.png";
            document.getElementById('llaveB').width ="250";
            document.getElementById('llaveB').height ="165";
            var conte = document.getElementById('contenedorkey_blue');
            conte.style.display = "none";
            var conta = document.getElementById('contadorkey_blue');
            conta.style.display = "none";
        }
        else{
            console.log("Hola",16);
            document.getElementById('llaveB').src ="https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/59904610_2381158021936904_3630824489995993088_n.jpg?_nc_cat=105&_nc_ht=scontent-mad1-1.xx&oh=b09ac4773b91ee855ae246c60469be4e&oe=5D2FAC40";
            var conte = document.getElementById('contenedorkey_blue');
            conte.style.display = "block";
            document.getElementById('contadorkey_blue').innerHTML=contkeyblue.toString();
            var conta =document.getElementById('contadorkey_blue');
            conta.style.display = "block";
            contkeyblue=0;
        }
        if(contkeyred==0) {
            console.log("Hola",17);
            document.getElementById('llaveR').src ="https://pngimage.net/wp-content/uploads/2018/05/candados-png-6.png";
            document.getElementById('llaveR').width ="250";
            document.getElementById('llaveR').height ="165";
            var conte = document.getElementById('contenedorkey_red');
            conte.style.display = "none";
            var conta = document.getElementById('contadorkey_red');
            conta.style.display = "none";
        }
        else{
            console.log("Hola",18);
            document.getElementById('llaveR').src ="https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/60104433_2381144115271628_7427805772727189504_n.jpg?_nc_cat=103&_nc_ht=scontent-mad1-1.xx&oh=ea2c6e453da7810df10e08054d6b06f3&oe=5D68709D";
            var conte = document.getElementById('contenedorkey_red');
            conte.style.display = "block";
            document.getElementById('contadorkey_red').innerHTML=contkeyred.toString();
            var conta = document.getElementById('contadorkey_red');
            conta.style.display = "block";
            contkeyred=0;
        }
    }, "json");

});
