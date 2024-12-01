<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Client Chat</title>
    <!-- Include Bootstrap CSS (from a CDN or locally) -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-pzjw8f+ua7Kw1TIq0XjBL3q4Z7JlkKOF+LVKZkW6MfeHZHk99NOADxflDFriqjX2" crossorigin="anonymous">
    <style>
        /* Custom styles for the chat window */
        #messages {
            height: 300px;
            overflow-y: auto;
            border: 1px solid #ddd;
            padding: 15px;
            background-color: #f8f9fa;
            margin-bottom: 20px;
        }

        #messageInput {
            width: 100%;
            padding: 10px;
        }

        .chat-container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
        }

        .message-box {
            border-radius: 5px;
            background-color: #e9ecef;
            padding: 10px;
            margin-bottom: 10px;
        }

        .sent-message {
            background-color: #d1ecf1;
        }

        .received-message {
            background-color: #f8d7da;
        }

        .send-btn {
            margin-top: 10px;
        }

        .status {
            font-size: 0.9em;
            color: #6c757d;
        }
    </style>
</head>
<body>
    <div class="container chat-container">
        <h2 class="text-center mb-4">Client Chat Interface</h2>

        <div id="messages" class="mb-4"></div>

        <div class="form-group">
            <input type="text" id="messageInput" class="form-control" placeholder="Type a message...">
        </div>
        <button class="btn btn-primary send-btn" onclick="sendMessage()">Send</button>
        <p id="status" class="status text-center"></p>
    </div>

    <!-- Include Bootstrap JS and Popper.js for functionality -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zyC6x4IOP5iPi1jeY6lwHpXWBw1o6Y9jq5pD6gW5" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-pzjw8f+ua7Kw1TIq0XjBL3q4Z7JlkKOF+LVKZkW6MfeHZHk99NOADxflDFriqjX2" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-pzjw8f+ua7Kw1TIq0XjBL3q4Z7JlkKOF+LVKZkW6MfeHZHk99NOADxflDFriqjX2" crossorigin="anonymous"></script>

    <script>
        let socket;

        function connect() {
            socket = new WebSocket("ws://localhost:8080/securityback/chatAuthSession");

            socket.onopen = function() {
                document.getElementById("status").innerHTML = "Connected to the server.";
                document.getElementById("status").classList.add("text-success");
                document.getElementById("status").classList.remove("text-danger");
            };

            socket.onmessage = function(event) {
                const message = event.data;
                document.getElementById("messages").innerHTML += `<div class="message-box received-message">${message}</div>`;
                document.getElementById("messages").scrollTop = document.getElementById("messages").scrollHeight;
            };

            socket.onclose = function() {
                document.getElementById("status").innerHTML = "Disconnected.";
                document.getElementById("status").classList.add("text-danger");
                document.getElementById("status").classList.remove("text-success");
            };

            socket.onerror = function(error) {
                console.error("WebSocket error:", error);
            };
        }

        function sendMessage() {
            const message = document.getElementById("messageInput").value;
            if (socket && socket.readyState === WebSocket.OPEN && message.trim() !== "") {
                socket.send(message);
                document.getElementById("messages").innerHTML += `<div class="message-box sent-message">You: ${message}</div>`;
                document.getElementById("messageInput").value = "";
                document.getElementById("messages").scrollTop = document.getElementById("messages").scrollHeight;
            }
        }

        window.onload = connect;
    </script>
</body>
</html>
