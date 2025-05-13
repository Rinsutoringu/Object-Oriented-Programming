package local.ui.homepage;

import javax.swing.JPanel;
import local.ui.homepage.subpage.*;

public class HomePageLogic extends local.ui.StandardUILogical {
    // public static final int DEFAULT = 0;
    private JPanel detaiJpanel;
    private JPanel overJPanel;
    private JPanel topviewPanel;
    HomePageUI homepage;

    public HomePageLogic() {
        super();
        homepage = new HomePageUI();

        this.detaiJpanel = homepage.getPanel("details");
        this.overJPanel = homepage.getPanel("overview");
        this.topviewPanel = homepage.getPanel("topview");

    }

    // 默认视图
    @Override
    protected void defaultView() {
        show(detaiJpanel, new HelloPage());
        show(overJPanel, new HelloPage());
    }

    // 为按钮注册点击事件
    @Override
    protected boolean addButtonAction() {
        try{
            // OverView页面
            homepage.getButton("overview").addActionListener(e ->{
                show(detaiJpanel, new SideBar());
                show(overJPanel, new Overview());
            });

            // search页面
            homepage.getButton("search").addActionListener(e ->{
                show(detaiJpanel, new SideBar());
                show(overJPanel, new Search());
            });

            // stock页面
            homepage.getButton("stock").addActionListener(e ->{
                show(detaiJpanel, new SideBar());
                show(overJPanel, new Stock());
            });
                    
            // To do页面
            homepage.getButton("todo").addActionListener(e ->{
                show(detaiJpanel, new ToDoSideBar());
                show(overJPanel, new UserToDo());
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
