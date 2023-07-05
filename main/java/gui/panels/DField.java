package gui.panels;

import client.Client;
import dragon.Dragon;
import gui.elements.DFloatingInfo;
import gui.elements.DragonSprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DField extends JPanel implements ActionListener {
    private final List<DragonSprite> objects;
    private Timer timer;
    private Timer animationTimer;
    private int maxX = 0;
    private int maxY = 0;
    public DField() {
        this.setPreferredSize(new Dimension(1280, 720));
        this.setBackground(new Color(0x5da130));
        this.setLayout(null);
        objects = new ArrayList<>();
        place();
        animationTimer = new Timer(33, this);
        animationTimer.addActionListener(this);
    }
    public void place(){
        int w_offset = getWidth()/2;
        int h_offset = getHeight()/2;
        for (Component c : getComponents()){
            if (c instanceof DragonSprite){
                remove(c);
            }
        }
        objects.clear();
        for (Integer k : Client.manager.getDragons().keySet()){
            Dragon d = Client.manager.getDragons().get(k);
            DragonSprite sp = new DragonSprite(d){{
                setText(d.getId().toString());
            }};
            objects.add(sp);
            sp.addActionListener(this);
        }
        setMaxCoors();
        for (DragonSprite sp : objects) {
            Dragon d = sp.getDragon();
            float x_m = (float) w_offset/maxX;
            float y_m = (float) h_offset/maxY;
            sp.setBounds((int)(x_m * d.getX()) + w_offset - sp.getWid()/2, -(int)(y_m * d.getY()) + h_offset - sp.getHei()/2, sp.getWid(), sp.getHei());
            add(sp);
        }
        timer = new Timer(500, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        int w_offset = getWidth()/2;
        int h_offset = getHeight()/2;
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(0, h_offset, w_offset * 2, h_offset);
        g2.drawLine(w_offset, 0, w_offset, h_offset * 2);
    }

    @Override
    protected void paintBorder(Graphics g) {

    }
    private void cleanInfos(){
        for (Component c : this.getComponents()){
            if (c instanceof DFloatingInfo){
                this.remove(c);
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource()==timer){
            place();
            revalidate();
            repaint();
        }
        if (actionEvent.getSource() instanceof DragonSprite) {
            for (DragonSprite dd : objects) {
                if (actionEvent.getSource() == dd) {
                    System.out.println(dd.getDragon());
                    cleanInfos();
                    add(new DFloatingInfo(dd.getDragon()) {{
                        setBounds(50, 50, 150, 250);
                        setParent(DField.this);
                    }});
                    revalidate();
                    repaint();
                }
            }
        }
    }

    private void setMaxCoors() {
        maxX = 0;
        maxY = 0;
        for (DragonSprite s : objects){
            if (Math.abs(s.getDragon().getX()) > maxX) maxX = Math.abs(s.getDragon().getX());
            if (Math.abs(s.getDragon().getY()) > maxY) maxY = Math.abs(s.getDragon().getY());
        }
    }

    public DragonSprite getDragonsSprite(Dragon d){
        for (DragonSprite s : objects){
            if (d.equals(s.getDragon())){
                return s;
            }
        }
        return null;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }
}
