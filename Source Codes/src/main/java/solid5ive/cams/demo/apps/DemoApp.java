/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solid5ive.cams.demo.apps;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import solid5ive.cams.configuration.SpringConfiguration;
import solid5ive.cams.demo.ui.LoginFrame;

/**
 *
 * @author bernard
 */
public class DemoApp {
        
    private static ApplicationContext applicationContext;
    
    public static ApplicationContext getContext() {
        return applicationContext;
    }
    
    public static void main(String... args) {
        final AnnotationConfigApplicationContext ctx = 
                new AnnotationConfigApplicationContext(SpringConfiguration.class);
        applicationContext = ctx;
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginFrame loginFrame = ctx.getBean(LoginFrame.class);
                loginFrame.setVisible(true);
            }
        });
    }
}
