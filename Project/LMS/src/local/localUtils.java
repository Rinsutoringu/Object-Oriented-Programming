package local;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;

public class localUtils {

    
    // 通用方法：添加组件到面板
    /**
     * 对于GridBagConstraints布局方法的再封装
     * @param panel 目标面板
     * @param component 添加对象
     * @param gbc 布局方法
     * @param gridx 组件所在列数
     * @param gridy 组件所在行数
     */
    public void addComponent(JPanel panel, JComponent component, GridBagConstraints gbc, int gridx, int gridy) {
        addComponent(panel, component, gbc, gridx, gridy, 0, 0, 0, 1, 1);
    }
    
    // 重载方法：支持跨行跨列
    /**
     * 对于GridBagConstraints布局方法的再封装
     * @param panel 目标面板
     * @param component 添加对象
     * @param gbc 布局方法
     * @param gridx 组件所在列数
     * @param gridy 组件所在行数
     * @param xsize 设置水平相对尺寸
     * @param ysize 设置垂直相对尺寸
     * @param fillOption 填充方式
     * @param gridwidth 组件跨越列数
     * @param gridheight 组件跨越行数
     */
    public void addComponent(JPanel panel, JComponent component, GridBagConstraints gbc, int gridx, int gridy, int xsize, int ysize, int fillOption, int gridwidth, int gridheight) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.weightx = xsize;
        gbc.weighty = ysize;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.fill = fillOption;
        panel.add(component, gbc);
    }
}
