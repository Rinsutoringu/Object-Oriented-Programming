package local.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageUtils {

    private static ImageUtils iu;

    private ImageUtils() {}

    public static ImageUtils getInstance() {
        if (iu == null) {
            iu = new ImageUtils();
        }
        return iu;
    }

    /**
     * 从文件载入图像
     * @param filePath 图像的相对路径
     * @return 成功载入的图像对象 若载入失败则报错并返回null
     */
    public static Image loadImage(String filePath) {
        try {
            // 使用 getResourceAsStream 从 JAR 文件中加载资源
            return ImageIO.read(ImageUtils.class.getResourceAsStream(filePath));
        } catch (Exception e) {
            System.err.println("Load img failed: " + filePath);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 对载入的图像进行处理
     * @param img 通过loadImage读取到的图片对象
     * @return 存在buffer里面的图片
     */
    public static BufferedImage toBufferedImage(Image img) {
        
        // 进行类型验证
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        };

        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // 创建对象
        Graphics2D g2d = bufferedImage.createGraphics();
        // 把img里的东西画上去
        g2d.drawImage(img, 0, 0,null);
        // ?为什么你不会被自动清理
        g2d.dispose();

        return bufferedImage;
    }

    /**
     * 将传入的图像处理为JLabel
     * @param bufferimg 读取的图像对象
     * @param x JLabel Width
     * @param y JLabel Height
     * @param border 边距
     * @return 转换为JLable的图像
     */
    public static JLabel imgToJLable(BufferedImage bufferimg, int x, int y, int border) {
        

        int originWidth = bufferimg.getWidth();
        int originalHeight = bufferimg.getHeight();
        int newWidth;
        int newHeight;
        JLabel iconJLabel = new JLabel();
        iconJLabel.setSize(x, y);

        // 要不要转换捏
        if (!(originWidth >x || originalHeight <y)) {
            // 那就用不着转换了~
            iconJLabel.setIcon(new ImageIcon(bufferimg));
            return iconJLabel;
        }
        
        // 得到原始宽高比
        double aspectRatio = (double) originWidth/originalHeight;

        if (originWidth>originalHeight) {
            // 宽大于高，是横着的图像
            newHeight = (int) y-border;
            newWidth = (int) ((newHeight)*aspectRatio);
        } else  {
            // 竖着的图像/方形
            newWidth = (int) x-border;
            newHeight = (int) ((newWidth)*aspectRatio);
        }
        // 缩放！
        Image scalImage = bufferimg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        BufferedImage scaledBufferedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledBufferedImage.createGraphics();
        g2d.drawImage(scalImage, 0, 0,null);
        g2d.dispose();

        iconJLabel.setIcon(new ImageIcon(scaledBufferedImage));
        return iconJLabel;
    }

    /**
     * 计算缩放
     * @param OriginIMGSize Point形式存储的原始图像尺寸
     * @param BorderSize Point形式存储的窗口尺寸
     * @return 缩放后的图像尺寸
     */
    public static Point zoom(Point OriginIMGSize, Point BorderSize) {
        double oldWidth = OriginIMGSize.getX();
        double oldHeight = OriginIMGSize.getY();
        double aspectRatio = oldWidth/oldHeight;
        double BorderWidth = BorderSize.getX();
        double BorderHeight = BorderSize.getY();
        double newWidth;
        double newHeight;
        if (oldWidth>BorderWidth) {
            // 宽大于高，是横着的图像
            newHeight = BorderHeight;
            newWidth = (newHeight)*aspectRatio;
        } else  {
            // 竖着的图像/方形
            newWidth = BorderWidth;
            newHeight = (newWidth)*aspectRatio;
        }
        return new Point((int) newWidth, (int) newHeight);
    } 

    /**
     * 获取某个图形的长和宽，需要BufferedImage
     * @param bfIMG 待测长宽的图片
     * @return 以Point的方式返回长和宽
     */
    public static Point getSizeBfIMG(BufferedImage bfIMG) {
        return new Point(bfIMG.getWidth(), bfIMG.getHeight());
    }
    
}