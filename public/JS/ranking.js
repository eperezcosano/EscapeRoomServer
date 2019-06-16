
$(document).ready(function() {

    $.get("http://147.83.7.205:8080/dsaApp/user/ranking", function (data) {
        var i=1;
        console.log("Data:"+data);
        while (i < 10) {
            var name = data[i].name;
            console.log("1:"+data[i].name);
            var currentTime = data[i].currentTime;
            console.log("2:"+data[i].currentTime);
            var currentEnemiesKilled = data[i].currentEnemiesKilled;
            console.log("3:"+data[i].currentEnemiesKilled);
            var currentLife = data[i].currentLife;
            console.log("Ranking:", data.rankings);
            var insertion = "<tr><td>" + name + "</td>><td>" + currentTime + "</td></tr><tr><td>" + currentEnemiesKilled + "</td><td>" + currentLife + "</td></tr>";
            $("#mytablaranking body").append(insertion);
            i++;
        }
    })
})