// 代码生成时间: 2025-09-12 09:20:12
class SystemPerformanceMonitor {

    /**
     * Retrieves CPU usage as a percentage.
     * @return CPU usage percentage
     */
    double getCpuUsage() {
        try {
            def cpuLoad = ManagementFactory.operatingSystemMXBean.systemCpuLoad
            return cpuLoad * 100
        } catch (Exception e) {
            println "Error retrieving CPU usage: ${e.message}"
            throw e
        }
    }

    /**
     * Retrieves memory usage in megabytes.
     * @return Memory usage in MB
     */
    long getMemoryUsage() {
        try {
            def runtime = Runtime.getRuntime()
            def totalMemory = runtime.totalMemory() / (1024 * 1024)
            def freeMemory = runtime.freeMemory() / (1024 * 1024)
            return totalMemory - freeMemory
        } catch (Exception e) {
            println "Error retrieving memory usage: ${e.message}"
            throw e
        }
    }

    /**
     * Retrieves disk usage in megabytes.
     * @return Disk usage in MB
     */
    long getDiskUsage() {
        try {
            def fileStore = FileStoreUtils.getFileSystemRoots()[0]
            def usage = fileStore.getUsableSpace() / (1024 * 1024)
            return usage
        } catch (Exception e) {
            println "Error retrieving disk usage: ${e.message}"
            throw e
        }
    }

    /**
     * Main method to run the performance monitor.
     * @param args Command line arguments
     */
    static void main(String[] args) {
        def monitor = new SystemPerformanceMonitor()
        println "CPU Usage: ${monitor.getCpuUsage()}%"
        println "Memory Usage: ${monitor.getMemoryUsage()}MB"
        println "Disk Usage: ${monitor.getDiskUsage()}MB"
    }
}
