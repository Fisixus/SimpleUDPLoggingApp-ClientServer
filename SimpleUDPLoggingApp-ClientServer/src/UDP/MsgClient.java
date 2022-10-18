package UDP;

import Utility.FileReadWrite;
import Utility.UDPSendReceive;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.*;

public class MsgClient {

    public MsgClient(String serverNameOrIP, int serverPort, String messagesPath) throws IOException, InterruptedException {
        this(serverNameOrIP, serverPort, messagesPath, 0);
    }

    public MsgClient(String serverNameOrIP, int serverPort, String messagesPath,  int delay) throws IOException, InterruptedException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress ipAddress = InetAddress.getByName(serverNameOrIP);
        String text = FileReadWrite.ReadTextFile(messagesPath);
        System.out.println("cMessages.txt is read it successfully!");
        String[] lines = text.split("[\\r\\n]+");

        byte[] receiveBuffer = new byte[1024]; // Design choice (maximum buffer size is 1024)
        byte[] sendBuffer = null;
        List<Object> returnList;

        //UDPSendReceive.sendString(socket, "letUsDoThis", ipAddress, serverPort);
        //returnList = UDPSendReceive.getString(socket, receiveBuffer);
        //if (!returnList.get(0).equals("getMessages")) {
         //   throw new RuntimeException("Undefined message from server");
        //}

        HashMap<Integer, String> packetsMap = new HashMap<>();

        //ByteArrayOutputStream outputStream;
        for (int i=0; i < lines.length ; i++){
            //outputStream = new ByteArrayOutputStream();
            //outputStream.write(ByteBuffer.allocate(4).putInt(i).array());
            //outputStream.write(lines[i].getBytes());
            //byte[] newPacket = outputStream.toByteArray();
            packetsMap.put(i, lines[i]);
        }


        for (HashMap.Entry<Integer, String> entry : packetsMap.entrySet()) {
            Arrays.fill(receiveBuffer, (byte) 0);
            UDPSendReceive.sendString(socket, entry.getValue(), ipAddress, serverPort);
            Thread.sleep(delay);
            returnList = UDPSendReceive.getString(socket,receiveBuffer);
            String receivedPacket = (String) returnList.get(0);
            System.out.println("Ack taken:" + receivedPacket);
        }

        System.out.println("Closing!");


        socket.close();
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length == 3) {
            MsgClient client = new MsgClient(args[0], Integer.parseInt(args[1]), args[2]);
        }
        else if (args.length == 4) {
            MsgClient client = new MsgClient(args[0], Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]));
        } else {
            System.out.println("Error: Wrong arguments");
        }
    }
}

