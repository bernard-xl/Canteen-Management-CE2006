/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solid5ive.cams.demo.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import solid5ive.cams.data.entities.Canteen;
import solid5ive.cams.data.entities.MenuReference;
import solid5ive.cams.data.entities.Stall;
import solid5ive.cams.service.dto.ReportTarget;
import solid5ive.cams.service.dto.SalesRecord;
import solid5ive.cams.service.dto.SalesReport;

/**
 *
 * @author bernard
 */
public class ChartBuilder {
    
    private static Comparator<SalesRecord> comparator =
            new Comparator<SalesRecord> () {

            @Override
            public int compare(SalesRecord o1, SalesRecord o2) {
                if(o1.getPurchaseTime().isBefore(o2.getPurchaseTime())) return -1;
                else if(o1.getPurchaseTime().isAfter(o2.getPurchaseTime())) return 1;
                else return 0;
            }
        };
    
    private static List<SalesRecord> filterRecordsWithDate(List<SalesRecord> records, LocalDate date) {
        List<SalesRecord> copy = new LinkedList<>();
        for(SalesRecord r : records) copy.add(r);
        Iterator<SalesRecord> iter = copy.iterator();
        while(iter.hasNext()) {
            if(!iter.next().getPurchaseTime().toLocalDate().equals(date))
                iter.remove();
        }
        return copy;
    }
    
    public static JFreeChart hourlyChart(SalesReport report, LocalDate date) {
        List<SalesRecord> records = filterRecordsWithDate(report.getRecords(), date);
        Collections.sort(records, comparator);
        Map<Integer, MenuReference> menuMap = report.getMenuMap();
        Map<Integer, Stall> stallMap = report.getStallMap();
        Map<Integer, Canteen> canteenMap = report.getCanteenMap();
        
        Map<Integer, Double> individualSales = new HashMap<>(report.getStallMap().size());
        Map<Integer, Double> canteenSales = new HashMap<>(report.getCanteenMap().size());
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        DateTime refTime = new DateTime().withTime(9, 0, 0, 0);
        
        for(Integer i : stallMap.keySet()) individualSales.put(i, 0.00);
        for(Integer i : canteenMap.keySet()) canteenSales.put(i, 0.00);
        
        for(SalesRecord r : records) {
            while(r.getPurchaseTime().getHourOfDay() > refTime.getHourOfDay()) {
                for(Integer i : individualSales.keySet()) {
                    Stall stall = stallMap.get(i);
                    Double sales = individualSales.get(i);
                    dataset.addValue(sales, stall.getName(), refTime.toString("HH:mm"));
                    individualSales.put(i, 0.00);
                }
                for(Integer i : canteenSales.keySet()) {
                    Canteen canteen = canteenMap.get(i);
                    Double sales = canteenSales.get(i);
                    dataset.addValue(sales, canteen.getName(), refTime.toString("HH:mm"));
                    canteenSales.put(i, 0.00);
                }
                refTime = refTime.plusHours(1);
            }
            if(r.getTarget() == ReportTarget.INDIVIDUAL) {
                Double sales = individualSales.get(r.getTargetId());
                Double orderAmount = 0.00;
                
                for(Integer i : r.getMenuIds()) {
                    orderAmount += menuMap.get(i).getPrice();
                }
                
                individualSales.put(r.getTargetId(), sales + orderAmount);
            } else {
                Double sales = canteenSales.get(r.getTargetId());
                Double orderAmount = 0.00;
                
                for(Integer i : r.getMenuIds()) {
                    orderAmount += menuMap.get(i).getPrice();
                }
                
                canteenSales.put(r.getTargetId(), sales + orderAmount);
            }
        }
        
        while(refTime.getHourOfDay() <= 21) {
            for(Integer i : individualSales.keySet()) {
                    Stall stall = stallMap.get(i);
                    Double sales = individualSales.get(i);
                    dataset.addValue(sales, stall.getName(), refTime.toString("HH:mm"));
                    individualSales.put(i, 0.00);
                }
                for(Integer i : canteenSales.keySet()) {
                    Canteen canteen = canteenMap.get(i);
                    Double sales = canteenSales.get(i);
                    dataset.addValue(sales, canteen.getName(), refTime.toString("HH:mm"));
                    canteenSales.put(i, 0.00);
                }
                refTime = refTime.plusHours(1);
        }
        
        return ChartFactory.createBarChart(date.toString("dd/MM/yyyy 'Sales'"), "Time", "Sales", dataset, PlotOrientation.VERTICAL, true, true, false);
    }
    
