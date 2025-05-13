package local.ui.homepage.subpage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelloPage extends JPanel {
    public HelloPage() {

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        String username = local.utils.GlobalVariables.currentUsr;
        this.add(new JLabel("Good morning, "+username));
        
    }
}
