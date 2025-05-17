package local.ui.homepage.subpage.overview;

import javax.swing.JPanel;

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
    private JPanel overviewPL;

    // 定义错误处理器
    private errorHandler eh = errorHandler.getInstance();


    public OverviewLogic() {

        // 注册默认方法
        super();
        try {
            // 创建对应的UI类实例
            overviewui = new OverviewUI();

            // 获取PL句柄
            this.overviewPL = overviewui.getPanel("overviewPL");

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
            show(getThis(), this.overviewPL);
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
        
    }

    // 为按钮注册点击事件
    @Override
    public void addButtonAction() {
        
        overviewui.getButton("example").addActionListener(e->{

            // 在此定义具体点击事件
            try {
                System.out.println("example button clicked");
            } catch (Exception ex) {
                // 基础的错误处理逻辑
                CatchException.handle(ex, eh);
            }

        }
        );

    }

    // 获取实例
    @Override
    public OverviewUI getThis() {
        return overviewui;
    }

}