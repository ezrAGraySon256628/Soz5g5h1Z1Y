// 代码生成时间: 2025-08-09 05:39:25
package com.example.tools

import groovy.io.FileType
import groovy.json.JsonSlurper
import groovy.util.logging.Slf4j
import org.apache.commons.io.FileUtils

@Slf4j
class FileBackupAndSyncTool {

    // 源文件夹路径
    private String sourceFolderPath
    // 备份文件夹路径
    private String backupFolderPath

    // 构造函数
    FileBackupAndSyncTool(String sourceFolderPath, String backupFolderPath) {
        this.sourceFolderPath = sourceFolderPath
        this.backupFolderPath = backupFolderPath
    }

    // 执行文件备份和同步操作
    void backupAndSync() {
        try {
            if (!new File(sourceFolderPath).exists()) {
                log.error "Source folder does not exist: $sourceFolderPath"
                return
            }
            if (!new File(backupFolderPath).exists()) {
                log.error "Backup folder does not exist: $backupFolderPath"
                return
            }

            // 遍历源文件夹
            new File(sourceFolderPath).eachFileRecurse(FileType.FILES) { file ->
                // 构建源文件和备份文件的完整路径
                String relativePath = file.path.substring(sourceFolderPath.length() + 1)
                File backupFile = new File("$backupFolderPath/$relativePath")

                // 检查备份文件是否存在，如果不存在则复制
                if (!backupFile.exists()) {
                    FileUtils.copyFile(file, backupFile)
                    log.info "Backup file created: $backupFile.path"
                } else {
                    // 如果备份文件存在，比较源文件和备份文件的修改时间
                    if (file.lastModified() > backupFile.lastModified()) {
                        FileUtils.copyFile(file, backupFile)
                        log.info "Backup file updated: $backupFile.path"
                    }
                }
            }
        } catch (Exception e) {
            log.error "Error occurred during backup and sync: ${e.message}"
        }
    }

    // 主函数，用于执行备份和同步操作
    static void main(String[] args) {
        // 配置源文件夹和备份文件夹路径
        String sourceFolderPath = "/path/to/source"
        String backupFolderPath = "/path/to/backup"

        // 创建工具实例并执行备份和同步操作
        new FileBackupAndSyncTool(sourceFolderPath, backupFolderPath).backupAndSync()
    }
}
