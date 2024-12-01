<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Client Chat</title>

    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <!-- Font Awesome for icons -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">

    <style>
        /* Custom styles for chat UI */
        body {
            background-color: #f4f7fc;
            font-family: Arial, sans-serif;
        }

        .container {
            max-width: 800px;
            margin-top: 50px;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        #messages {
            height: 300px;
            overflow-y: auto;
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 10px;
            background-color: #f9f9f9;
            margin-bottom: 20px;
            display: flex;
            flex-direction: column-reverse;
        }

        #messages p {
            padding: 10px;
            border-radius: 5px;
            background-color: #e9ecef;
            margin-bottom: 10px;
            transition: all 0.3s ease;
        }

        .message-you {
            background-color: #cce5ff;
            text-align: right;
        }

        .message-other {
            background-color: #e2e3e5;
            text-align: left;
        }

        .emoji-grid {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 10px;
            margin-top: 10px;
        }

        .emoji-btn {
            background-color: transparent;
            border: none;
            font-size: 22px;
            cursor: pointer;
            transition: transform 0.3s ease;
        }

        .emoji-btn:hover {
            transform: scale(1.2);
        }

        .message-input-group {
            display: flex;
            align-items: center;
            margin-top: 20px;
            flex-wrap: wrap;
        }

        #messageInput {
            flex: 1;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 5px;
            outline: none;
            transition: border 0.3s ease;
        }

        #messageInput:focus {
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

        #connectionStatus {
            margin-bottom: 20px;
        }

        .status-online {
            color: green;
        }

        .status-offline {
            color: red;
        }

        @media (max-width: 768px) {
            .container {
                padding: 15px;
                margin-top: 20px;
            }

            #messages {
                height: 250px;
            }

            #messageInput {
                font-size: 14px;
            }

            #sendBtn {
                padding: 8px 12px;
            }
        }
    </style>

    <script>
        let socket;
        let messageInput, messagesContainer, sendButton;

        function connect() {
            socket = new WebSocket("ws://localhost:8080/securityback/chatAuthSession");

            socket.onopen = function() {
                document.getElementById("connectionStatus").innerHTML = "<span class='status-online'>Connected <span>🟢</span></span>";
            };

            socket.onmessage = function(event) {
                addMessage(event.data, 'other');
            };

            socket.onclose = function() {
                document.getElementById("connectionStatus").innerHTML = "<span class='status-offline'>Disconnected <span>🔴</span></span>";
            };

            socket.onerror = function(error) {
                console.error("WebSocket error:", error);
            };
        }

        function sendMessage() {
            const message = messageInput.value.trim();
            if (message && socket.readyState === WebSocket.OPEN) {
                socket.send(message);
                addMessage(`You: ${message}`, 'you');
                messageInput.value = "";  // Clear input field
            }
        }

       /* function addMessage(message, type) {
            const messageElement = document.createElement('p');
            messageElement.className = type === 'you' ? 'message-you' : 'message-other';
            messageElement.textContent = message;
            messagesContainer.prepend(messageElement);  // Append new message at the top
            messagesContainer.scrollTop = messagesContainer.scrollHeight;  // Auto-scroll
        }*/
        function addMessage(message, type) {
            const messageElement = document.createElement('p');
            messageElement.className = type === 'you' ? 'message-you' : 'message-other';
            messageElement.innerHTML = message;  // Use innerHTML instead of textContent to parse HTML
            messagesContainer.prepend(messageElement);  // Append new message at the top
            messagesContainer.scrollTop = messagesContainer.scrollHeight;  // Auto-scroll
        }

        function addEmoji(emoji) {
            messageInput.value += emoji;
            messageInput.focus();
        }

        window.onload = function() {
            messageInput = document.getElementById('messageInput');
            messagesContainer = document.getElementById('messages');
            sendButton = document.getElementById('sendBtn');
            connect();
        };

        // Reconnect WebSocket on page reload or other issues
        window.onbeforeunload = function() {
            if (socket && socket.readyState === WebSocket.OPEN) {
                socket.close();
            }
        };
    </script>
</head>
<body>

    <div class="container">
        <h1 class="text-center">Client Chat Interface</h1>

        <!-- Connection Status -->
        <div id="connectionStatus" class="text-center mb-4">
            <span class="status-offline">Disconnected <span>🔴</span></span>
        </div>

        <!-- Messages Container -->
        <div id="messages"></div>

        <div class="message-input-group">
            <!-- Emoji Button Section -->
            <div class="emoji-grid">
                <button class="emoji-btn" onclick="addEmoji('👨‍💻')">👨‍💻</button>
                <button class="emoji-btn" onclick="addEmoji('😊')">😊</button>
                <button class="emoji-btn" onclick="addEmoji('❤️')">❤️</button>
                <button class="emoji-btn" onclick="addEmoji('🚗')">🚗</button>
                <button class="emoji-btn" onclick="addEmoji('🖊️')">🖊️</button>
                <button class="emoji-btn" onclick="addEmoji('🙌')">🙌</button>
                <button class="emoji-btn" onclick="addEmoji('🔥')">🔥</button>
                <button class="emoji-btn" onclick="addEmoji('🎉')">🎉</button>
            </div>
            
            <!-- Message Input -->
            <input type="text" id="messageInput" placeholder="Type a message..." onkeydown="if(event.key === 'Enter') sendMessage()">
            
            <!-- Send Button -->
            <button id="sendBtn" onclick="sendMessage()"><i class="fas fa-paper-plane"></i> Send</button>
        </div>
    </div>

    <!-- Bootstrap and FontAwesome Scripts -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
