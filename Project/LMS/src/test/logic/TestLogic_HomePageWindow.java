package test.logic;

import javax.swing.SwingUtilities;

import local.ui.mainwindow.*;
import local.ui.homepage.HomePageLogic;

public class TestLogic_HomePageWindow {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
            new Runnable() {
                @Override
                public void run() {
                    MainWindowUI lms = new MainWindowUI();
                    lms.addPanel(new HomePageLogic().getHomePage());
                }
            }
        );
    }
}