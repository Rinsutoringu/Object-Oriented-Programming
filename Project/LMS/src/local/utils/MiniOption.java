package local.utils;

/**
 * MiniOption类
 * 该类是一个简单的选项类，用于存储选项的标题和消息
 * 主要用于在UI中显示一些简单的选项
* @param title 小窗口标题
* @param message 小窗口里显示的消息内容
* @param messageType 小窗口类型 ERROR_MESSAGE, INFORMATION_MESSAGE, WARNING_MESSAGE, QUESTION_MESSAGE, PLAIN_MESSAGE
 */
public class MiniOption extends javax.swing.JOptionPane{
    public MiniOption(String title, String message, int messageType) {
        super();
        showMessageDialog(null, message, title, messageType);
        this.setVisible(true);
        
    }
    // 更新窗口信息
    public void updateMessage(String message) {
        this.setMessage(message);
        repaint();
    }
}
