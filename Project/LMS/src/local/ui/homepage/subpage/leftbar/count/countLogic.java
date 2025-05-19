package local.ui.homepage.subpage.leftbar.count;

import javax.swing.JPanel;

import database.db.DataBase;
import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import standard.StandardUILogical;

public class countLogic extends StandardUILogical {

    /**
     * 声明本Logicl类对应的UI类实例句柄
     */
    private countUI countui;

    /**
     * 声明UI类中包含的PL句柄
     * 用于动态绘制画面
     */
    private JPanel examplePL;

    // 定义错误处理器
    private errorHandler eh = errorHandler.getInstance();

    // 获取工具
    private DataBase dbUtils = DataBase.getInstance();


    public countLogic() {

        // 注册默认方法
        super();
        try {
            // 初始化本类的UI对象
            countui = new countUI();

            // 初始化类中自有的PL（全屏）
            putPL("count", getThis().getPanel("count"));

            // 初始化类中自有的CP（部分显示）
            // putCP("dataview", countui.getPanel("dataview"));

            // 初始化画面
            defaultView();

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
        // try {
        //     show(getThis(), this.examplePL);
        // } catch (Exception e) {
        //     CatchException.handle(e, eh);
        // }
        
    }

    // 为按钮注册点击事件
    @Override
    public void addButtonAction() {
        

    }

    // 获取实例
    @Override
    public countUI getThis() {
        return countui;
    }

}