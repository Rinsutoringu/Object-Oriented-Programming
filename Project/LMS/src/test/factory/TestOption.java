package test.factory;

import javax.swing.SwingUtilities;

import local.ui.font.FontUtil;
import local.ui.mainwindow.MainWindowUI;
import local.ui.miniwindow.MiniOption;
import standard.GlobalVariables;
import standard.StandardUI;

public class TestOption {

    public static void main(String[] args) {
        SwingUtilities.invokeLater((Runnable) () -> {
            if (args.length == 0) {
                System.err.println("Please specify a module to test .");
                System.err.println("Use this format");
                System.err.println("java -test.factory.TestOption <GUIPageName>+<Logic/UI>");
                return;
            }
            FontUtil.setGlobalFont("/resources/fonts/JetBrainsMono-Bold.ttf", 13f);
            GlobalVariables.getInstance();

            String moduleName = args[0];

            try {
                
                if (moduleName.equals("panel-mainwindow")) return;

                StandardUI UIModule = UIModuleFactory.createModule(moduleName);
                if (UIModule != null) {
                    MainWindowUI.getInstance().addPanel(UIModule.getThis());
                    return;
                    }

                MiniOption MiniUIModule = UIModuleFactory.createMiniOption(moduleName);
                if (MiniUIModule != null) return;

                

            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        });
    }
}