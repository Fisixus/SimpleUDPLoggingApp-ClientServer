package Utility;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

public class UDPSendReceive {
    static public List<Object> getString(DatagramSocket socket, byte[] receiveBuffer) throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        socket.receive(datagramPacket);
        String receivedData = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
        InetAddress ipAddress = datagramPacket.getAddress();
        int port = datagramPacket.getPort();
        return Arrays.asList(receivedData, ipAddress, port);
    }

    static public void sendString(DatagramSocket socket, String message, InetAddress address, int port) throws IOException {
        byte[] sendBuffer = message.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, port);
        socket.send(datagramPacket);
    }

    static public List<Object> getBytes(DatagramSocket socket, byte[] receiveBuffer) throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        socket.receive(datagramPacket);
        byte[] receivedData = datagramPacket.getData();
        InetAddress ipAddress = datagramPacket.getAddress();
        int port = datagramPacket.getPort();
        return Arrays.asList(receivedData, ipAddress, port);
    }

    static public void sendBytes(DatagramSocket socket, byte[] message, InetAddress address, int port) throws IOException {
        byte[] sendBuffer = message;
        DatagramPacket datagramPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, port);
        socket.send(datagramPacket);
    }

    static public List<Object> getInt(DatagramSocket socket, byte[] receiveBuffer) throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        socket.receive(datagramPacket);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(datagramPacket.getData());
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        int receivedData = dataInputStream.readInt();
        InetAddress ipAddress = datagramPacket.getAddress();
        int port = datagramPacket.getPort();
        dataInputStream.close();
        byteArrayInputStream.close();
        return Arrays.asList(receivedData, ipAddress, port);
    }

    static public void sendInt(DatagramSocket socket, int message, InetAddress address, int port) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeInt(message);
        byte[] sendBuffer = byteArrayOutputStream.toByteArray();
        DatagramPacket datagramPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, port);
        socket.send(datagramPacket);
        dataOutputStream.close();
        byteArrayOutputStream.close();
    }

    static public List<Object> getDouble(DatagramSocket socket, byte[] receiveBuffer) throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        socket.receive(datagramPacket);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(datagramPacket.getData());
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        double receivedData = dataInputStream.readDouble();
        InetAddress ipAddress = datagramPacket.getAddress();
        int port = datagramPacket.getPort();
        dataInputStream.close();
        byteArrayInputStream.close();
        return Arrays.asList(receivedData, ipAddress, port);
    }

    static public double getDouble2(byte[] data){
        return ByteBuffer.wrap(data).getDouble();
    }
    static public void sendDouble(DatagramSocket socket, double message, InetAddress address, int port) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeDouble(message);
        byte[] sendBuffer = byteArrayOutputStream.toByteArray();
        DatagramPacket datagramPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, port);
        socket.send(datagramPacket);
        dataOutputStream.close();
        byteArrayOutputStream.close();
    }

    static public List<Object> getBool(DatagramSocket socket, byte[] receiveBuffer) throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        socket.receive(datagramPacket);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(datagramPacket.getData());
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        boolean receivedData = dataInputStream.readBoolean();
        InetAddress ipAddress = datagramPacket.getAddress();
        int port = datagramPacket.getPort();
        dataInputStream.close();
        byteArrayInputStream.close();
        return Arrays.asList(receivedData, ipAddress, port);
    }



}
