// 代码生成时间: 2025-08-05 08:06:56
package com.myapp

import spock.lang.Specification

/**
 * 单元测试类，用于验证业务逻辑的正确性
 */
class UnitSpec extends Specification {

    /**
     * 测试方法，验证简单的加法运算
     */
    def 'Should add two numbers'() {
        when:
        int result = new Calculator().add(5, 3)
        then:
        result == 8
    }

    /**
     * 测试方法，验证减法运算
     */
    def 'Should subtract two numbers'() {
        when:
        int result = new Calculator().subtract(10, 4)
        then:
        result == 6
    }

    /**
     * 测试方法，验证乘法运算
     */
    def 'Should multiply two numbers'() {
        when:
        int result = new Calculator().multiply(4, 3)
        then:
        result == 12
    }

    /**
     * 测试方法，验证除法运算
     */
    def 'Should divide two numbers'() {
        when:
        int result = new Calculator().divide(12, 3)
        then:
        result == 4
    }

    /**
     * 测试方法，验证除法运算，考虑除数为零的情况
     */
    def 'Should throw exception when dividing by zero'() {
        when:
        new Calculator().divide(10, 0)
        then:
        thrown ArithmeticException
    }
}

/**
 * 被测试的计算器类
 */
class Calculator {

    /**
     * 加法运算
     * @param a 第一个操作数
     * @param b 第二个操作数
     * @return 结果
     */
    int add(int a, int b) {
        a + b
    }

    /**
     * 减法运算
     * @param a 第一个操作数
     * @param b 第二个操作数
     * @return 结果
     */
    int subtract(int a, int b) {
        a - b
    }

    /**
     * 乘法运算
     * @param a 第一个操作数
     * @param b 第二个操作数
     * @return 结果
     */
    int multiply(int a, int b) {
        a * b
    }

    /**
     * 除法运算
     * @param a 被除数
     * @param b 除数
     * @return 结果
     * @throws ArithmeticException 当除数为零时抛出异常
     */
    int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero")
        }
        a / b
    }
}