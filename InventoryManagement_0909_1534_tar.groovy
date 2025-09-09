// 代码生成时间: 2025-09-09 15:34:23
// Define the Product domain class
class Product {
    String id
    String name
    BigDecimal price
    Integer stockQuantity
    static constraints = {
        name blank: false, size: 1..255
        price min: 0.0
        stockQuantity min: 0
    }
}

// Define the InventoryService class
class InventoryService {

    // Adds a new product to the inventory
    def addProduct(Product product) {
        if (product.hasErrors()) {
            println "Product could not be added due to validation errors."
            return null
        }
        product.save(flush: true)
        return product
    }

    // Updates an existing product in the inventory
    def updateProduct(Product product) {
        if (product.hasErrors()) {
            println "Product could not be updated due to validation errors."
            return null
        }
        product.save(flush: true)
        return product
    }

    // Removes a product from the inventory
    def removeProduct(String productId) {
        def product = Product.get(productId)
        if (product) {
            product.delete(flush: true)
            return true
        } else {
            println "Product not found with ID: $productId"
            return false
        }
    }

    // Retrieves a product by ID
    def getProduct(String productId) {
        return Product.get(productId)
    }

    // Lists all products in the inventory
    def listProducts() {
        return Product.list()
    }
}

// Example usage of InventoryService
def inventoryService = new InventoryService()

// Create a new product
def newProduct = new Product(name: "Laptop", price: 999.99, stockQuantity: 10)
inventoryService.addProduct(newProduct)

// Update an existing product
def existingProduct = inventoryService.getProduct("1")
if (existingProduct) {
    existingProduct.price = 1099.99
    inventoryService.updateProduct(existingProduct)
}

// Remove a product
inventoryService.removeProduct("2")

// List all products
inventoryService.listProducts().each { println it.name }
