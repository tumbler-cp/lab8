package client;

import comm.Request;
import dragon.Dragon;
import interfaces.Handler;

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class ClientHandler implements Handler<Request> {
    private Request request;
    private final ClientNetwork network;
    private JTextArea area;

    public ClientHandler(ClientNetwork network, JTextArea textArea) {
        this.network = network;
        area = textArea;
    }

    public void run() throws IOException {
        switch (request.signal()) {
            case TEXT -> {
                area.append("\n----------COMMAND---------\n" + request.object());
                network.setPacketSize(4096);
            }
            case ALLOC -> {
                int newPackSize = Integer.parseInt(request.object().toString()) + 100;
                network.setPacketSize(newPackSize);
            }
            case TABLE -> {
                ConcurrentHashMap<Integer, Dragon> table = (ConcurrentHashMap<Integer, Dragon>) request.object();
                Client.manager.upd(table);
                area.append("\nТаблица обновлена\n");
                network.setPacketSize(4096);
            }
            case FILE -> {

            }
        }
    }

    public void setArea(JTextArea area){
        this.area = area;
    }

    @Override
    public void setObj(Request obj) {
        this.request = obj;
    }
}
