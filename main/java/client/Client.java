package client;

import gui.elements.DFloatingInfo;
import gui.frames.DragonAdderScreen;
import gui.frames.LogScreen;
import java.net.SocketException;
import java.util.ResourceBundle;
import comm.User;

public class Client {
    public static ClientDragonManager manager = new ClientDragonManager();
    public static ClientTerminal terminal;
    public static User user;
    public static Locale localization = Locale.ru_RU;
    public static ResourceBundle logBundle = ResourceBundle.getBundle("loginLocal_" + localization);
    public static ResourceBundle dragBundle = ResourceBundle.getBundle("dragonLocal_" + localization);
    public static ResourceBundle additBundle = ResourceBundle.getBundle("additLocal_" + localization);
    public static ResourceBundle terminalBundle = ResourceBundle.getBundle("terminalLocal_" + localization);
    public static void main(String[] args) {

        ClientNetwork network;
        try {
            network = new ClientNetwork("localhost", 7000);
        } catch (SocketException s){
            throw new RuntimeException(s);
        }
        LogReg lr = new LogReg(network);

        terminal = new ClientTerminal(network);
        LogScreen ignored = new LogScreen(lr, terminal);
    }

    public static void setLocale(Locale locale){
        localization = locale;
        logBundle = ResourceBundle.getBundle("loginLocal_" + localization);
        dragBundle = ResourceBundle.getBundle("dragonLocal_" + localization);
        additBundle = ResourceBundle.getBundle("additLocal_" + localization);
        terminalBundle = ResourceBundle.getBundle("terminalLocal_" + localization);
        DFloatingInfo.updLayout();
        DragonAdderScreen.updLayout();
    }
}
