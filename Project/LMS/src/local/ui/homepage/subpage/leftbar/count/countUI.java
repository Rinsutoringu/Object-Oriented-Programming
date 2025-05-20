package local.ui.homepage.subpage.leftbar.count;

import java.awt.*;
import javax.swing.*;
import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import standard.GlobalVariables;
import standard.StandardUI;

public class countUI extends StandardUI {

    private errorHandler eh = errorHandler.getInstance();

    public countUI() {
        super();
        try {
            init_countPL();
            setStyle();
            utils.addComponent(this, getPanel("count"), gbc, 1, 2);
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
    }

    @Override
    protected void setStyle() {
        JTextArea welcomeMsg = (JTextArea) getTextArea("welcomemsg");
        welcomeMsg.setLineWrap(true);
        welcomeMsg.setWrapStyleWord(true);
        welcomeMsg.setFont(GlobalVariables.getCustomFont().deriveFont(28f));
        welcomeMsg.setOpaque(false);
        welcomeMsg.setEditable(false);
        welcomeMsg.setMargin(new Insets(2, 2, 2, 2));

        JTextArea lastOperation = (JTextArea) getTextArea("lastoperation");
        lastOperation.setLineWrap(true);
        lastOperation.setWrapStyleWord(true);
        lastOperation.setFont(GlobalVariables.getCustomFont().deriveFont(16f));
        lastOperation.setOpaque(false);
        lastOperation.setEditable(false);
        lastOperation.setMargin(new Insets(2, 2, 2, 2));

        JTextArea itemsQuantity = (JTextArea) getTextArea("itemsquantity");
        itemsQuantity.setLineWrap(true);
        itemsQuantity.setWrapStyleWord(true);
        itemsQuantity.setFont(GlobalVariables.getCustomFont().deriveFont(16f));
        itemsQuantity.setOpaque(false);
        itemsQuantity.setEditable(false);
        itemsQuantity.setMargin(new Insets(2, 2, 2, 2));
    }

    private void init_countPL() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        gbc.insets = new Insets(5, 20, 10, 10);

        putTextArea("welcomemsg", new JTextArea("Welcome to Example1"));
        putTextArea("itemsquantity", new JTextArea("Items Quantity"));
        putTextArea("lastoperation", new JTextArea("Types of items"));

        int gheight = 0;

        utils.addComponent(panel, getTextArea("welcomemsg"), gbc, 1, ++gheight, 1, 0,
        GridBagConstraints.BOTH, 1, 1);

        utils.addComponent(panel, getTextArea("lastoperation"), gbc, 1, ++gheight, 1, 0,
        GridBagConstraints.BOTH, 1, 1);

        utils.addComponent(panel, getTextArea("itemsquantity"), gbc, 1, ++gheight, 1, 0,
        GridBagConstraints.BOTH, 1, 1);

        panel.setBackground(GlobalVariables.cgetCountLogic());
        panels.put("count", panel);
    }

    @Override
    public countUI getThis() {
        return this;
    }
}
