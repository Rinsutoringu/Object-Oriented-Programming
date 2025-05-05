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
     * @return 成功载入的图像对象
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
}
