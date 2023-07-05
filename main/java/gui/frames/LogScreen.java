package gui.frames;

import client.Client;
import client.ClientTerminal;
import client.LogReg;
import comm.Signal;
import gui.elements.DButton;
import gui.panels.DOptions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LogScreen implements ActionListener {
    private final LogReg engine;
    private final JFrame frame;
    private final JTextField loginField;
    private final JPasswordField passwordField;
    private final JLabel messageLabel;
    private final JButton button1;
    private final JButton button2;
    private final ClientTerminal buffTerm;
    private final JComboBox<?> langBox;
    private JLabel userL;
    private JLabel passL;

    //Locale
    String title = Client.logBundle.getString("logTitle");
    String logText = Client.logBundle.getString("username");
    String passText = Client.logBundle.getString("password");
    String entText = Client.logBundle.getString("login");
    String regText = Client.logBundle.getString("registration");

    public LogScreen(LogReg logReg, ClientTerminal terminal){
        buffTerm = terminal;

        this.engine = logReg;

        loginField = new JTextField();
        loginField.setPreferredSize(new Dimension(200, 30));

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 30));

        messageLabel = new JLabel();
        messageLabel.setForeground(Color.RED);

        button1 = new DButton();
        button1.setText(entText);
        button1.addActionListener(this);

        button2 = new DButton();
        button2.setText(regText);
        button2.addActionListener(this);

        File file = new File("dragon.jpg");
        Image img;
        try {
            img = ImageIO.read(file);
            img = img.getScaledInstance(1920, 1080, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        ImageIcon imgIc = new ImageIcon(img);
        JPanel filler = new JPanel();
        JLabel label = new JLabel(imgIc);
        label.setBounds(0, 0, 1920, 1080);
        filler.add(label);

        JPanel left = new JPanel();
        left.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        left.setPreferredSize(new Dimension(300, 400));

        JPanel group = new JPanel();
        group.setLayout(new BoxLayout(group, BoxLayout.Y_AXIS));

        userL = new JLabel(logText);
        passL = new JLabel(passText);

        group.add(Box.createVerticalGlue());

        group.add(Box.createRigidArea(new Dimension(0, 10)));
        group.add(Box.createHorizontalGlue());
        userL.setAlignmentX(Component.CENTER_ALIGNMENT);
        group.add(userL);

        group.add(Box.createRigidArea(new Dimension(0, 10)));
        group.add(Box.createHorizontalGlue());
        loginField.setAlignmentX(Component.CENTER_ALIGNMENT);
        group.add(loginField);

        group.add(Box.createRigidArea(new Dimension(0, 10)));
        group.add(Box.createHorizontalGlue());
        passL.setAlignmentX(Component.CENTER_ALIGNMENT);
        group.add(passL);

        group.add(Box.createRigidArea(new Dimension(0, 10)));
        group.add(Box.createHorizontalGlue());
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        group.add(passwordField);

        group.add(Box.createRigidArea(new Dimension(0, 10)));
        group.add(Box.createHorizontalGlue());
        button1.setAlignmentX(Component.CENTER_ALIGNMENT);
        group.add(button1);

        group.add(Box.createRigidArea(new Dimension(0, 10)));
        group.add(Box.createHorizontalGlue());
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);
        group.add(button2);

        group.add(Box.createRigidArea(new Dimension(0, 10)));
        group.add(Box.createHorizontalGlue());
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        group.add(messageLabel);

        group.add(Box.createRigidArea(new Dimension(0, 30)));
        group.add(Box.createHorizontalGlue());
        langBox = new JComboBox<>(DOptions.locals.keySet().toArray(new String[0]));
        langBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        langBox.addActionListener(this);
        group.add(langBox);

        group.add(Box.createRigidArea(new Dimension(0, 10)));
        group.add(Box.createVerticalGlue());

        left.add(group, gbc);


        frame = new JFrame();
        frame.setTitle(title);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setLayout(new BorderLayout());

        frame.add(left, BorderLayout.WEST);
        frame.add(filler, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
        langBox.setSelectedItem("Русский");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource()==button1) {
            String l = loginField.getText();
            String p = new String(passwordField.getPassword());
            try {
                if (engine.logReg(l, p, Signal.LOGIN)){
                    messageLabel.setForeground(Color.GREEN);
                    messageLabel.setText("Успешно авторизован!");
                    loginField.setText("");
                    passwordField.setText("");
                    this.frame.setVisible(false);
                    buffTerm.setUser(engine.user());
                    Client.user = engine.user();
                    new App(buffTerm);
                }
                else {
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Проверьте логин/пароль");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (actionEvent.getSource()==button2) {
            String l = loginField.getText();
            String p = new String(passwordField.getPassword());
            try {
                if (engine.logReg(l, p, Signal.REG)){
                    messageLabel.setForeground(Color.GREEN);
                    messageLabel.setText("Успешно зарегистрирован!");
                    loginField.setText("");
                    passwordField.setText("");
                    this.frame.setVisible(false);
                    Client.user = engine.user();
                    new App(buffTerm);
                }
                else {
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Пользователь с таким именем уже существует!");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (actionEvent.getSource()==langBox){
            Client.setLocale(DOptions.locals.get((String) langBox.getSelectedItem()));
            title = Client.logBundle.getString("logTitle");
            logText = Client.logBundle.getString("username");
            passText = Client.logBundle.getString("password");
            entText = Client.logBundle.getString("login");
            regText = Client.logBundle.getString("registration");

            frame.setTitle(title);
            userL.setText(logText);
            passL.setText(passText);
            button1.setText(entText);
            button2.setText(regText);

            frame.revalidate();
            frame.repaint();
            System.out.println(Client.logBundle.getBaseBundleName());
        }
    }
}
