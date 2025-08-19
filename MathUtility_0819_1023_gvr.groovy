// 代码生成时间: 2025-08-19 10:23:23
// MathUtility.groovy
// 该类提供了一组数学计算工具集

import groovy.transform.CompileStatic

@CompileStatic
class MathUtility {
    
    /**
     * 计算加法
     *
     * @param a 第一个加数
     * @param b 第二个加数
     * @return 和
     * @throws IllegalArgumentException 如果参数为null或非数字
     */
    static double add(double a, double b) {
        validateInput(a, b)
        return a + b
    }
    
    /**
     * 计算减法
     *
     * @param a 被减数
     * @param b 减数
     * @return 差
     * @throws IllegalArgumentException 如果参数为null或非数字
     */
    static double subtract(double a, double b) {
        validateInput(a, b)
        return a - b
    }
    
    /**
     * 计算乘法
     *
     * @param a 第一个乘数
     * @param b 第二个乘数
     * @return 积
     * @throws IllegalArgumentException 如果参数为null或非数字
     */
    static double multiply(double a, double b) {
        validateInput(a, b)
        return a * b
    }
    
    /**
     * 计算除法
     *
     * @param a 被除数
     * @param b 除数
     * @return 商
     * @throws IllegalArgumentException 如果参数为null或非数字
     * @throws ArithmeticException 如果除数为0
     */
    static double divide(double a, double b) {
        validateInput(a, b)
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero")
        }
        return a / b
    }
    
    /**
     * 验证输入是否合法
     *
     * @param values 数值数组
     * @throws IllegalArgumentException 如果参数为null或非数字
     */
    private static void validateInput(double... values) {
        values.each {
            if (it == null || !it.isNumber()) {
                throw new IllegalArgumentException("Invalid input: all arguments must be numbers")
            }
        }
    }
}
