const statusEl = document.getElementById("status");
const messagesEl = document.getElementById("messages");
const clientSubscription = document.getElementById("clientSubscription");
let topicValue ="";

const socket = new SockJS('http://localhost:8080/ws');
const stompClient = Stomp.over(socket);

stompClient.debug = () => {
};

stompClient.connect({}, () => {
    statusEl.textContent = "Connected to WebSocket Server";
    statusEl.classList.add("status");

    document.getElementById("submitBtn").addEventListener("click", function() {
        topicValue = document.getElementById("topicInput").value;
        clientSubscription.textContent = "Client Subscribed to following topics:" + topicValue

        stompClient.subscribe('/broadcast/location/to/'+topicValue, (msg) => {
            const data = msg.body;
            const msgDiv = document.createElement("div");
            msgDiv.className = "msg";
            msgDiv.textContent = data;
            messagesEl.appendChild(msgDiv);
            messagesEl.scrollTop = messagesEl.scrollHeight;
        });

        stompClient.subscribe('/connection/close/for/'+topicValue, () => {
            stompClient.disconnect(() => {

                const msgDiv = document.createElement("div");
                msgDiv.className = "msg";
                msgDiv.textContent = "Connection has been closed";
                messagesEl.appendChild(msgDiv);
                messagesEl.scrollTop = messagesEl.scrollHeight;
                statusEl.textContent = "Connection has been closed";
            });
        });
    });
}, (error) => {
    statusEl.textContent = "Connection failed: " + error;
});