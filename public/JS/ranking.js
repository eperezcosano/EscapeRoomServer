
$(document).ready(function() {

    while(i<data.lista.length) {
        var name = data.name;
        var currentTime = data.currentTime;
        var currentEnemiesKilled = data.currentEnemiesKilled;
        var currentLife = data.currentLife;
        console.log("Ranking:", data);
     var insertion = "<tr><td>" + name + "</td>><td>" + currentTime + "</td></tr><tr><td>" + currentEnemiesKilled + "</td><td>" + currentLife + "</td></tr>";
    $("#mytabla tbody").append(insertion);
}
}