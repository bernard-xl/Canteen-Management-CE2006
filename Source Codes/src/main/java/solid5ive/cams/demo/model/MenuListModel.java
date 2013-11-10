/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solid5ive.cams.demo.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import solid5ive.cams.data.entities.Menu;

/**
 *
 * @author bernard
 */
public class MenuListModel implements ListModel {

    private List<Menu> menuList;
    private List<ListDataListener> dataListeners;
    private boolean showTag;
    private boolean showDescription;
    
    public MenuListModel() {
        this.menuList = new ArrayList<>();
        dataListeners = new LinkedList<>();
    }
    
    public MenuListModel(List<Menu> menuList) {
        this.menuList = menuList;
        dataListeners = new LinkedList<>();
    }

    public boolean isShowTag() {
        return showTag;
    }

    public void setShowTag(boolean showTag) {
        this.showTag = showTag;
    }

    public boolean isShowDescription() {
        return showDescription;
    }

    public void setShowDescription(boolean showDescription) {
        this.showDescription = showDescription;
    }
    
    public List<Menu> asList() {
        return menuList;
    }
    
    public void set(List<Menu> menus) {
        menuList = menus;
        for(ListDataListener listener : dataListeners) {
            listener.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, menuList.size() - 1));
        }
    }
    
    public Menu get(int index) {
        return menuList.get(index);
    }
    
    public void add(Menu menu) {
        menuList.add(0, menu);
        for(ListDataListener listener : dataListeners) {
            listener.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, menuList.size() - 1));
        }
    }
    
    public void add(List<Menu> menus) {
        menuList.addAll(0, menus);
        for(ListDataListener listener : dataListeners) {
            listener.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, menuList.size() - 1));
        }
    }
    
    public void remove(int index) {
        menuList.remove(index);
        for(ListDataListener listener : dataListeners) {
            listener.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, menuList.size() - 1));
        }
    }
    
    public void clear() {
        menuList.clear();
        for(ListDataListener listener : dataListeners) {
            listener.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, menuList.size() - 1));
        }
    }
    
    public void refresh(int row) {
        for(ListDataListener listener : dataListeners) {
            listener.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, row, row));
        }
    }
    
    @Override
    public int getSize() {
        return menuList.size();
    }

    @Override
    public Object getElementAt(int index) {
        Menu menu = menuList.get(index);
        String retString = menu.getReference().getPrice() + "  " + menu.getReference().getName();
        if(showTag && menu.getTag() != null) retString += "(" + menu.getTag() + ")";
        if(showDescription && menu.getDescription() != null && !menu.getDescription().isEmpty()) retString += " - " + menu.getDescription();
        return retString;
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        dataListeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        dataListeners.remove(l);
    }
    
}
