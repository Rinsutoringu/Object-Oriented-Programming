package local.ui.homepage;

import local.error.ActionAddFailed;
import local.ui.homepage.subpage.overview.OverviewLogic;
import standard.StandardUILogical;

public class HomePageLogic extends StandardUILogical {

    /**
     * 属于本类的UI类对象
     */
    private HomePageUI homepageUI;


    // 本类构造函数
    public HomePageLogic() {
        // 初始化父类
        super();

        // 初始化本类的UI对象
        homepageUI = new HomePageUI();

        // 初始化本类持有的Page
        putPage("overview", new OverviewLogic());

        // 初始化类中自有的PL（全屏）
        putPL("home", getThis().getPanel("home"));

        // 初始化类中自有的CP（部分显示）
        putCP("top", getThis().getPanel("top"));
        putCP("sub", getThis().getPanel("sub"));
        putCP("main", getThis().getPanel("main"));


        addButtonAction();
        defaultView();
   }

    // 默认视图
    @Override
    protected void defaultView() {
        // show(detailJpanel, panels.get("hellopage"));
    }

    // 为按钮注册点击事件
    @Override
    protected void addButtonAction() throws ActionAddFailed{
        try{
            // 在左侧窄栏展示数据总览
            homepageUI.getButton("overview").addActionListener(e ->{
                // show(detailJpanel, panels.get("sidebar"));
                show(getCP("main"), getPage("overview", "overview"));
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

    public HomePageUI getThis() {
        return homepageUI;
    }
}
