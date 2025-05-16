package local.ui.homepage.subpage.overview;

import java.awt.*;

import javax.swing.*;

import standard.StandardUI;

public class OverviewUI extends StandardUI {

    public OverviewUI() {
        super();

        // 初始化组件,按需调用错误处理器
        init_overview();

        // 组件添加到面板
        utils.addComponent(this, getPanel("overview"), gbc, 1, 2);
    }
    // 面板搞定

    // 这是一个示范组件
    private void init_overview() {
        JPanel panel = new JPanel();
        // 设置layout manager
        // panel.setPreferredSize(new Dimension(0, 9));
        panel.setLayout(new GridBagLayout()); 

        // 创建界面上需要的组件
        buttons.put("example", new JButton("Example"));
        buttons.put("example2", new JButton("Example2"));

        checkBoxs.put("example3", new JCheckBox("Example3"));
        checkBoxs.put("example4", new JCheckBox("Example4"));

        // 设置组件到面板
        utils.addComponent(panel, buttons.get("example"), gbc, 1, 1);

        utils.addComponent(panel, buttons.get("example2"), gbc, 2, 1, 1, 1,
        GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, checkBoxs.get("example3"), gbc, 1, 2);

        utils.addComponent(panel, checkBoxs.get("example4"), gbc, 2, 2, 1, 1,
        GridBagConstraints.NONE, 1, 1);


        // 注册面板
        panels.put("overview", panel);
    }


    @Override
    public OverviewUI getThis() {
        return this;
    }
}
