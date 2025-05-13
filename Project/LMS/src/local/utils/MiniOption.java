package local.utils;

/**
 * MiniOption类
 * 该类是一个简单的选项类，用于存储选项的标题和消息
 * 主要用于在UI中显示一些简单的选项
 */
public class MiniOption extends  javax.swing.JOptionPane{

    public MiniOption(String title, String message, int messageType) {
        showMessageDialog(null, title, message, messageType);
        this.setVisible(true);
    }
}
