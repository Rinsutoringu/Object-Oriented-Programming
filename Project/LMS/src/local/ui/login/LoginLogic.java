package local.ui.login;

import javax.swing.JPanel;
import javax.swing.JOptionPane;

import database.db.DataBaseUtils;
import local.error.AuthFailed;
import local.error.GUIActionFailed;
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
            String usr = loginUI.getTextField("loginusr").getText();
            String pwd = loginUI.getTextField("loginpwd").getText();
            System.out.println("User: " + usr + ", Password: " + pwd);
            try {
                dbUtils.Login(usr, pwd);
            } catch (Exception ex) {
                new MiniOption("Login Failed", "Please check your UserName or Password!\n Error: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            }
        });

        loginUI.getButton("reg").addActionListener(e -> {
            // 注册按钮
            System.out.println("Register button clicked");
            String usr = loginUI.getTextField("regusr").getText();
            String pwd = loginUI.getTextField("regpwd").getText();
            System.out.println("User: " + usr + ", Password: " + pwd);
            try {
                dbUtils.Register(usr, pwd);
                new MiniOption("Register Success", "Register Success!", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                new MiniOption("Register Failed", "Please check your UserName or Password!\n Error: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public JPanel getThis() {return loginUI;}
}
