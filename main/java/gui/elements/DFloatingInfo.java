package gui.elements;

import client.Client;
import dragon.Dragon;
import gui.frames.DragonAdderScreen;
import gui.panels.DField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class DFloatingInfo extends JPanel implements ActionListener {
    private final DButton delete;
    private final DButton update;
    private final DButton close;
    private final Dragon object;
    private final Timer animTimer;
    private int frameCounter;
    private DField field;
    private int dspX;
    private int dspYMax;
    private int dspY;
    private int dspW;
    private int dspH;

    private static String idS;
    private static String nameS;
    private static String ageS;
    private static String coorS;
    private static String typeS;
    private static String characterS;
    private static String colorS;
    private static String caveS;
    private static String dateS;
    private static String ownerS;

    static {
        updLayout();
    }

    public DFloatingInfo(Dragon d){
        object = d;

        update = new DButton();
        update.setText(Client.additBundle.getString("buttUpdate"));
        update.addActionListener(this);

        delete = new DButton();
        delete.setText(Client.additBundle.getString("buttDelete"));
        delete.addActionListener(this);

        close = new DButton();
        close.setText(Client.additBundle.getString("buttClose"));
        close.addActionListener(this);

        setPreferredSize(new Dimension(150, 250));
        conf();

        animTimer = new Timer(15, this);
        frameCounter = 1;
    }
    private void conf(){
        int id = object.getId();
        String name = object.getName();
        int age = object.getAge();
        String coordinates = object.getCoordinates().toString();
        String type = object.getType().toString();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel(idS + ": " + id));
        add(new JLabel(nameS + ": " + name));
        add(new JLabel(ageS + ": " + age));
        add(new JLabel(coorS + ": " + coordinates));
        add(new JLabel(typeS + ": " + type));
        add(new JLabel(characterS + ": " + object.getCharacter().toString()));
        add(new JLabel(colorS + ": " + object.getColor()));
        add(new JLabel(caveS + ": " + object.getCave().toString()));
        add(new JLabel(dateS + ": " + object.getCreationDate()));
        add(new JLabel(ownerS + ": " + object.getOwner()));
        add(delete);
        add(update);
        add(close);

        if (!object.getOwner().equals(Client.user.getLogin())){
            delete.setVisible(false);
            update.setVisible(false);
        }

        revalidate();
        repaint();
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource()==delete){
            System.out.println(Arrays.toString(Client.manager.getDragons().values().toArray()));
            this.setVisible(false);
            DragonSprite dsp = field.getDragonsSprite(object);
            dspX = dsp.getX();
            dspY = dsp.getY();
            dspW = dsp.getWid();
            dspH = dsp.getHei();
            dspYMax = field.getHeight();
            System.out.println(dspY + " dada " + dspYMax);
            animTimer.start();
        }
        if (actionEvent.getSource()==animTimer){
            dspY += (int)(0.7 * frameCounter);
            field.getDragonsSprite(object).setBounds(dspX, dspY, dspW, dspH);
            field.getDragonsSprite(object).repaint();
            frameCounter++;

            if (dspY <= dspYMax){
                return;
            }

            System.out.println(frameCounter);
            Client.manager.delById(object.getId());
            frameCounter = 1;
            animTimer.stop();
            field.revalidate();
            field.repaint();
        }
        if (actionEvent.getSource()==update){
            DragonAdderScreen adderScreen = new DragonAdderScreen(true);
            adderScreen.setResult(object);
        }
        if (actionEvent.getSource()==close){
            setVisible(false);
        }
    }

    public static void updLayout(){
        idS = Client.dragBundle.getString("flId");
        nameS = Client.dragBundle.getString("flName");
        ageS = Client.dragBundle.getString("flAge");
        coorS = Client.dragBundle.getString("flCoords");
        typeS = Client.dragBundle.getString("flType");
        characterS = Client.dragBundle.getString("flCharacter");
        colorS = Client.dragBundle.getString("flColor");
        caveS = Client.dragBundle.getString("flCave");
        dateS = Client.dragBundle.getString("flCreationDate");
        ownerS = Client.dragBundle.getString("flOwner");
    }

    public void setParent(DField field) {
        this.field = field;
    }
}
