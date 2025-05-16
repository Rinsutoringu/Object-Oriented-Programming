package local.ui.example;

import java.awt.*;

import javax.swing.*;

import standard.StandardUI;

public class exampleUI extends StandardUI {

    public exampleUI() {
        super();

        // 初始化组件,按需调用错误处理器
        init_examplewindow();

        // 组件添加到面板
        utils.addComponent(this, getPanel("examplewindow"), gbc, 1, 2);
    }
    // 面板搞定

    // 这是一个示范组件
    private void init_examplewindow() {
        JPanel panel = new JPanel();
        // 设置layout manager
        // panel.setPreferredSize(new Dimension(0, 9));
        panel.setLayout(new GridBagLayout()); 

        // 创建界面上需要的组件
        putButton("example", new JButton("Example"));
        putButton("example2", new JButton("Example2"));

        putCheckBox("example3", new JCheckBox("Example3"));
        putCheckBox("example4", new JCheckBox("Example4"));

        // 设置组件到面板
        utils.addComponent(panel, getButton("example"), gbc, 1, 1);

        utils.addComponent(panel, getButton("example2"), gbc, 2, 1, 1, 1,
        GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, getCheckBox("example3"), gbc, 1, 2);

        utils.addComponent(panel, getCheckBox("example4"), gbc, 2, 2, 1, 1,
        GridBagConstraints.NONE, 1, 1);


        // 注册面板
        panels.put("examplewindow", panel);
    }


    @Override
    public exampleUI getThis() {
        return this;
    }
}
