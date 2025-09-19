// 代码生成时间: 2025-09-19 22:52:33
import grails.transaction.Transactional
import org.springframework.web.multipart.MultipartFile
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import groovy.json.JsonSlurper
import groovy.json.JsonBuilder
import org.apache.commons.io.IOUtils
import java.nio.charset.StandardCharsets

// 交互式图表生成器
@Transactional
class InteractiveChartGeneratorService {

    // 保存图表数据文件
    def saveChartDataFile(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new IllegalArgumentException("文件不能为空")
            }
            // 存储文件逻辑，这里省略
            // 假设存储后返回文件路径
            return "/path/to/saved/file"
        } catch (Exception e) {
            // 错误处理
            throw new RuntimeException("保存图表数据文件失败", e)
        }
    }

    // 生成图表
    def generateChart(String dataFilePath) {
        try {
            if (!dataFilePath) {
                throw new IllegalArgumentException("数据文件路径不能为空")
            }
            // 读取文件并生成图表逻辑，这里省略
            // 假设生成图表后返回图表的URL
            return "http://example.com/chart.png"
        } catch (Exception e) {
            // 错误处理
            throw new RuntimeException("生成图表失败", e)
        }
    }

    // REST API端点，用于上传图表数据文件
    def uploadChartDataFile(MultipartFile file) {
        def dataFilePath = saveChartDataFile(file)
        return ResponseEntity.ok(new JsonBuilder([dataFilePath: dataFilePath]).toString())
    }

    // REST API端点，用于根据上传的数据生成图表
    def generateChartFromDataFile(String dataFilePath) {
        def chartUrl = generateChart(dataFilePath)
        return ResponseEntity.ok(new JsonBuilder([chartUrl: chartUrl]).toString())
    }
}
