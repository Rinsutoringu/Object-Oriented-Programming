package local.ui.homepage.subpage.rightbar.competence;

import java.awt.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import standard.StandardUI;

public class competenceUI extends StandardUI {

    private DefaultTableModel tableModel;
    private JTable table;

    public competenceUI() {
        super();

        // 初始化面板
        init_overview();

        // 组件添加到面板
        utils.addComponent(this, getPanel("competence"), gbc, 1, 2);
    }

    // 主面板
    private void init_overview() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        // 创建界面上需要的组件
        createTableCP();
        putButton("refresh", new JButton("Refresh"));

        short gheight = 0;

        utils.addComponent(panel, getPanel("tableCP"), gbc, gmiddle, ++gheight, 1, 2,
            GridBagConstraints.BOTH, 1, 1);
            
        utils.addComponent(panel, getButton("refresh"), gbc, gmiddle, ++gheight, 1, 1,
            GridBagConstraints.NONE, 1, 1);
        // 注册面板
        panels.put("competence", panel);
    }

    /**
     * 表格组件
     */
    private void createTableCP() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        // 创建表头
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Username");
        columnNames.add("Registration Date");
        columnNames.add("Permission Level");

        // 初始化表格模型，行数为 0
        tableModel = new DefaultTableModel(columnNames, 0);

        // 创建表格实体并应用表格模型
        table = new JTable(tableModel);

        // 允许表格上下拖拽
        JScrollPane scrollPane = new JScrollPane(table);

        utils.addComponent(panel, scrollPane, gbc, 0, 1, 1, 1,
            GridBagConstraints.BOTH, 1, 1);

        putPanel("tableCP", panel);
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTable() {
        return table;
    }

    @Override
    public competenceUI getThis() {
        return this;
    }
}