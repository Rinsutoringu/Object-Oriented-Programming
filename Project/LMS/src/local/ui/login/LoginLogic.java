package local.ui.login;

public class LoginLogic {
    LoginUI loginUI;
    
    public LoginLogic() {
        // 实例化loginUI对象
        loginUI = new LoginUI();
    }

    private void addLoginAction() {
        // 登录按钮
        loginUI.getButton("login").addActionListener(e -> {
            // 获取用户名和密码
            String username = loginUI.getTextField("username").getText();
            String password = loginUI.getTextField("password").getText();
        }
        );
    }
}
        