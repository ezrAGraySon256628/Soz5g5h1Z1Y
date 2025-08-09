// 代码生成时间: 2025-08-09 16:22:53
package com.example.shoppingcart

import grails.transaction.Transactional

// 定义购物车服务，包含购物车功能
@Transactional
class ShoppingCartService {

    // 添加商品到购物车
    def addToCart(String userId, Long productId, Integer quantity) {
        // 检查商品ID和数量是否有效
        if (productId == null || quantity <= 0) {
            throw new IllegalArgumentException('Invalid product ID or quantity')
        }

        // 查找用户购物车
        ShoppingCart cart = ShoppingCart.findByUserId(userId)
        if (!cart) {
            cart = new ShoppingCart(userId: userId)
            cart.save(flush: true)
        }

        // 查找购物车中的商品项
        CartItem item = CartItem.findByCartAndProduct(cart, Product.get(productId))
        if (item) {
            // 更新商品数量
            item.quantity += quantity
        } else {
            // 添加新商品项
            item = new CartItem(cart: cart, product: Product.get(productId), quantity: quantity)
        }
        item.save(flush: true)
    }

    // 从购物车中移除商品
    def removeFromCart(String userId, Long productId) {
        // 查找购物车中的商品项
        CartItem item = CartItem.findByCartAndProduct(ShoppingCart.findByUserId(userId), Product.get(productId))
        if (item) {
            // 删除商品项
            item.delete()
        } else {
            throw new ItemNotFoundException('Item not found in the cart')
        }
    }

    // 清空购物车
    def clearCart(String userId) {
        // 查找用户购物车
        ShoppingCart cart = ShoppingCart.findByUserId(userId)
        if (cart) {
            // 删除购物车中的所有商品项
            CartItem.executeUpdate('delete CartItem where cart = ?', [cart])
            // 删除购物车
            cart.delete()
        } else {
            throw new CartNotFoundException('Cart not found for the user')
        }
    }

    // 获取购物车内容
    def getCart(String userId) {
        // 查找用户购物车
        ShoppingCart cart = ShoppingCart.findByUserId(userId)
        if (!cart) {
            throw new CartNotFoundException('Cart not found for the user')
        }

        // 返回购物车内容
        return cart.items
    }
}

// 定义购物车实体
class ShoppingCart {
    String userId
    static hasMany = [items: CartItem]
    static constraints = {
        userId nullable: false, blank: false
    }
}

// 定义购物车商品项实体
class CartItem {
    ShoppingCart cart
    Product product
    Integer quantity
    static belongsTo = [cart: ShoppingCart]
    static constraints = {
        cart nullable: false
        product nullable: false
        quantity min: 1
    }
}

// 定义商品实体
class Product {
    Long id
    String name
    BigDecimal price
    static constraints = {
        id generator: 'sequence', params: [sequence: 'product_id_seq']
        name nullable: false, blank: false
        price nullable: false
    }
}

// 自定义异常类
class ItemNotFoundException extends RuntimeException {
    ItemNotFoundException(String message) {
        super(message)
    }
}

class CartNotFoundException extends RuntimeException {
    CartNotFoundException(String message) {
        super(message)
    }
}