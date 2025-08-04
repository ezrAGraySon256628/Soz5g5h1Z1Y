// 代码生成时间: 2025-08-04 15:55:33
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Utility class for unzipping files using Java.
 *
 * @author YourName
 * @version 1.0
 */
public class UnzipUtility {

    /**
     * Unzips a given zip file to the specified destination directory.
     *
     * @param zipFilePath The path to the zip file to be unzipped.
     * @param destinationDirectory The path to the directory where the files will be extracted.
     *
     * @throws IOException if an I/O error occurs.
     */
    public void unzip(String zipFilePath, String destinationDirectory) throws IOException {
        File destDir = new File(destinationDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }

        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry();
        // Iterates over the entries in the zip file
        while (entry != null) {
            String filePath = destinationDirectory + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                // If the entry is a file, extracts it
                extractFile(zipIn, filePath);
            } else {
                // If the entry is a directory, make the directory
                File dir = new File(filePath);
                dir.mkdir();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
    }

    /**
     * Extracts a single file from the zip input stream to the specified path.
     *
     * @param zipIn The zip input stream.
     * @param filePath The path to extract the file to.
     * @throws IOException if an I/O error occurs.
     */
    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[4096];
        int read = 0;
        // Reads bytes from the zip input stream and writes them to the file
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }

    /**
     * Main method to test the UnzipUtility class.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        try {
            UnzipUtility unzipper = new UnzipUtility();
            String zipFilePath = "path/to/your/file.zip";
            String destinationDirectory = "path/to/destination/folder";
            unzipper.unzip(zipFilePath, destinationDirectory);
            System.out.println("Unzipped successfully!");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
