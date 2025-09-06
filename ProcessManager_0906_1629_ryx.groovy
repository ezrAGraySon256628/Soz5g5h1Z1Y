// 代码生成时间: 2025-09-06 16:29:12
import groovy.lang.Script
import java.lang.management.ManagementFactory
import java.lang.management.RuntimeMXBean
import java.lang.management.ThreadInfo
import java.lang.management.ThreadMXBean

/**
 * ProcessManager is a Grails script that allows for basic process management functionality.
 * It retrieves process information and thread details from the JVM.
 *
 * @author Your Name
 * @version 1.0
 */
class ProcessManager extends Script {

    // Constructor with no-arguments (required for Grails scripts)
    ProcessManager() {
        super()
    }

    /**
     * Prints the details of the current process.
     *
     * @param args Command line arguments (not used in this script)
     */
    void run(String[] args) {
        try {
            RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean()
            List<String> inputArguments = runtimeMXBean.getInputArguments()

            // Print process details
            println "Current Process ID: ${runtimeMXBean.pid}"
            println "Java Command Line Arguments: ${inputArguments.join(', ')}"

            // Print thread information
            ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean()
            long[] threadIds = threadMXBean.getAllThreadIds()
            ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(threadIds, 50)

            println "Thread Information:"
            threadInfos.each { ThreadInfo threadInfo ->
                println "Thread ID: ${threadInfo.threadId}, Name: ${threadInfo.threadName}, State: ${threadInfo.getThreadState().name()}, Locked Monitors: ${threadInfo.lockedMonitors?.size() ?: 'None'}, Locked Synchronizers: ${threadInfo.lockedSynchronizers?.size() ?: 'None'}"
            }
        } catch (Exception e) {
            // Handle any exceptions that might occur
            println "An error occurred while retrieving process details: ${e.message}"
        }
    }
}
