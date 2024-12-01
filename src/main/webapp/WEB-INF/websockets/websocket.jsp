<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>WebSocket Example</title>
    <script type="text/javascript">
        let socket;

        function connect() {
            socket = new WebSocket("ws://" + window.location.host + "${pageContext.request.contextPath}/websocket/endpoint");

            socket.onopen = function() {
                console.log("Connected to WebSocket");
            };

            socket.onmessage = function(event) {
                console.log("Received from server: " + event.data);
            };

            socket.onclose = function() {
                console.log("WebSocket connection closed");
            };

            socket.onerror = function(error) {
                console.error("WebSocket error: " + error);
            };
        }

        function sendMessage() {
            const message = document.getElementById("messageInput").value;
            socket.send(message);
            console.log("Sent to server: " + message);
        }
    </script>
</head>
<body onload="connect()">
    <h2>WebSocket Example</h2>
    <input type="text" id="messageInput" placeholder="Type a message" />
    <button onclick="sendMessage()">Send</button>
</body>
</html>
