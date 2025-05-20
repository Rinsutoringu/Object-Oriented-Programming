package local.ui.login;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.function.Consumer;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import laboratory.lab.workers.User;
import local.ui.login.subpage.getdbconnect.GetDBConLogic;
import local.ui.login.subpage.register.RegisterLogic;
import local.ui.miniwindow.MiniOption;
import standard.StandardUILogical;

public class LoginLogic extends StandardUILogical {
    private LoginUI loginUI;
    private GetDBConLogic getdbconlogic;
    private RegisterLogic registerlogic;
    private static errorHandler eh = errorHandler.getInstance();
    private Consumer<String> loginCallback;
    private static LoginLogic instance;

    public static LoginLogic getInstance() {
        if (instance == null) {
            instance = new LoginLogic();
        }
        return instance;
    }

    private LoginLogic() {
        super();
        try {
            loginUI = new LoginUI();

            getdbconlogic = new GetDBConLogic();
            registerlogic = new RegisterLogic();
            defaultView();
            addButtonAction();

        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
    }

    protected void defaultView() {
    }

    protected void addButtonAction() {
        loginUI.getButton("login").addActionListener(e -> {
            System.out.println("Login button clicked");
            JCheckBox checkBox = loginUI.getCheckBox("check");
            if (!checkBox.isSelected()) {
                new MiniOption("Login Failed", "Please read and accept the terms and conditions", JOptionPane.WARNING_MESSAGE);
                return;
            }

            JTextField loginusr = loginUI.getTextField("loginusr");
            JTextField loginpwd = loginUI.getTextField("loginpwd");
            String usr = loginusr.getText();
            String pwd = loginpwd.getText();

            new SwingWorker<Boolean, Void>() {
                @Override
                protected Boolean doInBackground() throws Exception {
                    return User.Login(usr, pwd);
                }

                @Override
                protected void done() {
                    try {
                        boolean success = get();
                        if (success) {
                            loginusr.setText("");
                            loginpwd.setText("");
                            if (loginCallback != null) {
                                loginCallback.accept(usr);
                            }
                        }
                    } catch (Exception ex) {
                        CatchException.handle(ex, eh);
                        new MiniOption("Login Failed", "Please check your Username and Password or DataBase.", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }.execute();
        });

        loginUI.getButton("setdb").addActionListener(e -> {
            try {
                showGetDBConnectInfo();
            } catch (Exception ex) {
                CatchException.handle(ex, eh);
            }
        });

        loginUI.getButton("regusr").addActionListener(e -> {
            try {
                showRegInfo();
            } catch (Exception ex) {
                CatchException.handle(ex, eh);
            }
        });

        getdbconlogic.getThis().getButton("close").addActionListener(e -> {
            try {
                closeRight();
            } catch (Exception ex) {
                CatchException.handle(ex, eh);
            }
        });

        registerlogic.getThis().getButton("close").addActionListener(e -> {
            try {
                closeRight();
            } catch (Exception ex) {
                CatchException.handle(ex, eh);
            }
        });

    }

    public void setLoginCallback(Consumer<String> callback) {
        this.loginCallback = callback;
    }

    public void showGetDBConnectInfo() {
        JPanel dbInfoPanel = getdbconlogic.getThis().getPanel("getDBInfo");
        loginUI.switchPanel("pic", dbInfoPanel);
    }

    public void showRegInfo() {
        JPanel regInfoPanel = registerlogic.getThis().getPanel("register");
        loginUI.switchPanel("pic", regInfoPanel);
    }

    public void closeRight() {
        JPanel target = loginUI.getPanel("pic");
        target.removeAll();
        target.revalidate();
        target.repaint();
    }

    public LoginUI getThis() {
        return loginUI;
    }
}
