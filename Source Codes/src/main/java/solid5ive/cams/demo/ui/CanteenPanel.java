/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solid5ive.cams.demo.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import solid5ive.cams.data.entities.Canteen;
import solid5ive.cams.data.entities.Menu;
import solid5ive.cams.data.entities.Stall;
import solid5ive.cams.data.entities.User;
import solid5ive.cams.demo.model.StallTableModel;
import solid5ive.cams.service.contracts.InfrastructureService;
import solid5ive.cams.service.contracts.MenuService;
import solid5ive.cams.service.contracts.UserService;

/**
 *
 * @author Bernard
 */
@Component
@Scope("prototype")
public class CanteenPanel extends javax.swing.JPanel {

    private Subject subject;
    private StallTableModel stallModel;
    private DefaultComboBoxModel<Canteen> canteenModel;
    private List<User> userList;
    @Autowired
    private InfrastructureService infService;
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    /**
     * Creates new form CanteenPanel
     */
    public CanteenPanel() {
        initComponents();
        stallModel = new StallTableModel();
        stallTable.setModel(stallModel);
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
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
        editButton = new com.alee.laf.button.WebButton();
        viewButton = new com.alee.laf.button.WebButton();
        webLabel1 = new com.alee.laf.label.WebLabel();
        canteenCombo = new com.alee.laf.combobox.WebComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        stallTable = new com.alee.laf.table.WebTable();

        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
        });

        backButton.setText("back");

        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        viewButton.setText("View");
        viewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewButtonActionPerformed(evt);
            }
        });

        webLabel1.setText("Canteen");

        canteenCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                canteenComboActionPerformed(evt);
            }
        });

        stallTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        stallTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stallTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(stallTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 652, Short.MAX_VALUE)
                                .addComponent(viewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(webLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(canteenCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(webLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(canteenCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void canteenComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_canteenComboActionPerformed
        int index = canteenCombo.getSelectedIndex();
        try {
            Canteen canteen = canteenModel.getElementAt(index);
            List<Stall> stalls = infService.listStalls(canteen.getId());
            stallModel.set(stalls);
        } catch (IllegalArgumentException ex) {
            UiToolKits.showExceptionDialog(this, "Invalid request", ex);
        } catch (UnknownSessionException ue) {
            UiToolKits.showErrorDialog(this, "Access Denied", "You are not logged in or the session has expired.");
            System.exit(0);
        } catch (Exception ex) {
            UiToolKits.showExceptionDialog(this, "Unexpected exception", ex);
        }
    }//GEN-LAST:event_canteenComboActionPerformed

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        try {
            List<Canteen> canteens = infService.listCanteens();
            canteenModel = new DefaultComboBoxModel<>(canteens.toArray(new Canteen[0]));
            canteenCombo.setModel(canteenModel);
            if (canteens.size() > 0) {
                canteenCombo.setSelectedIndex(0);
            }
        } catch (SecurityException se) {
            UiToolKits.showErrorDialog(
                    this,
                    "Access Denied",
                    "You are not authorised as an administrator.\n"
                    + "Please consult the department if this shouldn't be the case.");
            System.exit(0);
        } catch (UnknownSessionException ue) {
            UiToolKits.showErrorDialog(
                    this,
                    "Access Denied",
                    "You are not logged in or the session is expired.");
            System.exit(0);
        } catch (Exception ex) {
            UiToolKits.showExceptionDialog(this, "Unexpected exception", ex);
            System.exit(0);
        }
    }//GEN-LAST:event_formAncestorAdded

    private void viewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewButtonActionPerformed
        int index = stallTable.getSelectedRow();
        if (index != -1) {
            try {
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                Stall stall = stallModel.get(index);
                StallViewDialog dialog = new StallViewDialog(topFrame, false);
                UiToolKits.centerToScreen(dialog);
                dialog.setStall(stall);

                User owner = stall.getOwner();
                if (owner != null) {
                    dialog.setOwnerContacts(userService.listUserContacts(owner.getLoginName()));
                }

                List<Menu> menus = menuService.listMenus(stall.getId());
                dialog.setStallMenu(menus);

                dialog.setVisible(true);

            } catch (IllegalArgumentException ex) {
                UiToolKits.showExceptionDialog(this, "Invalid request", ex);
            } catch (UnknownSessionException ue) {
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                UiToolKits.showErrorDialog(
                        this,
                        "Access Denied",
                        "You are not logged in or the session is expired.");
                System.exit(0);
            } catch (Exception ex) {
                UiToolKits.showExceptionDialog(this, "Unexpected exception", ex);
            }
        }
    }//GEN-LAST:event_viewButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        int selectIndex = stallTable.getSelectedRow();
        if (selectIndex != -1) {
            try {
                if (userList == null) {
                    userList = userService.listUsers();
                }
                Stall stall = new Stall(stallModel.get(selectIndex));
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                StallEditDialog dialog = new StallEditDialog(topFrame, true);
                dialog.setUsers(userList);
                dialog.setStall(stall);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                dialog.setLocation(dim.width / 2 - dialog.getSize().width / 2, dim.height / 2 - dialog.getSize().height / 2);
                dialog.setVisible(true);

                if (dialog.isOkPressed()) {
                    String ownerLogin = (stall.getOwner() == null) ? null : stall.getOwner().getLoginName();
                    infService.updateStall(stall.getId(), ownerLogin, stall.getName(), stall.getFoodStyle());
                    Stall modified = stallModel.get(selectIndex);
                    modified.setName(stall.getName());
                    modified.setFoodStyle(stall.getFoodStyle());
                    modified.setOwner(stall.getOwner());
                    stallModel.refresh(selectIndex);
                }
            } catch (IllegalArgumentException ex) {
                UiToolKits.showExceptionDialog(this, "Invalid request", ex);
            } catch (UnknownSessionException ue) {
                UiToolKits.showErrorDialog(
                        this,
                        "Access Denied",
                        "You are not logged in or the session is expired.");
                System.exit(0);
            } catch (Exception ex) {
                UiToolKits.showExceptionDialog(this, "Unexpected exception", ex);
            }
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void stallTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stallTableMouseClicked
        if (evt.getClickCount() >= 2 && stallTable.getSelectedRow() != -1) {
            viewButtonActionPerformed(null);
        }
    }//GEN-LAST:event_stallTableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public com.alee.laf.button.WebButton backButton;
    private com.alee.laf.combobox.WebComboBox canteenCombo;
    private com.alee.laf.button.WebButton editButton;
    private javax.swing.JScrollPane jScrollPane1;
    private com.alee.laf.table.WebTable stallTable;
    private com.alee.laf.button.WebButton viewButton;
    private com.alee.laf.label.WebLabel webLabel1;
    // End of variables declaration//GEN-END:variables
}
