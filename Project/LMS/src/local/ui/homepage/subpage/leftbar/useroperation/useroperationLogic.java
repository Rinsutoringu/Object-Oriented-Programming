package local.ui.homepage.subpage.leftbar.useroperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import database.db.DataBase;
import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import laboratory.lab.workers.User;
import local.ui.homepage.HomePageLogic;
import local.ui.homepage.subpage.rightbar.competence.competenceLogic;
import local.ui.miniwindow.MiniOption;
import standard.GlobalVariables;
import standard.StandardUILogical;

public class useroperationLogic extends StandardUILogical {

    private useroperationUI useroperationui;
    private errorHandler eh = errorHandler.getInstance();
    private DataBase dbutils = DataBase.getInstance();
    private HomePageLogic homepagelogic;

    public useroperationLogic(HomePageLogic homepagelogic) {
        super();
        this.homepagelogic = homepagelogic;
        try {
            useroperationui = new useroperationUI();
            defaultView();
            addButtonAction();
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
    }

    @Override
    public void defaultView() {
        try {
            show(getThis(), useroperationui.getPanel("useroperation"));
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
    }

    @Override
    public void addButtonAction() {
        useroperationui.getButton("submitedit").addActionListener(e -> {
            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() {
                    try {
                        String username = getUserInput();
                        int permission = getPermissionInput();

                        if (username.isEmpty() || permission == -1) return null;

                        if (!isUserExists(username)) {
                            new MiniOption("Operation Error", "User does not exist.", JOptionPane.WARNING_MESSAGE);
                            return null;
                        }

                        Map<String, Object> whereMap = new java.util.HashMap<>();
                        whereMap.put("username", username);

                        Map<String, Object> updateMap = new java.util.HashMap<>();
                        updateMap.put("state", permission);

                        dbutils.updateRow(GlobalVariables.getStaffTableName(), whereMap, updateMap);
                        SwingUtilities.invokeLater(() -> getCompetenceLogic().refreshTableData());

                        new MiniOption("Success", "User permissions updated successfully.", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        CatchException.handle(ex, eh);
                    }
                    return null;
                }
            }.execute();
        });

        useroperationui.getButton("delete").addActionListener(e -> {
            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() {
                    try {
                        String username = getUserInput();

                        if (username.isEmpty()) return null;

                        if (!isUserExists(username)) {
                            new MiniOption("Operation Error", "User does not exist.", JOptionPane.WARNING_MESSAGE);
                            return null;
                        }

                        if (username.equals(GlobalVariables.getUserName())) {
                            new MiniOption("Operation Error", "You cannot delete your own account.", JOptionPane.WARNING_MESSAGE);
                            return null;
                        }

                        Map<String, Object> whereMap = new java.util.HashMap<>();
                        whereMap.put("username", username);

                        dbutils.deleteRow(GlobalVariables.getStaffTableName(), whereMap);

                        SwingUtilities.invokeLater(() -> getCompetenceLogic().refreshTableData());
                        clearUserInput();
                        new MiniOption("Success", "User deleted successfully.", JOptionPane.INFORMATION_MESSAGE);

                    } catch (Exception ex) {
                        CatchException.handle(ex, eh);
                    }
                    return null;
                }
            }.execute();
        });

        useroperationui.getButton("submitcreate").addActionListener(e -> {
            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() {
                    try {
                        String username = getNewUserInput();
                        String password = getPasswordInput();

                        if (username.isEmpty() || password.isEmpty()) return null;

                        if (User.userExists(username)) {
                            new MiniOption("Operation Error", "User already exists.", JOptionPane.WARNING_MESSAGE);
                            return null;
                        }

                        Map<String, Object> newUser = new java.util.HashMap<>();
                        newUser.put("username", username);
                        newUser.put("password", password);
                        newUser.put("regdate", new java.sql.Timestamp(System.currentTimeMillis()));
                        newUser.put("state", 1);

                        dbutils.insertRow(GlobalVariables.getStaffTableName(), newUser);

                        clearNewUserInput();

                        new MiniOption("Success", "User created successfully.", JOptionPane.INFORMATION_MESSAGE);

                        SwingUtilities.invokeLater(() -> getCompetenceLogic().refreshTableData());

                    } catch (Exception ex) {
                        CatchException.handle(ex, eh);
                    }
                    return null;
                }
            }.execute();
        });
    }

    private boolean isUserExists(String username) {
        try {
            String sql = "SELECT COUNT(*) FROM " + GlobalVariables.getStaffTableName() + " WHERE username = ?";
            try (Connection conn = dbutils.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1) > 0;
                    }
                }
            }
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
        return false;
    }

    private String getPasswordInput() {
        String passwordInput = useroperationui.getTextField("newpassword").getText();
        if (passwordInput.isEmpty()) {
            new MiniOption("getPasswordInput Error", "Please enter a password.", JOptionPane.WARNING_MESSAGE);
        }
        return passwordInput;
    }

    private String getNewUserInput() {
        String userInput = useroperationui.getTextField("newusername").getText();
        if (userInput.isEmpty()) {
            new MiniOption("getUserInput Error", "Please enter a username.", JOptionPane.WARNING_MESSAGE);
        }
        return userInput;
    }

    private String clearNewUserInput() {
        useroperationui.getTextField("newusername").setText("");
        useroperationui.getTextField("newpassword").setText("");
        return null;
    }

    private String clearUserInput() {
        useroperationui.getTextField("username").setText("");
        return null;
    }

    private String getUserInput() {
        String userInput = useroperationui.getTextField("username").getText();
        if (userInput.isEmpty()) {
            new MiniOption("getUserInput Error", "Please enter a username.", JOptionPane.WARNING_MESSAGE);
        }
        return userInput;
    }

    private int getPermissionInput() {
        String permissionInput = (String) useroperationui.getComboBox("permission").getSelectedItem();
        if (permissionInput.isEmpty()) {
            new MiniOption("getPermissionInput Error", "Please enter a permission level (0, 1, or 2).", JOptionPane.WARNING_MESSAGE);
            return -1;
        }
        switch (permissionInput) {
            case "ban":
                return 0;
            case "user":
                return 1;
            case "admin":
                return 2;
            default:
                new MiniOption("getPermissionInput Error", "Permission level must be 0 (Banned), 1 (User), or 2 (Admin).", JOptionPane.WARNING_MESSAGE);
                return -1;
        }
    }

    private competenceLogic getCompetenceLogic() {
        return (competenceLogic) homepagelogic.getPage("competence");
    }

    @Override
    public useroperationUI getThis() {
        return useroperationui;
    }
}