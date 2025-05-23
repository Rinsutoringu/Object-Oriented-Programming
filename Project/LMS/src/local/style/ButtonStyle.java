package local.style;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class ButtonStyle {
    public static void applyGlobalButtonStyle() {

        // 按钮样式
        UIManager.put("Button.background", new Color(255, 227, 236));
        UIManager.put("Button.focusPainted", false);
        

        // 复选框样式


        // 文本框样式
        UIManager.put("TextField.background", new Color(245, 245, 245));
        UIManager.put("TextField.foreground", Color.BLACK);
        UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 189, 189), 1),
                new EmptyBorder(0, 0, 0, 0)
        ));

        UIManager.put("PasswordField.background", new Color(245, 245, 245));
        UIManager.put("PasswordField.foreground", Color.BLACK);
        UIManager.put("PasswordField.border", BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 189, 189), 1),
                new EmptyBorder(0, 0, 0, 0)
        ));

        // 标签样式
        UIManager.put("Label.foreground", new Color(97, 97, 97));

        // 面板背景
        UIManager.put("Panel.background", Color.WHITE);

        // 滚动条样式
        UIManager.put("ScrollBar.thumb", new Color(189, 189, 189));
        UIManager.put("ScrollBar.track", new Color(245, 245, 245));

        // 表格样式
        UIManager.put("Table.background", Color.WHITE);
        UIManager.put("Table.foreground", Color.BLACK);
        UIManager.put("Table.gridColor", new Color(224, 224, 224));
        UIManager.put("Table.selectionBackground", new Color(33, 150, 243));
        UIManager.put("Table.selectionForeground", Color.WHITE);
    }
}