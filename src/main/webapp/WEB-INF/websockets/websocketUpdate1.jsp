<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>WebSocket Example</title>
    <script type="text/javascript">
        let socket;

        function connect() {
            socket = new WebSocket("ws://" + window.location.host + "${pageContext.request.contextPath}/websocket/endpoint1");

            socket.onopen = function() {
                console.log("Connected to WebSocket");
                document.getElementById("status").innerText = "Connected";
            };

            socket.onmessage = function(event) {
                console.log("Received from server: " + event.data);
                displayMessage("Server: " + event.data); // Call function to display message in UI
            };

            socket.onclose = function() {
                console.log("WebSocket connection closed");
                document.getElementById("status").innerText = "Disconnected";
            };

            socket.onerror = function(error) {
                console.error("WebSocket error: " + error);
                displayMessage("Error: " + error);
            };
        }

        function sendMessage() {
            const message = document.getElementById("messageInput").value;
            socket.send(message);
            displayMessage("You: " + message); // Display sent message in UI
            document.getElementById("messageInput").value = ""; // Clear input
        }

        function displayMessage(message) {
            const messageContainer = document.getElementById("messages");
            const messageElement = document.createElement("p");
            messageElement.textContent = message;
            messageContainer.appendChild(messageElement);
        }
    </script>
</head>
<body onload="connect()">
    <h2>WebSocket Example</h2>
    <div id="status" style="color: green;">Connecting...</div>
    <input type="text" id="messageInput" placeholder="Type a message" />
    <button onclick="sendMessage()">Send</button>

    <h3>Messages:</h3>
    <div id="messages" style="border: 1px solid #ddd; padding: 10px; height: 200px; overflow-y: auto;">
        <!-- Messages from server will be displayed here -->
    </div>
</body>
</html>
