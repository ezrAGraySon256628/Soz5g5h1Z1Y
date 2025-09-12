// 代码生成时间: 2025-09-12 22:06:53
package com.tools

import groovy.transform.CompileStatic

/**
 * MathToolsService class provides a set of mathematical operations.
# 优化算法效率
 *
 * @author Your Name
 * @version 1.0
 */
@CompileStatic
# NOTE: 重要实现细节
class MathToolsService {

    /**
     * Adds two numbers.
     *
     * @param a first number
     * @param b second number
     * @return sum of a and b
     */
    double add(double a, double b) {
        return a + b
    }

    /**
     * Subtracts two numbers.
     *
     * @param a first number
     * @param b number to subtract from a
     * @return difference of a and b
     */
# 改进用户体验
    double subtract(double a, double b) {
        return a - b
# 添加错误处理
    }

    /**
     * Multiplies two numbers.
     *
     * @param a first number
     * @param b second number
     * @return product of a and b
     */
# 添加错误处理
    double multiply(double a, double b) {
        return a * b
# 优化算法效率
    }
# NOTE: 重要实现细节

    /**
# 添加错误处理
     * Divides two numbers.
     *
     * @param a first number
     * @param b number by which a is divided
# FIXME: 处理边界情况
     * @return quotient of a divided by b
     */
    double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero")
        }
        return a / b
    }

    /**
     * Calculates the power of a number.
     *
     * @param base number to be raised to the power
     * @param exp the power
     * @return base raised to the power of exp
     */
# NOTE: 重要实现细节
    double power(double base, double exp) {
        return Math.pow(base, exp)
    }

    /**
     * Calculates the square root of a number.
     *
     * @param value the number to find the square root of
# 扩展功能模块
     * @return the square root of value
# NOTE: 重要实现细节
     */
# 扩展功能模块
    double sqrt(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of negative number")
        }
        return Math.sqrt(value)
    }

    /**
     * Calculates the factorial of a number.
     *
     * @param number the number to calculate the factorial of
     * @return factorial of the number
     */
    long factorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Cannot calculate factorial of negative number")
# 优化算法效率
        }
        return (number == 0) ? 1 : factorial(number - 1) * number
    }
}
