const statusEl = document.getElementById("status");
const messagesEl = document.getElementById("messages");

const socket = new SockJS('http://localhost:8080/ws');
const stompClient = Stomp.over(socket);

stompClient.debug = () => {
};

stompClient.connect({}, () => {
    statusEl.textContent = "Connected to WebSocket Server";
    statusEl.classList.add("status");

    stompClient.subscribe('/broadcast/location', (msg) => {
        const data = msg.body;
        const msgDiv = document.createElement("div");
        msgDiv.className = "msg";
        msgDiv.textContent = data;
        messagesEl.appendChild(msgDiv);
        messagesEl.scrollTop = messagesEl.scrollHeight;
    });

    stompClient.subscribe('/connection/close', () => {
        stompClient.disconnect(() => {

            const msgDiv = document.createElement("div");
            msgDiv.className = "msg";
            msgDiv.textContent = "Connection has been closed";
            messagesEl.appendChild(msgDiv);
            messagesEl.scrollTop = messagesEl.scrollHeight;
            statusEl.textContent = "Connection has been closed";
        });
    });
}, (error) => {
    statusEl.textContent = "Connection failed: " + error;
});