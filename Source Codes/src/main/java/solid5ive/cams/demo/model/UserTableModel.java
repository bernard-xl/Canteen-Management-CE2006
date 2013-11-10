/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solid5ive.cams.demo.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import solid5ive.cams.data.entities.User;

/**
 *
 * @author bernard
 */
public class UserTableModel extends AbstractTableModel {
    private final static String[] columnNames = { "Matric. Number", "Name" };
    private List<User> userList;
    
    public UserTableModel(List<User> userList) {
        this.userList = userList;
    }
    
    public UserTableModel() {
        this.userList = new ArrayList<>();
    }
    
    public User get(int index) {
        return userList.get(index);
    }
    
    public void set(List<User> users) {
        userList = users;
        fireTableDataChanged();
    }
    
    public void add(User user) {
        userList.add(user);
        fireTableRowsInserted(userList.size() - 1, userList.size() - 1);
    }
    
    public void remove(int index) {
        userList.remove(index);
        fireTableRowsDeleted(index, index);
    }
    
    public void clear() {
        userList.clear();
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return userList.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = userList.get(rowIndex);
        switch(columnIndex) {
            case 0: return user.getLoginName();
            case 1: return user.getName();
            default: return "";
        }
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
}
