package local.ui.homepage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import local.utils.*;

public class HomePageUI extends JPanel {
    
    private local.utils.UIUtils utils = new local.utils.UIUtils();

    public HomePageUI() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        utils.addComponent(this, overJPanel(), gbc, 0, 0, 1, 1);
        utils.addComponent(this, detaiJPanel(), gbc, 1, 0, 20, 1);

    }

    private JPanel overJPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBackground(Color.BLUE);        
        return panel;
    }

    private JPanel detaiJPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        utils.addComponent(panel, new JButton(), gbc, 1, 1);
        panel.setBackground(Color.ORANGE);        
        return panel;
    }
}
