// 代码生成时间: 2025-09-24 00:40:28
import grails.transaction.Transactional
# 扩展功能模块

@Transactional
class ShoppingCartService {

    // Assuming a ShoppingCart domain class exists with a Long id and a List of CartItems
    def findOrCreateCart() {
# TODO: 优化性能
        // Try to find an existing cart for the current session
        def cart = ShoppingCart.findWhere(session: UUID.randomUUID())
        if (!cart) {
            // If no cart exists, create a new one
# 改进用户体验
            cart = new ShoppingCart(
# 添加错误处理
                session: UUID.randomUUID(),
# 添加错误处理
                items: []
# 改进用户体验
            )
# 优化算法效率
            cart.save(flush: true)
        }
        return cart
# 扩展功能模块
    }

    // Add an item to the cart
    def addItemToCart(Long itemId, Integer quantity) {
        def cart = findOrCreateCart()
        if (!cart) {
            throw new RuntimeException('Shopping cart could not be found or created.')
        }
        // Check if the item is already in the cart
# 添加错误处理
        def itemInCart = cart.items.find { it.product.id == itemId }
        if (itemInCart) {
            itemInCart.quantity += quantity
        } else {
            // Add the new item to the cart
            cart.items << new CartItem(product: Product.get(itemId), quantity: quantity)
        }
        cart.save(flush: true)
    }

    // Remove an item from the cart
    def removeItemFromCart(Long itemId) {
        def cart = findOrCreateCart()
        if (!cart) {
            throw new RuntimeException('Shopping cart could not be found.')
        }
# TODO: 优化性能
        def itemToRemove = cart.items.find { it.product.id == itemId }
        if (itemToRemove) {
            cart.items.remove(itemToRemove)
            cart.save(flush: true)
        } else {
            throw new RuntimeException('Item not found in cart.')
# 增强安全性
        }
    }
# 增强安全性

    // Get the current cart items
# 扩展功能模块
    def getCartItems() {
        def cart = findOrCreateCart()
        if (!cart) {
            throw new RuntimeException('Shopping cart could not be found.')
        }
        return cart.items
    }

    // Clear the cart
    def clearCart() {
        def cart = findOrCreateCart()
        if (!cart) {
            throw new RuntimeException('Shopping cart could not be found.')
# 优化算法效率
        }
        cart.items.clear()
        cart.save(flush: true)
    }
}
