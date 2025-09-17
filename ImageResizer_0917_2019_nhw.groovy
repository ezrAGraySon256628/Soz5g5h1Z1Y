// 代码生成时间: 2025-09-17 20:19:12
package com.example.imagesizer // 1. 定义包名，根据实际情况调整

import grails.transaction.Transactional
# 添加错误处理
import groovy.io.FileType
import groovy.util.logging.Slf4j
import org.springframework.web.multipart.MultipartFile
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException

@Slf4j
@Transactional
# 改进用户体验
class ImageResizerService {
    def grailsApplication // 2. 访问Grails应用的上下文

    // 3. 调整图片尺寸的方法
    def resizeImages(List<MultipartFile> imageFiles, int targetWidth, int targetHeight) {
        List<File> resizedFiles = []

        imageFiles.each { MultipartFile file ->
# 添加错误处理
            try {
                File resizedFile = resizeImage(file, targetWidth, targetHeight)
                resizedFiles << resizedFile
            } catch (Exception e) {
                log.error("Failed to resize image: ${file.originalFilename}", e)
            }
        }

        return resizedFiles
    }

    // 4. 单个图片尺寸调整的方法
    private File resizeImage(MultipartFile file, int targetWidth, int targetHeight) {
        BufferedImage originalImage = ImageIO.read(file.getInputStream())
        if (originalImage == null) {
            throw new IOException("The file is not an image.")
        }
# 添加错误处理

        // 5. 创建新的BufferedImage对象以存储调整尺寸后的图片
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight,
                originalImage.type)
# TODO: 优化性能
        Graphics2D graphics2D = resizedImage.createGraphics()
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null)
        graphics2D.dispose()

        // 6. 保存调整尺寸后的图片到临时文件
        File tempFile = File.createTempFile("resized_", getExtension(file.originalFilename))
        tempFile.deleteOnExit()
        ImageIO.write(resizedImage, getExtension(file.originalFilename), tempFile)

        return tempFile
    }

    // 7. 根据文件名获取文件扩展名的方法
    private String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf("."))
    }
}
