
$(document).ready(function() {

    $.get("http://147.83.7.205:8080/dsaApp/user/ranking", function (data) {
        var i=0;
        console.log("Data:"+data);
        while (i < data.lista.length) {
            var name = data.name;
            var currentTime = data.currentTime;
            var currentEnemiesKilled = data.currentEnemiesKilled;
            var currentLife = data.currentLife;
            console.log("Ranking:", data);
            var insertion = "<tr><td>" + name + "</td>><td>" + currentTime + "</td></tr><tr><td>" + currentEnemiesKilled + "</td><td>" + currentLife + "</td></tr>";
            $("#mytablaranking body").append(insertion);
            i++;
        }
    })
})