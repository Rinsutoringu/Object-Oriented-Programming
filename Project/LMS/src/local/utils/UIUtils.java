package local.utils;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
public class UIUtils implements standard.StandardUTIL{

    private static UIUtils instance;

    private UIUtils() {
    }

    public static UIUtils getInstance() {
        if (instance == null) {
            instance = new UIUtils();
        }
        return instance;
    }

    private double weightx = 1;
    private double weighty = 0;
    private int fillOption = GridBagConstraints.NONE;
    private int gridwidth = 1;
    private int gridheight = 1;


    public void addComponent(JPanel panel, JComponent component, GridBagConstraints gbc, int gridx, int gridy) {
        addComponent(panel, component, gbc, gridx, gridy, this.weightx, this.weighty, this.fillOption, this.gridwidth, this.gridheight);
    }

    public void addComponent(JPanel panel, JComponent component, GridBagConstraints gbc, int gridx, int gridy, double weightx, double weighty, int fillOption, int gridwidth, int gridheight) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.fill = fillOption;
        panel.add(component, gbc);
    }

    public void addComponent(JPanel panel, JComponent component, GridBagConstraints gbc, int gridx, int gridy, double weightx, double weighty) {
        addComponent(panel, component, gbc, gridx, gridy, weightx, weighty, this.fillOption, this.gridwidth, this.gridheight); 
    }

    public void addComponent(JPanel panel, JComponent component, GridBagConstraints gbc, int gridx, int gridy, int fillOption) {
        addComponent(panel, component, gbc, gridx, gridy, this.weightx, this.weighty, fillOption, this.gridwidth, this.gridheight); 
    }





}
