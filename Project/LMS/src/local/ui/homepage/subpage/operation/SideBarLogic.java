package local.ui.homepage.subpage.operation;

import javax.swing.JPanel;

import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import local.ui.example.exampleUI;
import standard.StandardUILogical;

public class SideBarLogic extends StandardUILogical {

    /**
     * 声明本Logicl类对应的UI类实例句柄
     */
    private SideBarUI sidebarui;

    /**
     * 声明UI类中包含的PL句柄
     * 用于动态绘制画面
     */
    private JPanel examplePL;

    // 定义错误处理器
    private errorHandler eh = errorHandler.getInstance();

    /**
     * 构造函数要做的事情：
     * 1. 初始化持有的Page对象
     * 2. 把要用到的可视化组件（Page PL CP）注册到逻辑层
     */
    public SideBarLogic() {

        // 注册默认方法
        super();
        try {
            // 初始化本类的UI对象
            sidebarui = new SideBarUI();

            // 初始化本类持有的Page
            // putPage("name", new xxxlogical());

            // 初始化类中自有的PL（全屏）
            // TODO 并没有PL
            putPL("sidebar", getThis().getPanel("sidebar"));

            // 初始化类中自有的CP（部分显示）
            // TODO 并没有CP
            // putCP("example1", sidebarui.getPanel("example1"));

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
        try {
            // TODO 并没有PL
            show(getThis(), getPL("sidebar"));
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
        
    }

    // 为按钮注册点击事件
    @Override
    public void addButtonAction() {

        sidebarui.getButton("submit").addActionListener(e->{

            // 在此定义具体点击事件
            try {
                System.out.println("submit button clicked");
            } catch (Exception ex) {
                // 基础的错误处理逻辑
                CatchException.handle(ex, eh);
            }

        }
        );

    }

    // 获取实例
    @Override
    public SideBarUI getThis() {
        return sidebarui;
    }

}
