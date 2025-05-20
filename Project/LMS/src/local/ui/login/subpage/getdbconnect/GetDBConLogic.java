package local.ui.login.subpage.getdbconnect;


import javax.swing.JPanel;

import standard.GlobalVariables;
import standard.StandardUILogical;
import database.db.DataBase;
import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import local.ui.miniwindow.MiniOption;

public class GetDBConLogic extends StandardUILogical {

    // 需要加载逻辑的目标UI类
    private GetDBConUI getdbconUI;

    // UI类中的面板句柄
    // 这里的面板句柄是UI类中定义的面板
    private JPanel getDBInfo;

    private static errorHandler eh = errorHandler.getInstance();
    private static DataBase dbutils = DataBase.getInstance();

    public GetDBConLogic() {
        super();

        // 初始化界面各组件
        getdbconUI = new GetDBConUI();

        // 获取可操作的面板句柄并注册
        this.getDBInfo = getdbconUI.getPanel("getDBInfo");
        this.panels.put("getDBInfo", this.getDBInfo);

        defaultView();
        addButtonAction();

    }

    @Override
    protected void setStyle() {
        setFontSize(getLabel("registertooltip"), 25);
    }

    // 设置启动后的默认视图
    @Override
    public void defaultView() {

        // 设置默认显示内容 第一个值是目标，第二个值是显示的内容
        show(this, this.getDBInfo);
        
    }

    // 为按钮注册点击事件
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
                // 写入到配置文件
                dbutils.addDBCredentials(dbType, dbaddr, dbuser, dbpassword, dbport);
                // new MiniOption("LMS", "Your DataBase Connect Information save success! ", 1);
                
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
                CatchException.handle(ex, eh);
            }
        });



    }

    // 获取实例
    @Override
    public GetDBConUI getThis() {
        return getdbconUI;
    }

}

