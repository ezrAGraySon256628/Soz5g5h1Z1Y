// 代码生成时间: 2025-09-16 21:28:38
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A utility class for batch renaming files.
 */
public class BulkFileRenamer {

    // The directory containing the files to be renamed
    private String directoryPath;

    public BulkFileRenamer(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    /**
     * Renames all files in the specified directory based on a given pattern.
     *
     * @param pattern The pattern to use for renaming files.
     * @param extension The file extension to consider for renaming.
     * @throws IOException If an I/O error occurs.
     */
    public void renameFiles(String pattern, String extension) throws IOException {
        // Ensure the directory exists and is a directory.
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IOException("The specified directory does not exist or is not a directory.");
        }

        // Get all files in the directory matching the extension.
        try (Stream<Path> files = Files.walk(Paths.get(directoryPath)).filter(Files::isRegularFile)
                     .filter(p -> p.toString().endsWith("." + extension))) {
            List<File> fileList = files.map(Path::toFile).collect(Collectors.toList());

            // Rename each file based on the pattern.
            int fileIndex = 1;
            for (File file : fileList) {
                String fileName = String.format(pattern, fileIndex++);
                String newFileName = fileName + "." + extension;
                File newFile = new File(directory, newFileName);

                // Check if the new file name already exists to avoid overwriting.
                if (newFile.exists()) {
                    System.err.println("Skipping renaming of ' " + file.getName() + " ' as the new file name ' " + newFileName + " ' already exists.");
                    continue;
                }

                // Rename the file.
                if (!file.renameTo(newFile)) {
                    throw new IOException("Failed to rename file: " + file.getName());
                }
                System.out.println("Renamed ' " + file.getName() + " ' to ' " + newFileName + " ' ");
            }
        }
    }

    /**
     * Main method to run the BulkFileRenamer.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("Usage: BulkFileRenamer <directory path> <pattern> <file extension>");
            System.err.println("Example: BulkFileRenamer /path/to/files " + "newName_%d" + " .txt");
            System.exit(1);
        }

        String directoryPath = args[0];
        String pattern = args[1];
        String extension = args[2];

        try {
            BulkFileRenamer renamer = new BulkFileRenamer(directoryPath);
            renamer.renameFiles(pattern, extension);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}