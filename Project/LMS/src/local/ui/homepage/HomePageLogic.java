package local.ui.homepage;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import local.error.ActionAddFailed;
import local.ui.homepage.subpage.*;
import local.ui.homepage.subpage.overview.OverviewOLD;
import standard.StandardUILogical;

public class HomePageLogic extends StandardUILogical {
    // public static final int DEFAULT = 0;
    private JPanel detailJpanel;
    private JPanel overJPanel;
    // private JPanel topviewPanel;
    private HomePageUI homepageUI;

    public HomePageLogic() {
        super();
        // 初始化界面各组件
        homepageUI = new HomePageUI();
        this.detailJpanel = homepageUI.getPanel("details");
        this.overJPanel = homepageUI.getPanel("overview");
        // this.topviewPanel = homepageUI.getPanel("topview");

        // 把费事的加载丢到后台同步进行
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                panels.put("sidebar", new SideBar());
                panels.put("overview", new OverviewOLD());
                panels.put("search", new Search());
                panels.put("stock", new Stock());
                panels.put("todo", new UserToDo());
                panels.put("hellopage", new HelloPage());
                return null;
            }
            @Override
            protected void done() {
                defaultView();
            }
        };
        worker.execute();
        addButtonAction();
   }

    // 默认视图
    @Override
    protected void defaultView() {
        show(detailJpanel, panels.get("hellopage"));
    }

    // 为按钮注册点击事件
    @Override
    protected void addButtonAction() throws ActionAddFailed{
        try{
            // OverView页面
            homepageUI.getButton("overview").addActionListener(e ->{
                show(detailJpanel, panels.get("sidebar"));
                show(overJPanel, panels.get("overview"));
            });

            // search页面
            homepageUI.getButton("search").addActionListener(e ->{
                show(detailJpanel, panels.get("sidebar"));
                show(overJPanel, panels.get("search"));
            });

            // stock页面
            homepageUI.getButton("stock").addActionListener(e ->{
                show(detailJpanel, panels.get("sidebar"));
                show(overJPanel, panels.get("stock"));
            });

            // To do页面
            homepageUI.getButton("todo").addActionListener(e ->{
                show(detailJpanel, panels.get("sidebar"));
                show(overJPanel, panels.get("todo"));
            });
        } catch (Exception e) {
            throw new ActionAddFailed("为按钮添加事件失败", e);
        }
    }

    public HomePageUI getThis() {
        return homepageUI;
    }
}
