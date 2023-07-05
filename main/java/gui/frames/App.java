package gui.frames;

import client.Client;
import client.ClientTerminal;
import gui.data.Colors;
import gui.elements.DTabbedPaneUI;
import gui.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App implements ActionListener {
    private final JFrame frame;
    private final DTablePanel table;
    private final DTerminal terminal;
    private final DOptions options;
    private static String fieldTab = Client.additBundle.getString("tabField");
    private static String tableTab = Client.additBundle.getString("tabTable");
    private static String termTab = Client.additBundle.getString("tabTerminal");
    private static String optTab = Client.additBundle.getString("tabOptions");
    public App(ClientTerminal term) throws IOException {
        JTabbedPane tabPane = new JTabbedPane();
        tabPane.setUI(new DTabbedPaneUI());
        tabPane.setForeground(Colors.font);

        DField field = new DField();

        JScrollPane fieldBody = new JScrollPane(field);
        fieldBody.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        fieldBody.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        table = new DTablePanel();
        table.setBackground(Color.BLACK.brighter());
        JScrollPane tableBody = new JScrollPane(table);
        tableBody.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableBody.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        terminal = new DTerminal();
        terminal.setBackground(Color.BLUE);

        options = new DOptions();
        options.setParent(this);

        term.setArea(terminal.getArea());

        tabPane.addTab(fieldTab, fieldBody);
        tabPane.addTab(tableTab, tableBody);
        tabPane.addTab(termTab, terminal);
        tabPane.addTab(optTab, options);

        frame = new JFrame();
        frame.setSize(1280, 720);
        frame.setMinimumSize(new Dimension(640, 480));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(tabPane);
        frame.setVisible(true);

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(term);
    }

    public void updLayout(){
        fieldTab = Client.additBundle.getString("tabField");
        tableTab = Client.additBundle.getString("tabTable");
        termTab = Client.additBundle.getString("tabTerminal");
        optTab = Client.additBundle.getString("tabOptions");

        table.updLayout();
        terminal.updLayout();
        options.updLayout();

        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        frame.revalidate();
        frame.repaint();
    }
}
