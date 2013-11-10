/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solid5ive.cams.demo.ui;

import com.alee.laf.optionpane.WebOptionPane;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class UiToolKits {

    public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = UiToolKits.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, "");
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static void centerToScreen(JDialog component) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        component.setLocation(dim.width / 2 - component.getSize().width / 2, dim.height / 2 - component.getSize().height / 2);
    }

    public static void centerToScreen(JFrame component) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        component.setLocation(dim.width / 2 - component.getSize().width / 2, dim.height / 2 - component.getSize().height / 2);
    }

    public static void showExceptionDialog(JFrame frame, String title, Exception ex) {
        String cause = ex.getMessage();
        if (cause.length() > 255) {
            cause = cause.substring(0, 253) + "...";
        }

        WebOptionPane.showMessageDialog(
                frame,
                "Error occurred, Please try again.\n"
                + "Contact administrator if it caused any hardship to your life.\n"
                + "Cause:\n"
                + cause,
                title,
                WebOptionPane.ERROR_MESSAGE);
    }

    public static void showExceptionDialog(JPanel panel, String title, Exception ex) {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
        String cause = ex.getMessage();
        if (cause.length() > 255) {
            cause = cause.substring(0, 253) + "...";
        }

        WebOptionPane.showMessageDialog(
                topFrame,
                "Error occurred, Please try again.\n"
                + "Contact administrator if it caused any hardship to your life.\n"
                + "Cause:\n"
                + cause,
                title,
                WebOptionPane.ERROR_MESSAGE);
    }

    public static void showExceptionDialog(JDialog dialog, String title, Exception ex) {
        String cause = ex.getMessage();
        if (cause.length() > 255) {
            cause = cause.substring(0, 253) + "...";
        }

        WebOptionPane.showMessageDialog(
                dialog,
                "Error occurred, Please try again.\n"
                + "Contact administrator if it caused any hardship to your life.\n"
                + "Cause:\n"
                + cause,
                title,
                WebOptionPane.ERROR_MESSAGE);
    }

    public static void showErrorDialog(JDialog frame, String title, String message) {
        WebOptionPane.showMessageDialog(frame, message, title, WebOptionPane.ERROR_MESSAGE);
    }

    public static void showErrorDialog(JFrame frame, String title, String message) {
        WebOptionPane.showMessageDialog(frame, message, title, WebOptionPane.ERROR_MESSAGE);
    }

    public static void showErrorDialog(JPanel panel, String title, String message) {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
        WebOptionPane.showMessageDialog(topFrame, message, title, WebOptionPane.ERROR_MESSAGE);
    }
}
