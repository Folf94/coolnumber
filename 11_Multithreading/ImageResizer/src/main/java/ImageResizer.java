import org.imgscalr.Scalr;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;


public class ImageResizer extends Thread {

    private File[] files;
    private int newWidth;
    private String dstFolder;
    private Long start;

    public ImageResizer(File[] files, int newWidth, String dstFolder, Long start) {
        this.files = files;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.start = start;
    }

    @Override
    public void run() {
    bufferedImageScal();
    }

    public void bufferedImageScal()

    {
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }

                int newHeight = (int) Math.round(image.getHeight() / (image.getWidth() / (double) newWidth));

                BufferedImage scal = Scalr.resize(image, newWidth, newHeight, Scalr.OP_ANTIALIAS);

                int widthStep = image.getWidth() / newWidth;
                int heightStep = image.getHeight() / newHeight;

                for (int x = 0; x < newWidth; x++) {
                    for (int y = 0; y < newHeight; y++) {
                        int rgb = image.getRGB(x * widthStep, y * heightStep);
                        scal.setRGB(x, y, rgb);
                    }
                }
                String readerNames[] = ImageIO.getReaderFormatNames();
                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(scal, String.valueOf(readerNames), newFile);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("ImageResize time: " + (System.currentTimeMillis() - start) + " ms");
    }
}

