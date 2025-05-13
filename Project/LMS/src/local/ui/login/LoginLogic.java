package local.ui.login;

import javax.swing.JPanel;

import database.db.DataBaseUtils;
import local.error.GUIActionFailed;
import local.utils.MiniOption;

public class LoginLogic extends local.ui.StandardUILogical {
    LoginUI loginUI;
    private String usr, pwd;
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
            usr = loginUI.getTextField("username").getText();
            pwd = loginUI.getTextField("password").getText();
        });
        // if (!dbUtils.Login(usr, pwd)) new MiniOption("Login Failed", "Please check your UserName or Password!");
    }

    public JPanel getThis() {return loginUI;}
}
