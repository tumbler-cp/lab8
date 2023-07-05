package gui.elements;

import gui.data.Colors;

import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class DTabbedPaneUI extends BasicTabbedPaneUI {
    @Override
    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (isSelected) {
            g2.setColor(Colors.background);
            Shape shape = new RoundRectangle2D.Float(x, y, w, h, 15, 15);
            Shape shape1 = new RoundRectangle2D.Float(x, y + 10, w, h - 10, 0, 0);
            g2.fill(shape);
            g2.fill(shape1);
        } else if (tabPane.isEnabled() && tabPane.isEnabledAt(tabIndex)) {
            g2.setColor(Colors.background.darker());
            Shape shape = new RoundRectangle2D.Float(x, y, w, h, 15, 15);
            Shape shape1 = new RoundRectangle2D.Float(x, y + 10, w, h - 10, 0, 0);
            g2.fill(shape);
            g2.fill(shape1);
        }
    }

    @Override
    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {

    }

    @Override
    protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) {

    }

    @Override
    protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {

    }
}
