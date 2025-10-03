// 代码生成时间: 2025-10-04 02:19:21
import grails.transaction.Transactional

@Transactional
class SupplyChainTracingService {

    // Define a domain class to represent a supply chain event
    static hasMany = [supplyChainEvents: SupplyChainEvent]

    // Save a new supply chain event
    void addSupplyChainEvent(SupplyChainEvent event) {
        try {
            event.save(flush: true)
        } catch (Exception e) {
            // Log the error and re-throw to handle it at the controller level
            log.error("Failed to save supply chain event.", e)
            throw e
        }
    }

    // Get all supply chain events for a specific product
    List<SupplyChainEvent> getSupplyChainEventsForProduct(String productId) {
        try {
            return SupplyChainEvent.findAllByProductId(productId)
        } catch (Exception e) {
            // Log the error and re-throw to handle it at the controller level
            log.error("Failed to retrieve supply chain events for product ${productId}.", e)
            throw e
        }
    }
}

/*
 * SupplyChainEvent.groovy
 *
 * Domain class representing a single event in the supply chain.
 */
class SupplyChainEvent {
    String productId
    String stepDescription
    Date timestamp
    static belongsTo = [product: Product]

    static constraints = {
        productId(nullable: false, blank: false)
        stepDescription(nullable: false, blank: false)
        timestamp(nullable: false)
    }
}

/*
 * Product.groovy
 *
 * Domain class representing a product in the supply chain.
 */
class Product {
    String id
    String name
    Set<SupplyChainEvent> supplyChainEvents
    static hasMany = [supplyChainEvents: SupplyChainEvent]

    static constraints = {
        id(nullable: false, blank: false)
        name(nullable: false, blank: false)
    }
}
