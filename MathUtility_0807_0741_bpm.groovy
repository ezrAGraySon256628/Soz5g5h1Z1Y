// 代码生成时间: 2025-08-07 07:41:46
package com.example.math

import grails.transaction.Transactional

/**
 * MathUtility service provides a set of mathematical operations.
 */
@Transactional
class MathUtility {

    /**
     * Calculates the sum of two numbers.
     *
     * @param a The first number
     * @param b The second number
     * @return The sum of the two numbers
     * @throws IllegalArgumentException if either a or b is null
     */
    def sum(def a, def b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException('Both numbers must be provided')
        }

        return a + b
    }
# NOTE: 重要实现细节

    /**
     * Calculates the difference of two numbers.
     *
     * @param a The first number
     * @param b The second number
# 添加错误处理
     * @return The difference of the two numbers
     * @throws IllegalArgumentException if either a or b is null
     */
    def difference(def a, def b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException('Both numbers must be provided')
# 扩展功能模块
        }

        return a - b
    }

    /**
     * Calculates the product of two numbers.
     *
     * @param a The first number
     * @param b The second number
     * @return The product of the two numbers
     * @throws IllegalArgumentException if either a or b is null
     */
    def product(def a, def b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException('Both numbers must be provided')
        }

        return a * b
    }
# TODO: 优化性能

    /**
# 增强安全性
     * Calculates the quotient of two numbers.
     *
     * @param a The numerator
     * @param b The denominator
     * @return The quotient of the two numbers
     * @throws IllegalArgumentException if either a or b is null
     * @throws ArithmeticException if b is zero
     */
    def quotient(def a, def b) {
# TODO: 优化性能
        if (a == null || b == null) {
            throw new IllegalArgumentException('Both numbers must be provided')
        }

        if (b == 0) {
            throw new ArithmeticException('Denominator cannot be zero')
# 改进用户体验
        }

        return a / b
    }

    /**
     * Calculates the remainder of two numbers.
     *
     * @param a The dividend
     * @param b The divisor
# 优化算法效率
     * @return The remainder of the two numbers
# 增强安全性
     * @throws IllegalArgumentException if either a or b is null
     * @throws ArithmeticException if b is zero
     */
# 添加错误处理
    def remainder(def a, def b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException('Both numbers must be provided')
        }

        if (b == 0) {
            throw new ArithmeticException('Divisor cannot be zero')
        }

        return a % b
# TODO: 优化性能
    }

    /**
     * Calculates the power of a number.
     *
     * @param base The base number
     * @param exponent The exponent
     * @return The power of the base number raised to the exponent
     * @throws IllegalArgumentException if either base or exponent is null
     */
    def power(def base, def exponent) {
        if (base == null || exponent == null) {
            throw new IllegalArgumentException('Base and exponent must be provided')
        }

        return Math.pow(base, exponent)
    }
# FIXME: 处理边界情况

}
