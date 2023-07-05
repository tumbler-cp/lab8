package comm.clientReqs;

import client.DragonFactory;
import comm.CommType;
import comm.CommandClient;
import comm.User;
import dragon.Dragon;
import interfaces.Managing;
import interfaces.Other;
import interfaces.Updating;

public class UpdateReq implements CommandClient<Dragon>, Managing, Updating, Other {
    private User user;
    private String[] line;
    private Dragon dragon;
    @Override
    public CommType getType() {
        return CommType.UPDATE;
    }

    @Override
    public Dragon getArg() {
        return dragon;
    }

    @Override
    public void setArgLine(String[] argLine) {
        this.line = argLine;
    }

    @Override
    public String[] getArgLine() {
        return line;
    }

    @Override
    public boolean create() {
        try {
            var ignored = Integer.valueOf(line[0]);
        } catch (ClassCastException c){
            System.out.println("Id это число!");
            return false;
        }
        return dragon.check();
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    public void setDragon(Dragon dragon) {
        this.dragon = dragon;
    }
}
