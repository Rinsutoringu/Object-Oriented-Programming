package local.ui.homepage.subpage.leftbar.count;

import java.awt.*;

import javax.swing.*;

import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import standard.GlobalVariables;
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
        // Welcome Message
        JTextArea welcomeMsg = (JTextArea) getTextArea("welcomemsg");
        welcomeMsg.setLineWrap(true);
        welcomeMsg.setWrapStyleWord(true);
        // welcomeMsg.setPreferredSize(new Dimension(300, 100));
        // welcomeMsg.setMaximumSize(new Dimension(300, 100));
        welcomeMsg.setFont(GlobalVariables.getCustomFont().deriveFont(28f));
        welcomeMsg.setOpaque(false);
        welcomeMsg.setEditable(false);
        welcomeMsg.setMargin(new Insets(2, 2, 2, 2));


        // Last Operation
        JTextArea lastOperation = (JTextArea) getTextArea("lastoperation");
        lastOperation.setLineWrap(true);
        lastOperation.setWrapStyleWord(true);
        // lastOperation.setPreferredSize(new Dimension(300, 100));
        // lastOperation.setMaximumSize(new Dimension(300, 100));
        lastOperation.setFont(GlobalVariables.getCustomFont().deriveFont(16f));
        lastOperation.setOpaque(false);
        lastOperation.setEditable(false);
        lastOperation.setMargin(new Insets(2, 2, 2, 2));

        // Items Quantity
        JTextArea itemsQuantity = (JTextArea) getTextArea("itemsquantity");
        itemsQuantity.setLineWrap(true);
        itemsQuantity.setWrapStyleWord(true);
        // itemsQuantity.setPreferredSize(new Dimension(300, 100));
        // itemsQuantity.setMaximumSize(new Dimension(300, 100));
        itemsQuantity.setFont(GlobalVariables.getCustomFont().deriveFont(16f));
        itemsQuantity.setOpaque(false);
        itemsQuantity.setEditable(false);
        itemsQuantity.setMargin(new Insets(2, 2, 2, 2));


    }

    // 这是一个示范PL单元
    private void init_countPL() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); 
        gbc.insets = new Insets(5, 20, 10, 10);

        putTextArea("welcomemsg", new JTextArea("Welcome to Example1"));
        putTextArea("itemsquantity", new JTextArea("Items Quantity"));
        putTextArea("lastoperation", new JTextArea("Types of items"));

        // 设置组件到CP组件

        int gheight = 0;

        utils.addComponent(panel, getTextArea("welcomemsg"), gbc, 1, ++gheight, 1, 0,
        GridBagConstraints.BOTH, 1, 1);

        utils.addComponent(panel, getTextArea("lastoperation"), gbc, 1, ++gheight, 1, 0,
        GridBagConstraints.BOTH, 1, 1);

        utils.addComponent(panel, getTextArea("itemsquantity"), gbc, 1, ++gheight, 1, 0,
        GridBagConstraints.BOTH, 1, 1);


        panel.setBackground(GlobalVariables.cgetCountLogic());
        // 注册PL单元
        panels.put("count", panel);
    }

    @Override
    public countUI getThis() {
        return this;
    }
}
