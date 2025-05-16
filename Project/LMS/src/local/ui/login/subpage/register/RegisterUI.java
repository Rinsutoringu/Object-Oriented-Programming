package local.ui.login.subpage.register;

import java.awt.*;

import javax.swing.*;

import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import local.utils.UIUtils;
import standard.GlobalVariables;
import standard.StandardUI;

public class RegisterUI extends StandardUI {
    
    UIUtils uiutils = UIUtils.getInstance();
    errorHandler eh = errorHandler.getInstance();

    public RegisterUI() {
        super();

        try {
            init_register();
            utils.addComponent(this, this.getPanel("register"), gbc, 1, 1);
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }

    }
    // 面板搞定

    // 这是一个示范组件
    private void init_register() {

        JPanel panel = new JPanel();

        createUserInput();
        buttons.put("close", new JButton("Close"));
        panel.setLayout(new GridBagLayout());

        utils.addComponent(panel, getPanel("userinput"), gbc, 0, 0, 1, 1,
        GridBagConstraints.NONE, 2, 1);

        utils.addComponent(panel, getButton("close"), gbc, 0, 1, 1, 1,
        GridBagConstraints.NONE, 2, 1);

        panels.put("register", panel);
    }

    private void createUserInput() {
        JPanel panel = new JPanel();
        // 设置layout manager
        // panel.setPreferredSize(new Dimension(0, 9));
        panel.setLayout(new GridBagLayout()); 

        // 创建界面上需要的组件
        
        textFields.put("username", new JTextField(20));
        passwordFields.put("password", new JPasswordField(20));
        passwordFields.put("repassword", new JPasswordField(20));
        checkBoxs.put("apply", new JCheckBox("Allow EULA"));
        buttons.put("register", new JButton("Register"));
        buttons.put("close", new JButton("Close"));

        // 设置组件到面板
        int gheight = 0;

        utils.addComponent(panel, new JLabel(" "), gbc, 0, ++gheight);
        utils.addComponent(panel, new JLabel(" "), gbc, 0, ++gheight);
        utils.addComponent(panel, new JLabel(" "), gbc, 0, ++gheight);
        utils.addComponent(panel, new JLabel("Let's Register!"), gbc, gleft, ++gheight, 1, 1,
        GridBagConstraints.NONE, 2, 1);
        utils.addComponent(panel, new JLabel(" "), gbc, 0, ++gheight);
        utils.addComponent(panel, new JLabel(" "), gbc, 0, ++gheight);

        utils.addComponent(panel, new JLabel("UserName"), gbc, gleft, ++gheight);
        utils.addComponent(panel, getTextField("username"), gbc, gright, gheight);

        utils.addComponent(panel, new JLabel("Password"), gbc, gleft, ++gheight);
        utils.addComponent(panel, getPasswordField("password"), gbc, gright, gheight);

        utils.addComponent(panel, new JLabel("Re-Enter"), gbc, gleft, ++gheight);
        utils.addComponent(panel, getPasswordField("repassword"), gbc, gright, gheight);

        utils.addComponent(panel, checkBoxs.get("apply"), gbc, gleft, ++gheight, 1, 1,
        GridBagConstraints.NONE, 2, 1);

        utils.addComponent(panel, getButton("register"), gbc, gleft, ++gheight, 1, 1,
        GridBagConstraints.NONE, 2, 1);

        panel.setBackground(GlobalVariables.cgetRegisterLogic());
        
        panels.put("userinput", panel);
    }


    @Override
    public RegisterUI getThis() {
        return this;
    }
}
