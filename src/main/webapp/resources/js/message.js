var text;
function someFunc(){
text = document.getElementById("txt").value;
}
var btn = document.getElementById("btn");
btn.onclick = someFunc;
btn.addEventListener("click", defineText, false);

function defineText(evt) {
    
    var json = JSON.stringify({
        "text": text,
    });
    printText(json);
        sendText(json);
}

function printText(text) {
    var json = JSON.parse(text);
    var content = document.getElementById("output").innerHTML;
    document.getElementById("output").innerHTML = "<div>"+json.text+"</div>" + content;
}