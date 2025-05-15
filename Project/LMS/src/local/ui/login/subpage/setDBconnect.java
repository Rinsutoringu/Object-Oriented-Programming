package local.ui.login.subpage;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import local.ui.StandardUI;

public class setDBconnect extends StandardUI{

    errorHandler eh = errorHandler.getInstance();
    
    public setDBconnect() {

        try {
            init_getdbinfo();
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
        // utils.addComponent(this, panels.get("getDBInfo"), gbc, 0, 1);
    }

    /**
     * 提供输入框，让用户输入数据库连接信息
     * 连接信息包括：数据库地址、端口号、用户名、密码
     */
    private void init_getdbinfo() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        inputBoxs.put("dbAddress", new javax.swing.JTextField(20));
        inputBoxs.put("dbPort", new javax.swing.JTextField(20));
        inputBoxs.put("dbUser", new javax.swing.JTextField(20));
        inputBoxs.put("dbPassword", new javax.swing.JPasswordField(20));

        panel.add(new javax.swing.JLabel("Database Address:"));
        panel.add(inputBoxs.get("dbAddress"));
        panel.add(new javax.swing.JLabel("Database Port:"));
        panel.add(inputBoxs.get("dbPort"));
        panel.add(new javax.swing.JLabel("Database UserName:"));
        panel.add(inputBoxs.get("dbUser"));
        panel.add(new javax.swing.JLabel("Database Password:"));
        panel.add(inputBoxs.get("dbPassword")); 

        panels.put("getDBInfo", panel);
    }

    
    @Override
    public JPanel getThis() {
        return this;
    }
}
