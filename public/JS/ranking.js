
$(document).ready(function() {

    $.get("http://147.83.7.205:8080/dsaApp/user/ranking", function (data) {
        var i = 1;
        console.log("Data:" + data);
        while (i < 10) {
            var name = data[i].name;
            console.log("1:" + name);
            var recordTime = data[i].recordTime;
            console.log("2:" + recordTime);
            var currentEnemiesKilled = data[i].currentEnemiesKilled;
            console.log("3:" + currentEnemiesKilled);
            var currentLife = data[i].currentLife;
            console.log("Ranking:", currentLife);
            var insertion = "<tr><td>" + name + "</td><td>" + recordTime + "</td><td>" + currentEnemiesKilled + "</td><td>" + currentLife + "</td></tr>";
           console.log("Insertion:"+insertion);
            $("#rankingid tbody").append(insertion);
            i++;
        }
    }, "json");
});
