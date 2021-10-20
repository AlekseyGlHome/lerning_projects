import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ImageResizer extends Thread {

    private final String dstFolder;
    private final ConcurrentLinkedQueue<File> files;
    private final Long start;
    private final int newWidth;

    public ImageResizer(int newWidth, String dstFolder, ConcurrentLinkedQueue<File> files, Long start) {
        this.dstFolder = dstFolder;
        this.files = files;
        this.start = start;
        this.newWidth = newWidth;
    }

    @Override
    public void run() {
        try {
            while (files.size() > 0) {
                File file;
                if ((file = files.poll()) != null) {
                    BufferedImage image = ImageIO.read(file);
                    if (image == null) {
                        continue;
                    }
                    resizeImg(file.getName(), image);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("Finished after start: " + (System.currentTimeMillis() - start) + " ms.");
    }

    private void resizeImg(String fileName, BufferedImage image) throws IOException {
        int newHeight = (int) Math.round(
                image.getHeight() / (image.getWidth() / (double) newWidth)
        );
        BufferedImage newImage = Scalr.resize(image, Scalr.Method.QUALITY, Scalr.Mode.AUTOMATIC, newWidth, newHeight);
        File newFile = new File(dstFolder + "/" + fileName);
        ImageIO.write(newImage, "jpg", newFile);
    }

}
