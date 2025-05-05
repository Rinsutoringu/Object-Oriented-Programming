package test;

import javax.swing.SwingUtilities;

import local.ui.mainwindow.*;

public class TestMainWindow {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
            new Runnable() {
                @Override
                public void run() {
                    new MainWindowUI();
                }
            }
        );
    }
}