package local.ui.login;

public class LoginLogic extends local.ui.StandardUILogical {
    LoginUI loginUI;
    private String usr, pwd;
    
    public LoginLogic() {
        super();
        // 实例化loginUI对象
        loginUI = new LoginUI();
    }

    protected void defaultView() {
        // 默认视图
    }

    protected boolean addButtonAction() {
        // 添加按钮事件
        addLoginAction();
        return true;
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
}
        