package local.ui.login;

import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import laboratory.lab.workers.User;
import local.error.*;
import local.ui.login.subpage.getdbconnect.GetDBConLogic;
import local.ui.login.subpage.register.RegisterLogic;
import local.ui.miniwindow.MiniOption;
import standard.GlobalVariables;
import standard.StandardUILogical;

public class LoginLogic extends StandardUILogical {
    private LoginUI loginUI;
    private GetDBConLogic getdbconlogic;
    private RegisterLogic registerlogic;
    private static errorHandler eh = errorHandler.getInstance();
    

    public LoginLogic() {
        super();
        try {
            // 实例化loginUI对象
            loginUI = new LoginUI();

            getdbconlogic = new GetDBConLogic();
            registerlogic = new RegisterLogic();
            // 实例化数据库对象
            // 展示默认内容
            defaultView();
            // 添加按钮事件
            addButtonAction();

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
            JCheckBox checkBox = loginUI.getCheckBox("check");
            if(!checkBox.isSelected()) {
                new MiniOption("Login Failed", "Please read and accept the fucking terms and conditions", JOptionPane.WARNING_MESSAGE);
                return;
            }
            JTextField loginusr = loginUI.getTextField("loginusr");
            JTextField loginpwd = loginUI.getTextField("loginpwd");
            String usr = loginusr.getText();
            String pwd = loginpwd.getText();
            try {
                if (!User.Login(usr, pwd)) throw new AuthFailed("Login failed"); 
                System.out.println("Login success");
                GlobalVariables.setUserName(usr);
                loginusr.setText("");
                loginpwd.setText("");
            } catch (Exception ex) {
                CatchException.handle(ex, eh);
            }
        });

        loginUI.getButton("setdb").addActionListener(e -> {
            // 获取数据库连接信息
            try {
                showGetDBConnectInfo();
            } catch (Exception ex) {
                CatchException.handle(ex, eh);
            }
        });

        loginUI.getButton("regusr").addActionListener(e -> {
            // 获取注册信息
            try {
                showRegInfo();
            } catch (Exception ex) {
                CatchException.handle(ex, eh);
            }
        });

        getdbconlogic.getThis().getButton("close").addActionListener(e -> {
            try {
                closeRight();
            } catch (Exception ex) {
                CatchException.handle(ex, eh);
            }
        });

        registerlogic.getThis().getButton("close").addActionListener(e -> {
            try {
                closeRight();
            } catch (Exception ex) {
                CatchException.handle(ex, eh);
            }
        });

    }

    public void showGetDBConnectInfo() {
        // 初始化数据库连接信息面板
        JPanel dbInfoPanel = getdbconlogic.getThis().getPanel("getDBInfo");
        loginUI.switchPanel("pic", dbInfoPanel);
    }

    public void showRegInfo() {
        // 初始化注册信息面板
        JPanel regInfoPanel = registerlogic.getThis().getPanel("register");
        loginUI.switchPanel("pic", regInfoPanel);
    }

    public void closeRight() {
        JPanel target = loginUI.getPanel("pic");
        target.removeAll();
        target.revalidate();
        target.repaint();
    }




    public LoginUI getThis() {
        return loginUI;
    }
}
