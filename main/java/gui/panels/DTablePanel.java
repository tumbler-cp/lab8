package gui.panels;

import client.Client;
import dragon.Dragon;
import gui.elements.DButton;
import gui.frames.DragonAdderScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DTablePanel extends JPanel implements ActionListener {
    private final DTableModel model;
    private final JComboBox<SortingType> comboBox;
    private final DButton addButton;
    private final Timer timer;
    private final JTextField keyField;
    private final DButton delButton;
    private final JTextField updField;
    private final DButton updButton;
    private final JLabel updLabel;
    private final JLabel keyLabel;
    private final JLabel label;
    public DTablePanel(){
        JPanel inner = new JPanel();
        inner.setPreferredSize(new Dimension(230, 150));
        inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));
        inner.setBackground(Color.BLACK);
        inner.setAlignmentY(CENTER_ALIGNMENT);
        inner.setAlignmentX(CENTER_ALIGNMENT);

        comboBox = new JComboBox<>(SortingType.values());
        comboBox.addActionListener(this);
        comboBox.setAlignmentX(CENTER_ALIGNMENT);
        JPanel left = new JPanel();
        left.setBackground(Color.BLACK);
        label = new JLabel();
        label.setText(Client.additBundle.getString("LSort") + " ");
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setPreferredSize(new Dimension(100, 30));
        label.setForeground(Color.WHITE);
        addButton = new DButton();
        addButton.setText(Client.additBundle.getString("buttAdd"));
        addButton.setAlignmentX(CENTER_ALIGNMENT);
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.BLACK);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel1.add(Box.createHorizontalGlue());
        panel1.add(label);
        panel1.add(Box.createHorizontalGlue());
        panel1.add(comboBox);
        panel1.add(Box.createHorizontalGlue());
        keyField = new JTextField();
        updField = new JTextField();

        delButton = new DButton();
        delButton.setText(Client.additBundle.getString("buttDelete"));
        delButton.setPreferredSize(new Dimension(100, 30));
        delButton.addActionListener(this);

        updButton = new DButton();
        updButton.setText(Client.additBundle.getString("buttUpdate"));
        updButton.setPreferredSize(new Dimension(100, 30));
        updButton.addActionListener(this);

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.BLACK);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

        keyLabel = new JLabel(Client.additBundle.getString("LKey") + ": ");
        keyLabel.setForeground(Color.white);

        panel2.add(Box.createHorizontalGlue());
        panel2.add(Box.createRigidArea(new Dimension(10, 0)));
        panel2.add(keyLabel);
        panel2.add(Box.createHorizontalGlue());
        panel2.add(Box.createRigidArea(new Dimension(10, 0)));
        panel2.add(keyField);
        panel2.add(Box.createHorizontalGlue());
        panel2.add(Box.createRigidArea(new Dimension(10, 0)));
        panel2.add(delButton);
        panel2.add(Box.createHorizontalGlue());
        panel2.add(Box.createRigidArea(new Dimension(10, 0)));

        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.BLACK);
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

        updLabel = new JLabel(Client.additBundle.getString("LId") + ": ");
        updLabel.setForeground(Color.white);

        panel3.add(Box.createHorizontalGlue());
        panel3.add(Box.createRigidArea(new Dimension(10, 0)));
        panel3.add(updLabel);
        panel3.add(Box.createHorizontalGlue());
        panel3.add(Box.createRigidArea(new Dimension(10, 0)));
        panel3.add(updField);
        panel3.add(Box.createHorizontalGlue());
        panel3.add(Box.createRigidArea(new Dimension(10, 0)));
        panel3.add(updButton);
        panel3.add(Box.createHorizontalGlue());
        panel3.add(Box.createRigidArea(new Dimension(10, 0)));

        left.add(Box.createVerticalGlue());

        inner.add(Box.createRigidArea(new Dimension(0, 10)));
        inner.add(panel1);
        inner.add(Box.createRigidArea(new Dimension(0, 10)));
        inner.add(addButton);
        inner.add(Box.createRigidArea(new Dimension(0, 10)));
        inner.add(panel2);
        inner.add(Box.createRigidArea(new Dimension(0, 10)));
        inner.add(panel3);
        inner.add(Box.createRigidArea(new Dimension(0, 10)));

        left.add(inner);

        left.add(Box.createVerticalGlue());

        addButton.addActionListener(this);

        model = new DTableModel();
        JTable table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        pane.setPreferredSize(new Dimension(1280, 720));
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setLayout(new BorderLayout());
        this.add(left, BorderLayout.WEST);
        this.add(pane, BorderLayout.CENTER);
        timer = new Timer(1000, this);
        timer.addActionListener(this);
        timer.start();
    }

    public void updLayout(){
        addButton.setText(Client.additBundle.getString("buttAdd"));
        delButton.setText(Client.additBundle.getString("buttDelete"));
        updButton.setText(Client.additBundle.getString("buttUpdate"));
        keyLabel.setText(Client.additBundle.getString("LKey") + ": ");
        updLabel.setText(Client.additBundle.getString("LId") + ": ");
        label.setText(Client.additBundle.getString("LSort") + " ");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource()==comboBox){
            model.setSorting(SortingType.type((SortingType) comboBox.getSelectedItem()));
            revalidate();
            repaint();
        }
        if (actionEvent.getSource()==addButton){
            new DragonAdderScreen(false);
            revalidate();
            repaint();
        }
        if (actionEvent.getSource()==timer){
            model.fireTableDataChanged();
        }
        if (actionEvent.getSource()==delButton){
            int key = Integer.parseInt(keyField.getText().trim());
            Client.manager.del(key);
        }
        if (actionEvent.getSource()==updButton){
            int id = Integer.parseInt(updField.getText().trim());
            Dragon d = Client.manager.getDragons().get(Client.manager.getKeyById(id));
            DragonAdderScreen dragonAdderScreen = new DragonAdderScreen(true);
            dragonAdderScreen.setResult(d);
        }
    }
}
