package local.ui.login.subpage.register;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import laboratory.lab.workers.User;
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
                if (!checkBox.isSelected()) {
                    new MiniOption("Register", "Please read and accept the terms and conditions", JOptionPane.WARNING_MESSAGE);
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
                regusr.setText("");
                regpwd.setText("");
                regrepwd.setText("");
                GlobalVariables.setUserName(usr);
                new MiniOption("Register Success", "Register Success!\nYou can use your account login now! :D", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                CatchException.handle(ex, eh);
            }

        });

    }

    @Override
    public RegisterUI getThis() {
        return registerUI;
    }

}