// 代码生成时间: 2025-09-14 08:31:51
package com.example.bulkrenamer

import groovy.io.FileType
import java.nio.file.Paths
import java.nio.file.Files

/**
 * BulkFileRenamer is a Grails script application for batch file renaming.
 */
class BulkFileRenamer {

    static void main(String[] args) {
        // Check for command line arguments
        if (args.length < 2) {
            println "Usage: BulkFileRenamer <directory> <prefix>"
            return
        }

        // Extract command line arguments
        String directoryPath = args[0]
        String prefix = args[1]

        // Validate the directory
        File directory = new File(directoryPath)
        if (!directory.exists() || !directory.isDirectory()) {
            println "The specified directory does not exist or is not a directory."
            return
        }

        int count = renameFiles(directory, prefix)
        println "Renamed $count files."
    }

    /**
     * Renames files in the given directory with the specified prefix.
     *
     * @param directory The directory containing the files to rename
     * @param prefix The prefix to prepend to each file name
     * @return The number of files renamed
     */
    static int renameFiles(File directory, String prefix) {
        int count = 0
        directory.eachFileMatch(~/.*/) { File file ->
            try {
                // Construct the new file name with the prefix
                String fileName = "${prefix}_${file.name}"
                File newFile = new File(file.parent, fileName)

                // Rename the file
                if (!newFile.exists()) {
                    boolean success = file.renameTo(newFile)
                    if (success) {
                        println "Renamed ${file.name} to ${newFile.name}"
                        count++
                    } else {
                        println "Failed to rename ${file.name} to ${newFile.name}"
                    }
                } else {
                    println "Skipping ${file.name} as the target file ${fileName} already exists."
                }
            } catch (IOException e) {
                println "Error renaming ${file.name}: ${e.message}"
            }
        }
        return count
    }
}
