package local.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageUtils {

    /**
     * 从文件载入图像
     * @param filePath 图像的相对路径
     * @return 成功载入的图像对象 若载入失败则报错并返回null
     */
    public static Image loadImage(String filePath) {
        try {
            return ImageIO.read(new File(filePath));
        } catch (Exception e) {
            System.err.println("Load img failed"+ filePath);
            e.printStackTrace();
            return null;
        }
    }


    // 对载入的图像进行处理
    public static BufferedImage toBufferedImage(Image img) {
        
        // 进行类型验证
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        };

        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

    }

    /**
     * 将传入的图像处理为JLabel
     * @param image 读取的图像对象
     * @return 转换为JLable的图像
     */
    
    
}