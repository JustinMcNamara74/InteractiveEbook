function allowDrop(ev)
{
    ev.preventDefault();
}

function drag(ev, number)
{
    ev.dataTransfer.setData("Text", ev.target.id);
    ev.dataTransfer.setData("Number", number);
}

function drop(ev, number)
{
    ev.preventDefault();
    var data = ev.dataTransfer.getData("Text");
    var num = ev.dataTransfer.getData("Number");

    if (number == num) {
        document.getElementById("row" + number).style.setProperty("background", "green");
        document.getElementById("row" + number).style.setProperty("color", "white");
        ev.target.appendChild(document.getElementById(data));
    }
}
