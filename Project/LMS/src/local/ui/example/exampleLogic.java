package local.ui.example;

import javax.swing.JPanel;

import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import standard.StandardUILogical;

public class exampleLogic extends StandardUILogical {

    /**
     * 声明本Logicl类对应的UI类实例句柄
     */
    private exampleUI exampleui;

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
    public exampleLogic() {

        // 注册默认方法
        super();
        try {
            // 创建对应的UI类实例
            exampleui = new exampleUI();

            // （如果有子UI的话）添加子UI到注册表
            // putUI("nameq", new xxxlogical());

            // 获取PL句柄
            this.examplePL = exampleui.getPanel("examplePL");

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
            show(getThis(), this.examplePL);
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
        
    }

    // 为按钮注册点击事件
    @Override
    public void addButtonAction() {
        
        exampleui.getButton("example").addActionListener(e->{

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
    public exampleUI getThis() {
        return exampleui;
    }

}

/**#######################################################
##########################################################

 * 完成ui和logic的构建后，需要添加测试方法
 * src\test\factory\UIModuleFactory.java路径 添加一行case
 * case "<启动指令>":
 *     return new <要测试的类>();
 * 然后去vscode的launch.json -> inputs -> options添加一行预设指令
 * "<启动指令>",
 * 按下F5运行，选择对应的预设指令即可

###########################################################
########################################################### */


