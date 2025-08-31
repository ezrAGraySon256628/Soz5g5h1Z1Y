// 代码生成时间: 2025-08-31 20:14:22
package com.example.dbpool

import groovy.sql.Sql
import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.stereotype.Service

/**
 * DatabasePoolManager provides a connection pool management service.
 * It uses Apache Commons DBCP for pooling.
 */
@Service
class DatabasePoolManager {

    // BasicDataSource for managing a pool of connections to a database
    private BasicDataSource dataSource

    /**
     * Constructor to initialize the BasicDataSource with properties.
     * @param url The JDBC URL of the database
     * @param username The username to use when connecting to the database
     * @param password The password to use when connecting to the database
     */
    DatabasePoolManager(String url, String username, String password) {
        dataSource = new BasicDataSource()
        dataSource.setUrl(url)
        dataSource.setUsername(username)
        dataSource.setPassword(password)
        dataSource.setInitialSize(5)
        dataSource.setMaxTotal(10)
        dataSource.setMaxIdle(5)
        dataSource.setMinIdle(2)
        dataSource.setValidationQuery("SELECT 1")
    }

    /**
     * Returns a connection from the pool.
     * @return a Groovy Sql object representing the connection
     */
    Sql getConnection() {
        try {
            return new Sql(dataSource.getConnection())
        } catch (Exception e) {
            // Handle exception - log and/or rethrow as appropriate
            throw new RuntimeException("Failed to get a database connection from the pool", e)
        }
    }

    /**
     * Closes the connection pool when the application is done.
     */
    void closePool() {
        try {
            dataSource.close()
        } catch (Exception e) {
            // Handle exception - log and/or rethrow as appropriate
            throw new RuntimeException("Failed to close the database connection pool", e)
        }
    }

    // Optional: Add methods to manage connection pool properties dynamically, if needed
    // For example, to set min/max idle/total connections, test on borrow, etc.
}
