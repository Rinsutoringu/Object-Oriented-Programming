package local.ui.homepage.subpage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Stock extends JPanel {
    public Stock() {

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        String username = "Test User";
        this.add(new JLabel("Good morning, "+username));
        this.add(new JLabel("Now is Stock page"));
    }
}