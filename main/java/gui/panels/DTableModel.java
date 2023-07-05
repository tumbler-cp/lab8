package gui.panels;

import client.Client;
import comm.Unit;
import dragon.Dragon;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DTableModel extends AbstractTableModel {
    private int sorting = 0;
    public DTableModel(){

    }
    private final String[] columnNames = {
            "key",
            "id",
            "name",
            "coordinates",
            "age",
            "creation_date",
            "color",
            "type",
            "character",
            "cave",
            "owner"
    };

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    Object[][] data(){
        List<Unit> src;
        switch (sorting){
            case 0 -> src = Client.manager.keySort();
            case 1 -> src = Client.manager.nameSort();
            case 2 -> src = Client.manager.idSort();
            case 3 -> src = Client.manager.ageSort();
            case 4 -> src = Client.manager.coordinatesSort();
            case 5 -> src = Client.manager.dateSort();
            case 6 -> src = Client.manager.colorSort();
            case 7 -> src = Client.manager.typeSort();
            case 8 -> src = Client.manager.characterSort();
            case 9 -> src = Client.manager.caveSort();
            case 10 -> src = Client.manager.ownerSort();
            default -> src = Client.manager.normal();
        }
        int s = Client.manager.normal().size();
        Object[][] r = new Object[s][11];
        int i = 0;
        for (Unit curr : src) {
            Dragon d = curr.dragon();
            r[i][0] = curr.key();
            r[i][1] = d.getId();
            r[i][2] = d.getName();
            r[i][3] = d.getCoordinates();
            r[i][4] = d.getAge();
            r[i][5] = d.getCreationDate();
            r[i][6] = d.getColor();
            r[i][7] = d.getType();
            r[i][8] = d.getCharacter();
            r[i][9] = d.getCave();
            r[i][10] = d.getOwner();
            i++;
        }
        return r;
    }
    @Override
    public int getRowCount() {
        return data().length;
    }

    @Override
    public int getColumnCount() {
        return 11;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        return data()[i][i1];
    }

    public void setSorting(int sorting) {
        this.sorting = sorting;
    }
}
