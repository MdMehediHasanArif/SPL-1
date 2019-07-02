package effects;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Average {

    File file = null;
    BufferedImage image = null;

    private Average() {

    }

    public Average(File file) {
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

        int pictureFile[][] = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pictureFile[i][j] = image.getRGB(i, j);
            }

        }

        int output[][] = new int[height][width];

        for (int v = 1; v < height; v++) {
            for (int u = 1; u < width; u++) {
                int sum = 0, sumr = 0, sumg = 0, sumb = 0, newP;
                for (int j = -1; j <= 1; j++) {
                    for (int k = -1; k <= 1; k++) {
                        if ((u + (j) >= 0 && v + (k) >= 0 && u + (j) < width && v + (k) < height)) {
                            int P = pictureFile[v + (k)][u + (j)];
                            int rr = (P >> 16) & 0xff;
                            int rg = (P >> 8) & 0xff;
                            int rb = P & 0xff;
                            sumr += rr;
                            sumg += rg;
                            sumb += rb;
                            sumr /= 9;
                            sumg /= 9;
                            sumb /= 9;
                            newP = (sumr << 16) | (sumg << 8) | sumb;
                            sum = sum + newP;
                        }
                    }
                }

                int q = sum;
                output[v][u] = q;
            }
        }

        BufferedImage theImage = new BufferedImage(
                height,
                width,
                BufferedImage.TYPE_INT_RGB);
        int value;
        for (int y = 1; y < height; y++) {
            for (int x = 1; x < width; x++) {
                value = output[y][x];
                theImage.setRGB(y, x, value);
            }
        }

        image = theImage;
    }
}