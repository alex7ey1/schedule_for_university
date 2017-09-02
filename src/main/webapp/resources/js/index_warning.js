function warn() {
    var n = document.getElementById("selectGroup").options.selectedIndex;
    var selGR = document.getElementById("selectGroup").options[n].value;

    var m = document.getElementById("selectDay").options.selectedIndex;
    var selD = document.getElementById("selectDay").options[m].value;

    if (selGR ==="Choose group" || selD ==="Choose Day"){
        alert("You Haven`t Chosen Group or Day");
        return false;
    }
    return true;
}

