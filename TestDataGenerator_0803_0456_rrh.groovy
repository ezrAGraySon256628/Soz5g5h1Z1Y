// 代码生成时间: 2025-08-03 04:56:45
import grails.transaction.Transactional

@Transactional
class TestDataGenerator {
    
    /**
     * Generates a list of test users.
     *
     * @param count The number of users to generate.
     * @return A list of User objects.
     */
    List<User> generateUsers(int count) {
        List<User> users = []
        for (int i = 0; i < count; i++) {
            users << new User(
                username: "user${i}",
                email: "user${i}@example.com",
                password: "password"
            ).save(failOnError: true)
        }
        users
    }

    /**
     * Generates a list of test products.
     *
     * @param count The number of products to generate.
     * @return A list of Product objects.
     */
    List<Product> generateProducts(int count) {
        List<Product> products = []
        for (int i = 0; i < count; i++) {
            products << new Product(
                name: "Product ${i}",
                description: "This is a test product ${i}",
                price: (i + 1) * 10.00
            ).save(failOnError: true)
        }
        products
    }

    /**
     * Generates a test data set for the application.
     *
     * @param userCount The number of users to generate.
     * @param productCount The number of products to generate.
     */
    void generateTestData(int userCount, int productCount) {
        try {
            println "Generating ${userCount} users..."
            generateUsers(userCount)
            println "Generating ${productCount} products..."
            generateProducts(productCount)
            println "Test data generation complete."
        } catch (Exception e) {
            // Handle any errors that occur during data generation
            println "An error occurred during test data generation: ${e.message}"
        }
    }
}
