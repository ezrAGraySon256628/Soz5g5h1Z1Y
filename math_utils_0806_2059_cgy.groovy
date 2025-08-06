// 代码生成时间: 2025-08-06 20:59:09
class MathUtils {
    
    /**
     * Adds two numbers and returns the result.
     *
     * @param number1 The first number to add
     * @param number2 The second number to add
     * @return The sum of number1 and number2
     */
    static double add(double number1, double number2) {
        return number1 + number2
    }
    
    /**
     * Subtracts the second number from the first and returns the result.
     *
     * @param number1 The number to subtract from
     * @param number2 The number to be subtracted
     * @return The difference between number1 and number2
     */
    static double subtract(double number1, double number2) {
        return number1 - number2
    }
    
    /**
     * Multiplies two numbers and returns the result.
     *
     * @param number1 The first number to multiply
     * @param number2 The second number to multiply
     * @return The product of number1 and number2
     */
    static double multiply(double number1, double number2) {
        return number1 * number2
    }
    
    /**
     * Divides the first number by the second and returns the result.
     * @throws ArithmeticException if number2 is zero
     *
     * @param number1 The number to divide
     * @param number2 The number to divide number1 by
     * @return The quotient of number1 divided by number2
     */
    static double divide(double number1, double number2) {
        if (number2 == 0) {
            throw new ArithmeticException("Cannot divide by zero.")
        }
        return number1 / number2
    }
    
    /**
     * Calculates the power of a number and returns the result.
     *
     * @param base The base number
     * @param exponent The exponent to raise the base to
     * @return The result of base raised to the power of exponent
     */
    static double power(double base, double exponent) {
        return Math.pow(base, exponent)
    }
    
    /**
     * Calculates the square root of a number and returns the result.
     *
     * @param number The number to calculate the square root of
     * @return The square root of number
     */
    static double sqrt(double number) {
        return Math.sqrt(number)
    }
    
    // Additional mathematical functions can be added here...
    
    /**
     * Main method for testing the MathUtils class.
     * @param args Command line arguments
     */
    static void main(String[] args) {
        println "Addition: 5 + 3 = " + add(5, 3)
        println "Subtraction: 5 - 3 = " + subtract(5, 3)
        println "Multiplication: 5 * 3 = " + multiply(5, 3)
        println "Division: 5 / 3 = " + divide(5, 3)
        println "Power: 2^3 = " + power(2, 3)
        println "Square Root: " + sqrt(9)
    }
}
