package local.ui.homepage.subpage.leftbar.itemoperation;

import java.awt.*;

import javax.swing.*;

import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import standard.GlobalVariables;
import standard.StandardUI;

public class itemoperationUI extends StandardUI {

    // 定义错误处理器
    private errorHandler eh = errorHandler.getInstance();
    private int subwindow_width = 260;
    private int subwindow_height = 228;

    public itemoperationUI() {

        // 初始化UI并注册默认方法
        super();

        try {
            // 初始化本类自有的PL
            init_sidebarPL();
            
            // 设置样式
            setStyle();

            // 把本类PL加入到UI
            utils.addComponent(this, getPanel("sidebar"), gbc, 1, 2);
        } catch (Exception e) {
            // 使用默认错误处理器处理错误
            CatchException.handle(e, eh);
        }
    }

    // 设置样式
    @Override
    protected void setStyle() {
        setFontSize(getLabel("searchitem"), 20);
        setFontSize(getLabel("addtooltip"), 20);
        getButton("plus1").setPreferredSize(new Dimension(21, 21));
        getButton("minus1").setPreferredSize(new Dimension(21, 21));
        getButton("plus1").setMargin(new Insets(0, 0, 0, 0));
        getButton("minus1").setMargin(new Insets(0, 0, 0, 0));
        setFontSize(getButton("plus1"), 20);
        setFontSize(getButton("minus1"), 20);
        getTextField("objectnumber").setHorizontalAlignment(JTextField.CENTER);
        getButton("search").setMargin(new Insets(0, 5, 0, 5));
        getButton("search").setPreferredSize(new Dimension(60, 25));
        setFontSize(getButton("search"), 12);
        getTextField("search").setPreferredSize(new Dimension(120, 25));
        getTextField("result").setPreferredSize(new Dimension(200, 30));
        getTextField("objectname").setPreferredSize(new Dimension(200, 30));
        
    }

    // 这是一个示范PL单元
    private void init_sidebarPL() {
        JPanel panel = new JPanel();

        panel.setLayout(new GridBagLayout()); 

        // 创建PL单元上需要的组件
        createAddAndEditCP();
        createSearch();

        short gheight = 0;

        utils.addComponent(panel, getPanel("search"), gbc, gmiddle, ++gheight, 1, 1,
        GridBagConstraints.NONE, 2, 1);

        utils.addComponent(panel, getPanel("addandedit"), gbc, gmiddle, ++gheight, 1, 1,
        GridBagConstraints.NONE, 2, 1);


        // 注册PL单元
        panels.put("sidebar", panel);
    }
    // 增改单元
    private void createAddAndEditCP() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); 
        panel.setPreferredSize(new Dimension(subwindow_width, subwindow_height)); 
        userinput();
        putButton("submit", new JButton("Add/Edit"));
        putButton("delete", new JButton(" DELETE "));
        short gheight = 0;
        utils.addComponent(panel, getPanel("userinput"), gbc, gmiddle, ++gheight, 1, 1,
        GridBagConstraints.NONE, 2, 1);
        utils.addComponent(panel, getButton("submit"), gbc, gleft, ++gheight, 1, 1,
        GridBagConstraints.NONE, 1, 1);
        utils.addComponent(panel, getButton("delete"), gbc, gright, gheight, 1, 1,
        GridBagConstraints.NONE, 1, 1);



        panel.setBackground(GlobalVariables.cgetSideBarLogic());
        // 注册CP组件
        putPanel("addandedit", panel);
    }

    // 增改组件
    private void userinput() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        

        putLabel("addtooltip", new JLabel("Add & Edit Item"));
        putTextField("objectname", new JTextField(17));
        createplusorminus();
        
        short gheight = 0;

        gbc.insets = new Insets(10, 5, 35, 5);
        utils.addComponent(panel, getLabel("addtooltip"), gbc, gmiddle, ++gheight, 1, 1,
        GridBagConstraints.NONE, 2, 1);

        gbc.insets = new Insets(5, 5, 5, 5);

        utils.addComponent(panel, new JLabel("Item  "), gbc, gleft, ++gheight, 0.1, 1,
        GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, getTextField("objectname"), gbc, gright, gheight, 1, 1,
        GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, new JLabel("Number"), gbc, gleft, ++gheight, 0.1, 1,
        GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, getPanel("plusorminus"), gbc, gright, gheight, 1, 1,
        GridBagConstraints.NONE, 1, 1);

        panel.setBackground(GlobalVariables.cgetSideBarLogic());
        putPanel("userinput", panel);
    }

    // 加减按钮
    private void createplusorminus() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); 
        // panel.setPreferredSize(new Dimension(80, 200));

        putButton("minus1", new JButton("-"));
        putTextField("objectnumber", new JTextField("1",3));
        putButton("plus1", new JButton("+"));
        
        gbc.insets = new Insets(5, 2, 5, 2);

        utils.addComponent(panel, getButton("minus1"), 
        gbc, 1, 1, 1, 1,GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, getTextField("objectnumber"), 
        gbc, 2, 1, 1, 1,GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, getButton("plus1"), 
        gbc, 3, 1, 1, 1,GridBagConstraints.NONE, 1, 1);
        
        panel.setBackground(GlobalVariables.cgetSideBarLogic());
        putPanel("plusorminus", panel);
    }

    // 搜索单元
    private void createSearch() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); 

        panel.setPreferredSize(new Dimension(subwindow_width, subwindow_height)); 


        putLabel("searchitem", new JLabel("Search Item here"));
        putLabel("searchlabel", new JLabel("Item"));
        putTextField("search", new JTextField(12));
        putButton("search", new JButton("Search"));
        putTextField("result", new JTextField(" Search Result show here. ", 29));
        putButton("clear", new JButton("Clear"));


        short gheight = 0;

        gbc.insets = new Insets(10, 10, 35, 10);

        utils.addComponent(panel, getLabel("searchitem"), gbc, gmiddle, ++gheight, 1, 1,
        GridBagConstraints.NONE, 3, 1);

        gbc.insets = new Insets(5, 10, 5, 10);

        utils.addComponent(panel, getLabel("searchlabel"), gbc, gleft, ++gheight, 0.1, 1,
        GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, getTextField("search"), gbc, gright, gheight, 1, 1,
        GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, getButton("search"), gbc, gright+1, gheight, 1, 1,
        GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, getTextField("result"), gbc, gleft, ++gheight, 1, 1,
        GridBagConstraints.NONE, 3, 1);

        utils.addComponent(panel, getButton("clear"), gbc, gleft, ++gheight, 1, 1,
        GridBagConstraints.NONE, 3, 1);

        panel.setBackground(GlobalVariables.cgetSideBarLogic());
        putPanel("search", panel);
    }



    @Override
    public itemoperationUI getThis() {
        return this;
    }
}
