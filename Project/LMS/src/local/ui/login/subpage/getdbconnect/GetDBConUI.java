package local.ui.login.subpage.getdbconnect;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import standard.GlobalVariables;
import standard.StandardUI;
import local.utils.UIUtils;

public class GetDBConUI extends StandardUI{

    UIUtils uiutils = UIUtils.getInstance();
    errorHandler eh = errorHandler.getInstance();
    
    public GetDBConUI() {
        
        super();

        try {
            init_getdbinfo();
            setStyle();
            utils.addComponent(this, this.getPanel("getDBInfo"), gbc, 1, 1);
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }

    }

    @Override
    protected void setStyle() {
        setFontSize(getLabel("dbtooltip"), 25);
        getTextField("dbAddress").setPreferredSize(new java.awt.Dimension(200, 30));
        getTextField("dbPort").setPreferredSize(new java.awt.Dimension(200, 30));
        getTextField("dbUser").setPreferredSize(new java.awt.Dimension(200, 30));
        getTextField("dbPassword").setPreferredSize(new java.awt.Dimension(200, 30));
        getComboBox("dbType").setPreferredSize(new java.awt.Dimension(162, 30));
    }

    /**
     * 提供输入框，让用户输入数据库连接信息
     * 连接信息包括：数据库地址、端口号、用户名、密码
     */
    private void init_getdbinfo() {

        JPanel panel = new JPanel();

        createUserInput();
        buttons.put("close", new javax.swing.JButton("Close"));
        panel.setLayout(new GridBagLayout());

        utils.addComponent(panel, getPanel("userinput"), gbc, 0, 0, 1, 1,
        GridBagConstraints.NONE, 2, 1);

        utils.addComponent(panel, getButton("close"), gbc, 0, 1, 1, 1,
        GridBagConstraints.NONE, 2, 1);
        
        panels.put("getDBInfo", panel);

    }

    private void createDBType() {
        String[] dbTypes = {"MySQL", "PostgreSQL", "SQLite"};
        JComboBox<String> dbTypeCombo = new JComboBox<>(dbTypes);
        comboBoxs.put("dbType", dbTypeCombo);

        dbTypeCombo.addActionListener(e -> {
            String selected = (String) dbTypeCombo.getSelectedItem();
            javax.swing.JTextField dbAddress = getTextField("dbAddress");
            javax.swing.JTextField dbPort = getTextField("dbPort");
            if ("SQLite".equals(selected)) {
                dbAddress.setEnabled(true);
                dbPort.setEnabled(false);
            } else {
                dbAddress.setEnabled(true);
                dbPort.setEnabled(true);
            }
        });
    }

    private void createUserInput() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        createDBType();
        putLabel("dbtooltip",new JLabel("Set DataBase Connection"));
        textFields.put("dbAddress", new javax.swing.JTextField(20));
        textFields.put("dbPort", new javax.swing.JTextField(20));
        textFields.put("dbUser", new javax.swing.JTextField(20));
        textFields.put("dbPassword", new javax.swing.JPasswordField(14));
        buttons.put("connect", new javax.swing.JButton("LINK START"));
        buttons.put("save", new javax.swing.JButton("save"));
        
        int gheight = 0;

        gbc.insets = new Insets(100, 15, 50, 15);
        utils.addComponent(panel, getLabel("dbtooltip"), gbc, gleft, ++gheight, 1, 1,
        GridBagConstraints.NONE, 2, 1);

        gbc.insets = new Insets(5, 10, 5, 10);


        utils.addComponent(panel, new JLabel("Choose a DB type"), gbc, gleft, ++gheight);
        utils.addComponent(panel, getComboBox("dbType"), gbc, gright, gheight);

        utils.addComponent(panel, new JLabel("Database Address:"), gbc, gleft, ++gheight);
        utils.addComponent(panel, getTextField("dbAddress"), gbc, gright, gheight);

        utils.addComponent(panel, new JLabel("Database Port:"), gbc, gleft, ++gheight);
        utils.addComponent(panel, getTextField("dbPort"), gbc, gright, gheight);

        utils.addComponent(panel, new JLabel("Database User:"), gbc, gleft, ++gheight);
        utils.addComponent(panel, getTextField("dbUser"), gbc, gright, gheight);

        utils.addComponent(panel, new JLabel("Database Password:"), gbc, gleft, ++gheight);
        utils.addComponent(panel, getTextField("dbPassword"), gbc, gright, gheight);

        gbc.insets = new Insets(5, 10, 20, 10);

        utils.addComponent(panel, getButton("save"), gbc, gleft, ++gheight, 1, 1,
        GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, getButton("connect"), gbc, gright, gheight, 1, 1,
        GridBagConstraints.NONE, 1, 1);

        panel.setBackground(GlobalVariables.cgetDBConLogic());

        panels.put("userinput", panel);
    }

    
    @Override
    public JPanel getThis() {
        return this;
    }
}
