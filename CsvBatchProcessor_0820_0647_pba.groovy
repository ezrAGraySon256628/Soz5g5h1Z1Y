// 代码生成时间: 2025-08-20 06:47:47
package com.example.batchprocessing

import groovy.text.SimpleTemplateEngine
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVRecord
import org.apache.commons.lang3.StringUtils

import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.Path
import java.io.Reader
import java.io.Writer
import java.io.FileOutputStream

/**
 * CSV文件批量处理器
 */
class CsvBatchProcessor {

    /**
     * 处理CSV文件
     * @param inputCsvFilePath CSV文件路径
     * @param outputCsvFilePath 输出文件路径
     * @param templateFilePath 模板文件路径
     * @param batchSize 批次大小
     */
    void processCsvFile(String inputCsvFilePath, String outputCsvFilePath, String templateFilePath, int batchSize) {
        try {
            // 读取模板文件
            String template = new File(templateFilePath).text

            // 使用模板引擎创建输出文件模板
            SimpleTemplateEngine templateEngine = new SimpleTemplateEngine()
            templateEngine.setTemplateConfig(new ConfigObject().templateConfiguration)
            SimpleTemplate template = templateEngine.createTemplate(template)

            // 读取CSV文件
            Path inputCsv = Paths.get(inputCsvFilePath)
            Reader reader = Files.newBufferedReader(inputCsv)
            CSVParser csvParser = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(reader)

            int count = 0
            Map<String, Object> binding = new HashMap<>(batchSize)

            // 处理CSV文件记录
            for (CSVRecord record : csvParser) {
                // 将CSV记录转换为Map
                Map<String, String> recordMap = record.toMap()
                binding.putAll(recordMap)

                // 模板替换
                String outputLine = template.make(binding).toString()

                // 写入输出文件
                new FileOutputStream(outputCsvFilePath, true).withWriter { Writer writer ->
                    writer.write(outputLine + '
')
                }

                // 重置binding以处理下一批记录
                if (++count % batchSize == 0) {
                    binding.clear()
                }
            }

            // 关闭资源
            reader.close()
            csvParser.close()

            println 'CSV文件处理完成'

        } catch (Exception e) {
            e.printStackTrace()
            throw new RuntimeException('处理CSV文件时出错', e)
        }
    }
}
