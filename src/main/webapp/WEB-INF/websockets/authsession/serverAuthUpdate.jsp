<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Server Chat Interface</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center mb-4">Server Chat Interface</h2>
    
    <!-- Chat Box -->
    <div id="server-chat-box" class="card mb-3">
        <div class="card-body" id="server-message-area" style="height: 400px; overflow-y: auto;">
            <!-- Messages from clients and server responses will appear here -->
        </div>
    </div>

    <!-- Input Area -->
    <div class="input-group">
        <input type="text" id="server-message-input" class="form-control" placeholder="Type a response">
        <button class="btn btn-outline-secondary" id="server-send-btn">
            <i class="fas fa-paper-plane"></i> Send
        </button>
    </div>
</div>

<script>
    const serverMessageInput = document.getElementById('server-message-input');
    const serverMessageArea = document.getElementById('server-message-area');
    const serverSendBtn = document.getElementById('server-send-btn');

    // Append message function
    function appendServerMessage(content, sender = 'server') {
        const messageDiv = document.createElement('div');
        const messageClass = sender === 'client' ? 'bg-primary text-white' : 'bg-secondary text-light';
        messageDiv.classList.add('mb-2', 'p-2', 'rounded', messageClass);
        messageDiv.style.maxWidth = '75%';
        messageDiv.innerHTML = `<span>${content}</span>`;
        
        // Align client messages to the right
        if (sender === 'client') {
            messageDiv.classList.add('ms-auto', 'text-end');
        } else {
            messageDiv.classList.add('text-start');
        }
        
        serverMessageArea.appendChild(messageDiv);
        serverMessageArea.scrollTop = serverMessageArea.scrollHeight;
    }

    // Send button functionality
    serverSendBtn.addEventListener('click', () => {
        const message = serverMessageInput.value.trim();
        if (message) {
            appendServerMessage(message, 'server');
            serverMessageInput.value = '';
            // Optionally, make an AJAX call here to send the response back to the client
        }
    });

    // Allow Enter key to send message
    serverMessageInput.addEventListener('keypress', (e) => {
        if (e.key === 'Enter') {
            serverSendBtn.click();
        }
    });
</script>
</body>
</html>
