// 代码生成时间: 2025-08-26 07:48:23
import java.lang.management.ManagementFactory
import java.lang.management.MemoryMXBean
import java.lang.management.MemoryUsage
import groovy.transform.CompileStatic

@CompileStatic
class MemoryUsageAnalyzer {
    /**
     * Prints memory usage statistics.
     *
     * @return A string containing memory usage statistics.
     */
    String analyzeMemoryUsage() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean()
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage()
        MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage()
        
        String heapMemoryUsed = bytesToMegabytes(heapMemoryUsage.getUsed())
        String heapMemoryMax = bytesToMegabytes(heapMemoryUsage.getMax())
        String nonHeapMemoryUsed = bytesToMegabytes(nonHeapMemoryUsage.getUsed())
        String nonHeapMemoryMax = bytesToMegabytes(nonHeapMemoryUsage.getMax())
        
        return "Heap Memory Used: ${heapMemoryUsed} MB
Heap Memory Max: ${heapMemoryMax} MB

Non-Heap Memory Used: ${nonHeapMemoryUsed} MB
Non-Heap Memory Max: ${nonHeapMemoryMax} MB"
    }
    
    /**
     * Converts bytes to megabytes.
     *
     * @param bytes The number of bytes to convert.
     * @return The number of megabytes.
     */
    private static String bytesToMegabytes(long bytes) {
        String megabytes = String.format('%.2f', bytes / (1024 * 1024))
        return megabytes + ' MB'
    }

    /**
     * Entry point for the program.
     *
     * @param args Command-line arguments (not used).
     */
    static void main(String[] args) {
        try {
            MemoryUsageAnalyzer analyzer = new MemoryUsageAnalyzer()
            String memoryStatistics = analyzer.analyzeMemoryUsage()
            println memoryStatistics
        } catch (Exception e) {
            println "An error occurred while analyzing memory usage: $e.message"
        }
    }
}
