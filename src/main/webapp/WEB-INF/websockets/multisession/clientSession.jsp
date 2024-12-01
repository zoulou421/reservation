<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>WebSocket Client</title>
    <script type="text/javascript">
        let socket;

        function connectWebSocket() {
            socket = new WebSocket("ws://localhost:8080/securityback/websocketEndpointSession");

            socket.onopen = function () {
                document.getElementById("messages").innerHTML += "<p>Connected to server</p>";
            };

            socket.onmessage = function (event) {
                document.getElementById("messages").innerHTML += "<p>Server says: " + event.data + "</p>";
            };

            socket.onclose = function () {
                document.getElementById("messages").innerHTML += "<p>Disconnected from server</p>";
            };

            socket.onerror = function (error) {
                console.error("WebSocket error: ", error);
            };
        }

        function sendMessage() {
            const message = document.getElementById("messageInput").value;
            if (socket && socket.readyState === WebSocket.OPEN) {
                socket.send(message);
                document.getElementById("messages").innerHTML += "<p>You say: " + message + "</p>";
            }
        }
    </script>
</head>
<body onload="connectWebSocket()">
    <h1>WebSocket Client</h1>
    <div id="messages"></div>
    <input type="text" id="messageInput" placeholder="Type a message...">
    <button onclick="sendMessage()">Send</button>
</body>
</html>
