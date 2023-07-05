package gui.panels;

import client.Client;
import client.Locale;
import gui.frames.App;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class DOptions extends JPanel implements ActionListener {
    public final static HashMap<String, Locale> locals = new HashMap<>(){{
        put("Русский", Locale.ru_RU);
        put("English (SAR)", Locale.en_ZA);
        put("Ελληνικά", Locale.el_GR);
        put("Slovenský", Locale.sk_SK);
    }};
    private JLabel lang;
    private JLabel usr;
    private App parent;
    private final JComboBox<?> comboBox;
    public DOptions(){
        comboBox = new JComboBox<>(locals.keySet().toArray());
        comboBox.addActionListener(this);
        comboBox.setAlignmentX(CENTER_ALIGNMENT);
        comboBox.setAlignmentY(CENTER_ALIGNMENT);

        lang = new JLabel();
        lang.setText(Client.additBundle.getString("locale"));
        lang.setAlignmentX(CENTER_ALIGNMENT);
        lang.setAlignmentY(CENTER_ALIGNMENT);

        usr = new JLabel(Client.additBundle.getString("user"));
        usr.setAlignmentX(CENTER_ALIGNMENT);
        usr.setAlignmentY(CENTER_ALIGNMENT);

        JLabel currUsr = new JLabel(Client.user.getLogin());
        currUsr.setAlignmentX(CENTER_ALIGNMENT);
        currUsr.setAlignmentY(CENTER_ALIGNMENT);

        add(lang);
        add(comboBox);
        add(usr);
        add(currUsr);
    }

    public void updLayout(){
        lang.setText(Client.additBundle.getString("locale"));
        usr.setText(Client.additBundle.getString("user"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==comboBox){
            String str = (String) comboBox.getSelectedItem();
            Client.setLocale(locals.get(str));
            parent.updLayout();
        }
    }

    public void setParent(App parent) {
        this.parent = parent;
    }
}
