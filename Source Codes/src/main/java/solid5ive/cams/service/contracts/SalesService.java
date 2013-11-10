package solid5ive.cams.service.contracts;

import solid5ive.cams.data.entities.UserAccount;
import solid5ive.cams.service.dto.ReportQuery;
import solid5ive.cams.service.dto.SalesReport;

/**
 * Service layer that deal with sales related stuff, it depends on user service for balance transfer.
 * 
 * @author Poh Shie Liang
 */
public interface SalesService{
    public UserAccount acceptOrder(Integer stallId, String customerLogin, Integer... orderNumbers);
    public SalesReport reportSales(ReportQuery query);
}
