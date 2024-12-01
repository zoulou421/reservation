<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Client WebSocket</title>
</head>
<body>
    <h2>Client Chat</h2>
    <div id="messages"></div>
    <input type="text" id="messageInput" placeholder="Type a message">
    <button onclick="sendMessage()">Send</button>

    <script>
        // Establish WebSocket connection
        const socket = new WebSocket("ws://" + window.location.host + "/securityback/chat");

        socket.onopen = function () {
            console.log("Connected to server.");
        };

        socket.onmessage = function (event) {
            // Display server message
            const messageDisplay = document.getElementById("messages");
            const messageElement = document.createElement("p");
            messageElement.textContent = "Server: " + event.data;
            messageDisplay.appendChild(messageElement);
        };

        function sendMessage() {
            const message = document.getElementById("messageInput").value;
            socket.send(message);

            // Display client message
            const messageDisplay = document.getElementById("messages");
            const messageElement = document.createElement("p");
            messageElement.textContent = "You: " + message;
            messageDisplay.appendChild(messageElement);

            document.getElementById("messageInput").value = "";
        }
    </script>
</body>
</html>
