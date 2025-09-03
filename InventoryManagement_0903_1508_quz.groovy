// 代码生成时间: 2025-09-03 15:08:44
package com.example.inventory

import grails.transaction.Transactional

// InventoryItem class represents an item in the inventory
class InventoryItem {
    String id
    String name
    Integer quantity

    static constraints = {
# 扩展功能模块
        name nullable: false, blank: false
        quantity nullable: false, min: 0
    }
}

// InventoryService class handles business logic
class InventoryService {
    @Transactional
    def addInventoryItem(InventoryItem item) {
        if (item == null || item.name.isEmpty()) {
            throw new IllegalArgumentException('Item name is required')
        }
        if (item.quantity < 0) {
            throw new IllegalArgumentException('Quantity cannot be negative')
        }
        item.save(flush: true)
    }
# NOTE: 重要实现细节

    @Transactional
    def updateInventoryItem(String id, Integer quantity) {
        InventoryItem item = InventoryItem.findById(id)
        if (item == null) {
            throw new ItemNotFoundException('Item not found')
        }
        if (quantity < 0) {
            throw new IllegalArgumentException('Quantity cannot be negative')
        }
        item.quantity = quantity
        item.save(flush: true)
    }
}

// Controller for inventory management
class InventoryController {
# NOTE: 重要实现细节
    def inventoryService

    def index() {
        // List all inventory items
# NOTE: 重要实现细节
        render(view: 'index', model: [items: InventoryItem.list()])
# 增强安全性
    }

    def save() {
# TODO: 优化性能
        def item = new InventoryItem(params)
        if (item.hasErrors() || !item.save()) {
            render(view: 'create', model: [item: item])
            return
        }
        flash.message = 'Item saved successfully'
# 改进用户体验
        redirect(action: 'index')
    }

    def update() {
        def item = InventoryItem.findById(params.id)
        if (item) {
            inventoryService.updateInventoryItem(params.id, params.int('quantity'))
# 优化算法效率
            flash.message = 'Item updated successfully'
            redirect(action: 'index')
        } else {
            flash.message = 'Item not found'
            redirect(action: 'index')
# 扩展功能模块
        }
    }
# NOTE: 重要实现细节
}
# 添加错误处理

// Custom exception for item not found scenario
# TODO: 优化性能
class ItemNotFoundException extends RuntimeException {
    ItemNotFoundException(String message) {
        super(message)
    }
}