// 代码生成时间: 2025-09-19 07:53:39
 * Usage:
 * - Run this script in a Grails environment to start monitoring system performance.
 */

import groovy.json.JsonBuilder
import java.lang.management.ManagementFactory
import java.lang.management.OperatingSystemMXBean
import java.lang.management.RuntimeMXBean
import java.lang.management.ThreadInfo
import java.lang.management.ThreadMXBean
import com.sun.management.OperatingSystemMXBean

class SystemPerformanceMonitor {

    // Get the operating system MX bean for system performance metrics
    private OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class)
    // Get the runtime MX bean for JVM performance metrics
    private RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean()
    // Get the thread MX bean for thread performance metrics
    private ThreadMXBean threadBean = ManagementFactory.getThreadMXBean()

    // Method to retrieve CPU usage
    double getCpuUsage() {
        return osBean.systemCpuLoad / 100.0
    }

    // Method to retrieve JVM memory usage
    long getJvmMemoryUsage() {
        def heapMemoryUsage = runtimeBean.heapMemoryUsage
        return heapMemoryUsage.used
    }

    // Method to retrieve JVM memory max
    long getJvmMemoryMax() {
        def heapMemoryUsage = runtimeBean.heapMemoryUsage
        return heapMemoryUsage.max
    }

    // Method to retrieve total number of threads
    int getTotalThreads() {
        return threadBean.threadCount
    }

    // Method to retrieve number of deadlocked threads
    int getDeadlockedThreads() {
        ThreadInfo[] deadlockedThreads = threadBean.findDeadlockedThreads()
        return deadlockedThreads ? deadlockedThreads.length : 0
    }

    // Method to retrieve disk usage
    long getDiskUsage(String path) {
        File file = new File(path)
        return file.getTotalSpace() - file.getFreeSpace()
    }

    // Method to build a JSON object with system performance metrics
    String buildPerformanceReport() {
        Map performanceData = [
            cpuUsage     : getCpuUsage(),
            jvmMemoryUsed: getJvmMemoryUsage(),
            jvmMemoryMax : getJvmMemoryMax(),
            totalThreads  : getTotalThreads(),
            deadlockedThreads: getDeadlockedThreads()
        ]
        // Format the performance data as JSON
        return new JsonBuilder(performanceData).toString()
    }

    // Main method to start the system performance monitoring tool
    static void main(String[] args) {
        SystemPerformanceMonitor monitor = new SystemPerformanceMonitor()
        String performanceReport = monitor.buildPerformanceReport()
        println performanceReport
    }
}
