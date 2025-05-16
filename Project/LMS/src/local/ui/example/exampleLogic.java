package local.ui.example;

import javax.swing.JPanel;

import standard.StandardUILogical;

public class exampleLogic extends StandardUILogical {

    // 需要加载逻辑的目标UI类
    private exampleUI exampleui;

    // UI类中的面板句柄
    // 这里的面板句柄是UI类中定义的面板
    private JPanel examplewindow;


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

    @Override
    public void defaultView() {
        show(this, this.examplewindow);
        
    }

    @Override
    public void addButtonAction() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public exampleLogic getThis() {
        return this;
    }
}
