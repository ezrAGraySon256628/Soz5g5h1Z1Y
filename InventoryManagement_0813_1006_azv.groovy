// 代码生成时间: 2025-08-13 10:06:49
package inventory

import grails.transaction.Transactional

// 库存管理的实体类
class Product {
    String name
    Integer quantity

    static constraints = {
        name blank: false, nullable: false
        quantity min: 0
    }
}

// 库存管理的业务类
@Transactional
class InventoryService {
    // 添加产品
    def addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException('Product cannot be null')
        }
        product.save flush: true
    }

    // 更新产品库存
    def updateProductQuantity(String id, Integer newQuantity) {
        Product product = Product.get(id)
        if (product == null) {
            throw new NotFoundException('Product not found')
        }
        product.quantity = newQuantity
        product.save flush: true
    }

    // 获取所有产品
    def listProducts() {
        Product.list()
    }

    // 删除产品
    def removeProduct(String id) {
        Product product = Product.get(id)
        if (product == null) {
            throw new NotFoundException('Product not found')
        }
        product.delete flush: true
    }
}

// 异常类
class NotFoundException extends RuntimeException {
    NotFoundException(String message) {
        super(message)
    }
}

// 控制器类
class InventoryController {
    static responseFormats = ['json']
    def inventoryService

    // 添加产品
    def addProduct() {
        def product = new Product(params)
        if (!product.validate()) {
            render status: 400, text: 'Validation errors: ' + product.errors as JSON
            return
        }
        try {
            inventoryService.addProduct(product)
            render status: 201, text: 'Product added successfully' as JSON
        } catch (IllegalArgumentException e) {
            render status: 400, text: e.message as JSON
        }
    }

    // 更新产品库存
    def updateProductQuantity() {
        try {
            inventoryService.updateProductQuantity(params.id, params.newQuantity.toInteger())
            render status: 200, text: 'Product quantity updated successfully' as JSON
        } catch (NotFoundException e) {
            render status: 404, text: e.message as JSON
        }
    }

    // 获取所有产品
    def listProducts() {
        def products = inventoryService.listProducts()
        render products as JSON
    }

    // 删除产品
    def removeProduct() {
        try {
            inventoryService.removeProduct(params.id)
            render status: 200, text: 'Product removed successfully' as JSON
        } catch (NotFoundException e) {
            render status: 404, text: e.message as JSON
        }
    }
}
