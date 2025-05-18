package local.ui.homepage.subpage.operation;

import java.awt.*;

import javax.swing.*;

import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import standard.GlobalVariables;
import standard.StandardUI;

public class SideBarUI extends StandardUI {

    // 定义错误处理器
    private errorHandler eh = errorHandler.getInstance();

    public SideBarUI() {

        // 初始化UI并注册默认方法
        super();

        try {
            // 初始化本类自有的PL
            init_sidebarPL();

            // 把本类PL加入到UI
            utils.addComponent(this, getPanel("sidebar"), gbc, 1, 2);
        } catch (Exception e) {
            // 使用默认错误处理器处理错误
            CatchException.handle(e, eh);
        }
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

    private void createAddAndEditCP() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); 
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

    // 这是一个示范CP组件
    private void userinput() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); 

        putButton("example", new JButton("Example"));
        putTextField("objectname", new JTextField(15));
        putButton("minus1", new JButton("-1"));
        putTextField("objectnumber", new JTextField(4));
        putButton("plus1", new JButton("+1"));



        short gheight = 0;

        utils.addComponent(panel, new JLabel("Plz Type In Item Name"), gbc, gmiddle, ++gheight, 1, 1,
        GridBagConstraints.NONE, 3, 1);

        utils.addComponent(panel, getTextField("objectname"), gbc, gmiddle, ++gheight, 1, 1,
        GridBagConstraints.NONE, 3, 1);

        utils.addComponent(panel, new JLabel("Number?"), gbc, gmiddle, ++gheight, 1, 1,
        GridBagConstraints.NONE, 3, 1);

        utils.addComponent(panel, getButton("minus1"), gbc, gleft, ++gheight, 1, 1,
        GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, getTextField("objectnumber"), gbc, gright, gheight, 1, 1,
        GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, getButton("plus1"), gbc, gright+1, gheight, 1, 1,
        GridBagConstraints.NONE, 1, 1);



        panel.setBackground(GlobalVariables.cgetSideBarLogic());
        // 注册CP组件
        putPanel("userinput", panel);
    }

    private void createSearch() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); 

        putTextField("search", new JTextField(10));
        putButton("search", new JButton("Search"));
        putTextField("result", new JTextField(18));
        putButton("clear", new JButton("Clear"));


        short gheight = 0;

        utils.addComponent(panel, new JLabel("Name"), gbc, gmiddle, ++gheight, 1, 1,
        GridBagConstraints.NONE, 2, 1);

        utils.addComponent(panel, getTextField("search"), gbc, gleft, ++gheight, 1, 1,
        GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, getButton("search"), gbc, gright, gheight, 1, 1,
        GridBagConstraints.NONE, 1, 1);

        utils.addComponent(panel, getTextField("result"), gbc, gleft, ++gheight, 1, 1,
        GridBagConstraints.NONE, 2, 1);

        utils.addComponent(panel, getButton("clear"), gbc, gleft, ++gheight, 1, 1,
        GridBagConstraints.NONE, 2, 1);

        panel.setBackground(GlobalVariables.cgetSideBarLogic());
        // 注册CP组件
        putPanel("search", panel);
    }

    @Override
    public SideBarUI getThis() {
        return this;
    }
}
