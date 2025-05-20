package local.ui.homepage.subpage.rightbar.overview;

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

        init_overview();

        utils.addComponent(this, getPanel("overview"), gbc, 1, 2);
    }

    private void init_overview() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); 

        createTableCP();
        createSortControls();
        
        short gheight = 0;

        utils.addComponent(panel, getPanel("tableCP"), gbc, gmiddle, ++gheight, 1, 2, 
            GridBagConstraints.BOTH, 1, 1);
        
        utils.addComponent(panel, getPanel("sortcontrol"), gbc, gmiddle, ++gheight, 1, 0.05,
            GridBagConstraints.NONE, 1, 1);

        panels.put("overview", panel);
    }

    private void createTableCP() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        panel.setPreferredSize(new Dimension(1000, 1000));

        Vector<String> columnNames = new Vector<>();
        columnNames.add("Item Name");
        columnNames.add("Number");
        columnNames.add("Last Updated");
        columnNames.add("Update User");

        tableModel = new DefaultTableModel(columnNames, 0);
        
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        utils.addComponent(panel, scrollPane, gbc, 0, 1, 1, 1,
                GridBagConstraints.BOTH, 1, 1);

        putPanel("tableCP", panel);
    }

    private void createSortControls() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        panel.setPreferredSize(new Dimension(1000, 10));

        this.putButton("refresh", new JButton("Refresh"));

        this.putButton("sortNameAsc", new JButton("Sort Name Asc"));
        this.putButton("sortNameDesc", new JButton("Sort Name Desc"));
        this.putButton("sortNumberAsc", new JButton("Sort Number Asc"));
        this.putButton("sortNumberDesc", new JButton("Sort Number Desc"));

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
