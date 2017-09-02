/**
 * Created by Alex on 07.05.2017.
 */

function autocompleteBegEndTime() {
    var n = document.getElementById("lesson-number").options.selectedIndex;
    var autocom = document.getElementById("lesson-number").options[n].value;

    switch (autocom){

        case '1' :
            document.getElementById('begin-time').options[0].value="08:30";
            document.getElementById('begin-time').options[0].text="08:30";
            document.getElementById('end-time').options[0].value="10:00";
            document.getElementById('end-time').options[0].text="10:00";
            break;
        case '2' :
            document.getElementById('begin-time').options[0].value="10:10";
            document.getElementById('begin-time').options[0].text="10:10";
            document.getElementById('end-time').options[0].value="11:40";
            document.getElementById('end-time').options[0].text="11:40";
            break;
        case '3' :
            document.getElementById('begin-time').options[0].value="11:50";
            document.getElementById('begin-time').options[0].text="11:50";
            document.getElementById('end-time').options[0].value="13:20";
            document.getElementById('end-time').options[0].text="13:20";
            break;
        case '4' :
            document.getElementById('begin-time').options[0].value="13:30";
            document.getElementById('begin-time').options[0].text="13:30";
            document.getElementById('end-time').options[0].value="15:00";
            document.getElementById('end-time').options[0].text="15:00";
            break;
        case '5' :
            document.getElementById('begin-time').options[0].value="15:10";
            document.getElementById('begin-time').options[0].text="15:10";
            document.getElementById('end-time').options[0].value="16:40";
            document.getElementById('end-time').options[0].text="16:40";
            break;
        case '6' :
            document.getElementById('begin-time').options[0].value="16:50";
            document.getElementById('begin-time').options[0].text="16:50";
            document.getElementById('end-time').options[0].value="18:20";
            document.getElementById('end-time').options[0].text="18:20";
            break;
        case '7' :
            document.getElementById('begin-time').options[0].value="18:30";
            document.getElementById('begin-time').options[0].text="18:30";
            document.getElementById('end-time').options[0].value="20:00";
            document.getElementById('end-time').options[0].text="20:00";
            break;
    }
}
