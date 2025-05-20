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
            // 设置全局字体
            FontUtil.setGlobalFont("/resources/fonts/JetBrainsMono-Bold.ttf", 13f);
            GlobalVariables.getInstance();


            // src\test\factory\TestOption.java
            // src\resources\fonts\JetBrainsMono-Bold.ttf

            String moduleName = args[0];

            try {
                
                // mainwindow是主窗口，不能被加载,直接跳过接下来的代码即可
                if (moduleName.equals("panel-mainwindow")) return;

                // 尝试以UI模式获取模块
                StandardUI UIModule = UIModuleFactory.createModule(moduleName);
                // 找到了就直接返回，没找到就继续找
                if (UIModule != null) {
                    new MainWindowUI().addPanel(UIModule.getThis());
                    return;
                    }

                // 尝试以Logic模式获取模块
                MiniOption MiniUIModule = UIModuleFactory.createMiniOption(moduleName);
                if (MiniUIModule != null) return;

                // 获取JPanel本体，然后在窗口展示
                

            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        });
    }
}