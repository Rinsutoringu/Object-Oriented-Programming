package local.ui.homepage.subpage.rightbar.overview;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import database.db.DataBase;
import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import standard.StandardUILogical;

public class OverviewLogic extends StandardUILogical {

    /**
     * 声明本Logicl类对应的UI类实例句柄
     */
    private OverviewUI overviewui;

    /**
     * 声明UI类中包含的PL句柄
     * 用于动态绘制画面
     */
    private JPanel overview;

    // 定义错误处理器
    private errorHandler eh = errorHandler.getInstance();

    // 获取工具
    private DataBase dbUtils = DataBase.getInstance();


    public OverviewLogic() {

        // 注册默认方法
        super();
        try {
            // 创建对应的UI类实例
            overviewui = new OverviewUI();

            // 获取PL句柄
            this.overview = overviewui.getPanel("overview");

            // 初始化画面
            defaultView();
            refreshTableData(null, true);

            // 初始化点击事件
            addButtonAction();
        } catch (Exception e) {
            // 使用默认错误处理器处理错误
            CatchException.handle(e, eh);
        }
    }

    // 设置启动后的默认视图
    @Override
    public void defaultView() {

        // 设置默认显示内容 第一个值是目标，第二个值是显示的内容
        // 啥都不加就默认显示UI加载完后的内容
        try {
            show(getThis(), this.overview);
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
        
    }

    // 为按钮注册点击事件
    @Override
    public void addButtonAction() {
        
        overviewui.getButton("refresh").addActionListener(e->{

            // 在此定义具体点击事件
            try {
                refreshTableData(null, true);
            } catch (Exception ex) {
                // 基础的错误处理逻辑
                CatchException.handle(ex, eh);
            }
        }
        );
            overviewui.getButton("sortNameAsc").addActionListener(e -> refreshTableData("obj_name", true));
            overviewui.getButton("sortNameDesc").addActionListener(e -> refreshTableData("obj_name", false));
            overviewui.getButton("sortNumberAsc").addActionListener(e -> refreshTableData("obj_number", true));
            overviewui.getButton("sortNumberDesc").addActionListener(e -> refreshTableData("obj_number", false));
    }

    public void refreshTableData(String orderBy, boolean asc) {
        try {
            DefaultTableModel model = overviewui.getTableModel();
            model.setRowCount(0);

            String sql = "SELECT obj_name, obj_number, obj_lastuptime, lastuser FROM shelf";
            if (orderBy != null && !orderBy.isEmpty()) {
                sql += " ORDER BY " + orderBy + (asc ? " ASC" : " DESC");
            }

            for (Object[] row : dbUtils.queryCustomShelfTable(sql)) {
                if (row[2] != null) {
                    row[2] = parseDateTime(row[2].toString());
                }
                model.addRow(row);
            }
        } catch (Exception e) {
            // CatchException.handle(e, eh);
        }
    }

    /**
     * 解析日期时间字符串或时间戳
     * @param dateTime 日期时间字符串或时间戳
     * @return 可读的日期时间字符串
     */
    private String parseDateTime(String dateTime) {
        try {
            if (dateTime.matches("\\d+")) {
                long timestamp = Long.parseLong(dateTime);
                return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .format(new java.util.Date(timestamp));
            }

            java.text.SimpleDateFormat isoFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            java.util.Date date = isoFormat.parse(dateTime);
            return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        } catch (Exception e) {
            return dateTime;
        }
    }

    

    // 获取实例
    @Override
    public OverviewUI getThis() {
        return overviewui;
    }

}