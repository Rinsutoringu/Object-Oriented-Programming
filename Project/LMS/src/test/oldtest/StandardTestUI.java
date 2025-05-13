package test.oldtest;

import javax.swing.SwingUtilities;

import local.error.GUIActionFailed;
import local.ui.StandardUILogical;
import local.ui.mainwindow.MainWindowUI;

public abstract class StandardTestUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // 创建子类实例
                StandardTestUI testUI = createTestInstance();
                MainWindowUI lms = new MainWindowUI();
                lms.addPanel(testUI.getUIInstance());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error starting UI", e);
            }
        });
    }

    /**
     * 子类需要实现此方法，返回具体的 UI 实例
     */
    protected abstract StandardUILogical getUIInstance() throws GUIActionFailed;

    /**
     * 子类需要实现此方法，用于创建自身实例
     */
    protected static StandardTestUI createTestInstance() {
        throw new UnsupportedOperationException("Please override createTestInstance() in the subclass");
    }
}