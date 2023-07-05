package client;

import comm.*;
import interfaces.Managing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;

public class ClientTerminal implements Runnable, ActionListener {
    private final ClientNetwork network;
    private final ClientHandler handler;
    private User user;
    private Receiver receiver;
    private JTextArea area;
    private final Timer timer;
    public ClientTerminal(ClientNetwork network) {
        this.network = network;
        handler = new ClientHandler(network, area);
        this.user = null;
        timer = new Timer(500, this);
        receiver = new Receiver(network, handler);
    }


    public void run() {
        network.request(Signal.TABLE, user);
        timer.start();
    }

    public void setArea(JTextArea area) {
        this.area = area;
        this.handler.setArea(area);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void mkCommand(CommandClient<?> command){
        if (command instanceof Managing) ((Managing) command).setUser(user);
        network.request(Signal.COMMAND, command);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (!receiver.isAlive()){
            receiver = new Receiver(network, handler);
            Executors.newSingleThreadExecutor().execute(receiver);
        }
    }
}
