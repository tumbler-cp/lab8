package gui.elements;

import gui.data.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class DButton extends JButton {
    public DButton(){
        setBackground(Colors.background);
        setForeground(Colors.font);
    }
    @Override
    public void setText(String text) {
        int len = 5 * text.length();
        super.setText(text);
        setSize(len + 30, getHeight());
        setOpaque(false);
        setContentAreaFilled(false);
        setBackground(getBackground());
        setForeground(getForeground());
        setFocusable(false);
        addMouseListener(new MouseAdapter() {
            Color background = getBackground();
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(background.darker());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(background);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Shape roundedRectangle = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20);
        g2.setColor(getBackground());
        g2.fill(roundedRectangle);
        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {}
}
