// 代码生成时间: 2025-10-03 17:06:43
package com.example.metadata

import grails.transaction.Transactional

// 元数据管理服务
@Transactional
class MetadataService {

    // 添加元数据
    void addMetadata(Map metadata) {
        try {
            Metadata obj = new Metadata(**metadata)
            obj.save(failOnError: true)
        } catch (Exception e) {
            // 错误处理
            log.error('Failed to add metadata', e)
            throw new RuntimeException('Failed to add metadata', e)
        }
    }

    // 更新元数据
    void updateMetadata(Long id, Map updatedMetadata) {
        try {
            Metadata obj = Metadata.get(id)
            if (obj) {
                obj.properties = updatedMetadata
                obj.save(failOnError: true)
           } else {
               throw new RuntimeException('Metadata not found')
           }
        } catch (Exception e) {
            // 错误处理
            log.error('Failed to update metadata', e)
            throw new RuntimeException('Failed to update metadata', e)
        }
    }

    // 删除元数据
    void deleteMetadata(Long id) {
        try {
            Metadata obj = Metadata.get(id)
            if (obj) {
                obj.delete(failOnError: true)
            } else {
                throw new RuntimeException('Metadata not found')
            }
        } catch (Exception e) {
            // 错误处理
            log.error('Failed to delete metadata', e)
            throw new RuntimeException('Failed to delete metadata', e)
        }
    }

    // 获取元数据
    Metadata getMetadata(Long id) {
        try {
            return Metadata.get(id)
        } catch (Exception e) {
            // 错误处理
            log.error('Failed to get metadata', e)
            throw new RuntimeException('Failed to get metadata', e)
        }
    }
}

// 元数据实体类
class Metadata {
    String name
    String description
    // 其他元数据属性...

    static constraints = {
        name blank: false, nullable: false
        description nullable: true
        // 其他约束...
    }
}
