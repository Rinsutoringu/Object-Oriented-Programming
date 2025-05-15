package local.ui.login;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import database.db.DataBaseUtils;
import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import local.error.*;
import local.ui.login.subpage.setDBconnect;
import local.utils.MiniOption;
import standard.StandardUILogical;

public class LoginLogic extends StandardUILogical {
    private LoginUI loginUI;
    private setDBconnect setDBconnect;
    private DataBaseUtils dbUtils;
    private errorHandler eh;
    
    public LoginLogic() {
        super();

        eh = errorHandler.getInstance();

        try {
            // 实例化loginUI对象
            loginUI = new LoginUI();

            setDBconnect = new setDBconnect();
            // 实例化数据库对象
            dbUtils = DataBaseUtils.getInstance();
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
                if (!dbUtils.Login(usr, pwd)) throw new AuthFailed("Login failed"); 
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
                dbUtils.Register(usr, pwd);
                // 注册成功后清空输入框
                regusr.setText("");
                regpwd.setText("");
                loginUI.getCheckbox("check").setSelected(false);
                // 将用户名注册为全局变量以便于后续使用
                local.utils.GlobalVariables.currentUsr = usr;
                new MiniOption("Register Success", "Register Success!\nYou can use your account login now! :D", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                CatchException.handle(ex, eh);
            }
        });
    }

    // 将右侧展示框切换到数据库连接信息获取界面
    public void showGetDBConnectInfo() {
        JPanel target = loginUI.getPanel("pic");
        target.setLayout(new GridBagLayout());
        
        GridBagConstraints topGbc = new GridBagConstraints();
        topGbc.gridx = 0;
        topGbc.gridy = 0;
        topGbc.weightx = 1.0;
        topGbc.weighty = 1.0; // 分配多余空间
        topGbc.fill = GridBagConstraints.BOTH;

        JPanel ph = new JPanel();
        ph.setOpaque(false);
        target.add(ph, topGbc);



        // 添加实际组件到底部
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0; // 不分配多余空间
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(40, 70, 10, 70);
        target.add(setDBconnect.getPanel("getDBInfo"), gbc);
        

        // target.add(setDBconnect.getPanel("getDBInfo"), gbc);

        // save layout
        // show(target, setDBconnect.getPanel("getDBInfo"), gbc);
    }


    public JPanel getThis() {return loginUI;}
}
