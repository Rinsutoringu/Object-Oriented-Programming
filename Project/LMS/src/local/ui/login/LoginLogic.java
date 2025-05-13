package local.ui.login;

public class LoginLogic {
    LoginUI loginUI;
    private String usr, pwd;
    
    public LoginLogic() {
        // 实例化loginUI对象
        loginUI = new LoginUI();
        
        
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
}
        