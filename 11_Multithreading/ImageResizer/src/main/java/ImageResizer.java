import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;


public class ImageResizer extends Thread {

    private List<File> files;
    private int newWidth = 300;
    private String dstFolder;
    private Long start;

    public ImageResizer(List<File> files, String dstFolder, Long start) {
        this.files = files;
        this.dstFolder = dstFolder;
        this.start = start;
    }

    @Override
    public void run() {
        bufferedImageScal();
    }

    public void bufferedImageScal() {
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }

                int newHeight = (int) Math.round(image.getHeight() / (image.getWidth() / (double) newWidth));

                BufferedImage scal = Scalr.resize(image, Scalr.Mode.AUTOMATIC, newWidth, newHeight);

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

