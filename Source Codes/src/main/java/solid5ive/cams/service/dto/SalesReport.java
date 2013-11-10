package solid5ive.cams.service.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;
import solid5ive.cams.data.entities.Canteen;
import solid5ive.cams.data.entities.MenuReference;
import solid5ive.cams.data.entities.Stall;

/**
 * Data transfer object that carries all information about a sales report.
 * It merely consist lines of sales record, representation of data are client/GUI responsibility.
 * 
 * @author Poh Shie Liang
 */
public class SalesReport {
    private Map<Integer, Stall> stallMap;
    private Map<Integer, Canteen> canteenMap;
    private Map<Integer, MenuReference> menuMap;
    private List<SalesRecord> records;

    public SalesReport() {
        stallMap = new HashMap<>();
        canteenMap = new HashMap<>();
        menuMap = new HashMap<>();
        records = new LinkedList<>();
    }
    
    public void addIndividualRecord(Stall stall, DateTime purchaseTime, List<MenuReference> menus) {
        List<Integer> menuIds = new ArrayList<>(menus.size());
        stallMap.put(stall.getId(), stall);
        for(MenuReference m : menus) {
            menuIds.add(m.getId());
            menuMap.put(m.getId(), m);
        }
        records.add(new SalesRecord(stall.getId(), ReportTarget.INDIVIDUAL, menuIds, purchaseTime));
    }
    
    public void addCanteenRecord(Canteen canteen, DateTime purchaseTime, List<MenuReference> menus) {
        List<Integer> menuIds = new ArrayList<>(menus.size());
        canteenMap.put(canteen.getId(), canteen);
        for(MenuReference m : menus) {
            menuIds.add(m.getId());
            menuMap.put(m.getId(), m);
        }
        records.add(new SalesRecord(canteen.getId(), ReportTarget.CANTEEN, menuIds, purchaseTime));
    }

    public Map<Integer, Stall> getStallMap() {
        return stallMap;
    }

    public void setStallMap(Map<Integer, Stall> stallMap) {
        this.stallMap = stallMap;
    }

    public Map<Integer, Canteen> getCanteenMap() {
        return canteenMap;
    }

    public void setCanteenMap(Map<Integer, Canteen> canteenMap) {
        this.canteenMap = canteenMap;
    }

    public Map<Integer, MenuReference> getMenuMap() {
        return menuMap;
    }

    public void setMenuMap(Map<Integer, MenuReference> menuMap) {
        this.menuMap = menuMap;
    }

    public List<SalesRecord> getRecords() {
        return records;
    }

    public void setRecords(List<SalesRecord> records) {
        this.records = records;
    }
}
