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
        init_overview();
        utils.addComponent(this, getPanel("competence"), gbc, 1, 2);
    }

    private void init_overview() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        createTableCP();
        putButton("refresh", new JButton("Refresh"));

        short gheight = 0;

        utils.addComponent(panel, getPanel("tableCP"), gbc, gmiddle, ++gheight, 1, 2,
            GridBagConstraints.BOTH, 1, 1);

        utils.addComponent(panel, getButton("refresh"), gbc, gmiddle, ++gheight, 1, 0.05,
            GridBagConstraints.NONE, 1, 1);

        panels.put("competence", panel);
    }

    private void createTableCP() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(1000, 1000));

        Vector<String> columnNames = new Vector<>();
        columnNames.add("Username");
        columnNames.add("Registration Date");
        columnNames.add("Permission Level");

        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

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