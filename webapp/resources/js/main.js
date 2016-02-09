var stompClient;

function connect() {
    var socket = new SockJS("/auth");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        //setConnected(true);
        console.log('----Connected: ' + frame);
        stompClient.subscribe('/user', function(calResult){
            //showResult(JSON.parse(calResult.body).result);
            console.log(calResult);
        }, function(res){
            console.log('-eee--Connected: ' + res);
        });

        stompClient.send("/app/auth", {}, JSON.stringify({ 'num1': 1, 'num2': 2 }));
    });
}
function disconnect() {
    stompClient.disconnect();
    //setConnected(false);
    console.log("Disconnected");
}
function auth() {
    //var num1 = document.getElementById('num1').value;
    //var num2 = document.getElementById('num2').value;
    connect();
    //stompClient.send("/app/auth", {}, JSON.stringify({ 'num1': 1, 'num2': 2 }));
    /*
    var socket = new WebSocket("ws://localhost:8080/auth");
    socket.onopen = function() {
        alert("Connection ready.");
    };

    socket.onclose = function(event) {
        if (event.wasClean) {
            alert('Connection clean close');
        } else {
            alert('Connection close'); // например, "убит" процесс сервера
        }
        alert('Code: ' + event.code + ' : ' + event.reason);
    };

    socket.onmessage = function(event) {
        alert("Receive " + event.data);
    };

    socket.onerror = function(error) {
        alert("Error " + error.message);
    };
    socket.send("Hello");*/
}

var socket;
function authSocket(){
    socket = new SockJS('/auth');

    socket.onopen = function () {
        // Socket open.. start the game loop.
        console.log('Info: WebSocket connection opened.');
        console.log('Info: Press an arrow key to begin.');
        socket.send("hi");
    };

    socket.onclose = function () {
        console.log('Info: WebSocket closed.');
    };

    socket.onmessage = function (message) {
        console.log(message);
    };
}
$(document).ready(function(){

    $("#auth-btn").on("click", auth);
});