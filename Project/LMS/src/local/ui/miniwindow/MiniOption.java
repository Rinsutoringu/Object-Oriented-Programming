package local.ui.miniwindow;

public class MiniOption extends javax.swing.JOptionPane{
    public MiniOption(String title, String message, int messageType) {
        super();
        showMessageDialog(null, message, title, messageType);
        this.setVisible(true);
        
    }
    public void updateMessage(String message) {
        this.setMessage(message);
        repaint();
    }
}
