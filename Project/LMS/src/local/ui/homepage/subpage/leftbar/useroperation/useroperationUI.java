package local.ui.homepage.subpage.leftbar.useroperation;

import java.awt.*;
import javax.swing.*;

import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import standard.GlobalVariables;
import standard.StandardUI;

public class useroperationUI extends StandardUI {

    private errorHandler eh = errorHandler.getInstance();

    private int subwindow_width = 260;
    private int subwindow_height = 228;

    public useroperationUI() {
        super();
        try {
            init_sidebarPL();
            setStyle();
            utils.addComponent(this, getPanel("useroperation"), gbc, 1, 1);
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
    }

    @Override
    protected void setStyle() {
        setFontSize(getLabel("usermanagement"), 20);
        getButton("submitedit").setPreferredSize(new Dimension(100, 30));
        getButton("delete").setPreferredSize(new Dimension(100, 30));
        getTextField("username").setPreferredSize(new Dimension(200, 30));
        getComboBox("permission").setPreferredSize(new Dimension(123, 30));

        setFontSize(getLabel("newusermanagement"), 20);
        getButton("submitcreate").setPreferredSize(new Dimension(100, 30));
        getTextField("newusername").setPreferredSize(new Dimension(200, 30));
        getTextField("newpassword").setPreferredSize(new Dimension(200, 30));
    }

    private void init_sidebarPL() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        createEditUser();
        createNewUser();
        
        short gheight = 0;

        gbc.insets = new Insets(5, 5, 5, 5);
        utils.addComponent(panel, getPanel("edituser"), gbc, gleft, ++gheight, 1, 1,
            GridBagConstraints.NONE, 2, 1);

        utils.addComponent(panel, getPanel("newuser"), gbc, gleft, ++gheight, 1, 1,
            GridBagConstraints.NONE, 2, 1);

        panels.put("useroperation", panel);
    }

    private void createEditUser() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); 
        panel.setPreferredSize(new Dimension(subwindow_width, subwindow_height)); 

        createUserInput();
        putButton("submitedit", new JButton("Set"));
        putButton("delete", new JButton("Delete"));

        short gheight = 0;

        gbc.insets = new Insets(10, 5, 5, 5);

        utils.addComponent(panel, getPanel("userinput"), gbc, gleft, ++gheight, 1, 1,
            GridBagConstraints.NONE, 2, 1);

        gbc.insets = new Insets(5, 5, 10, 5);

        utils.addComponent(panel, getButton("submitedit"), gbc, gleft, ++gheight, 1, 1,
            GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, getButton("delete"), gbc, gright, gheight, 1, 1,
            GridBagConstraints.NONE, 1, 1);

        panel.setBackground(GlobalVariables.cgetUserOperationLogic());
        putPanel("edituser", panel);
    }

    private void createUserInput() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); 
        
        putLabel("usermanagement", new JLabel("Edit User Permission"));
        putLabel("usernamelabel", new JLabel("Username:"));
        putTextField("username", new JTextField(15));
        putLabel("permissionlabel", new JLabel("Permission:"));
        putComboBox("permission", new JComboBox<>(new String[]{"ban", "user", "admin"}));

        short gheight = 0;

        gbc.insets = new Insets(10, 5, 35, 5);
        utils.addComponent(panel, getLabel("usermanagement"), gbc, gmiddle, ++gheight, 1, 1,
            GridBagConstraints.NONE, 2, 1);

        gbc.insets = new Insets(5, 5, 5, 5);

        utils.addComponent(panel, getLabel("usernamelabel"), gbc, gleft, ++gheight, 0.1, 1,
            GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, getTextField("username"), gbc, gright, gheight, 1, 1,
            GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, getLabel("permissionlabel"), gbc, gleft, ++gheight, 0.1, 1,
            GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, getComboBox("permission"), gbc, gright, gheight, 1, 1,
            GridBagConstraints.NONE, 1, 1);

        panel.setBackground(GlobalVariables.cgetUserOperationLogic());
        putPanel("userinput", panel);
    }

    private void createNewUser() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); 
        panel.setPreferredSize(new Dimension(subwindow_width, subwindow_height)); 

        createNewUserInput();
        putButton("submitcreate", new JButton("Submit"));

        short gheight = 0;
        gbc.insets = new Insets(10, 5, 5, 5);

        utils.addComponent(panel, getPanel("newuserinput"), gbc, gleft, ++gheight, 1, 1,
            GridBagConstraints.NONE, 1, 1);

        gbc.insets = new Insets(5, 5, 10, 5);

        utils.addComponent(panel, getButton("submitcreate"), gbc, gleft, ++gheight, 1, 1,
            GridBagConstraints.NONE, 1, 1);

        panel.setBackground(GlobalVariables.cgetUserOperationLogic());

        putPanel("newuser", panel);
    }

    private void createNewUserInput() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); 

        putLabel("newusermanagement", new JLabel("New User"));
        putLabel("newusernamelabel", new JLabel("Username:"));
        putTextField("newusername", new JTextField(15));
        putLabel("newpasswordlabel", new JLabel("Password:"));
        putTextField("newpassword", new JTextField(15));

        short gheight = 0;
        gbc.insets = new Insets(10, 5, 10, 5);
        utils.addComponent(panel, getLabel("newusermanagement"), gbc, gmiddle, ++gheight, 1, 1,
            GridBagConstraints.NONE, 2, 1);

        gbc.insets = new Insets(5, 5, 5, 5);

        utils.addComponent(panel, getLabel("newusernamelabel"), gbc, gleft, ++gheight, 0.1, 1,
            GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, getTextField("newusername"), gbc, gright, gheight, 1, 1,
            GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, getLabel("newpasswordlabel"), gbc, gleft, ++gheight, 0.1, 1,
            GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, getTextField("newpassword"), gbc, gright, gheight, 1, 1,
            GridBagConstraints.NONE, 1, 1);

        panel.setBackground(GlobalVariables.cgetUserOperationLogic());
        putPanel("newuserinput", panel);
    }

    @Override
    public useroperationUI getThis() {
        return this;
    }
}