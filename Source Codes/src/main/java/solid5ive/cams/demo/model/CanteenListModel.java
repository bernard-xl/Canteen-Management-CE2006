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
import solid5ive.cams.data.entities.Canteen;

/**
 *
 * @author bernard
 */
public class CanteenListModel implements ListModel {

    private List<Canteen> canteenList;
    private List<ListDataListener> dataListeners;
    
    public CanteenListModel() {
        this.canteenList = new ArrayList<>();
        dataListeners = new LinkedList<>();
    }
    
    public CanteenListModel(List<Canteen> canteenList) {
        this.canteenList = canteenList;
        dataListeners = new LinkedList<>();
    }
    
    public Canteen get(int index) {
        return canteenList.get(index);
    }
    
    public void set(List<Canteen> canteens) {
        canteenList = canteens;
        for(ListDataListener listener : dataListeners) {
            listener.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, canteenList.size() - 1));
        }
    }
    
    public void add(Canteen canteen) {
        canteenList.add(0, canteen);
        for(ListDataListener listener : dataListeners) {
            listener.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, canteenList.size() - 1));
        }
    }
    
    public void remove(int index) {
        canteenList.remove(index);
        for(ListDataListener listener : dataListeners) {
            listener.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, canteenList.size() - 1));
        }
    }
    
    public void clear() {
        canteenList.clear();
        for(ListDataListener listener : dataListeners) {
            listener.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, canteenList.size() - 1));
        }
    }
    
    @Override
    public int getSize() {
        return canteenList.size();
    }

    @Override
    public Object getElementAt(int index) {
        Canteen canteen = canteenList.get(index);
        return canteen.getName();
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
