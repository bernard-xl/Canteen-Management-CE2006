package solid5ive.cams.service.dto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.joda.time.DateTime;

/**
 * Data transfer object that carries all information about a report query to be sent to the system.
 * 
 * @author Poh Shie Liang
 */
public class ReportQuery {
    private List<Integer> individualTarget;
    private List<Integer> groupTarget;
    private DateTime beginDate;
    private DateTime endDate;

    public ReportQuery() {
    }
    
    public ReportQuery(Integer individualId, DateTime beginDate, DateTime endDate) {
        this.individualTarget = new ArrayList<>();
        individualTarget.add(individualId);
        this.groupTarget = new LinkedList<>();
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public ReportQuery(List<Integer> individualTarget, DateTime beginDate, DateTime endDate) {
        this.individualTarget = individualTarget;
        this.groupTarget = new LinkedList<>();
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public ReportQuery(List<Integer> individualTarget, List<Integer> groupTarget, DateTime beginDate, DateTime endDate) {
        this.individualTarget = individualTarget;
        this.groupTarget = groupTarget;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public List<Integer> getIndividualTarget() {
        return individualTarget;
    }

    public void setIndividualTarget(List<Integer> individualTarget) {
        this.individualTarget = individualTarget;
    }

    public List<Integer> getGroupTarget() {
        return groupTarget;
    }

    public void setGroupTarget(List<Integer> groupTarget) {
        this.groupTarget = groupTarget;
    }

    public DateTime getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(DateTime beginDate) {
        this.beginDate = beginDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }
}
