package local.ui.example;

import java.awt.*;

import javax.swing.*;

import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import standard.StandardUI;

public class exampleUI extends StandardUI {

    private errorHandler eh = errorHandler.getInstance();

    public exampleUI() {
        super();

        try {
            init_examplePL();

            setStyle();

            utils.addComponent(this, getPanel("examplewindow"), gbc, 1, 2);
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
    }

    @Override
    protected void setStyle() {
        // your style settings here
    }

    private void init_examplePL() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        createExample();
        putButton("example", new JButton("Example"));

        short gheight = 0;

        utils.addComponent(panel, getPanel("example1"), gbc, gmiddle, ++gheight);

        utils.addComponent(panel, getButton("example"), gbc, gmiddle, ++gheight, 1, 1,
                GridBagConstraints.NONE, 1, 1);

        putPanel("examplewindow", panel);
    }

    private void createExample() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        putButton("example1", new JButton("Example1"));

        utils.addComponent(panel, getButton("example1"), gbc, 1, 1, 1, 1,
                GridBagConstraints.NONE, 1, 1);

        putPanel("example1", panel);
    }

    @Override
    public exampleUI getThis() {
        return this;
    }
}
