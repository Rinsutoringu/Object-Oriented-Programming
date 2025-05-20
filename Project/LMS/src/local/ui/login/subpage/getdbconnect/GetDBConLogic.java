package local.ui.login.subpage.getdbconnect;

import javax.swing.JPanel;

import standard.GlobalVariables;
import standard.StandardUILogical;
import database.db.DataBase;
import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import local.ui.miniwindow.MiniOption;

public class GetDBConLogic extends StandardUILogical {

    private GetDBConUI getdbconUI;

    private JPanel getDBInfo;

    private static errorHandler eh = errorHandler.getInstance();
    private static DataBase dbutils = DataBase.getInstance();

    public GetDBConLogic() {
        super();

        getdbconUI = new GetDBConUI();

        this.getDBInfo = getdbconUI.getPanel("getDBInfo");
        this.panels.put("getDBInfo", this.getDBInfo);

        defaultView();
        addButtonAction();

    }

    @Override
    protected void setStyle() {
        setFontSize(getLabel("registertooltip"), 25);
    }

    @Override
    public void defaultView() {
        show(this, this.getDBInfo);
    }

    @Override
    public void addButtonAction() {

        getdbconUI.getButton("save").addActionListener(e -> {
            try {
                String dbType = getdbconUI.getComboBox("dbType").getSelectedItem().toString();
                String dbaddr = getdbconUI.getTextField("dbAddress").getText();
                String dbport = getdbconUI.getTextField("dbPort").getText();
                String dbuser = getdbconUI.getTextField("dbUser").getText();
                String dbpassword = getdbconUI.getTextField("dbPassword").getText();
                switch (dbType) {
                    case "MySQL":
                        GlobalVariables.setDBType("MySQL");
                        break;
                    case "PostgreSQL":
                        GlobalVariables.setDBType("PostgreSQL");
                        break;
                    case "SQLite":
                        GlobalVariables.setDBType("SQLite");
                        break;
                    default:
                        break;
                }
                dbutils.addDBCredentials(dbType, dbaddr, dbuser, dbpassword, dbport);

                getdbconUI.getTextField("dbAddress").setText("");
                getdbconUI.getTextField("dbPort").setText("");
                getdbconUI.getTextField("dbUser").setText("");
                getdbconUI.getTextField("dbPassword").setText("");

            } catch (Exception ex) {
                CatchException.handle(ex, eh);
            }
        });

        getdbconUI.getButton("connect").addActionListener(e -> {
            try {
                getdbconUI.getButton("save").doClick();
                DataBase.getInstance().ResetRetryCount();
                dbutils.getConnection();
                new MiniOption("LMS", "Your DataBase Connect Success! ", 1);
            } catch (Exception ex) {
                new MiniOption("LMS", "Your DataBase Connect Failed! ", 0);
            }
        });

    }

    @Override
    public GetDBConUI getThis() {
        return getdbconUI;
    }

}

