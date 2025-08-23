// 代码生成时间: 2025-08-24 04:43:06
package demo

import groovy.sql.Sql
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.namedparam.SqlParameterSource
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Service
import javax.sql.DataSource

/**
 * Service class to demonstrate prevention of SQL injection using Grails and Spring JDBC.
 * This class includes methods to safely interact with the database,
 * avoiding SQL injection vulnerabilities.
 */
@Service
class SqlInjectionPreventionService {

    private JdbcTemplate jdbcTemplate

    /**
     * Constructor to inject the DataSource, which is used to create a JdbcTemplate.
     * @param dataSource The DataSource for database connection.
     */
    SqlInjectionPreventionService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource)
    }

    /**
     * Method to safely insert a user into the database using named parameters.
     * @param username The username of the user.
     * @param email The email of the user.
     * @return The ID of the newly inserted user.
     */
    Long insertUser(String username, String email) {
        try {
            String sql = "INSERT INTO users (username, email) VALUES (:username, :email)"
            SqlParameterSource params = new MapSqlParameterSource()
                .addValue("username", username)
                .addValue("email", email)

            KeyHolder keyHolder = new GeneratedKeyHolder()
            jdbcTemplate.update(sql, params, keyHolder)
            return Long.parseLong(keyHolder.getKey().toString())
        } catch (Exception e) {
            throw new RuntimeException("Failed to insert user", e)
        }
    }

    /**
     * Method to safely retrieve a user from the database using named parameters.
     * @param username The username of the user to retrieve.
     * @return The user details or null if not found.
     */
    Map getUserByUsername(String username) {
        try {
            String sql = "SELECT * FROM users WHERE username = :username"
            SqlParameterSource params = new MapSqlParameterSource().addValue("username", username)
            return jdbcTemplate.queryForMap(sql, params)
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve user", e)
        }
    }
}
