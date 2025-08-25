// 代码生成时间: 2025-08-25 08:22:29
// Define the domain class for Inventory
class Inventory {
    String id // Unique identifier
    String name // Inventory item name
    Integer quantity // Quantity of the item in stock
    String description // Description of the item
    Date dateCreated // When the item was added to the inventory
    static constraints = {
        name(blank: false, size: 1..255)
        description(nullable: true)
        quantity(min: 0)
        dateCreated(nullable: true)
    }
    // Add a toString method for easy debugging
    String toString() {
        return "Inventory [id: $id, name: $name, quantity: $quantity, description: $description, dateCreated: $dateCreated]"
    }
}

// Define the InventoryController to handle CRUD operations
class InventoryController {
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    
    def index() {
        // Return a list of all inventory items
        respond Inventory.list()
    }
    
    def show(Long id) {
        // Return a specific inventory item
        respond Inventory.get(id)
    }
    
    def create() {
        // Save a new inventory item
        respond new Inventory(params)
    }
    
    def save(Inventory inventoryInstance) {
        if (inventoryInstance == null) {
            notFound()
            return
        }
        if (inventoryInstance.hasErrors()) {
            respond inventoryInstance.errors, view:'create' // List errors and return to create form
            return
        }
        inventoryInstance.save flush:true
        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'inventory.label', default: 'Inventory'), inventoryInstance.id])
                redirect inventoryInstance
            }
            '*' { respond inventoryInstance, [status: CREATED] }
        }
    }
    
    def edit(Long id) {
        // Update an existing inventory item
        respond Inventory.get(id)
    }
    
    def update(Inventory inventoryInstance) {
        if (inventoryInstance == null) {
            notFound()
            return
        }
        if (inventoryInstance.hasErrors()) {
            respond inventoryInstance.errors, view:'edit' // List errors and return to edit form
            return
        }
        inventoryInstance.save flush:true
        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'inventory.label', default: 'Inventory'), inventoryInstance.id])
                redirect inventoryInstance
            }
            '*' { respond inventoryInstance }
        }
    }
    
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }
        def inventoryInstance = Inventory.get(id)
        if (inventoryInstance == null) {
            notFound()
            return
        }
        inventoryInstance.delete flush:true
        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'inventory.label', default: 'Inventory'), id])
                redirect action: "index"
            }
            '*' { header 'HTTP/1.1 204 No Content' }
        }
    }
    
    protected void notFound() {
        request.withFormat {
            form {}\            '*'{ render status: NOT_FOUND }
        }
    }
}
