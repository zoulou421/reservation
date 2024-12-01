<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Server Chat Panel</title>
</head>
<body>
    <h1>Server Chat Panel</h1>
    <div id="messages"></div>
    <script>
        let socket;

        function connect() {
            socket = new WebSocket("ws://localhost:8080/securityback/chatAuthSession");

            socket.onopen = function() {
                document.getElementById("messages").innerHTML += "<p>Connected to clients</p>";
            };

            socket.onmessage = function(event) {
                document.getElementById("messages").innerHTML += "<p>Client: " + event.data + "</p>";
            };

            socket.onclose = function() {
                document.getElementById("messages").innerHTML += "<p>Disconnected</p>";
            };

            socket.onerror = function(error) {
                console.error("WebSocket error:", error);
            };
        }

        function sendResponse() {
            const message = document.getElementById("responseInput").value;
            if (socket && socket.readyState === WebSocket.OPEN) {
                socket.send("Server: " + message);
                document.getElementById("messages").innerHTML += "<p>Server: " + message + "</p>";
            }
        }

        window.onload = connect;
    </script>
    <input type="text" id="responseInput" placeholder="Type a response...">
    <button onclick="sendResponse()">Respond</button>
</body>
</html>
