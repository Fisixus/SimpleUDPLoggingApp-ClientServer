package UDP;

import Utility.UDPSendReceive;

import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.*;

public class UDPClient {

    private String[] operators = {"88", "/", "-4", "+", "10", "*", "8" };

    public UDPClient(int port) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress ipAddress = InetAddress.getByName("127.0.0.1");
        byte[] receiveBuffer = new byte[1024]; // Design choice (maximum buffer size is 1024)
        byte[] sendBuffer = null;
        List<Object> returnList;

        UDPSendReceive.sendString(socket, "letUsDoThis", ipAddress, port);
        returnList = UDPSendReceive.getString(socket, receiveBuffer);
        if (!returnList.get(0).equals("getOperators")) {
            throw new RuntimeException("Undefined message from server");
        }

        HashMap<Integer, byte[]> packetsMap = new HashMap<>();

        ByteArrayOutputStream outputStream;
        //System.arraycopy(sendBuffer, 0, ByteBuffer.allocate(4).putInt(1).array(), 0, sendBuffer.length);
        for (int i=0; i < operators.length ; i++){
            outputStream = new ByteArrayOutputStream();

            outputStream.write(ByteBuffer.allocate(4).putInt(i).array());
            outputStream.write(operators[i].getBytes());

            byte[] newPacket = outputStream.toByteArray();
            packetsMap.put(i, newPacket);
        }

        do {
            Arrays.fill(receiveBuffer, (byte) 0);

            for (HashMap.Entry<Integer, byte[]> entry : packetsMap.entrySet()) {
                UDPSendReceive.sendBytes(socket, entry.getValue(), ipAddress, port);
            }

            returnList = UDPSendReceive.getInt(socket, receiveBuffer);
            int ackNum = (int) returnList.get(0);
            if(packetsMap.get(ackNum) != null){
                packetsMap.remove(ackNum);
                if(packetsMap.isEmpty())
                    break;
            }

        } while (true);

        Arrays.fill(receiveBuffer, (byte) 0);
        returnList = UDPSendReceive.getDouble(socket, receiveBuffer);
        double result = (double) returnList.get(0);
        System.out.println("Received Result:" + result);


        socket.close();
    }


    public static void main(String[] args) throws IOException {
        UDPClient client = new UDPClient(4000);
    }
}

