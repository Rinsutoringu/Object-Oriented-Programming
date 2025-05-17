package local.ui.homepage;

import javax.swing.JPanel;
import local.error.ActionAddFailed;
import local.ui.homepage.subpage.overview.OverviewLogic;
import standard.StandardUILogical;

public class HomePageLogic extends StandardUILogical {

    // 声明UI句柄变量
    /**
     * 本类持有Overview UI
     */
    private OverviewLogic overviewLogic;

    // 声明PL句柄变量
    /**
     * 本类持有detail PL
     */
    private JPanel detailJpanel;

    /**
     * 本类持有overview PL
     */
    private JPanel overJPanel;

    /**
     * 本类持有topview PL
     */
    // private JPanel topviewPanel;

    /**
     * 本类持有homepage UI
     */
    private HomePageUI homepageUI;


    // 本类构造函数
    public HomePageLogic() {
        // 初始化父类
        super();

        // 初始化UI
        homepageUI = new HomePageUI();

        // 初始化UI所持有的UI定义
        overviewLogic = new OverviewLogic();


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
                show(detailJpanel, panels.get("sidebar"));
                show(overJPanel, panels.get("overview"));
            });

            // 在左栏展示聚合工具菜单
            homepageUI.getButton("operation").addActionListener(e ->{
                show(detailJpanel, panels.get("sidebar"));
                show(overJPanel, panels.get("operation"));
            });

            // 在左栏展示快速添加菜单
            homepageUI.getButton("stock").addActionListener(e ->{
                show(detailJpanel, panels.get("sidebar"));
                show(overJPanel, panels.get("stock"));
            });

            // 打开新UI：用户的个人待办
            // TODO 该按钮应该被注册到MainWindowLogic.java中
            homepageUI.getButton("todo").addActionListener(e ->{
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
