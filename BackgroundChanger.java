package effects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackgroundChanger {

    BufferedImage image = null;
    BufferedImage background = null;
    File imagePath = null;
    File backgroundPath = null;

    private BackgroundChanger() {

    }

    public BackgroundChanger(File imagePath, File backgroundPath) {
        this.imagePath = imagePath;
        this.backgroundPath = backgroundPath;
    }

    public boolean readImages() {
        try {

            image = ImageIO.read(imagePath);
            background = ImageIO.read(backgroundPath);

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean writeImage(String path) {
        try {
            ImageIO.write(image, "jpg", new File(path));
            return true;
        } catch (IOException e) {
            System.out.println("Exception : " + e);
            return false;
        }
    }

    public void applyEffects() {
        int imageHeight = image.getHeight();
        int imageWidth = image.getWidth();

        int backgroundHeight = background.getHeight();
        int backgroundWidth = background.getWidth();

        for (int y = 0; y < imageHeight && y < backgroundHeight; y++) {
            for (int x = 0; x < imageWidth && x < backgroundWidth; x++) {
                int p = image.getRGB(x, y);

                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                if (g > 200 && r < 30 && b < 30) {
                    p = background.getRGB(x, y);
                    image.setRGB(x, y, p);
                }
            }
        }
    }

}