    public static JFreeChart dailyChart(SalesReport report, DateTime begin, DateTime end) {
        List<SalesRecord> records = report.getRecords();
        Collections.sort(records, comparator);
        Map<Integer, MenuReference> menuMap = report.getMenuMap();
        Map<Integer, Stall> stallMap = report.getStallMap();
        Map<Integer, Canteen> canteenMap = report.getCanteenMap();
        
        Map<Integer, Double> individualSales = new HashMap<>(report.getStallMap().size());
        Map<Integer, Double> canteenSales = new HashMap<>(report.getCanteenMap().size());
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        DateTime refTime = begin;
        
        for(Integer i : stallMap.keySet()) individualSales.put(i, 0.00);
        for(Integer i : canteenMap.keySet()) canteenSales.put(i, 0.00);
        
        for(SalesRecord r : records) {
            while(r.getPurchaseTime().getDayOfMonth() > refTime.getDayOfMonth()) {
                for(Integer i : individualSales.keySet()) {
                    Stall stall = stallMap.get(i);
                    Double sales = individualSales.get(i);
                    dataset.addValue(sales, stall.getName(), refTime.toString("dd MMM"));
                    individualSales.put(i, 0.00);
                }
                for(Integer i : canteenSales.keySet()) {
                    Canteen canteen = canteenMap.get(i);
                    Double sales = canteenSales.get(i);
                    dataset.addValue(sales, canteen.getName(), refTime.toString("dd MMM"));
                    canteenSales.put(i, 0.00);
                }
                refTime = refTime.plusDays(1);
            }
            
            if(r.getTarget() == ReportTarget.INDIVIDUAL) {
                Double sales = individualSales.get(r.getTargetId());
                Double orderAmount = 0.00;
                
                for(Integer i : r.getMenuIds()) {
                    orderAmount += menuMap.get(i).getPrice();
                }
                
                individualSales.put(r.getTargetId(), sales + orderAmount);
            } else {
                Double sales = canteenSales.get(r.getTargetId());
                Double orderAmount = 0.00;
                
                for(Integer i : r.getMenuIds()) {
                    orderAmount += menuMap.get(i).getPrice();
                }
                
                canteenSales.put(r.getTargetId(), sales + orderAmount);
            }
        }
        
        while(!refTime.isAfter(end)) {
            for(Integer i : individualSales.keySet()) {
                    Stall stall = stallMap.get(i);
                    Double sales = individualSales.get(i);
                    dataset.addValue(sales, stall.getName(), refTime.toString("dd MMM"));
                    individualSales.put(i, 0.00);
                }
                for(Integer i : canteenSales.keySet()) {
                    Canteen canteen = canteenMap.get(i);
                    Double sales = canteenSales.get(i);
                    dataset.addValue(sales, canteen.getName(), refTime.toString("dd MMM"));
                    canteenSales.put(i, 0.00);
                }
                refTime = refTime.plusDays(1);
        }
        
        return ChartFactory.createBarChart("Daily Sales", "Date", "Sales", dataset, PlotOrientation.VERTICAL, true, true, false);
    }
    
