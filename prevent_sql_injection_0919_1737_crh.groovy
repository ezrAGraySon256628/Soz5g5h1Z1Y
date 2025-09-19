// 代码生成时间: 2025-09-19 17:37:11
package com.example

import grails.transaction.Transactional
import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.jdbc.support.rowset.SqlRowSet
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData
import org.springframework.jdbc.support.rowset.SqlRowSetResultSetExtractor
import javax.sql.DataSource

/**
 * 数据访问对象，用于执行数据库操作，防止SQL注入
 */
@Transactional
class PreventSqlInjectionService {

    // 注入数据源
    private DataSource dataSource

    PreventSqlInjectionService(DataSource dataSource) {
        this.dataSource = dataSource
    }

    /**
     * 查询示例，使用JdbcTemplate防止SQL注入
     * @param userId 用户ID
     * @return 返回查询结果
     * @throws DataAccessException 数据访问异常
     */
    String findUserById(String userId) {
        try {
            // 使用?占位符防止SQL注入
            String sql = "SELECT username FROM users WHERE id = ?"
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource)
            return jdbcTemplate.queryForObject(sql, [userId].toArray(), String)
        } catch (DataAccessException e) {
            // 异常处理
            log.error("Error querying user by ID", e)
            throw e
        }
    }

    /**
     * 更新示例，使用JdbcTemplate防止SQL注入
     * @param username 用户名
     * @param userId 用户ID
     * @return 返回更新结果
     * @throws DataAccessException 数据访问异常
     */
    int updateUser(String username, String userId) {
        try {
            // 使用?占位符防止SQL注入
            String sql = "UPDATE users SET username = ? WHERE id = ?"
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource)
            return jdbcTemplate.update(sql, username, userId)
        } catch (DataAccessException e) {
            // 异常处理
            log.error("Error updating user", e)
            throw e
        }
    }

    /**
     * 删除示例，使用JdbcTemplate防止SQL注入
     * @param userId 用户ID
     * @return 返回删除结果
     * @throws DataAccessException 数据访问异常
     */
    int deleteUser(String userId) {
        try {
            // 使用?占位符防止SQL注入
            String sql = "DELETE FROM users WHERE id = ?"
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource)
            return jdbcTemplate.update(sql, userId)
        } catch (DataAccessException e) {
            // 异常处理
            log.error("Error deleting user", e)
            throw e
        }
    }
}