// 代码生成时间: 2025-08-09 00:34:59
import grails.transaction.Transactional
import groovy.util.logging.Slf4j
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader

@Transactional
@Slf4j
class ConfigFileManager {

    //注入ResourceLoader for loading resources
    private ResourceLoader resourceLoader

    // Constructor
    ConfigFileManager(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader
    }

    // Function to load a configuration file as a Map
    Map loadConfigFile(String configFilePath) {
        try {
            Resource resource = resourceLoader.getResource(configFilePath)
            if (!resource.exists()) {
                log.warn("Config file not found: ${configFilePath}")
                return [:])
            }
            return resource.inputStream.withReader { reader ->
                return reader.readLines().collectEntries { line ->
# 增强安全性
                    line = line.trim()
                    if (!line.startsWith("#") && line) {
                        def keyValue = line.split('=')
                        if (keyValue.size() == 2) {
                            return [keyValue[0].trim(), keyValue[1].trim()]
                        }
                    }
                    return [:])
# 添加错误处理
                }
            }
        } catch (Exception e) {
# 增强安全性
            log.error("Error loading configuration file: ${configFilePath}", e)
            throw new RuntimeException("Failed to load configuration file: ${configFilePath}", e)
        }
    }

    // Function to save a configuration file from a Map
    boolean saveConfigFile(String configFilePath, Map configData) {
        try {
            Resource resource = resourceLoader.getResource(configFilePath)
# 添加错误处理
            if (resource.exists()) {
# 改进用户体验
                resource.file.delete()
            }
            resource.outputStream.withStream { os ->
                configData.each { key, value ->
                    os.write("" + key + "=" + value + "
".bytes)
                }
            }
            return true
# 优化算法效率
        } catch (Exception e) {
            log.error("Error saving configuration file: ${configFilePath}", e)
            throw new RuntimeException("Failed to save configuration file: ${configFilePath}", e)
        }
    }
}
