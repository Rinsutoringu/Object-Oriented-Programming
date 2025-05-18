package local.ui.homepage;

import local.error.ActionAddFailed;
import local.ui.homepage.subpage.operation.SideBarLogic;
import local.ui.homepage.subpage.overview.OverviewLogic;
import standard.StandardUILogical;

public class HomePageLogic extends StandardUILogical {

    /**
     * 属于本类的UI类对象
     */
    private HomePageUI homepageUI;

    private boolean isShowOverview;
    private boolean isShowOperation;
    private boolean isShowStock;


    // 本类构造函数
    public HomePageLogic() {

        // 注册默认方法
        super();

        // 初始化本类的UI对象
        homepageUI = new HomePageUI();

        // 初始化本类持有的Page
        putPage("overview", new OverviewLogic());
        putPage("sidebar", new SideBarLogic(this));

        // 初始化类中自有的PL（全屏）
        putPL("home", getThis().getPanel("home"));

        // 初始化类中自有的CP（部分显示）
        putCP("top", getThis().getPanel("top"));
        putCP("sub", getThis().getPanel("sub"));
        putCP("main", getThis().getPanel("main"));

        isShowOverview = false;
        isShowOperation = false;
        isShowStock = false;

        addButtonAction();
        defaultView();
   }

    // 默认视图
    @Override
    protected void defaultView() {
        showOverview();
        
    }

    // 为按钮注册点击事件
    @Override
    protected void addButtonAction() throws ActionAddFailed{
        try{
            // 在左侧窄栏展示数据总览
            homepageUI.getButton("briefing").addActionListener(e ->{
                // show(detailJpanel, panels.get("sidebar"));
                // show(getCP("main"), getPage("overview", "overview"));
                show(getCP("sub"), getPage("sidebar", "sidebar"));
            });

            // 在左栏展示聚合工具菜单
            homepageUI.getButton("operation").addActionListener(e ->{

                System.out.println("home   "+getPL("home").getSize());
                System.out.println("top    "+getCP("top").getSize());
                System.out.println("sub     "+getCP("sub").getSize());
                System.out.println("main    "+getCP("main").getSize());
                System.out.println(" ");
                
                // TODO  // show(detailJpanel, panels.get("sidebar"));
                
                // TODO  // show(overJPanel, panels.get("operation"));
            });

            // 在左栏展示快速添加菜单
            homepageUI.getButton("stock").addActionListener(e ->{
                
                // TODO  // show(detailJpanel, panels.get("sidebar"));
                
                // TODO  // show(overJPanel, panels.get("stock"));
            });

            // TODO  // 打开新UI：用户的个人待办
            homepageUI.getButton("todo").addActionListener(e ->{
                // 该按钮应该被注册到MainWindowLogic.java中
                throw new ActionAddFailed("请到MainWindowLogic.java中注册该按钮");
            });
        } catch (Exception e) {
            throw new ActionAddFailed("为按钮添加事件失败", e);
        }
    }

    public void showOverview() {
        if (isShowOverview) {
            close();
            
            return;
        }
        else {
            show(getCP("main"), getPage("overview", "overview"));
            isShowOverview = true;
        }
    }

    public void showOperation() {
        if (isShowOperation) {
            return;
        }
        isShowOperation = true;
    }

    public void showStock() {
        if (isShowStock) {
            return;
        }
        isShowStock = true;
    }

    public void close() {
        show(getCP("sub"), getPage("overview", "overview"));
        isShowOverview = false;
        isShowOperation = false;
        isShowStock = false;
    }

    public HomePageUI getThis() {
        return homepageUI;
    }
}
