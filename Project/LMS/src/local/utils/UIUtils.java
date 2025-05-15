package local.utils;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Toolkit;

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

    private double weightx = 1; // 默认水平权重，允许组件在水平方向上分配额外空间
    private double weighty = 0; // 默认垂直权重，组件在垂直方向上不分配额外空间
    private int fillOption = GridBagConstraints.NONE; // 默认填充方式，组件水平填充
    private int gridwidth = 1; // 默认跨越列数为1
    private int gridheight = 1; // 默认跨越行数为1
    public Image image = Toolkit.getDefaultToolkit().getImage("src\\resources\\images.png");

    /**
     * 通用方法：添加组件到面板; 对于GridBagConstraints布局方法的再封装
     * @param panel 目标面板
     * @param component 添加对象
     * @param gbc 布局方法
     * @param gridx 组件所在列数
     * @param gridy 组件所在行数
     */
    public void addComponent(JPanel panel, JComponent component, GridBagConstraints gbc, int gridx, int gridy) {
        addComponent(panel, component, gbc, gridx, gridy, this.weightx, this.weighty, this.fillOption, this.gridwidth, this.gridheight);
    }

    /**
     * 通用方法：添加组件到面板; 对于GridBagConstraints布局方法的再封装
     * @param panel 目标面板
     * @param component 添加对象
     * @param gbc 布局方法
     * @param gridx 组件所在列数
     * @param gridy 组件所在行数
     * @param weightx 组件在水平方向上分配额外空间的权重
     * @param weighty 组件在垂直方向上分配额外空间的权重
     */

    public void addComponent(JPanel panel, JComponent component, GridBagConstraints gbc, int gridx, int gridy, double weightx, double weighty) {
        addComponent(panel, component, gbc, gridx, gridy, weightx, weighty, this.fillOption, this.gridwidth, this.gridheight); 
    }

    /**
     * 通用方法：添加组件到面板; 对于GridBagConstraints布局方法的再封装
     * @param panel 目标面板
     * @param component 添加对象
     * @param gbc 布局方法
     * @param gridx 组件所在列数
     * @param gridy 组件所在行数
     * @param fillOption 填充方式
     */
    public void addComponent(JPanel panel, JComponent component, GridBagConstraints gbc, int gridx, int gridy, int fillOption) {
        addComponent(panel, component, gbc, gridx, gridy, this.weightx, this.weighty, fillOption, this.gridwidth, this.gridheight); 
    }

    /**
     * 通用方法：添加组件到面板; 对于GridBagConstraints布局方法的再封装
     * @param panel 目标面板
     * @param component 添加对象
     * @param gbc 布局方法
     * @param gridx 组件所在列数
     * @param gridy 组件所在行数
     * @param weightx 组件在水平方向上分配额外空间的权重
     * @param weighty 组件在垂直方向上分配额外空间的权重
     * @param fillOption 填充方式
     * @param gridwidth 组件跨越列数
     * @param gridheight 组件跨越行数
     */
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



}
