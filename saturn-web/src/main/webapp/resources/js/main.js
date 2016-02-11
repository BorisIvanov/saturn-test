var stompClient;

var requestSuccess = {
    type: "LOGIN_CUSTOMER",
    sequence_id: "09caaa73-b2b1-187e-2b24-683550a49b23",
    data: {
        email:"AAA",
        password:"AAA"
    }
};

function connect() {
    var socket = new SockJS("/auth");
    stompClient = Stomp.over(socket);
    stompClient.connect('guest', 'guest'/*{}*/, function(frame) {
        console.log('----Connected: ' + frame);

        var suffix = frame.headers['queue-suffix'];
        console.log(suffix);
        stompClient.subscribe('/user', function(calResult){
            //showResult(JSON.parse(calResult.body).result);
            console.log(calResult);
        }, function(res){
            console.log('-eee--Connected: ' + res);
        });

        stompClient.send("/app/auth", {}, JSON.stringify(requestSuccess));
    });
}
function disconnect() {
    stompClient.disconnect();
    console.log("Disconnected");
}
function auth() {
    //connect();
    ws();
}

function ws(){
    var connection = new WebSocket('ws://localhost:8080/auth');
    connection.onopen = function(){
        /*Send a small message to the console once the connection is established */
        console.log('Connection open!');
        connection.send(JSON.stringify(requestSuccess));

        /*
        * http://www.byteslounge.com/tutorials/java-ee-html5-websockets-with-multiple-clients-example
        * */
    };

    connection.onmessage = function(event) {
        console.log("Receive data " + event.data);
    };
}
$(document).ready(function(){

    $("#auth-btn").on("click", auth);
});