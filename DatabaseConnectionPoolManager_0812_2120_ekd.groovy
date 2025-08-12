// 代码生成时间: 2025-08-12 21:20:19
import grails.transaction.Transactional
import org.apache.commons.dbcp2.BasicDataSource
import javax.sql.DataSource
import groovy.sql.Sql
import org.springframework.beans.factory.annotation.Autowired

/**
 * This class manages the database connection pool using Apache Commons DBCP2.
 * It's a singleton class to ensure that only one instance of the pool exists.
 *
 * @author Your Name
 * @since 1.0
 */
class DatabaseConnectionPoolManager {

    // The DataSource object is a singleton, so we declare it as a static variable.
    private static final DataSource dataSource = createDataSource()

    // Private constructor to prevent instantiation from other classes.
    private DatabaseConnectionPoolManager() {}

    /**
     * Returns the singleton instance of the DataSource.
     *
     * @return DataSource
     */
    static DataSource getDataSource() {
        return dataSource
    }

    /**
     * Creates and configures the DataSource using Apache Commons DBCP2.
     *
     * @return DataSource
     */
    private static DataSource createDataSource() {
        BasicDataSource ds = new BasicDataSource()
        ds.setUrl('jdbc:yourDatabaseUrl') // Set your database URL here
        ds.setUsername('username') // Set your database username here
        ds.setPassword('password') // Set your database password here
        ds.setMinIdle(5) // Set minimum idle connections
        ds.setMaxIdle(10) // Set maximum idle connections
        ds.setMaxOpenPreparedStatements(100) // Set maximum open prepared statements
        
        // Add more configurations as needed
        
        return ds
    }

    /**
     * Closes the connection and resources safely.
     *
     * @param sql the Sql object to close
     */
    static void closeSql(Sql sql) {
        if (sql) {
            try {
                sql.close()
            } catch (Exception e) {
                // Log the exception or handle it according to your error handling strategy
                println 'Error closing Sql connection: ' + e.message
            }
        }
    }
}

/**
 * A utility class to interact with the database using the connection pool.
 * This class provides methods to execute SQL queries and manage transactions.
 *
 * @author Your Name
 * @since 1.0
 */
@Transactional
class DatabaseService {

    @Autowired
    DatabaseConnectionPoolManager poolManager // Inject the pool manager

    /**
     * Executes a SQL query and returns the result as a list of maps.
     *
     * @param sqlQuery the SQL query to execute
     * @return List<Map> the list of result rows as maps
     */
    List<Map> executeQuery(String sqlQuery) {
        Sql sql = null
        try {
            sql = new Sql(poolManager.getDataSource())
            return sql.rows(sqlQuery)
        } catch (Exception e) {
            // Handle the exception and rethrow if necessary
            throw new RuntimeException('Failed to execute query: ' + sqlQuery, e)
        } finally {
            poolManager.closeSql(sql)
        }
    }

    /**
     * Executes a SQL update and returns the number of affected rows.
     *
     * @param sqlUpdate the SQL update to execute
     * @return int the number of affected rows
     */
    int executeUpdate(String sqlUpdate) {
        Sql sql = null
        try {
            sql = new Sql(poolManager.getDataSource())
            return sql.executeUpdate(sqlUpdate)
        } catch (Exception e) {
            // Handle the exception and rethrow if necessary
            throw new RuntimeException('Failed to execute update: ' + sqlUpdate, e)
        } finally {
            poolManager.closeSql(sql)
        }
    }
}
