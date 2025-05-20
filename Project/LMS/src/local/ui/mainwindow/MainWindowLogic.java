package local.ui.mainwindow;

import local.ui.login.LoginLogic;
import local.utils.UIUtils;
import database.db.DataBase;
import laboratory.lab.workers.User;
import local.ui.font.FontUtil;
import local.ui.homepage.HomePageLogic;
import standard.GlobalVariables;

public class MainWindowLogic {

    private LoginLogic login;
    private HomePageLogic homepagelogic;
    private MainWindowUI mainWindowUI;

    public MainWindowLogic() {

        // 初始化全局变量
        GlobalVariables.getInstance();
        // 初始化工具类
        UIUtils.getInstance();
        // 初始化数据库
        DataBase.getInstance();
        // 初始化字体
        FontUtil.setGlobalFont("/resources/fonts/JetBrainsMono-Bold.ttf", 13f);
        // 初始化本程序所有界面
        login = LoginLogic.getInstance();
        homepagelogic = HomePageLogic.getInstance();
        mainWindowUI = MainWindowUI.getInstance();

        // 显示登录页面
        showLoginPage();
    }

    private void showLoginPage() {
        mainWindowUI.addPanel(login.getThis());
        login.setLoginCallback(this::onLoginSuccess);
    }

    private void showHomePage() {
        mainWindowUI.addPanel(homepagelogic.getThis());
    }

    // 登录成功后的回调
    private void onLoginSuccess(String username) {
        GlobalVariables.setUserName(username);
        showHomePage();
        homepagelogic.setStockButtonVisibility(User.isAdmin(username));
    }
}