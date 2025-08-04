// 代码生成时间: 2025-08-04 09:10:04
package com.example.errorlog

import groovy.util.logging.Slf4j
import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import org.springframework.stereotype.Service

import java.io.File
import java.io.FileInputStream
import java.io.IOException

/**
 * Service class to collect error logs.
 */
@Service
@Slf4j
class ErrorLogCollector implements Serializable {

    /**
     * Collects error logs from a specified file.
     *
     * @param filePath The path to the log file.
     * @return A list of error log entries.
     * @throws IOException If an I/O error occurs.
     */
    List<String> collectErrorLogs(String filePath) throws IOException {
        List<String> errorLogs = []
        try {
            File file = new File(filePath)
            if (!file.exists()) {
                log.error("Log file does not exist: \${filePath}")
                return errorLogs
            }

            FileInputStream fis = new FileInputStream(file)
            List<String> lines = IOUtils.readLines(fis, "UTF-8")
            fis.close()

            // Filter lines to find error logs (e.g., lines containing 'ERROR')
            errorLogs = lines.findAll { line -> line.contains("ERROR") }

        } catch (IOException e) {
            log.error("Error collecting error logs: \${e.message}", e)
            throw e
        }
        return errorLogs
    }

    /**
     * Saves collected error logs to a new file.
     *
     * @param errorLogs A list of error log entries.
     * @param destinationPath The path to save the error logs.
     * @throws IOException If an I/O error occurs.
     */
    void saveErrorLogs(List<String> errorLogs, String destinationPath) throws IOException {
        try {
            File outputFile = new File(destinationPath)
            if (!outputFile.getParentFile().exists() && !outputFile.getParentFile().mkdirs()) {
                log.error("Failed to create output directory: \${outputFile.parent}")
                return
            }

            FileUtils.writeLines(outputFile, errorLogs)
            log.info("Error logs saved to: \${destinationPath}")
        } catch (IOException e) {
            log.error("Error saving error logs: \${e.message}", e)
            throw e
        }
    }
}
