package local.ui.example;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import standard.StandardUI;
import local.utils.UIUtils;

public class exampleUI extends StandardUI {

    UIUtils uiUtils = UIUtils.getInstance();
    public exampleUI() {
        super();

        // 初始化组件,按需调用错误处理器
        init_examplewindow();

        // 组件添加到面板
        uiUtils.addComponent(this, buttons.get("example"), gbc, 1, 2);

        // 面板搞定

    }

    // 这是一个示范组件
    private void init_examplewindow() {
        JPanel panel = new JPanel();
        // 设置layout manager
        panel.setPreferredSize(new Dimension(0, 9));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS)); 

        // 创建界面上需要的组件
        buttons.put("example", new JButton("Example"));
        buttons.put("example2", new JButton("Example2"));

        checkBoxs.put("example3", new JCheckBox("Example3"));
        checkBoxs.put("example4", new JCheckBox("Example4"));

        // 设置组件到面板
        uiUtils.addComponent(panel, buttons.get("example"), gbc, 1, 1);

        uiUtils.addComponent(panel, buttons.get("example2"), gbc, 2, 1, 2.5, 0.9,
        GridBagConstraints.NONE, 1, 1);

        uiUtils.addComponent(panel, buttons.get("example"), gbc, 1, 2);

        uiUtils.addComponent(panel, buttons.get("example2"), gbc, 2, 2, 2.5, 0.9,
        GridBagConstraints.NONE, 1, 1);

        // 注册面板
        panels.put("examplewindow", panel);
    }


    @Override
    public exampleUI getThis() {
        return this;
    }
}
