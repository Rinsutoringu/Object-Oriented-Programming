package local.ui.homepage;

import javax.swing.JPanel;

import local.error.ActionAddFailed;
import local.ui.homepage.subpage.*;

public class HomePageLogic extends local.ui.StandardUILogical {
    // public static final int DEFAULT = 0;
    private JPanel detailJpanel;
    private JPanel overJPanel;
    private JPanel topviewPanel;
    private HomePageUI homepage;

    public HomePageLogic() {
        super();
        // 初始化界面各组件
        homepage = new HomePageUI();
        this.detailJpanel = homepage.getPanel("details");
        this.overJPanel = homepage.getPanel("overview");
        this.topviewPanel = homepage.getPanel("topview");
        defaultView();
        addButtonAction();
   }

    // 默认视图
    @Override
    protected void defaultView() {
        show(detailJpanel, new HelloPage());
        show(overJPanel, new HelloPage());
    }

    // 为按钮注册点击事件
    @Override
    protected void addButtonAction() throws ActionAddFailed{
        try{
            // OverView页面
            homepage.getButton("overview").addActionListener(e ->{
                show(detailJpanel, new SideBar());
                show(overJPanel, new Overview());
            });

            // search页面
            homepage.getButton("search").addActionListener(e ->{
                show(detailJpanel, new SideBar());
                show(overJPanel, new Search());
            });

            // stock页面
            homepage.getButton("stock").addActionListener(e ->{
                show(detailJpanel, new SideBar());
                show(overJPanel, new Stock());
            });
                    
            // To do页面
            homepage.getButton("todo").addActionListener(e ->{
                show(detailJpanel, new ToDoSideBar());
                show(overJPanel, new UserToDo());
            });
        } catch (Exception e) {
            throw new ActionAddFailed("为按钮添加事件失败", e);
        }
    }
    public JPanel getThis() {
        return homepage;
    }
}
