
$(document).ready(function() {

    $.get("http://147.83.7.205:8080/dsaApp/user/ranking", function (data) {
        var i=1;
        console.log("Data:"+data);
        while (i < 10) {
            var name = data.ranking[i].name;
            var currentTime = data.ranking[i].currentTime;
            var currentEnemiesKilled = data.ranking[i].currentEnemiesKilled;
            var currentLife = data.ranking[i].currentLife;
            console.log("Ranking:", data);
            var insertion = "<tr><td>" + name + "</td>><td>" + currentTime + "</td></tr><tr><td>" + currentEnemiesKilled + "</td><td>" + currentLife + "</td></tr>";
            $("#mytablaranking body").append(insertion);
            i++;
        }
    })
})