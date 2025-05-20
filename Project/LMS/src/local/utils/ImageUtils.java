package local.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
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
     * Load image from file
     * @param filePath Relative path of the image
     * @return Loaded image object, or null if loading fails
     */
    public static Image loadImage(String filePath) {
        try {
            return ImageIO.read(ImageUtils.class.getResourceAsStream(filePath));
        } catch (Exception e) {
            System.err.println("Load img failed: " + filePath);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Process the loaded image
     * @param img Image object read by loadImage
     * @return Image stored in buffer
     */
    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        };

        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return bufferedImage;
    }

    /**
     * Convert the input image to JLabel
     * @param bufferimg Image object
     * @param x JLabel Width
     * @param y JLabel Height
     * @param border Margin
     * @return Converted JLabel image
     */
    public static JLabel imgToJLable(BufferedImage bufferimg, int x, int y, int border) {
        int originWidth = bufferimg.getWidth();
        int originalHeight = bufferimg.getHeight();
        int newWidth;
        int newHeight;
        JLabel iconJLabel = new JLabel();
        iconJLabel.setSize(x, y);

        if (!(originWidth > x || originalHeight < y)) {
            iconJLabel.setIcon(new ImageIcon(bufferimg));
            return iconJLabel;
        }

        double aspectRatio = (double) originWidth / originalHeight;

        if (originWidth > originalHeight) {
            newHeight = (int) y - border;
            newWidth = (int) ((newHeight) * aspectRatio);
        } else {
            newWidth = (int) x - border;
            newHeight = (int) ((newWidth) * aspectRatio);
        }

        Image scalImage = bufferimg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        BufferedImage scaledBufferedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledBufferedImage.createGraphics();
        g2d.drawImage(scalImage, 0, 0, null);
        g2d.dispose();

        iconJLabel.setIcon(new ImageIcon(scaledBufferedImage));
        return iconJLabel;
    }

    /**
     * Calculate scaling
     * @param OriginIMGSize Original image size stored as Point
     * @param BorderSize Window size stored as Point
     * @return Scaled image size
     */
    public static Point zoom(Point OriginIMGSize, Point BorderSize) {
        double oldWidth = OriginIMGSize.getX();
        double oldHeight = OriginIMGSize.getY();
        double aspectRatio = oldWidth / oldHeight;
        double BorderWidth = BorderSize.getX();
        double BorderHeight = BorderSize.getY();
        double newWidth;
        double newHeight;
        if (oldWidth > BorderWidth) {
            newHeight = BorderHeight;
            newWidth = (newHeight) * aspectRatio;
        } else {
            newWidth = BorderWidth;
            newHeight = (newWidth) * aspectRatio;
        }
        return new Point((int) newWidth, (int) newHeight);
    }

    /**
     * Get the width and height of a graphic, requires BufferedImage
     * @param bfIMG Image to measure
     * @return Width and height as Point
     */
    public static Point getSizeBfIMG(BufferedImage bfIMG) {
        return new Point(bfIMG.getWidth(), bfIMG.getHeight());
    }
}