package local.ui.homepage;

import javax.swing.*;
import java.awt.*;

public class HomePageUI extends JPanel {
    
    private local.utils.UIUtils utils = new local.utils.UIUtils();

    public HomePageUI() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0,0,0);

        utils.addComponent(this, topview(), gbc, 0, 0,1, 0.1,
        GridBagConstraints.BOTH, 2, 1);
        utils.addComponent(this, detaiJPanel(), gbc, 0, 1, 1, 0.9,
        GridBagConstraints.BOTH, 1, 1);
        utils.addComponent(this, overJPanel(), gbc, 1, 1, 2.5, 0.9,
        GridBagConstraints.BOTH, 1, 1);

    }

    private JPanel topview() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        
        panel.setBackground(Color.RED);
        return panel;
    }

    private JPanel overJPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        panel.setBackground(Color.BLUE);        
        return panel;
    }

    private JPanel detaiJPanel() {  
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        utils.addComponent(panel, new JButton("111"), gbc, 1, 1);
        panel.setBackground(Color.ORANGE);        
        return panel;
    }
}
