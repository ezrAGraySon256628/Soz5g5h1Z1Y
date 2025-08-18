// 代码生成时间: 2025-08-18 15:08:49
class RandomNumberGenerator {

    /**
     * Generates a random number between the specified range.
     *
     * @param min The minimum value of the range (inclusive).
     * @param max The maximum value of the range (inclusive).
     * @return A random number within the specified range.
     * @throws IllegalArgumentException If min is greater than max or if min or max is negative.
     */
    def generateRandomNumber(min, max) {
        // Check if the inputs are valid
        if (min > max) {
            throw new IllegalArgumentException("Minimum value cannot be greater than maximum value.")
        }
        if (min < 0 || max < 0) {
            throw new IllegalArgumentException("Minimum and maximum values must be non-negative.")
        }

        // Generate a random number within the specified range
        return (new Random()).nextInt((max - min) + 1) + min
    }

    /**
     * Main method to demonstrate the usage of the random number generator.
     */
    static void main(String[] args) {
        RandomNumberGenerator generator = new RandomNumberGenerator()
        try {
            // Example usage: Generate a random number between 1 and 100
            def randomNum = generator.generateRandomNumber(1, 100)
            println "Generated random number: "" + randomNum + """
        } catch (IllegalArgumentException e) {
            println "Error: "" + e.message + """
        }
    }
}