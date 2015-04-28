function sivamtime() {
    now=new Date();
    hour=now.getHours();
    min=now.getMinutes();
    sec=now.getSeconds();

    if (min<=9) { min="0"+min; }
    if (sec<=9) { sec="0"+sec; }

    time = ((hour<=9) ? "0"+hour : hour) + ":" + min + ":" + sec;

    if (document.getElementById) { document.getElementById('clock').innerHTML = time; }
    else if (document.layers) {
        document.layers.clock.document.write(time);
        document.layers.clock.document.close(); }

    setTimeout("sivamtime()", 1000);
}

window.onload = sivamtime;