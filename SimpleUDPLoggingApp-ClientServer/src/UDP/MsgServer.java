package UDP;

import Utility.FileReadWrite;
import Utility.UDPSendReceive;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MsgServer {

    public MsgServer(int serverPort, int delay) throws IOException, InterruptedException {

        DatagramSocket socket;
        byte[] receiveBuffer = new byte[1024]; // Design choice (maximum buffer size is 1024)
        byte[] sendBuffer = null;
        List<Object> returnList;
        socket = new DatagramSocket(serverPort);
        //returnList = UDPSendReceive.getString(socket, receiveBuffer);
        //if (!returnList.get(0).equals("letUsDoThis")) {
        //    throw new RuntimeException("Undefined message from client");
        //}
        //UDPSendReceive.sendString(socket, "getMessages", (InetAddress) returnList.get(1), (int) returnList.get(2));

        do {
            Arrays.fill(receiveBuffer, (byte) 0);
            returnList = UDPSendReceive.getString(socket, receiveBuffer);
            String receivedPacket = (String) returnList.get(0);

            //byte[] idPart = new byte[4];
            //int receivedId = 0;
            //for (int i = 0; i < 4; i++)
            //{
            //    idPart[i] = receivedPacket[i];
           // }
            //for (byte b : idPart) {
            //    receivedId = (receivedId << 8) + (b & 0xFF);
            //}

            Thread.sleep(delay);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            UDPSendReceive.sendString(socket, receivedPacket, (InetAddress) returnList.get(1), (int) returnList.get(2));

            System.out.println("Sending Ack To:" + (InetAddress) returnList.get(1) + " " + receivedPacket);

            FileReadWrite.AppendTextFile("sMessages.txt", dtf.format(now) + "\t" +receivedPacket);

        } while (true);

        //socket.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length == 2) {
            MsgServer server = new MsgServer(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        } else {
            System.out.println("Error: Wrong arguments");
        }
    }
}
