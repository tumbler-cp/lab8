package comm.clientReqs;

import comm.CommType;
import comm.CommandClient;
import interfaces.FileCommand;

import java.io.File;

public class ExecuteScriptReq implements CommandClient<String>, FileCommand {
    private String path;
    @Override
    public CommType getType() {
        return CommType.EXECUTE_SCRIPT;
    }

    @Override
    public String getArg() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void setArgLine(String[] argLine) {

    }

    @Override
    public String[] getArgLine() {
        return new String[0];
    }

    @Override
    public boolean create() {
        return new File(path).exists();
    }
}
