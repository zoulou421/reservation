<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Server Response UI</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 20px;
        }
        #chatBox {
            border: 1px solid #ddd;
            width: 60%;
            height: 400px;
            overflow-y: scroll;
            padding: 10px;
            background: #f9f9f9;
        }
        .message {
            margin: 5px 0;
        }
        .server {
            color: blue;
        }
        .client {
            color: green;
            text-align: right;
        }
        #inputSection {
            margin-top: 15px;
            display: flex;
            gap: 10px;
        }
        #messageInput {
            width: 50%;
            padding: 8px;
        }
    </style>
</head>
<body>
    <h2>Interactive Server Response UI</h2>
    <div id="chatBox"></div>

    <div id="inputSection">
        <input type="text" id="messageInput" placeholder="Type your message here">
        <button onclick="sendMessage()">Send</button>
    </div>

    <script>
        let socket;

        // Establish WebSocket connection
        function connect() {
            socket = new WebSocket("ws://" + window.location.host + "/securityback/websocket/endpoint3");

            socket.onopen = function() {
                displayMessage("Connected to the server.", "server");
            };

            socket.onmessage = function(event) {
                displayMessage(event.data, "server");
            };

            socket.onclose = function() {
                displayMessage("Disconnected from the server.", "server");
            };

            socket.onerror = function(error) {
                console.error("WebSocket error:", error);
                displayMessage("Error connecting to the server.", "server");
            };
        }

        // Send a message to the server
        function sendMessage() {
            const message = document.getElementById("messageInput").value.trim();
            if (message) {
                displayMessage(message, "client");
                socket.send(message);
                document.getElementById("messageInput").value = "";
            }
        }

        // Display a message in the chat box
        function displayMessage(message, sender) {
            const chatBox = document.getElementById("chatBox");
            const messageElement = document.createElement("p");
            messageElement.classList.add("message", sender);
            messageElement.textContent = (sender === "server" ? "Server: " : "You: ") + message;
            chatBox.appendChild(messageElement);
            chatBox.scrollTop = chatBox.scrollHeight; // Auto-scroll to latest message
        }

        // Initialize WebSocket connection
        connect();
    </script>
</body>
</html>
