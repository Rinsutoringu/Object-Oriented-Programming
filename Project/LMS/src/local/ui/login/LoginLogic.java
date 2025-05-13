package local.ui.login;

import javax.swing.JPanel;

public class LoginLogic extends local.ui.StandardUILogical {
    LoginUI loginUI;
    private String usr, pwd;
    
    public LoginLogic() {
        super();
        // 实例化loginUI对象
        loginUI = new LoginUI();
        
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
        }
        );
    }
    
    public JPanel getThis() {
        return loginUI;
    }
}
        