package local.ui.font;

// 导入相关类
import java.awt.*;
import java.io.InputStream;
import javax.swing.*;

public class FontUtil {
    public static void setGlobalFont(String fontResourcePath, float size) {
        try {
            // 读取字体资源
            InputStream is = FontUtil.class.getResourceAsStream(fontResourcePath);
            Font font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(size);

            // 设置全局字体
            UIManager.put("Label.font", font);
            UIManager.put("Button.font", font);
            UIManager.put("Table.font", font);
            UIManager.put("TableHeader.font", font);
            UIManager.put("TextField.font", font);
            UIManager.put("TextArea.font", font);
            UIManager.put("CheckBox.font", font);
            UIManager.put("RadioButton.font", font);
            UIManager.put("ComboBox.font", font);
            UIManager.put("Menu.font", font);
            UIManager.put("MenuItem.font", font);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Font loadCustomFont(String fontPath, float size) {
        try (InputStream is = FontUtil.class.getResourceAsStream(fontPath)) {
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            return font.deriveFont(size);
        } catch (Exception e) {
            e.printStackTrace();
            return new Font("SansSerif", Font.PLAIN, (int) size);
        }
    }
}