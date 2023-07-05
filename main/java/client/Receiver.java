package client;

import comm.Request;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;

public class Receiver extends Thread {
    private ClientNetwork network;
    private ClientHandler handler;
    public Receiver(ClientNetwork clientNetwork, ClientHandler clientHandler){
        network = clientNetwork;
        handler = clientHandler;
    }
    @Override
    public void run() {
        System.out.println("Ресивер начал работать");
        try {
            network.receive();
        } catch (IOException e) {
            System.out.println("Ошибка получения данных!");
        }
        Request response = SerializationUtils.deserialize(network.buffer());
        System.out.println(response);
        handler.setObj(response);
        try {
            handler.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
