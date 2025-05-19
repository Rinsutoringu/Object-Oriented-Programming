package local.ui.homepage.subpage.overview;

import java.awt.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import standard.StandardUI;

public class OverviewUI extends StandardUI {

    private DefaultTableModel tableModel;

    private JTable table;

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
        createSortControls();
        

        short gheight = 0;

        utils.addComponent(panel, getPanel("tableCP"), gbc, gmiddle, ++gheight, 1, 2, 
            GridBagConstraints.BOTH, 1, 1);
        
        utils.addComponent(panel, getPanel("sortcontrol"), gbc, gmiddle, ++gheight, 1, 0.05,
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

        panel.setPreferredSize(new Dimension(1000, 1000));

        // 创建表头
        Vector<String> columnNames = new Vector<String>();
        columnNames.add("Item Name");
        columnNames.add("Number");
        columnNames.add("Last Updated");
        columnNames.add("Update User");

        // 初始化表格模型，行数为0
        tableModel = new DefaultTableModel(columnNames, 0);
        
        // 创建表格实体并应用表格模型
        table = new JTable(tableModel);

        // 允许表格上下拖拽
        JScrollPane scrollPane = new JScrollPane(table);

        utils.addComponent(panel, scrollPane, gbc, 0, 1, 1, 1,
                GridBagConstraints.BOTH, 1, 1);

        putPanel("tableCP", panel);
    }

    /**
     * 排序控件
     */
    private void createSortControls() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        panel.setPreferredSize(new Dimension(1000, 10));

        this.putButton("refresh", new JButton("Refresh"));


        this.putButton("sortNameAsc", new JButton("Sort Name Asc"));
        this.putButton("sortNameDesc", new JButton("Sort Name Desc"));
        this.putButton("sortNumberAsc", new JButton("Sort Number Asc"));
        this.putButton("sortNumberDesc", new JButton("Sort Number Desc"));

        // 设置组件到面板
        short gheight = 0;

        utils.addComponent(panel, getButton("sortNameAsc"), gbc, gmiddle, gheight, 1, 1, 
            GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, getButton("sortNameDesc"), gbc, gmiddle+1, gheight, 1, 1, 
            GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, getButton("sortNumberAsc"), gbc, gmiddle+2, gheight, 1, 1, 
            GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, getButton("sortNumberDesc"), gbc, gmiddle+3, gheight, 1, 1, 
            GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, getButton("refresh"), gbc, gmiddle, ++gheight, 1, 1,
        GridBagConstraints.NONE, 4, 1);

        putPanel("sortcontrol", panel);
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTable() {
        return table;
    }

    @Override
    public OverviewUI getThis() {
        return this;
    }
}
