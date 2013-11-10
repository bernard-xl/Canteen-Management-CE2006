package solid5ive.cams.service.dto;

import java.util.List;
import org.joda.time.DateTime;

/**
 * Data transfer object that represent a line of sales record in the sales report.
 * 
 * @author Poh Shie Liang
 */
public class SalesRecord {
    private Integer targetId;
    private List<Integer> menuIds;
    private DateTime purchaseTime;
    private ReportTarget target;

    public SalesRecord() {
    }

    public SalesRecord(Integer targetId, ReportTarget target, List<Integer> menuIds, DateTime purchaseTime) {
        this.targetId = targetId;
        this.menuIds = menuIds;
        this.purchaseTime = purchaseTime;
        this.target = target;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public List<Integer> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Integer> menuIds) {
        this.menuIds = menuIds;
    }

    public DateTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(DateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public ReportTarget getTarget() {
        return target;
    }

    public void setTarget(ReportTarget target) {
        this.target = target;
    }
}
