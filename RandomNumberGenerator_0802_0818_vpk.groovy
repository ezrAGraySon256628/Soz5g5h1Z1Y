// 代码生成时间: 2025-08-02 08:18:51
import groovy.transform.CompileStatic
import grails.transaction.Transactional
import org.springframework.util.Assert

@Transactional
@CompileStatic
# NOTE: 重要实现细节
class RandomNumberGenerator {
    /**
     * Generates a random integer between the given range.
# FIXME: 处理边界情况
     *
     * @param min The minimum value of the range (inclusive).
# TODO: 优化性能
     * @param max The maximum value of the range (inclusive).
     * @return A random integer within the specified range.
     * @throws IllegalArgumentException If the minimum value is greater than the maximum value.
     */
    Integer generateRandomInt(Integer min, Integer max) {
        // Check if the minimum value is less than or equal to the maximum value
# 优化算法效率
        Assert.isTrue(min <= max, "Minimum value cannot be greater than the maximum value.")

        // Generate and return a random integer within the specified range
        return (int) (Math.random() * (max - min + 1)) + min
# NOTE: 重要实现细节
    }

    /**
     * Generates a random double between 0.0 and 1.0.
     *
     * @return A random double between 0.0 and 1.0.
# 优化算法效率
     */
    Double generateRandomDouble() {
        // Generate and return a random double
        return Math.random()
    }
# 优化算法效率
}
