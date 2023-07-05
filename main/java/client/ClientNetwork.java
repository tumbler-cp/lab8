package client;

import comm.Request;
import comm.Signal;
import interfaces.Network;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.net.*;

public class ClientNetwork implements Network {
    private byte[] bufferArr;
    private final SocketAddress serverAddress;
    private final DatagramSocket socket;
    private int packetSize;
    ClientNetwork (String HOST, int PORT) throws SocketException {
        serverAddress = new InetSocketAddress(HOST, PORT);
        socket = new DatagramSocket();
        socket.connect(serverAddress);
        packetSize = 1024;
    }
    @Override
    public boolean send(byte[] data) {
        DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress);
        try {
            socket.send(packet);
        } catch (IOException e){
            return false;
        }
        return true;
    }
    @Override
    public boolean receive() throws IOException {
        System.out.println("получение данных от сервера");
        byte[] bytes = new byte[packetSize];
        bufferArr = bytes;
        DatagramPacket received = new DatagramPacket(bytes, bytes.length);
        socket.receive(received);
        bufferArr = received.getData();
        return true;
    }
    @Override
    public void close() {
        socket.disconnect();
        socket.close();
    }

    @Override
    public void request(Signal signal, Object object) {
        send(new Request(signal, object).bytes());
    }

    @Override
    public byte[] buffer() {
        return bufferArr;
    }

    @Override
    public SocketAddress connected() {
        return serverAddress;
    }

    public void setPacketSize(int packetSize) {
        this.packetSize = packetSize;
    }
}
