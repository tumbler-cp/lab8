package gui.panels;

import client.ClientTerminal;
import comm.User;
import gui.data.Colors;

import javax.swing.*;
import java.awt.*;

public class DAccount extends JPanel {
    private User user;
    private ClientTerminal terminal;
    private JPanel infoPanel;
    public DAccount (User user, ClientTerminal terminal) {
        this.user = user;
        this.terminal = terminal;

        infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(100, 100));
        infoPanel.setBackground(Color.BLACK);

        this.setPreferredSize(new Dimension(1280, 720));
        this.setBackground(Colors.background);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(infoPanel);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTerminal(ClientTerminal terminal) {
        this.terminal = terminal;
    }
}
