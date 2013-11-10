/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solid5ive.cams.demo.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import solid5ive.cams.data.entities.Menu;

/**
 *
 * @author bernard
 */
public class MenuTableModel extends AbstractTableModel {
    private final static String[] columnNames = { "Name", "Price", "Description" };
    private List<Menu> menuList;
    
    public MenuTableModel(List<Menu> menuList) {
        this.menuList = menuList;
    }
    
    public MenuTableModel() {
        this.menuList = new ArrayList<>();
    }
    
    public void set(List<Menu> menus) {
        menuList = menus;
        fireTableDataChanged();
    }
    
    public Menu get(int index) {
        return menuList.get(index);
    }
    
    public void add(Menu menu) {
        menuList.add(menu);
        fireTableRowsInserted(menuList.size() - 1, menuList.size() - 1);
    }
    
    public void remove(int index) {
        menuList.remove(index);
        fireTableRowsDeleted(index, index);
    }
    
    public void clear() {
        menuList.clear();
        fireTableDataChanged();
    }
    
    public void refresh(int row) {
        fireTableRowsUpdated(row, row);
    }
    
    @Override
    public int getRowCount() {
        return menuList.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Menu menu = menuList.get(rowIndex);
        switch(columnIndex) {
            case 0: return menu.getReference().getName();
            case 1: return menu.getReference().getPrice();
            case 2: return menu.getDescription();
            default: return "";
        }
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
    
}
