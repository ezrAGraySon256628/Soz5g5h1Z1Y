// 代码生成时间: 2025-08-07 20:50:13
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import groovy.io.FileType

/**
 * Excel表格自动生成器
 * 此脚本用于生成Excel表格文件
 * @author 你的姓名
 * @date 2023年4月9日
 */
class ExcelGenerator {

    /**
     * 生成Excel文件
     * @param filePath 文件路径
     * @param data 要写入的数据
     * @return 生成的文件路径
     */
    def generateExcel(String filePath, List<List<String>> data) {
        try {
            // 创建工作簿
            def workbook = new XSSFWorkbook()
            def sheet = workbook.createSheet('Sheet1')

            // 写入数据
            data.eachWithIndex { List<String> row, int rowIndex ->
                def cellRow = sheet.createRow(rowIndex)
                row.eachWithIndex { String cellData, int cellIndex ->
                    def cell = cellRow.createCell(cellIndex)
                    cell.setCellValue(cellData)
                }
            }

            // 将工作簿写入文件
            def fileOut = new FileOutputStream(filePath)
            workbook.write(fileOut)
            fileOut.close()

            // 关闭工作簿
            workbook.close()

            // 返回生成的文件路径
            return filePath
        } catch (Exception e) {
            // 错误处理
            e.printStackTrace()
            return null
        }
    }

    /**
     * 测试生成Excel文件
     */
    static void main(String[] args) {
        // 创建实例
        ExcelGenerator generator = new ExcelGenerator()

        // 要写入的数据
        List<List<String>> data = [["Header1", "Header2", "Header3"], ["Data1", "Data2", "Data3"], ["Data4", "Data5", "Data6"]]

        // 生成Excel文件
        String filePath = 'output.xlsx'
        String generatedFilePath = generator.generateExcel(filePath, data)

        // 打印生成的文件路径
        if (generatedFilePath) {
            println "Excel文件已生成在路径: ${generatedFilePath}"
        } else {
            println "Excel文件生成失败"
        }
    }
}
