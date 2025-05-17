package local.ui.homepage.subpage.overview;

import java.awt.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import standard.StandardUI;

public class OverviewUI extends StandardUI {

    public OverviewUI() {
        super();

        // 初始化面板
        init_overview();

        // 组件添加到面板
        utils.addComponent(this, getPanel("overview"), gbc, 1, 2);
    }

    // 主面板
    private void init_overview() {
        JPanel panel = new JPanel();
        // 设置layout manager
        // panel.setPreferredSize(new Dimension(0, 9));
        panel.setLayout(new GridBagLayout()); 

        // 创建界面上需要的组件
        createTableCP();
        this.putButton("refresh", new JButton("Refresh"));

        // 设置组件到面板
        short gheight = 0;

        utils.addComponent(panel, getPanel("tableCP"), gbc, gmiddle, gheight++, 1, 1, 
            GridBagConstraints.BOTH, 1, 1);
        
        utils.addComponent(panel, getButton("refresh"), gbc, gmiddle, gheight++, 1, 1,
            GridBagConstraints.NONE, 1, 1);


        // 注册面板
        panels.put("overview", panel);
    }

    /**
     * 表格组件
     */
    private void createTableCP() {
        
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

        putPanel("tableCP", panel);

    }


    @Override
    public OverviewUI getThis() {
        return this;
    }
}
