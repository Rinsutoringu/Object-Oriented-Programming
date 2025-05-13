package test.factory;

import local.ui.StandardUI;
import local.ui.homepage.HomePageLogic;
import local.ui.homepage.HomePageUI;
import local.ui.login.LoginLogic;
import local.ui.login.LoginUI;

public class ModuleFactory {

    public static StandardUI createModule(String moduleName) {
        switch (moduleName.toLowerCase()) {
            case "homepage-logic":
                    return new HomePageLogic();
            case "homepage-ui":
                    return new HomePageUI();
            case "loginpage-logic":
                    return new LoginLogic();
            case "loginpage-ui":
                    return new LoginUI();
            case "login":
                return new LoginLogic();
            case "mainwindow":
                return null;
            default:
                throw new IllegalArgumentException("Unknown module: " + moduleName);
        }
    }
}