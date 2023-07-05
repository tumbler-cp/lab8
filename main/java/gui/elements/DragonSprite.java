package gui.elements;

import comm.Unit;
import dragon.Dragon;
import gui.data.Colors;

import javax.swing.*;
import java.awt.*;

public class DragonSprite extends JButton {
    private Dragon drag;
    private final int size = 10;
    private int wid = 16*size;
    private int hei = 13*size;
    private final int[] figure_x = {1,4,5,4,5,7,7,6,14,9,9,12,16,12,10,10,9,10,6,8,7,5,4,4,3,5,5,3,1};
    private final int[] figure_y = {4,1,3,3,4,5,3,0,0,4,6,6,5,8,9,11,12,13,13,11,9,8,8,9,8,7,5,4,5};
    public DragonSprite(Dragon src) {
        setOpaque(false);
        setContentAreaFilled(false);
        setBackground(Colors.background);
        setForeground(Colors.font);
        setFocusable(false);
        drag = src;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int[] x = new int[figure_x.length];
        int[] y = new int[figure_y.length];
        for (int i = 0;i < x.length;i++){
            x[i] = figure_x[i]*size;
            wid = 16*size;
        }
        for (int i = 0;i < y.length;i++){
            y[i] = figure_y[i]*size;
            hei = 13*size;
        }
        Color color = dragon.Color.awtColor(drag.getColor());
        g2.setPaint(color);
        g2.fillPolygon(x, y, x.length);
        g2.setStroke(new BasicStroke(2));
        g2.setPaint(Color.BLACK);
        g2.drawPolygon(x, y, x.length);
    }

    @Override
    protected void paintBorder(Graphics g) {

    }

    public int getWid() {
        return wid;
    }

    public int getHei() {
        return hei;
    }

    public Dragon getDragon() {
        return drag;
    }
}
