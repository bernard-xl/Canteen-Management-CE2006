/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solid5ive.cams.demo.model;

import java.util.LinkedList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import solid5ive.cams.data.entities.Stall;

/**
 *
 * @author bernard
 */
public class StallListModel implements ListModel {

    private List<Stall> stalls;
    private List<ListDataListener> dataListeners;
    private final boolean showCanteenName;
    
    public StallListModel() {
        stalls = new LinkedList<>();
        dataListeners = new LinkedList<>();
        showCanteenName = false;
    }
    
    public StallListModel(boolean showCanteenName) {
        stalls = new LinkedList<>();
        dataListeners = new LinkedList<>();
        this.showCanteenName = showCanteenName;
    }
    
    public List<Stall> asList() {
        return stalls;
    }
    
    public void set(List<Stall> stalls) {
        this.stalls = stalls;
        for(ListDataListener listener : dataListeners) {
            listener.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, stalls.size() - 1));
        }
    }
    
    public Stall get(int index) {
        return stalls.get(index);
    }
    
    public void add(Stall stall) {
        stalls.add(0, stall);
        for(ListDataListener listener : dataListeners) {
            listener.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, stalls.size() - 1));
        }
    }
    
    public void add(List<Stall> stalls) {
        this.stalls.addAll(0, stalls);
        for(ListDataListener listener : dataListeners) {
            listener.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, stalls.size() - 1));
        }
    }
    
    public void remove(int index) {
        stalls.remove(index);
        for(ListDataListener listener : dataListeners) {
            listener.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, stalls.size() - 1));
        }
    }
    
    public void clear() {
        stalls.clear();
        for(ListDataListener listener : dataListeners) {
            listener.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, stalls.size() - 1));
        }
    }
    
    @Override
    public int getSize() {
        return stalls.size();
    }

    @Override
    public Object getElementAt(int index) {
        Stall stallAt = stalls.get(index);
        return (stallAt.getId() == -1)? 
                stallAt.getCanteen().getName() : 
                stallAt.getLotNumber() + " " + stallAt.getName() + ((showCanteenName)? "(" + stallAt.getCanteen().getName() + ")" : "");
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
