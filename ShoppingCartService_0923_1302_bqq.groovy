// 代码生成时间: 2025-09-23 13:02:25
import grails.transaction.Transactional

@Transactional
class ShoppingCartService {

    // Adds an item to the shopping cart
    def addItemToCart(String cartId, String itemId, int quantity) {
        try {
            // Assuming ShoppingCart and ShoppingCartItem are domain classes
            ShoppingCart cart = ShoppingCart.findByCartId(cartId)
            if (!cart) {
                throw new Exception("Shopping cart not found")
            }

            ShoppingCartItem item = ShoppingCartItem.findByItemId(itemId)
            if (!item) {
                throw new Exception("Item not found")
            }

            def cartItem = ShoppingCartItem.findOrCreateByCartAndItem(cart, item) {
                new ShoppingCartItem(cart: cart, item: item, quantity: 0)
            }
            cartItem.quantity += quantity
            cartItem.save(failOnError: true)
        } catch (Exception e) {
            // Log error and handle it accordingly
            log.error("Error adding item to cart: " + e.getMessage())
            throw e
        }
    }

    // Removes an item from the shopping cart
    def removeItemFromCart(String cartId, String itemId) {
        try {
            ShoppingCart cart = ShoppingCart.findByCartId(cartId)
            if (!cart) {
                throw new Exception("Shopping cart not found")
            }

            ShoppingCartItem item = ShoppingCartItem.findByCartAndItem(cart, ShoppingCartItem.findByItemId(itemId))
            if (!item) {
                throw new Exception("Item not found in cart")
            }

            item.delete(failOnError: true)
        } catch (Exception e) {
            log.error("Error removing item from cart: " + e.getMessage())
            throw e
        }
    }

    // Updates the quantity of an item in the shopping cart
    def updateItemQuantity(String cartId, String itemId, int quantity) {
        try {
            ShoppingCart cart = ShoppingCart.findByCartId(cartId)
            if (!cart) {
                throw new Exception("Shopping cart not found")
            }

            ShoppingCartItem item = ShoppingCartItem.findByCartAndItem(cart, ShoppingCartItem.findByItemId(itemId))
            if (!item) {
                throw new Exception("Item not found in cart")
            }

            item.quantity = quantity
            item.save(failOnError: true)
        } catch (Exception e) {
            log.error("Error updating item quantity in cart: " + e.getMessage())
            throw e
        }
    }

    // Gets the shopping cart details for a given cartId
    def getShoppingCart(String cartId) {
        ShoppingCart.findByCartId(cartId)
    }

    // Empties the shopping cart for a given cartId
    def emptyShoppingCart(String cartId) {
        try {
            ShoppingCart cart = ShoppingCart.findByCartId(cartId)
            if (!cart) {
                throw new Exception("Shopping cart not found")
            }

            cart.items.each { item ->
                item.delete(failOnError: true)
            }
        } catch (Exception e) {
            log.error("Error emptying cart: " + e.getMessage())
            throw e
        }
    }

}

/*
 * Additional domain classes (assuming these are defined elsewhere in the project)
 */

class ShoppingCart {
    String cartId
    Set<ShoppingCartItem> items = []
    // ... other properties and methods
}

class ShoppingCartItem {
    ShoppingCart cart
    String itemId
    int quantity
    // ... other properties and methods
}
