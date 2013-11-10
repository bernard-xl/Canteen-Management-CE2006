/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solid5ive.cams.demo.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import solid5ive.cams.data.entities.Stall;

/**
 *
 * @author bernard
 */
public class StallTableModel extends AbstractTableModel{
    private final static String[] columnNames = { "Canteen", "Lot No.", "Food Type", "Stall Name", "Owner Name" };
    private List<Stall> stallList;
    
    public StallTableModel() {
        stallList = new ArrayList<>();
    }
    
    public StallTableModel(List<Stall> stallList) {
        this.stallList = stallList;
    }
    
    public void set(List<Stall> stalls) {
        stallList = stalls;
        fireTableDataChanged();
    }
    
    public Stall get(int index) {
        return stallList.get(index);
    }
    
    public void add(Stall stall) {
        stallList.add(stall);
        fireTableRowsInserted(stallList.size() - 1, stallList.size() - 1);
    }
    
    public void remove(int index) {
        stallList.remove(index);
        fireTableRowsDeleted(index, index);
    }
    
    public void clear() {
        fireTableDataChanged();
    }
    
    public void refresh(int row) {
        fireTableRowsUpdated(row, row);
    }
    
    @Override
    public int getRowCount() {
        return stallList.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Stall stall = stallList.get(rowIndex);
        switch(columnIndex) {
            case 0: return stall.getCanteen().getName();
            case 1: return stall.getLotNumber();
            case 2: return stall.getFoodStyle();
            case 3: return stall.getName();
            case 4: return (stall.getOwner() == null)? "" : stall.getOwner().getName();
            default: return "";
        }
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
    
}
