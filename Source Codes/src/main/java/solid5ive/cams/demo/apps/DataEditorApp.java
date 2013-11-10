/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solid5ive.cams.demo.apps;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import solid5ive.cams.configuration.SpringConfiguration;
import solid5ive.cams.demo.ui.DataEditorFrame;

/**
 *
 * @author bernard
 */
public class DataEditorApp {
    public static void main(String... args) {
        AnnotationConfigApplicationContext ctx = 
                new AnnotationConfigApplicationContext(SpringConfiguration.class);
        DataEditorFrame frame = ctx.getBean(DataEditorFrame.class);
        frame.loadData();
        frame.setVisible(true);
    }
}
