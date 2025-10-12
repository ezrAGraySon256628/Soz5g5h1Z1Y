// 代码生成时间: 2025-10-12 20:09:58
package tools

import groovy.io.FileType
import groovy.io.FileUtil

/**
 * A utility class for splitting and merging files.
 * @author Your Name
 * @version 1.0
 */
class FileSplitMergeTool {

    /**
     * Splits a large file into smaller chunks.
     *
     * @param fileToSplit The file to be split.
     * @param chunkSize The size of each chunk in bytes.
     */
    static void splitFile(File fileToSplit, int chunkSize) {
        if (!fileToSplit.exists()) {
            throw new IllegalArgumentException("File does not exist: ${fileToSplit.path}")
        }

        int chunkNumber = 0
        long fileSize = fileToSplit.length()
        byte[] buffer = new byte[chunkSize]
        int bytesRead

        while ((bytesRead = fileToSplit.newDataInputStream().read(buffer)) != -1) {
            File chunkFile = new File(fileToSplit.parent, "chunk_${chunkNumber++}.${fileToSplit.name}")
            chunkFile.withDataOutputStream { dos ->
                dos.write(buffer, 0, bytesRead)
            }
        }
    }

    /**
     * Merges multiple file chunks into a single file.
     *
     * @param destinationFile The file to write the merged content to.
     * @param chunkFiles The list of chunk files to merge.
     */
    static void mergeFiles(File destinationFile, List<File> chunkFiles) {
        if (chunkFiles.isEmpty()) {
            throw new IllegalArgumentException("No files to merge")
        }

        if (!destinationFile.exists()) {
            destinationFile.createNewFile()
        }

        chunkFiles.each { chunkFile ->
            chunkFile.eachByte { byteValue ->
                destinationFile << byteValue
            }
        }
    }

    /**
     * Main method to test the functionality of the FileSplitMergeTool.
     *
     * @param args Command line arguments.
     */
    static void main(String[] args) {
        // Example usage:
        // To split a file: FileSplitMergeTool.splitFile(new File("largeFile.txt"), 1024 * 1024)
        // To merge files: FileSplitMergeTool.mergeFiles(new File("mergedFile.txt"), [new File("chunk_0.largeFile.txt\), new File("chunk_1.largeFile.txt")])
        
        try {
            // Splitting a file into chunks of 1MB each.
            File largeFile = new File("largeFile.txt")
            int chunkSize = 1024 * 1024 // 1MB
            FileSplitMergeTool.splitFile(largeFile, chunkSize)
            println "File split successfully."

            // Merging chunks back into the original file.
            File mergedFile = new File("mergedFile.txt")
            List<File> chunks = largeFile.parent.listFiles({ dir, name ->
                name.startsWith("chunk_") && name.endsWith(".largeFile.txt")
            } as FilenameFilter)
            FileSplitMergeTool.mergeFiles(mergedFile, chunks)
            println "Files merged successfully."
        } catch (Exception e) {
            println "Error: ${e.message}"
        }
    }
}
