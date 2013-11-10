/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solid5ive.cams.demo.ui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.Subject;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import solid5ive.cams.data.entities.Stall;
import solid5ive.cams.demo.model.ChartBuilder;
import solid5ive.cams.service.contracts.InfrastructureService;
import solid5ive.cams.service.contracts.SalesService;
import solid5ive.cams.service.dto.ReportQuery;
import solid5ive.cams.service.dto.SalesReport;

/**
 *
 * @author Bernard
 */
@Component
@Scope("prototype")
public class ReportPanel extends javax.swing.JPanel {

    private Subject subject;
    private SalesReport report;
    private List<DateTime> reportRange;
    private boolean adminStyle;
    @Autowired
    private SalesService salesService;
    @Autowired
    private InfrastructureService infService;

    /**
     * Creates new form ReportPanel
     */
    public ReportPanel() {
        initComponents();
        reportRange = new LinkedList<>();
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public boolean isAdminStyle() {
        return adminStyle;
    }

    public void setAdminStyle(boolean AdminStyle) {
        this.adminStyle = AdminStyle;
        if(!adminStyle) tabbedPane.setVisible(false);
    }

    private ChartPanel createChartPanel(JFreeChart chart) {
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setDisplayToolTips(true);
        chartPanel.setRangeZoomable(false);
        chartPanel.setDomainZoomable(false);
        return chartPanel;
    }

    private void adminQuery() {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        AdminReportDialog dialog = new AdminReportDialog(infService, topFrame, true);
        dialog.setVisible(true);

        if (dialog.isOkPressed()) {
            try {
                List<Integer> indiTarget = new ArrayList<>();
                List<Integer> grpTarget = new ArrayList<>();

                for (Stall stall : dialog.getSelectedStall()) {
                    if (stall.getId() != -1) {
                        indiTarget.add(stall.getId());
                    } else {
                        grpTarget.add(stall.getCanteen().getId());
                    }
                }

                ReportQuery query = new ReportQuery(indiTarget, grpTarget, dialog.getBeginDate(), dialog.getEndDate().withTime(23, 59, 59, 59));
                report = salesService.reportSales(query);

                reportRange.clear();
                hourlyDateCombo.removeAllItems();
                for (DateTime begin = query.getBeginDate(); !begin.isAfter(query.getEndDate()); begin = begin.plusDays(1)) {
                    reportRange.add(begin);
                    hourlyDateCombo.addItem(begin.toString("dd/MM/yyyy"));
                }

                hourlyDateCombo.setEnabled(true);
                dailyPanel.removeAll();
                monthlyPanel.removeAll();
                itemsPanel.removeAll();
                dailyPanel.add(createChartPanel(ChartBuilder.dailyChart(report, dialog.getBeginDate(), dialog.getEndDate())), BorderLayout.CENTER);
                monthlyPanel.add(createChartPanel(ChartBuilder.monthlyChart(report, dialog.getBeginDate(), dialog.getEndDate())), BorderLayout.CENTER);
                itemsPanel.add(createChartPanel(ChartBuilder.menulyChart(report)), BorderLayout.CENTER);

            } catch (IllegalArgumentException ex) {
                UiToolKits.showExceptionDialog(this, "Invalid request", ex);
            } catch (UnknownSessionException ue) {
                UiToolKits.showErrorDialog(this, "Access Denied", "You are not logged in or the session has expired.");
                System.exit(0);
            } catch (Exception ex) {
                UiToolKits.showExceptionDialog(this, "Unexpected exception", ex);
            }
        }
    }

    private void hawkerQuery() {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        HawkerReportDialog dialog = new HawkerReportDialog(topFrame, true);
        dialog.setVisible(true);

        if (dialog.isOkPressed()) {
            try {
                Session session = subject.getSession();
                Stall stall = (Stall) session.getAttribute("Stall");
                ReportQuery query = new ReportQuery(stall.getId(), dialog.getBeginTime(), dialog.getEndTime().withTime(23, 59, 59, 59));
                report = salesService.reportSales(query);

                reportRange.clear();
                hourlyDateCombo.removeAllItems();
                for (DateTime begin = query.getBeginDate(); !begin.isAfter(query.getEndDate()); begin = begin.plusDays(1)) {
                    reportRange.add(begin);
                    hourlyDateCombo.addItem(begin.toString("dd/MM/yyyy"));
                }

                hourlyDateCombo.setEnabled(true);
                dailyPanel.removeAll();
                monthlyPanel.removeAll();
                itemsPanel.removeAll();
                dailyPanel.add(createChartPanel(ChartBuilder.dailyChart(report, dialog.getBeginTime(), dialog.getEndTime())), BorderLayout.CENTER);
                monthlyPanel.add(createChartPanel(ChartBuilder.monthlyChart(report, dialog.getBeginTime(), dialog.getEndTime())), BorderLayout.CENTER);
                itemsPanel.add(createChartPanel(ChartBuilder.menulyChart(report)), BorderLayout.CENTER);

            } catch (IllegalArgumentException ex) {
            } catch (UnknownSessionException ue) {
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new com.alee.laf.button.WebButton();
        generateButton = new com.alee.laf.button.WebButton();
        tabbedPane = new com.alee.laf.tabbedpane.WebTabbedPane();
        webPanel1 = new com.alee.laf.panel.WebPanel();
        webLabel1 = new com.alee.laf.label.WebLabel();
        hourlyDateCombo = new com.alee.laf.combobox.WebComboBox();
        hourlyPanel = new com.alee.laf.panel.WebPanel();
        dailyPanel = new com.alee.laf.panel.WebPanel();
        monthlyPanel = new com.alee.laf.panel.WebPanel();
        itemsPanel = new com.alee.laf.panel.WebPanel();

        backButton.setText("Back");

        generateButton.setText("Generate");
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });

        tabbedPane.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        webLabel1.setText("Date");

        hourlyDateCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hourlyDateComboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout webPanel1Layout = new javax.swing.GroupLayout(webPanel1);
        webPanel1.setLayout(webPanel1Layout);
        webPanel1Layout.setHorizontalGroup(
            webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(webPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(webLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(hourlyDateCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(728, Short.MAX_VALUE))
            .addComponent(hourlyPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        webPanel1Layout.setVerticalGroup(
            webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(webPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(webLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hourlyDateCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hourlyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Hourly", webPanel1);
        tabbedPane.addTab("Daily", dailyPanel);
        tabbedPane.addTab("Monthly", monthlyPanel);
        tabbedPane.addTab("Items", itemsPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(generateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(generateButton, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        if (!adminStyle) {
            hawkerQuery();
        } else {
            adminQuery();
        }
    }//GEN-LAST:event_generateButtonActionPerformed

    private void hourlyDateComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hourlyDateComboActionPerformed
        int selectIndex = hourlyDateCombo.getSelectedIndex();
        if (report != null && selectIndex != -1) {
            DateTime dateTime = reportRange.get(selectIndex);
            hourlyPanel.removeAll();
            hourlyPanel.add(createChartPanel(ChartBuilder.hourlyChart(report, dateTime.toLocalDate())), BorderLayout.CENTER);
            hourlyPanel.validate();
        }
    }//GEN-LAST:event_hourlyDateComboActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public com.alee.laf.button.WebButton backButton;
    private com.alee.laf.panel.WebPanel dailyPanel;
    private com.alee.laf.button.WebButton generateButton;
    private com.alee.laf.combobox.WebComboBox hourlyDateCombo;
    private com.alee.laf.panel.WebPanel hourlyPanel;
    private com.alee.laf.panel.WebPanel itemsPanel;
    private com.alee.laf.panel.WebPanel monthlyPanel;
    private com.alee.laf.tabbedpane.WebTabbedPane tabbedPane;
    private com.alee.laf.label.WebLabel webLabel1;
    private com.alee.laf.panel.WebPanel webPanel1;
    // End of variables declaration//GEN-END:variables
}
