package effects;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Grayscale {

    File file = null;
    BufferedImage image = null;

    private Grayscale() {

    }

    public Grayscale(File file) {
        this.file = file;
    }

    public boolean readImage() {
        try {
            image = ImageIO.read(file);

            return true;

        } catch (IOException e) {
            System.out.println("Exception : " + e);
            return false;
        }
    }

    public boolean writeImage(String path) {
        try {
            ImageIO.write(image, "jpg", new File(path));

            return true;
        } catch (Exception e) {
            System.out.println("Exception : " + e);
            return false;
        }
    }


    public void applyEffects() {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = image.getRGB(x, y);

                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                int avg = (r + g + b) / 3;

                p = (a << 24) | (avg << 16) | (avg << 8) | avg;

                image.setRGB(x, y, p);
            }
        }
    }
}