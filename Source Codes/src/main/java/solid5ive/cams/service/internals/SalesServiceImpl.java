package solid5ive.cams.service.internals;

import java.util.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solid5ive.cams.data.entities.*;
import solid5ive.cams.data.repositories.*;
import solid5ive.cams.service.contracts.SalesService;
import solid5ive.cams.service.contracts.UserService;
import solid5ive.cams.service.dto.ReportQuery;

import java.util.List;
import solid5ive.cams.service.dto.SalesReport;

@Service
@Transactional
public class SalesServiceImpl implements SalesService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StallRepository stallRepository;
    
    @Autowired
    private CanteenRepository canteenRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    
    @Autowired
    private AccountRepository accRepository;

    @Override
    public UserAccount acceptOrder(Integer stallId, String customerLogin, Integer... orderNumbers) {
        Stall stall = stallRepository.findOne(stallId);
        if(stall == null) throw new IllegalArgumentException("The stall doesn't exist.");

        User hawker = stall.getOwner();
        if(hawker == null) throw new IllegalArgumentException("The stall doesn't has an owner.");

        User customer = userRepository.findOne(customerLogin);
        if(customer == null) throw new IllegalArgumentException("The customer doesn't exist.");

        Order order = new Order(stall, customer);
        List<Orderline> orderlines = order.getLines();
        Double totalAmount = 0.00;

        for(Integer o : orderNumbers) {
             Menu menu = menuRepository.findOne(o);
            if(menu != null && menu.getStall().getId().equals(stall.getId())) {
                orderlines.add(new Orderline(menu.getReference()));
                totalAmount += menu.getReference().getPrice();
            }
        }

        userService.transferBetweenAccount(customerLogin, hawker.getLoginName(), totalAmount);
        orderRepository.save(order);
        return accRepository.findByUser(customer);
    }

    @Override
    public SalesReport reportSales(ReportQuery query) {
        SalesReport report = new SalesReport();
        
        for(Integer i : query.getIndividualTarget()) {
            Stall stall = stallRepository.findOne(i);
            List<Order> orders = orderRepository.findByStallAndPeriod(stall, query.getBeginDate(), query.getEndDate());
            
            for(Order o : orders) {
                List<Orderline> lines = o.getLines();
                List<MenuReference> menus = new LinkedList<>();
                for(Orderline ol : lines) menus.add(ol.getReference());
                report.addIndividualRecord(stall, o.getTimestamp(), menus);
            }
        }
        
        for(Integer i : query.getGroupTarget()) {
            Canteen canteen = canteenRepository.findOne(i);
            List<Stall> stalls = stallRepository.findByCanteen(canteen);
            List<Order> orders = new LinkedList<>();
            
            for(Stall stall : stalls) {
                orders.addAll(orderRepository.findByStallAndPeriod(stall, query.getBeginDate(), query.getEndDate()));
            }
            
            for(Order o : orders) {
                List<Orderline> lines = o.getLines();
                List<MenuReference> menus = new LinkedList<>();
                for(Orderline ol : lines) menus.add(ol.getReference());
                report.addCanteenRecord(canteen, o.getTimestamp(), menus);
            }
       }
       
       return report;
    }
}
