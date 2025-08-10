// 代码生成时间: 2025-08-11 02:52:07
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.ss.util.CellRangeAddressList
import org.apache.poi.ss.util.WorkbookUtil
import groovy.transform.CompileStatic

/**
 * Excel表格自动生成器
 *
 * @author Your Name
 * @version 1.0
 */
@CompileStatic
class ExcelGenerator {

    /**
     * 使用POI库生成Excel表格
     *
     * @param data 要写入表格的数据，格式为二维列表
     * @param sheetName 工作表名称
     * @return 生成的Excel文件字节流
     */
    static byte[] generateExcel(List<List<Object>> data, String sheetName) {
        try {
            // 创建Excel工作簿
            Workbook workbook = new XSSFWorkbook()
            // 创建工作表
            Sheet sheet = workbook.createSheet(sheetName)
            // 创建样式
            CellStyle headerStyle = workbook.createCellStyle()
            headerStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex())
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND)
            headerStyle.setBorderBottom(BorderStyle.THIN)
            headerStyle.setBorderLeft(BorderStyle.THIN)
            headerStyle.setBorderRight(BorderStyle.THIN)
            headerStyle.setBorderTop(BorderStyle.THIN)
            headerStyle.setAlignment(HorizontalAlignment.CENTER)

            // 写入数据
            int rowNum = 0
            data.each { List<Object> row ->
                Row sheetRow = sheet.createRow(rowNum++)
                int cellNum = 0
                row.each { Object cellData ->
                    Cell sheetCell = sheetRow.createCell(cellNum++)
                    sheetCell.setCellValue(cellData)
                }
            }

            // 设置标题行样式
            Row headerRow = sheet.getRow(0)
            headerRow.each { Cell cell ->
                cell.cellStyle = headerStyle
            }

            // 将数据写入字节流
            ByteArrayOutputStream bos = new ByteArrayOutputStream()
            workbook.write(bos)
            bos.close()
            return bos.toByteArray()
        } catch (Exception e) {
            // 错误处理
            e.printStackTrace()
            return null
        }
    }
}
