# SimpleUDPLoggingApp-ClientServer
A client/server Java application that logs messages sent from the client to the server. The application will depend on the UDP socket.

1. The client:
    The client program (MsgClient.java) will send messages in sequential order after reading them from a file (cMessages.txt). Each message is a single line in the server. The client takes the following arguments from the command line:
    -a: server address
    -p: server port number
    -f: messages file path and name
    -i: delay in milliseconds from receiving an acknowledgment from the server for the previous message to the time of sending the following message (default 0ms).
    
2. The server:
The server program (MsgServer.java) will receive messages from the client, save them with their received day and time to a file (sMessages.txt), and acknowledge each received message by sending the message back to the client. The server takes the following arguments:
    -p: server port number
    -r: average of the random delay in milliseconds from receiving the message to acknowledging it.
