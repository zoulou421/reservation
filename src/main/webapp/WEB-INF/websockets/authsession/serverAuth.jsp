<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Server Chat Panel</title>

    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <style>
        /* Custom styles for chat UI */
        body {
            background-color: #f4f7fc;
            font-family: Arial, sans-serif;
            padding: 20px;
        }

        .chat-container {
            max-width: 800px;
            margin: auto;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        .messages {
            height: 400px;
            overflow-y: auto;
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 15px;
            background-color: #f9f9f9;
            margin-bottom: 20px;
            display: flex;
            flex-direction: column;
        }

        .messages p {
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 10px;
            transition: all 0.3s ease;
        }

        .message-server {
            background-color: #d4edda;
            text-align: left;
        }

        .message-client {
            background-color: #cce5ff;
            text-align: right;
        }

        .input-group {
            display: flex;
            align-items: center;
        }

        #responseInput {
            flex: 1;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 5px;
            outline: none;
            transition: border 0.3s ease;
        }

        #responseInput:focus {
            border-color: #007bff;
        }

        #sendBtn {
            margin-left: 10px;
            padding: 10px 15px;
            font-size: 16px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        #sendBtn:hover {
            background-color: #0056b3;
        }

        /* Connection status */
        .connection-status {
            margin-bottom: 10px;
            padding: 5px;
            background-color: #f1f1f1;
            border-radius: 5px;
            text-align: center;
        }

        .status-online {
            color: green;
        }

        .status-offline {
            color: red;
        }

        /* Responsive design for mobile */
        @media (max-width: 768px) {
            .chat-container {
                padding: 15px;
                margin-top: 20px;
            }

            .messages {
                height: 300px;
            }

            #responseInput {
                font-size: 14px;
            }

            #sendBtn {
                padding: 8px 12px;
            }
        }
    </style>
</head>
<body>

    <div class="chat-container">
        <h1 class="text-center mb-4">Server Chat Panel <span>💬</span></h1>

        <!-- Connection status -->
        <div id="connectionStatus" class="connection-status">
            <span class="status-offline">Disconnected <span>🔴</span></span>
        </div>

        <!-- Messages display area -->
        <div class="messages" id="messages"></div>

        <!-- Input and response button -->
        <div class="input-group">
            <input type="text" id="responseInput" class="form-control" placeholder="Type a response... 😊">
            <button id="sendBtn" onclick="sendResponse()">Respond <span>👍</span></button>
        </div>
    </div>

    <!-- Bootstrap and jQuery Scripts -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <script>
        let socket;

        function connect() {
            socket = new WebSocket("ws://localhost:8080/securityback/chatAuthSession");

            socket.onopen = function() {
                document.getElementById("connectionStatus").innerHTML = "<span class='status-online'>Connected <span>🟢</span></span>";
            };

            socket.onmessage = function(event) {
                document.getElementById("messages").innerHTML += "<p class='message-client'>Client: " + event.data + " <span>👨‍💻</span></p>";
                document.getElementById("messages").scrollTop = document.getElementById("messages").scrollHeight; // Auto scroll to latest message
            };

            socket.onclose = function() {
                document.getElementById("connectionStatus").innerHTML = "<span class='status-offline'>Disconnected <span>🔴</span></span>";
            };

            socket.onerror = function(error) {
                console.error("WebSocket error:", error);
            };
        }

        function sendResponse() {
            const message = document.getElementById("responseInput").value;
            if (socket && socket.readyState === WebSocket.OPEN && message.trim() !== "") {
                socket.send("Server: " + message + " <span>🤖</span>");
                document.getElementById("messages").innerHTML += "<p class='message-server'>Server: " + message + " <span>🛠️</span></p>";
                document.getElementById("responseInput").value = "";  // Clear input field
                document.getElementById("messages").scrollTop = document.getElementById("messages").scrollHeight; // Auto scroll to latest message
            }
        }

        window.onload = connect;
    </script>

</body>
</html>
