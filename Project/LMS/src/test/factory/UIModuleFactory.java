package test.factory;

import local.ui.StandardUI;
import local.ui.homepage.HomePageLogic;
import local.ui.homepage.HomePageUI;
import local.ui.login.LoginLogic;
import local.ui.login.LoginUI;
import local.utils.MiniOption;

public class UIModuleFactory {

    public static StandardUI createModule(String moduleName) {
        switch (moduleName.toLowerCase()) {
            case "panel-homepage-logic":
                return new HomePageLogic();
            case "panel-homepage-ui":
                return new HomePageUI();
            case "panel-loginpage-logic":
                return new LoginLogic();
            case "panel-loginpage-ui":
                return new LoginUI();
            case "panel-login":
                return new LoginLogic();
            default:
                return null;
        }
    }

    public static MiniOption createMiniOption(String moduleName) {

        switch (moduleName.toLowerCase()) {
            case "mini-error":
                return new MiniOption("error", "This is an error module", 0);
            case "mini-information":
                return new MiniOption("information", "This is an information module", 1);
            case "mini-warning":
                return new MiniOption("warning", "This is a warning module", 2);
            case "mini-question":
                return new MiniOption("question", "This is a question module", 3);
            case "mini-plain":
                return new MiniOption("plain", "This is a plain module", -1);
            default:
                return null;
        }
    }
}

            // default:
            //     return new MiniOption("Unknown", "This is an unknown module");