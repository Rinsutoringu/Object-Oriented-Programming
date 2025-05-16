package local.ui.login.subpage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import standard.StandardUI;
import local.utils.UIUtils;

public class GetDBConUI extends StandardUI{

    UIUtils uiutils = UIUtils.getInstance();
    errorHandler eh = errorHandler.getInstance();
    
    public GetDBConUI() {

        try {
            init_getdbinfo();
            utils.addComponent(this, this.getPanel("getDBInfo"), gbc, 1, 1);
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }

    }

    /**
     * 提供输入框，让用户输入数据库连接信息
     * 连接信息包括：数据库地址、端口号、用户名、密码
     */
    private void init_getdbinfo() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        int gheight = 0;
        int gleft = 0;
        int gright = 1;


        inputBoxs.put("dbAddress", new javax.swing.JTextField(20));
        inputBoxs.put("dbPort", new javax.swing.JTextField(20));
        inputBoxs.put("dbUser", new javax.swing.JTextField(20));
        inputBoxs.put("dbPassword", new javax.swing.JPasswordField(20));
        buttons.put("dbConnect", new javax.swing.JButton("Connect"));
        buttons.put("close", new javax.swing.JButton("close"));
        createDBType();


        utils.addComponent(panel, new JLabel("Please enter the Storage information:"), gbc, gleft, ++gheight, 1, 1,
        GridBagConstraints.NONE, 2, 1);

        utils.addComponent(panel, new JLabel("Choose a DB type"), gbc, gleft, ++gheight);
        utils.addComponent(panel, comboBoxs.get("dbType"), gbc, gright, gheight);
        
        utils.addComponent(panel, new JLabel("Database Address:"), gbc, gleft, ++gheight);
        utils.addComponent(panel, inputBoxs.get("dbAddress"), gbc, gright, gheight);

        utils.addComponent(panel, new JLabel("Database Port:"), gbc, gleft, ++gheight);
        utils.addComponent(panel, inputBoxs.get("dbPort"), gbc, gright, gheight);

        utils.addComponent(panel, new JLabel("Database User:"), gbc, gleft, ++gheight);
        utils.addComponent(panel, inputBoxs.get("dbUser"), gbc, gright, gheight);

        utils.addComponent(panel, new JLabel("Database Password:"), gbc, gleft, ++gheight);
        utils.addComponent(panel, inputBoxs.get("dbPassword"), gbc, gright, gheight);



        utils.addComponent(panel, getButton("dbConnect"), gbc, gleft, ++gheight, 1, 1,
        GridBagConstraints.NONE, 2, 1);

        utils.addComponent(panel, getButton("close"), gbc, gleft, ++gheight, 1, 1,
        GridBagConstraints.NONE, 2, 1);

        panels.put("getDBInfo", panel);

    }

    private void createDBType() {
        String[] dbTypes = {"MySQL", "PostgreSQL", "SQLite"};
        JComboBox<String> dbTypeCombo = new JComboBox<>(dbTypes);
        comboBoxs.put("dbType", dbTypeCombo);
    }


    
    
// TODO 实际的检测逻辑
    
    @Override
    public JPanel getThis() {
        return this;
    }
}
