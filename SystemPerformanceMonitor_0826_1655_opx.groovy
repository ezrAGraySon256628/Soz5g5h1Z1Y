// 代码生成时间: 2025-08-26 16:55:50
package monitoring

import groovy.lang.Script
import java.lang.management.ManagementFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

// Service class to monitor system performance
@Service
class SystemPerformanceMonitor implements Script {

    // Autowiring the logger
    @Autowired
    private Logger log

    // Method to get CPU usage
    double getCpuUsage() {
        def cpuLoad = ManagementFactory.operatingSystemMXBean.systemCpuLoad
        return Double.parseDouble(cpuLoad.toString()) * 100
    }

    // Method to get memory usage
    double getMemoryUsage() {
        def memory = ManagementFactory.memoryMXBean.heapMemoryUsage
        return (memory.used / memory.max) * 100
    }

    // Method to get disk usage
    double getDiskUsage(String mountPath) {
        def fileStore = ManagementFactory.fileDescriptor.getFileSystem(fileStore).fileStore
        return (fileStore.usableSpace / fileStore.totalSpace) * 100
    }

    // Method to log the system performance
    void logSystemPerformance() {
        try {
            double cpuUsage = getCpuUsage()
            double memoryUsage = getMemoryUsage()
            double diskUsage = getDiskUsage('/')

            log.info("CPU Usage: ${cpuUsage}%")
            log.info("Memory Usage: ${memoryUsage}%")
            log.info("Disk Usage: ${diskUsage}%")

        } catch (Exception e) {
            log.error("Error monitoring system performance", e)
        }
    }

    // Main method to run the performance monitor
    static void main(String[] args) {
        SystemPerformanceMonitor monitor = new SystemPerformanceMonitor()
        monitor.logSystemPerformance()
    }
}
