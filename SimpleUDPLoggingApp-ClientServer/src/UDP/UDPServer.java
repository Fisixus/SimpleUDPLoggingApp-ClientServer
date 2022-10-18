package UDP;

import Utility.UDPSendReceive;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;

public class UDPServer {
    private int waitingPacketCount = 7;

    public static boolean AllPacketsAreRead(HashMap<Integer, String> packetCheckerMap){
        for (HashMap.Entry<Integer, String> entry : packetCheckerMap.entrySet()) {
            if(entry.getValue() == null){
                return false;
            }
        }
        return true;
    }

    public static String AllPackageToString(HashMap<Integer, String> packetCheckerMap){
        int totalByteLength = 0;
        String s = "";
        for (HashMap.Entry<Integer, String> entry : packetCheckerMap.entrySet()) {
            s += entry.getValue();
        }

        return s;
    }

    public UDPServer(int port) throws IOException {

        HashMap<Integer, String> packetCheckerMap = new HashMap<>();
        for(int i = 0; i < waitingPacketCount; i++){
            packetCheckerMap.put(i,null);
        }

        DatagramSocket socket;
        byte[] receiveBuffer = new byte[1024]; // Design choice (maximum buffer size is 1024)
        byte[] sendBuffer = null;
        List<Object> returnList;
        //do {
        socket = new DatagramSocket(port);
        returnList = UDPSendReceive.getString(socket, receiveBuffer);
        if (!returnList.get(0).equals("letUsDoThis")) {
            throw new RuntimeException("Undefined message from client");
        }
        UDPSendReceive.sendString(socket, "getOperators", (InetAddress) returnList.get(1), (int) returnList.get(2));

        do {
            Arrays.fill(receiveBuffer, (byte) 0);
            returnList = UDPSendReceive.getBytes(socket, receiveBuffer);
            byte[] receivedPacket = (byte[])returnList.get(0);

            byte[] idPart = new byte[4];
            int receivedId = 0;
            for (int i = 0; i < 4; i++)
            {
                idPart[i] = receivedPacket[i];
            }
            for (byte b : idPart) {
                receivedId = (receivedId << 8) + (b & 0xFF);
            }

            if(packetCheckerMap.get(receivedId) == null){
                String s = "";
                try{
                    s = "" + Double.parseDouble(new String(receivedPacket,4,receivedPacket.length-4));
                }catch (Exception e){
                    s = new String(receivedPacket,4,1);
                }

                packetCheckerMap.put(receivedId,s);
                UDPSendReceive.sendInt(socket, receivedId, (InetAddress) returnList.get(1), (int) returnList.get(2));
            }


        } while (!AllPacketsAreRead(packetCheckerMap));
        //UDPSendReceive.sendInt(socket, -1, (InetAddress) returnList.get(1), (int) returnList.get(2));

        String s = AllPackageToString(packetCheckerMap);
        System.out.println("Received Equation:" + s);

        //UDPSendReceive.sendDouble(socket, d, (InetAddress) returnList.get(1), (int) returnList.get(2));

        socket.close();
        //} while (true);
    }

    public static void main(String[] args) throws IOException {
        UDPServer server = new UDPServer(4000);
    }
}
