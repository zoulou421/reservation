<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Client Chat</title>
    <script>
        let socket;

        function connect() {
            socket = new WebSocket("ws://localhost:8080/securityback/chatAuthSession");

            socket.onopen = function() {
                document.getElementById("messages").innerHTML += "<p>Connected to server</p>";
            };

            socket.onmessage = function(event) {
                document.getElementById("messages").innerHTML += "<p>" + event.data + "</p>";
            };

            socket.onclose = function() {
                document.getElementById("messages").innerHTML += "<p>Disconnected</p>";
            };

            socket.onerror = function(error) {
                console.error("WebSocket error:", error);
            };
        }

        function sendMessage() {
            const message = document.getElementById("messageInput").value;
            if (socket && socket.readyState === WebSocket.OPEN) {
                socket.send(message);
                document.getElementById("messages").innerHTML += "<p>You: " + message + "</p>";
            }
        }

        window.onload = connect;
    </script>
</head>
<body>
    <h1>Client Chat Interface</h1>
    <div id="messages"></div>
    <input type="text" id="messageInput" placeholder="Type a message...">
    <button onclick="sendMessage()">Send</button>
</body>
</html>