    public static JFreeChart monthlyChart(SalesReport report, DateTime begin, DateTime end) {
        List<SalesRecord> records = report.getRecords();
        Collections.sort(records, comparator);
        Map<Integer, MenuReference> menuMap = report.getMenuMap();
        Map<Integer, Stall> stallMap = report.getStallMap();
        Map<Integer, Canteen> canteenMap = report.getCanteenMap();
        
        Map<Integer, Double> individualSales = new HashMap<>(report.getStallMap().size());
        Map<Integer, Double> canteenSales = new HashMap<>(report.getCanteenMap().size());
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        DateTime refTime = begin;
        
        for(Integer i : stallMap.keySet()) individualSales.put(i, 0.00);
        for(Integer i : canteenMap.keySet()) canteenSales.put(i, 0.00);
        
        for(SalesRecord r : records) {
            while(r.getPurchaseTime().getMonthOfYear()> refTime.getMonthOfYear()) {
                for(Integer i : individualSales.keySet()) {
                    Stall stall = stallMap.get(i);
                    Double sales = individualSales.get(i);
                    dataset.addValue(sales, stall.getName(), refTime.toString("MMM"));
                    individualSales.put(i, 0.00);
                }
                for(Integer i : canteenSales.keySet()) {
                    Canteen canteen = canteenMap.get(i);
                    Double sales = canteenSales.get(i);
                    dataset.addValue(sales, canteen.getName(), refTime.toString("MMM"));
                    canteenSales.put(i, 0.00);
                }
                refTime = refTime.plusMonths(1);
            }
            
            if(r.getTarget() == ReportTarget.INDIVIDUAL) {
                Double sales = individualSales.get(r.getTargetId());
                Double orderAmount = 0.00;
                
                for(Integer i : r.getMenuIds()) {
                    orderAmount += menuMap.get(i).getPrice();
                }
                
                individualSales.put(r.getTargetId(), sales + orderAmount);
            } else {
                Double sales = canteenSales.get(r.getTargetId());
                Double orderAmount = 0.00;
                
                for(Integer i : r.getMenuIds()) {
                    orderAmount += menuMap.get(i).getPrice();
                }
                
                canteenSales.put(r.getTargetId(), sales + orderAmount);
            }
        }
        
        while(refTime.getMonthOfYear() <= end.getMonthOfYear()) {
            for(Integer i : individualSales.keySet()) {
                    Stall stall = stallMap.get(i);
                    Double sales = individualSales.get(i);
                    dataset.addValue(sales, stall.getName(), refTime.toString("MMM"));
                    individualSales.put(i, 0.00);
                }
                for(Integer i : canteenSales.keySet()) {
                    Canteen canteen = canteenMap.get(i);
                    Double sales = canteenSales.get(i);
                    dataset.addValue(sales, canteen.getName(), refTime.toString("MMM"));
                    canteenSales.put(i, 0.00);
                }
                refTime = refTime.plusMonths(1);
        }
        
        return ChartFactory.createBarChart("Monthly Sales", "Month", "Sales", dataset, PlotOrientation.VERTICAL, true, true, false);
    }
    
    public static JFreeChart menulyChart(SalesReport report) {
        List<SalesRecord> records = report.getRecords();
        Map<Integer, MenuReference> menuMap = report.getMenuMap();
        Map<Integer, Stall> stallMap = report.getStallMap();
        Map<Integer, Canteen> canteenMap = report.getCanteenMap();
 
        Map<Integer, Integer> menuSales = new HashMap<>(report.getMenuMap().size());
        Map<Integer, String> menuName = new HashMap<>(report.getMenuMap().size());

        for(Integer i : menuMap.keySet()) menuSales.put(i, 0);
        
        for(SalesRecord r : records) {
            for(Integer i : r.getMenuIds()) {
                MenuReference menu = menuMap.get(i);
                Integer sales = menuSales.get(i) + 1;
                menuSales.put(i, sales);
                if(!menuName.containsKey(i)) {
                    String name = (r.getTarget().equals(ReportTarget.INDIVIDUAL))?
                            stallMap.get(r.getTargetId()).getName() :
                            canteenMap.get(r.getTargetId()).getName();
                    menuName.put(i, menu.getName() + "(ID: " + menu.getId() + ", price: " + menu.getPrice() + ", seller: " + name + ")");
                }
            }
        }
        
        DefaultPieDataset dataset = new DefaultPieDataset();
        for(Integer i : menuSales.keySet()) {
            dataset.setValue(menuName.get(i), menuSales.get(i));
        }
        
        return ChartFactory.createPieChart("Item Sales", dataset, true, true, false);
    }
}
