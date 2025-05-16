package local.ui.login.subpage;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import standard.StandardUILogical;
import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import local.utils.CfgIOutils;

public class GetDBConLogic extends StandardUILogical {

    // 需要加载逻辑的目标UI类
    private GetDBConUI getdbconUI;

    // UI类中的面板句柄
    // 这里的面板句柄是UI类中定义的面板
    private JPanel getDBInfo;

    private errorHandler eh = errorHandler.getInstance();

    public GetDBConLogic() {
        super();

        // 初始化界面各组件
        getdbconUI = new GetDBConUI();

        // 获取可操作的面板句柄
        this.getDBInfo = getdbconUI.getPanel("getDBInfo");

        defaultView();
        addButtonAction();

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


        getdbconUI.getButton("dbConnect").addActionListener(e -> {
            try {
                String dbaddr = getdbconUI.getTextField("dbAddress").getText();
                String dbport = getdbconUI.getTextField("dbPort").getText();
                String dbuser = getdbconUI.getTextField("dbUser").getText();
                String dbpassword = getdbconUI.getTextField("dbPassword").getText();
                
                // 从配置读取所有信息
                Map<String,Map<String, String>> cfg = CfgIOutils.readjson("appconfig.cfg");
                
                // 如果cfg是空的，那就创建一个空字典
                if (cfg == null) cfg = new HashMap<>();
                
                Map<String, String> dbconfig = cfg.get("dbconfig");

                // 如果dbconfig是空的，那就往cfg字典里加一个空字典
                if (dbconfig == null) {
                    dbconfig = new HashMap<>();
                    cfg.put("dbconfig", dbconfig);
                }

                // 将用户输入的信息存入cfg字典
                dbconfig.put("dbuser", dbuser);
                dbconfig.put("dbpassword", dbpassword);
                dbconfig.put("dbaddr", dbaddr);
                dbconfig.put("dbport", dbport);

                // 将更新后的cfg写回文件
                CfgIOutils.writejson("appconfig.cfg", cfg);
                
            } catch (Exception ex) {
                CatchException.handle(ex, eh);
            }
        });

    }

    // 获取实例
    @Override
    public GetDBConLogic getThis() {
        return this;
    }
}

