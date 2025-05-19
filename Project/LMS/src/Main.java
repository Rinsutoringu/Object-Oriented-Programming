import javax.swing.SwingUtilities;

import local.ui.mainwindow.*;
import local.ui.font.FontUtil;
import local.ui.homepage.HomePageLogic;
import local.ui.login.LoginLogic;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FontUtil.setGlobalFont("/fonts/myfont.ttf", 24f);
                MainWindowUI mainWindow = new MainWindowUI();
                LoginLogic loginLogic = new LoginLogic();
                HomePageLogic homePageLogic = new HomePageLogic();

                // 创建主窗口
                
                // 设置窗口可见
                mainWindow.setVisible(true);
                mainWindow.addPanel(loginLogic.getThis());

            }
        });
    }
}