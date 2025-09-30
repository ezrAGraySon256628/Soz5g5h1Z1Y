// 代码生成时间: 2025-09-30 18:24:56
import grails.util.Environment
import groovy.io.FileType
import groovy.text.SimpleTemplateEngine
import org.apache.commons.io.FileUtils

/**
 * TextFileAnalyzer: A Grails service that analyzes text file contents.
 * This service provides methods to read and analyze text files.
 */
class TextFileAnalyzer {

    /**
     * Reads a text file and returns its content.
     *
     * @param filePath The path to the text file to read.
     * @return The content of the text file as a String.
     * @throws IOException If the file cannot be read.
     */
    String readFileContent(String filePath) {
        try {
            return FileUtils.readFileToString(new File(filePath), "UTF-8")
        } catch (IOException e) {
            throw new IOException("Failed to read file: ${filePath}. Reason: ${e.message}", e)
        }
    }

    /**
     * Analyzes the content of a text file for specified patterns.
     *
     * @param filePath The path to the text file to analyze.
     * @param pattern The pattern to search for in the file content.
     * @return A list of occurrences of the pattern in the file content.
     */
    List<String> analyzeFileContent(String filePath, String pattern) {
        List<String> matches = []
        String content = readFileContent(filePath)
        try {
            content.eachLine {
                String line ->
                if (line.contains(pattern)) {
                    matches.add(line)
                }
            }
        } catch (IOException e) {
            throw new IOException("Failed to analyze file: ${filePath}. Reason: ${e.message}", e)
        }
        return matches
    }

    /**
     * Writes analysis results to a new text file.
     *
     * @param analysisResults The results to write to the file.
     * @param outputFilePath The path to the output file.
     * @throws IOException If the file cannot be written.
     */
    void writeAnalysisResults(List<String> analysisResults, String outputFilePath) {
        try {
            new File(outputFilePath).write(analysisResults.join("
"), "UTF-8")
        } catch (IOException e) {
            throw new IOException("Failed to write analysis results to file: ${outputFilePath}. Reason: ${e.message}", e)
        }
    }
}
