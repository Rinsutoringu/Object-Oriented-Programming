package local.ui.homepage;

import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;


import laboratory.lab.shelf.Shelf;
import laboratory.lab.workers.User;
import local.error.ActionAddFailed;
import local.ui.homepage.subpage.leftbar.count.countLogic;
import local.ui.homepage.subpage.leftbar.count.countUI;
import local.ui.homepage.subpage.leftbar.itemoperation.itemoperationLogic;
import local.ui.homepage.subpage.leftbar.useroperation.useroperationLogic;
import local.ui.homepage.subpage.rightbar.competence.competenceLogic;
import local.ui.homepage.subpage.rightbar.overview.OverviewLogic;
import local.ui.login.LoginLogic;
import local.ui.mainwindow.MainWindowUI;
import standard.GlobalVariables;
import standard.StandardUILogical;

public class HomePageLogic extends StandardUILogical {


    private HomePageUI homepageUI;

    private boolean isShowOverview;
    private boolean isShowOperation;
    private boolean isShowStock;
    private static HomePageLogic instance;

    public static HomePageLogic getInstance() {
        if (instance == null) {
            instance = new HomePageLogic();
        }
        return instance;
    }


    private HomePageLogic() {

        super();

        homepageUI = new HomePageUI();

        putPage("overview", new OverviewLogic());
        putPage("sidebar", new itemoperationLogic(this));
        putPage("count", new countLogic());
        putPage("useroperation", new useroperationLogic(this));
        putPage("competence", new competenceLogic());

        putPL("home", getThis().getPanel("home"));

        putCP("top", getThis().getPanel("top"));
        putCP("sub", getThis().getPanel("sub"));
        putCP("main", getThis().getPanel("main"));

        isShowOverview = false;
        isShowOperation = false;
        isShowStock = false;

        addButtonAction();
        defaultView();
   }

    @Override
    protected void defaultView() {
        
    }

    @Override
    protected void addButtonAction() throws ActionAddFailed{
        try{
            homepageUI.getButton("briefing").addActionListener(e ->{

                show(getCP("sub"), getPage("sidebar", "sidebar"));
                show(getCP("main"), getPage("overview", "overview"));
            });

            homepageUI.getButton("operation").addActionListener(e ->{

                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        ((countUI) getPage("count").getThis()).getTextArea("welcomemsg").setText(generateWelcomeMessage(GlobalVariables.getUserName(), User.isAdmin(GlobalVariables.getUserName())));
                        ((countUI) getPage("count").getThis()).getTextArea("itemsquantity").setText(generateItemsQuantityMsg());
                        ((countUI) getPage("count").getThis()).getTextArea("lastoperation").setText(generateLastOperationMsg());
                        return null;
                    }
                };
                worker.execute();
                show(getCP("sub"), getPage("count", "count"));
                show(getCP("main"), getPage("overview", "overview"));
            });

            homepageUI.getButton("stock").addActionListener(e -> {
                show(getCP("sub"), getPage("useroperation", "useroperation"));
                show(getCP("main"), getPage("competence", "competence"));
            });

            homepageUI.getButton("logout").addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to log out?",
                    "Logout Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    GlobalVariables.setUserName(null);
                    MainWindowUI.getInstance().addPanel(LoginLogic.getInstance().getThis());
                }
            });

        } catch (Exception e) {
            throw new ActionAddFailed("Add button action failed", e);
        }
    }

    public void setStockButtonVisibility(boolean isVisible) {
        JButton stockButton = (JButton) getThis().getButton("stock");
        if (stockButton != null) {
            stockButton.setVisible(isVisible);
        }
    }

    private String generateWelcomeMessage(String username, boolean isAdmin) {
        LocalTime now = LocalTime.now();
        String timePeriod;

        if (now.isBefore(LocalTime.of(6, 0))) {
            timePeriod = "Good Night";
        } else if (now.isBefore(LocalTime.of(9, 0))) {
            timePeriod = "Good Morning";
        } else if (now.isBefore(LocalTime.of(12, 0))) {
            timePeriod = "Good Morning";
        } else if (now.isBefore(LocalTime.of(18, 0))) {
            timePeriod = "Good Afternoon";
        } else if (now.isBefore(LocalTime.of(22, 0))) {
            timePeriod = "Good Evening";
        } else {
            timePeriod = "Good Night";
        }

        String role = isAdmin ? "Admin" : "User";
        return String.format("%s, \n%s %s\n\n", timePeriod, role, username);
    }

    private String generateLastOperationMsg() {
        String[] lastUpdate = Shelf.getClosestLastUpdate();
        if (lastUpdate != null) {
            return String.format("Last operation user: [%s] \n\nAt time: \n[%s]", lastUpdate[0], lastUpdate[1]);
        }
        return "Last operation: Unknown";
    }

    private String generateItemsQuantityMsg() {
        int itemQuantity = Shelf.getItemQuantity();
        int totalItemQuantity = Shelf.getTotalItemQuantity();
        if (itemQuantity == -1 || totalItemQuantity == -1) {
            return "Error: Unable to retrieve item quantities.";
        }
        return String.format(
                        "Types of Materials: \n[%d]\n\n" +
                        "Total Number: \n[%d]\n\n", itemQuantity, totalItemQuantity);
    }

    public void showOverview() {
        if (isShowOverview) {
            close();
            return;
        }
        else {
            show(getCP("main"), getPage("overview", "overview"));
            isShowOverview = true;
        }
    }

    public void showOperation() {
        if (isShowOperation) {
            return;
        }
        isShowOperation = true;
    }

    public void showStock() {
        if (isShowStock) {
            return;
        }
        isShowStock = true;
    }

    public void close() {
        show(getCP("sub"), getPage("overview", "overview"));
        isShowOverview = false;
        isShowOperation = false;
        isShowStock = false;
    }

    public HomePageUI getThis() {
        return homepageUI;
    }
}
