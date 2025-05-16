package local.ui.homepage.subpage.overview;

import java.awt.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import standard.StandardUI;

public class OverviewUI extends StandardUI {

    public OverviewUI() {
        super();

        createTable();

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

        

        // 设置组件到面板

        utils.addComponent(panel, getPanel("table"), gbc, 0, 3, 2, 1,
                GridBagConstraints.BOTH, 1, 1);

        utils.addComponent(panel, buttons.get("example"), gbc, 1, 1);

        utils.addComponent(panel, buttons.get("example2"), gbc, 2, 1, 1, 1,
        GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, checkBoxs.get("example3"), gbc, 1, 2);

        utils.addComponent(panel, checkBoxs.get("example4"), gbc, 2, 2, 1, 1,
        GridBagConstraints.NONE, 1, 1);


        // 注册面板
        panels.put("overview", panel);
    }

    private void createTable() {
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        Vector<String> columnNames = new Vector<String>();
        columnNames.add("Item Name");
        columnNames.add("Number");
        columnNames.add("Last Updated");
        columnNames.add("Update User");

        // 创建表格模型
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // 创建表格
        JTable table = new JTable(tableModel);

        // 将表格添加到面板
        JScrollPane scrollPane = new JScrollPane(table);
        utils.addComponent(panel, scrollPane, gbc, 0, 3, 2, 1,
                GridBagConstraints.BOTH, 1, 1);

        putPanel("table", panel);

    }


    @Override
    public OverviewUI getThis() {
        return this;
    }
}
