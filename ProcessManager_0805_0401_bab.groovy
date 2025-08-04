// 代码生成时间: 2025-08-05 04:01:32
import groovy.lang.GroovyObject
import groovy.util.logging.Slf4j

@Slf4j
class ProcessManager implements GroovyObject {

    // 用于执行系统命令
    private ProcessBuilder processBuilder
    // 用于存储进程信息
    private Map<String, Process> runningProcesses = [:]

    ProcessManager() {
        // 初始化ProcessBuilder
        processBuilder = new ProcessBuilder()
    }

    def getProperty(String property) {
        // GroovyObject接口实现，用于动态属性访问
        return this."${property}"
    }

    void setProperty(String property, value) {
        // GroovyObject接口实现，用于动态属性赋值
        this."${property}" = value
    }

    def methodMissing(String name, args) {
        // GroovyObject接口实现，用于动态方法调用
        log.error("Method ${name} with arguments ${args} does not exist.")
        throw new MissingMethodException(name, ProcessManager.class, args)
    }

    // 启动新进程
    void startProcess(String command) {
        try {
            processBuilder.command('bash', '-c', command)
            Process process = processBuilder.start()
            runningProcesses[command] = process
            log.info("Process started: ${command}")
        } catch (IOException e) {
            log.error("Failed to start process: ${command}")
            e.printStackTrace()
        }
    }

    // 终止指定进程
    void stopProcess(String command) {
        Process process = runningProcesses[command]
        if (process) {
            process.destroy()
            runningProcesses.remove(command)
            log.info("Process stopped: ${command}")
        } else {
            log.warn("Process not found: ${command}")
        }
    }

    // 列出所有运行中的进程
    List<String> listRunningProcesses() {
        return runningProcesses.keySet().toList()
    }

    // 获取进程输出
    String getProcessOutput(String command) {
        Process process = runningProcesses[command]
        if (process) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))
                return reader.readLines().join('
')
            } catch (IOException e) {
                log.error("Failed to read output from process: ${command}")
                e.printStackTrace()
            }
        } else {
            log.warn("Process not found: ${command}")
        }
        return null
    }
}
