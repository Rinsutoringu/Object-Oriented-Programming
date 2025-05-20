package local.ui.mainwindow;

import local.ui.login.LoginLogic;
import local.utils.UIUtils;
import database.db.DataBase;
import laboratory.lab.workers.User;
import local.style.ButtonStyle;
import local.ui.font.FontUtil;
import local.ui.homepage.HomePageLogic;
import standard.GlobalVariables;

public class MainWindowLogic {

    private LoginLogic login;
    private HomePageLogic homepagelogic;
    private MainWindowUI mainWindowUI;

    public MainWindowLogic() {

        ButtonStyle.applyGlobalButtonStyle();

        GlobalVariables.getInstance();
        UIUtils.getInstance();
        DataBase.getInstance();
        FontUtil.setGlobalFont("/resources/fonts/JetBrainsMono-Bold.ttf", 13f);
        login = LoginLogic.getInstance();

        homepagelogic = HomePageLogic.getInstance();
        mainWindowUI = MainWindowUI.getInstance();

        showLoginPage();
    }

    private void showLoginPage() {
        mainWindowUI.addPanel(login.getThis());
        login.setLoginCallback(this::onLoginSuccess);
    }

    private void showHomePage() {
        mainWindowUI.addPanel(homepagelogic.getThis());
    }

    private void onLoginSuccess(String username) {
        GlobalVariables.setUserName(username);
        showHomePage();
        homepagelogic.setStockButtonVisibility(User.isAdmin(username));
        homepagelogic.getThis().getButton("operation").doClick();
    }
}