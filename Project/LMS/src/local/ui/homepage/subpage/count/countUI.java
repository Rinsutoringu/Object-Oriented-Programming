package local.ui.homepage.subpage.count;

import java.awt.*;

import javax.swing.*;

import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import standard.StandardUI;

public class countUI extends StandardUI {

    // 定义错误处理器
    private errorHandler eh = errorHandler.getInstance();

    public countUI() {
        
        // 初始化UI并注册默认方法
        super();

        try {
            // 初始化本类自有的PL
            init_countPL();

            setStyle();

            // 把本类PL加入到UI
            utils.addComponent(this, getPanel("count"), gbc, 1, 2);
        } catch (Exception e) {
            // 使用默认错误处理器处理错误
            CatchException.handle(e, eh);
        }
    }

    @Override
    protected void setStyle() {
        getLabel("welcomemsg").setPreferredSize(new Dimension(200, 30));
        getLabel("itemsquantity").setPreferredSize(new Dimension(200, 30));
        getLabel("typesofitems").setPreferredSize(new Dimension(200, 30));

    }

    // 这是一个示范PL单元
    private void init_countPL() {
        JPanel panel = new JPanel();
        // 设置layout manager
        // panel.setPreferredSize(new Dimension(0, 9));
        panel.setLayout(new GridBagLayout()); 

        // 创建PL单元上需要的组件
        createDataView();

        // 设置组件到PL单元

        short gheight = 0;

        utils.addComponent(panel, getPanel("dataview"), gbc, gmiddle, ++gheight);


        // 注册PL单元
        panels.put("count", panel);
    }

    // 这是一个示范CP组件
    private void createDataView() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); 

        putLabel("welcomemsg", new JLabel("Welcome to Example1"));
        putLabel("itemsquantity", new JLabel("Items Quantity"));
        putLabel("typesofitems", new JLabel("Types of items"));

        // 设置组件到CP组件

        int gheight = 0;

        utils.addComponent(panel, getLabel("welcomemsg"), gbc, 1, ++gheight, 1, 1,
        GridBagConstraints.NONE, 1, 1);
        utils.addComponent(panel, getLabel("itemsquantity"), gbc, 1, ++gheight, 1, 1,
        GridBagConstraints.NONE, 1, 1);
        utils.addComponent(panel, getLabel("typesofitems"), gbc, 1, ++gheight, 1, 1,
        GridBagConstraints.NONE, 1, 1);

        // 注册CP组件
        panels.put("dataview", panel);
    }

    @Override
    public countUI getThis() {
        return this;
    }
}
