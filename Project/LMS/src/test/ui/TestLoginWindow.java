package test.ui;

import javax.swing.SwingUtilities;

import local.ui.mainwindow.*;
import local.ui.login.*;

public class TestLoginWindow {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
            new Runnable() {
                @Override
                public void run() {
                    MainWindowUI lms = new MainWindowUI();
                    lms.addPanel(new LoginUI());
                    
                }
            }
        );
    }
}