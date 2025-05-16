package local.ui.example;

import javax.swing.JPanel;

import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import standard.StandardUILogical;

public class exampleLogic extends StandardUILogical {

    // 需要加载逻辑的目标UI类
    private exampleUI exampleui;

    // UI类中的面板句柄
    // 这里的面板句柄是UI类中定义的面板
    private JPanel examplewindow;

    // 错误处理器
    private errorHandler eh = errorHandler.getInstance();


    public exampleLogic() {
        super();

        // 初始化界面各组件
        exampleui = new exampleUI();

        // 获取可操作的面板句柄
        this.examplewindow = exampleui.getPanel("examplewindow");

        // 初始化显示内容
        defaultView();

        // 为按钮增加点击事件
        addButtonAction();
    }

    // 设置启动后的默认视图
    @Override
    public void defaultView() {

        // 设置默认显示内容 第一个值是目标，第二个值是显示的内容
        show(this, this.examplewindow);
        
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
    public exampleLogic getThis() {
        return this;
    }
}

// 完成ui和logic的构建后，需要添加测试方法
// src\test\factory\UIModuleFactory.java路径 添加一行case

// case "<启动指令>":
//     return new <要测试的类>();

// 然后去vscode的launch.json -> inputs -> options添加一行预设指令

// "<启动指令>",

// 按下F5运行，选择对应的预设指令即可