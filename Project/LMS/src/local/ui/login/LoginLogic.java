package local.ui.login;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import database.db.DataBaseUtils;
import local.error.*;
import local.utils.MiniOption;

public class LoginLogic extends local.ui.StandardUILogical {
    LoginUI loginUI;
    private DataBaseUtils dbUtils;
    
    public LoginLogic() {
        super();

        try {
            // 实例化loginUI对象
            loginUI = new LoginUI();

            // 实例化数据库对象
            dbUtils = new DataBaseUtils();

        } catch (Exception e) {throw new GUIActionFailed("窗口加载失败", e);}

        defaultView();
        addButtonAction();
    }

    protected void defaultView() {
        // 默认视图
    }

    protected void addButtonAction() {
        // 添加按钮事件
        addLoginAction();
    }


    private void addLoginAction() {
        // 登录按钮
        
        loginUI.getButton("login").addActionListener(e -> {
            // 获取用户名和密码
            System.out.println("Login button clicked");
            // 傻逼java
            JCheckBox checkBox = loginUI.getCheckbox("check");
            if(!checkBox.isSelected()) {
                new MiniOption("Login Failed", "Please read and accept the fucking terms and conditions", JOptionPane.WARNING_MESSAGE);
                return;
            }

            JTextField loginusr = loginUI.getTextField("loginusr");
            JTextField loginpwd = loginUI.getTextField("loginpwd");
            String usr = loginusr.getText();
            String pwd = loginpwd.getText();
            // DEBUG
            // System.out.println("User: " + usr + ", Password: " + pwd);
            try {
                dbUtils.Login(usr, pwd);
                loginusr.setText("");
                loginpwd.setText("");
            } catch (Exception ex) {
                checkBox.setSelected(false);
                new MiniOption("Login Failed", "Please check your UserName or Password!\n Error: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            }
        });

        loginUI.getButton("reg").addActionListener(e -> {
            // 注册按钮
            System.out.println("Register button clicked");
            JTextField regusr = loginUI.getTextField("regusr");
            JTextField regpwd = loginUI.getTextField("regpwd");
            String usr = regusr.getText();
            String pwd = regpwd.getText();
            System.out.println("User: " + usr + ", Password: " + pwd);
            try {
                dbUtils.Register(usr, pwd);
                // 注册成功后清空输入框
                regusr.setText("");
                regpwd.setText("");
                loginUI.getCheckbox("check").setSelected(false);
                // 将用户名注册为全局变量以便于后续使用
                local.utils.GlobalVariables.currentUsr = usr;
                // 断开与数据库的连接
                dbUtils.disconnectDB();
                new MiniOption("Register Success", "Register Success!\nYou can use your account login now! :D", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                new MiniOption("Register Failed", "Please check your UserName or Password!\n Error: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public JPanel getThis() {return loginUI;}
}
