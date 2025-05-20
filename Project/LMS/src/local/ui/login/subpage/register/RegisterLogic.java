package local.ui.login.subpage.register;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import laboratory.lab.workers.User;
import local.error.UserInfoError;
import local.ui.miniwindow.MiniOption;
import standard.GlobalVariables;
import standard.StandardUILogical;

public class RegisterLogic extends StandardUILogical {

    private RegisterUI registerUI;

    private errorHandler eh = errorHandler.getInstance();

    public RegisterLogic() {
        super();

        registerUI = new RegisterUI();

        defaultView();

        addButtonAction();
    }

    @Override
    public void defaultView() {
    }

    @Override
    public void addButtonAction() {

    registerUI.getButton("register").addActionListener(e -> {
        try {
            JCheckBox checkBox = registerUI.getCheckBox("apply");
            JTextField regusr = registerUI.getTextField("username");
            JPasswordField regpwd = registerUI.getPasswordField("password");
            JPasswordField regrepwd = registerUI.getPasswordField("repassword");

            String usr = regusr.getText();
            String pwd = new String(regpwd.getPassword());
            String repwd = new String(regrepwd.getPassword());

            User.Register(usr, pwd, repwd, checkBox);

            regusr.setText("");
            regpwd.setText("");
            regrepwd.setText("");

            GlobalVariables.setUserName(usr);

            new MiniOption("Register Success", "Register Success!", JOptionPane.INFORMATION_MESSAGE);

        } catch (UserInfoError ex) {
            new MiniOption("Register Failed", ex.getMessage(), JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            new MiniOption("Register Failed", "Please check your input and try again.", JOptionPane.ERROR_MESSAGE);
        }
    });

    }

    @Override
    public RegisterUI getThis() {
        return registerUI;
    }

}