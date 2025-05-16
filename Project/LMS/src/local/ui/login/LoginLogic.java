package local.ui.login;

import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import laboratory.lab.workers.User;
import local.error.*;
import local.ui.login.subpage.GetDBConLogic;
import local.ui.miniwindow.MiniOption;
import standard.GlobalVariables;
import standard.StandardUILogical;

public class LoginLogic extends StandardUILogical {
    private LoginUI loginUI;
    private GetDBConLogic getdbconlogic;
    private static errorHandler eh = errorHandler.getInstance();
    
    public LoginLogic() {
        super();

        eh = errorHandler.getInstance();

        try {
            // 实例化loginUI对象
            loginUI = new LoginUI();

            getdbconlogic = new GetDBConLogic();
            // 实例化数据库对象
            // 展示默认内容
            defaultView();
            // 添加按钮事件
            addButtonAction();
            // DEBUG: 设置右侧输入框
            showGetDBConnectInfo();

        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
    }

    protected void defaultView() {
        // 默认视图
    }

    protected void addButtonAction() {
        // 添加按钮事件
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
                if (!User.Login(usr, pwd)) throw new AuthFailed("Login failed"); 
                System.out.println("Login success");
                loginusr.setText("");
                loginpwd.setText("");
            } catch (Exception ex) {
                CatchException.handle(ex, eh);
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
                User.Register(usr, pwd);
                // 注册成功后清空输入框
                regusr.setText("");
                regpwd.setText("");
                loginUI.getCheckbox("check").setSelected(false);
                // 将用户名注册为全局变量以便于后续使用
                GlobalVariables.setUserName(usr);
                new MiniOption("Register Success", "Register Success!\nYou can use your account login now! :D", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                CatchException.handle(ex, eh);
            }
        });
    }

    public void showGetDBConnectInfo() {
        JPanel dbInfoPanel = getdbconlogic.getThis().getPanel("getDBInfo");
        loginUI.showGetDBConnectInfoPanel(dbInfoPanel);
    }

    public void closeGetDBConnectInfo() {
        JPanel target = loginUI.getPanel("pic");
        target.removeAll();
        target.revalidate();
        target.repaint();
    }


    public LoginUI getThis() {
        return loginUI;
    }
}
