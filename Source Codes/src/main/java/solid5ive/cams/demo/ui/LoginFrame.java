/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solid5ive.cams.demo.ui;

import com.alee.laf.WebLookAndFeel;
import java.awt.Color;
import java.awt.Font;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import solid5ive.cams.demo.apps.DemoApp;
import solid5ive.cams.security.SecurityRuntime;

/**
 *
 * @author Bernard
 */
@Component
@Scope("prototype")
public class LoginFrame extends javax.swing.JFrame {

    @Autowired
    private SecurityRuntime securityRuntime;
    
    /**
     * Creates new form LoginFrame
     */
    public LoginFrame() {
        WebLookAndFeel.initializeManagers();
        WebLookAndFeel.install(true);
        initComponents();
        UiToolKits.centerToScreen(this);
        uniLabel.setDrawShade(true);
        uniLabel.setShadeColor(Color.yellow);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usernameField = new com.alee.laf.text.WebTextField();
        passwordField = new com.alee.laf.text.WebPasswordField();
        webButton1 = new com.alee.laf.button.WebButton();
        webLabel1 = new com.alee.laf.label.WebLabel();
        webLabel2 = new com.alee.laf.label.WebLabel();
        uniLabel = new com.alee.laf.label.WebLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CaMS Login");

        usernameField.setToolTipText("Enter your username for sign in here");
        usernameField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        usernameField.setHideInputPromptOnFocus(false);
        usernameField.setInputPrompt("Username");
        usernameField.setInputPromptFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        passwordField.setToolTipText("Key in your password here");
        passwordField.setEchoChar('●');
        passwordField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        passwordField.setInputPrompt("Password");
        passwordField.setInputPromptFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });

        webButton1.setText("Sign in");
        webButton1.setFocusable(false);
        webButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webButton1ActionPerformed(evt);
            }
        });

        webLabel1.setText("Hello?");
        webLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        webLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo.png"))); // NOI18N

        uniLabel.setForeground(new java.awt.Color(255, 255, 255));
        uniLabel.setText("Beitu University");
        uniLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(679, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(webLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usernameField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(webButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(webLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uniLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(616, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(webLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(uniLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 343, Short.MAX_VALUE)
                .addComponent(webLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(webButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void webButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webButton1ActionPerformed
        String username = usernameField.getText();
        String[] splited = username.split("@");
        try {
            if(splited.length == 2) {
                
                Subject subject = securityRuntime.newSubject();
                subject.login(new UsernamePasswordToken(splited[0], passwordField.getText()));
                securityRuntime.bindSubject(subject);
                switch (splited[1]) {
                    case "stall": {
                        setVisible(false);
                        HawkerMainFrame mainFrame = DemoApp.getContext().getBean(HawkerMainFrame.class);
                        mainFrame.setSubject(subject);
                        mainFrame.setVisible(true);
                        return; 
                    }
                    case "admin": {
                        setVisible(false);
                        AdminMainFrame mainFrame = DemoApp.getContext().getBean(AdminMainFrame.class);
                        mainFrame.setSubject(subject);
                        mainFrame.setVisible(true);
                        return;
                    }
                    default:
                        UiToolKits.showErrorDialog(this, "Unable to sign in", "Unknown domain.");
                }
            } else {
                UiToolKits.showErrorDialog(this, "Unable to sign in", "Incorrect username or password.");
            }
        } catch(Exception ex) {
            UiToolKits.showErrorDialog(this, "Unable to sign in", "Incorrect username or password.");
        }
    }//GEN-LAST:event_webButton1ActionPerformed

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        webButton1ActionPerformed(null);
    }//GEN-LAST:event_passwordFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.text.WebPasswordField passwordField;
    private com.alee.laf.label.WebLabel uniLabel;
    private com.alee.laf.text.WebTextField usernameField;
    private com.alee.laf.button.WebButton webButton1;
    private com.alee.laf.label.WebLabel webLabel1;
    private com.alee.laf.label.WebLabel webLabel2;
    // End of variables declaration//GEN-END:variables
}