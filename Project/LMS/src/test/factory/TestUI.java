package test.factory;

import javax.swing.SwingUtilities;

import local.ui.mainwindow.MainWindowUI;
import local.ui.StandardUI;

public class TestUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            if (args.length == 0) {
                System.err.println("Please specify a module to test .");
                System.err.println("Use this format");
                System.err.println("java -test.factory.TestModule <GUIPageName>+<Logic/UI>");
                return;
            }

            String moduleName = args[0];
            MainWindowUI lms = new MainWindowUI();

            try {
                // 根据命令行参数选择模块
                StandardUI module = UIModuleFactory.createModule(moduleName);
                // 获取JPanel本体，然后在窗口展示
                if (module!= null) lms.addPanel(module.getThis());
                
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        });
    }
}