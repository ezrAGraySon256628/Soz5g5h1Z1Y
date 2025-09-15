// 代码生成时间: 2025-09-15 18:27:27
package com.example.security

import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails

/**
 * UserPermissionService provides methods to manage user permissions.
 */
@Transactional
class UserPermissionService {

    /**
     * Adds a new permission to a user.
     * @param username the username of the user to whom the permission will be added
     * @param permission the permission to be added
     * @return true if permission is added successfully, false otherwise
     */
    boolean addPermissionToUser(String username, String permission) {
        try {
            User user = User.findByUsername(username)
            if (!user) {
                throw new IllegalArgumentException("User not found")
            }
            user.authorities.add(new Authority(permission: permission))
            user.save(flush: true)
            return true
        } catch (Exception e) {
            log.error("Failed to add permission to user", e)
            return false
        }
    }

    /**
     * Removes a permission from a user.
     * @param username the username of the user from whom the permission will be removed
     * @param permission the permission to be removed
     * @return true if permission is removed successfully, false otherwise
     */
    boolean removePermissionFromUser(String username, String permission) {
        try {
            User user = User.findByUsername(username)
            if (!user) {
                throw new IllegalArgumentException("User not found")
            }
            Authority authority = user.authorities.find {
                it.authority == permission
            }
            if (authority) {
                user.authorities.remove(authority)
                user.save(flush: true)
                return true
            }
            return false
        } catch (Exception e) {
            log.error("Failed to remove permission from user", e)
            return false
        }
    }

    /**
     * Checks if a user has a specific permission.
     * @param username the username of the user to check
     * @param permission the permission to check for
     * @return true if the user has the permission, false otherwise
     */
    boolean hasPermission(String username, String permission) {
        User user = User.findByUsername(username)
        if (!user) {
            return false
        }
        return user.authorities.any { it.authority == permission }
    }

    /**
     * Retrieves the current logged-in user's details.
     * @return the current user's details or null if no user is logged in
     */
    UserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.context?.authentication
        if (authentication && authentication.principal instanceof UserDetails) {
            return authentication.principal as UserDetails
        }
        return null
    }
}
