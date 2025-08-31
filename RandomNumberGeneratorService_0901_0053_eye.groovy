// 代码生成时间: 2025-09-01 00:53:33
class RandomNumberGeneratorService {

    /**
     * Generates a random number between the specified minimum and maximum values.
     *
     * @param min The minimum value (inclusive)
     * @param max The maximum value (exclusive)
     * @return A random number within the specified range
     * @throws IllegalArgumentException If min is greater than max
     */
    def generateRandomNumber(int min, int max) {
        // Check if the provided range is valid
        if (min >= max) {
            throw new IllegalArgumentException("Invalid range: min must be less than max")
        }

        // Generate and return a random number within the specified range
        return new Random().nextInt(max - min) + min
    }
}
