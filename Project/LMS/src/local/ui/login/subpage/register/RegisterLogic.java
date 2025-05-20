package local.ui.login.subpage.register;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import laboratory.lab.workers.User;
import local.ui.miniwindow.MiniOption;
import standard.GlobalVariables;
import standard.StandardUILogical;

public class RegisterLogic extends StandardUILogical {

    // 需要加载逻辑的目标UI类
    private RegisterUI registerUI;

    // UI类中的面板句柄
    // 这里的面板句柄是UI类中定义的面板
    private JPanel register;

    // 错误处理器
    private errorHandler eh = errorHandler.getInstance();


    public RegisterLogic() {
        super();

        // 初始化界面各组件
        registerUI = new RegisterUI();

        // 获取可操作的面板句柄
        this.register = registerUI.getPanel("register");

        // 初始化显示内容
        defaultView();

        // 为按钮增加点击事件
        addButtonAction();
    }

    // 设置启动后的默认视图
    @Override
    public void defaultView() {

        // 设置默认显示内容 第一个值是目标，第二个值是显示的内容
        // 啥都不加就默认显示UI加载完后的内容
        // show(this, this.examplewindow);
        
    }

    // 为按钮注册点击事件
    @Override
    public void addButtonAction() {

        registerUI.getButton("register").addActionListener(e->{
            // 在此定义具体点击事件
            try {

                JCheckBox checkBox = registerUI.getCheckBox("apply");
                if(!checkBox.isSelected()) {
                    new MiniOption("Register", "Please read and accept the fucking terms and conditions", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                JTextField regusr = registerUI.getTextField("username");
                JPasswordField regpwd = registerUI.getPasswordField("password");
                JPasswordField regrepwd = registerUI.getPasswordField("repassword");

                String usr = regusr.getText();
                String pwd = new String(regpwd.getPassword());
                String repwd = new String(regrepwd.getPassword());

                if (!pwd.equals(repwd)) {
                    new MiniOption("Register Failed", "Please make sure the two passwords are the same", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }

                User.Register(usr, pwd);
                // 注册成功后清空输入框
                regusr.setText("");
                regpwd.setText("");
                regrepwd.setText("");
                // 将用户名注册为全局变量以便于后续使用
                GlobalVariables.setUserName(usr);
                new MiniOption("Register Success", "Register Success!\nYou can use your account login now! :D", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                // 基础的错误处理逻辑
                CatchException.handle(ex, eh);
            }

        });

        

    }

    // 获取实例
    @Override
    public RegisterUI getThis() {
        return registerUI;
    }

}