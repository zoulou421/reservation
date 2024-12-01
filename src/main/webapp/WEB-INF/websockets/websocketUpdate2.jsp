<!DOCTYPE html>
<html>
<head>
    <title>WebSocket Example</title>
</head>
<body>
    <h2>WebSocket Communication</h2>
    <div id="messages"></div>
    <input type="text" id="messageInput" placeholder="Type a message">
    <button onclick="sendMessage()">Send Message</button>

    <script>
        let socket;

        // Connect to the WebSocket when the page loads
        function connect() {
            socket = new WebSocket("ws://" + window.location.host + "/securityback/websocket/endpoint2");

            // When the connection opens
            socket.onopen = function() {
                console.log("Connected to WebSocket");
            };

            // When a message is received from the server
            socket.onmessage = function(event) {
                console.log("Message from server: " + event.data);
                displayMessage(event.data); // Show the message in the UI
            };

            // When the connection is closed
            socket.onclose = function() {
                console.log("WebSocket connection closed");
            };

            // When there is an error
            socket.onerror = function(error) {
                console.error("WebSocket error: ", error);
            };
        }

        // Send a message to the server
        function sendMessage() {
            const message = document.getElementById("messageInput").value;
            socket.send(message);
            displayMessage("You: " + message); // Show sent message in the UI
            document.getElementById("messageInput").value = ""; // Clear input
        }

        // Display a message in the UI
        function displayMessage(message) {
            const messagesDiv = document.getElementById("messages");
            const messageElement = document.createElement("p");
            messageElement.textContent = message;
            messagesDiv.appendChild(messageElement);
        }

        // Start the connection
        connect();
    </script>
</body>
</html>
