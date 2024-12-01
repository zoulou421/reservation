<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Server WebSocket</title>
</head>
<body>
    <h2>Server Chat</h2>
    <div id="messages"></div>
    <input type="text" id="serverMessageInput" placeholder="Type a response">
    <button onclick="sendServerMessage()">Send Response</button>

    <script>
        // Connect to the same WebSocket endpoint as the client
        const socket = new WebSocket("ws://" + window.location.host + "/securityback/chat");

        socket.onopen = function () {
            console.log("Server connected to WebSocket.");
        };

        socket.onmessage = function (event) {
            // Display client message
            const messageDisplay = document.getElementById("messages");
            const messageElement = document.createElement("p");
            messageElement.textContent = "Client: " + event.data;
            messageDisplay.appendChild(messageElement);
        };

        function sendServerMessage() {
            const message = document.getElementById("serverMessageInput").value;
            socket.send(message);

            // Display server message
            const messageDisplay = document.getElementById("messages");
            const messageElement = document.createElement("p");
            messageElement.textContent = "You (Server): " + message;
            messageDisplay.appendChild(messageElement);

            document.getElementById("serverMessageInput").value = "";
        }
    </script>
</body>
</html>
