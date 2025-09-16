// 代码生成时间: 2025-09-17 04:23:07
import org.apache.commons.lang3.SystemUtils
import java.lang.management.ManagementFactory
import java.lang.management.MemoryMXBean
import java.lang.management.MemoryUsage
import groovy.util.logging.Slf4j

// MemoryAnalysisService class to analyze memory usage
@Slf4j // Enable logging
class MemoryAnalysisService {

    // Get memory usage statistics
    def getMemoryUsage() {
        try {
            MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean()
            MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage()
            MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage()

            // Return memory usage statistics as a map
            return [
                heapMemoryUsage: heapMemoryUsage,
                nonHeapMemoryUsage: nonHeapMemoryUsage
            ]
        } catch (Exception e) {
            log.error('Error retrieving memory usage statistics', e)
            throw e
        }
    }

    // Display memory usage statistics
    def displayMemoryUsage() {
        MemoryUsage heapMemoryUsage = getMemoryUsage().heapMemoryUsage
        MemoryUsage nonHeapMemoryUsage = getMemoryUsage().nonHeapMemoryUsage

        println '
Memory Usage Statistics'
        println 'Heap Memory Usage:'
        println "  Initial: ${heapMemoryUsage.getInit() / (1024 * 1024)} MB"
        println "  Used: ${heapMemoryUsage.getUsed() / (1024 * 1024)} MB"
        println "  Committed: ${heapMemoryUsage.getCommitted() / (1024 * 1024)} MB"
        println "  Max: ${heapMemoryUsage.getMax() / (1024 * 1024)} MB"
        println 'Non-Heap Memory Usage:'
        println "  Initial: ${nonHeapMemoryUsage.getInit() / (1024 * 1024)} MB"
        println "  Used: ${nonHeapMemoryUsage.getUsed() / (1024 * 1024)} MB"
        println "  Committed: ${nonHeapMemoryUsage.getCommitted() / (1024 * 1024)} MB"
        println "  Max: ${nonHeapMemoryUsage.getMax() / (1024 * 1024)} MB"
    }
}

// Main class to run the memory analysis service
class MemoryAnalysisMain {
    static MemoryAnalysisService memoryAnalysisService = new MemoryAnalysisService()

    static void main(String[] args) {
        try {
            memoryAnalysisService.displayMemoryUsage()
        } catch (Exception e) {
            println 'Error running memory analysis: ' + e.message
        }
    }
}