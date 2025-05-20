import javax.swing.SwingUtilities;

import local.ui.mainwindow.*;
import standard.GlobalVariables;
import local.ui.font.FontUtil;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindowLogic();

            }
        });
    }
}