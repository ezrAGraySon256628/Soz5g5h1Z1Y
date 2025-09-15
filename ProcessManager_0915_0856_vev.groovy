// 代码生成时间: 2025-09-15 08:56:51
class ProcessManager {

    // Method to list all running processes
    List<Map<String, Object>> listProcesses() {
        try {
            // Command to list all processes
            String command = 'ps -ef'
            // Execute the command and capture the output
            def process = command.execute()
            process.waitFor()
            // Read the output of the command
            def processOutput = process.text.trim()
            // Split the output into lines and parse each line as a process
            List<Map<String, Object>> processes = processOutput.readLines().drop(1).collect {
                // Split each line by whitespace and create a map of process details
                it.split(/\s+/).findAll { it }.collectEntries {
                    [intToColumnIndex(it[0]), it[1..-1].join(' ')]
                }
            }
            return processes
        } catch (Exception e) {
            // Handle exceptions and return an empty list
            e.printStackTrace()
            return []
        }
    }

    // Method to kill a process by its pid
    boolean killProcessByPid(int pid) {
        try {
            // Command to kill a process by pid
            String command = "kill -9 ${pid}"
            // Execute the command
            command.execute().waitFor()
            return true
        } catch (Exception e) {
            // Handle exceptions and return false
            e.printStackTrace()
            return false
        }
    }

    // Helper method to convert a character to its corresponding column index
    private int intToColumnIndex(char c) {
        // Mapping of character to column index based on ps command output
        switch (c) {
            case 'U': return 0 // User
            case '%': return 1 // CPU usage
            case 'T': return 2 // TTY
            case 'S': return 3 // Process state
            case 'T': return 4 // Time started
            case 'C': return 5 // Command name
            default: return -1
        }
    }

    // Main method to test the ProcessManager class
    static void main(String[] args) {
        def processManager = new ProcessManager()

        // List all processes
        processManager.listProcesses().each { process ->
            println "User: ${process.User}, CPU: ${process.CPU}, TTY: ${process.TTY}, State: ${process.State}, Time: ${process.Time}, Command: ${process.Command}"
        }

        // Example: Kill a process by pid
        // int pid = 1234 // Replace with actual pid
        // processManager.killProcessByPid(pid)
    }
}