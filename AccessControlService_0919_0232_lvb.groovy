// 代码生成时间: 2025-09-19 02:32:14
package com.example.security

import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.security.access.annotation.Secured

/**
 * Service class for handling access control.
 */
@Transactional
class AccessControlService {

    def permissionEvaluator
    def springSecurityService

    /**
     * Checks if the current user has the specified authority.
     *
     * @param authority The authority to check for.
     * @return true if the user has the authority, false otherwise.
     */
    @Secured('IS_AUTHENTICATED_FULLY')
    boolean hasAuthority(String authority) {
        try {
            boolean hasAuth = permissionEvaluator.hasPermission(springSecurityService.principal, authority)
            return hasAuth
        } catch (Exception e) {
            // Log the exception and return false
            log.error("Failed to check authority: ${e.message}", e)
            return false
        }
    }

    /**
     * Checks if the current user has any of the specified authorities.
     *
     * @param authorities The list of authorities to check for.
     * @return true if the user has any of the authorities, false otherwise.
     */
    @Secured('IS_AUTHENTICATED_FULLY')
    boolean hasAnyAuthority(List<String> authorities) {
        try {
            boolean hasAnyAuth = authorities.any { authority -> permissionEvaluator.hasPermission(springSecurityService.principal, authority) }
            return hasAnyAuth
        } catch (Exception e) {
            // Log the exception and return false
            log.error("Failed to check any authority: ${e.message}", e)
            return false
        }
    }

    /**
     * Checks if the current user is in the specified role.
     *
     * @param role The role to check for.
     * @return true if the user is in the role, false otherwise.
     */
    @Secured('IS_AUTHENTICATED_FULLY')
    boolean hasRole(String role) {
        try {
            boolean hasRole = springSecurityService.principal?.hasRole(role)
            return hasRole
        } catch (Exception e) {
            // Log the exception and return false
            log.error("Failed to check role: ${e.message}", e)
            return false
        }
    }

    /**
     * Checks if the current user is in any of the specified roles.
     *
     * @param roles The list of roles to check for.
     * @return true if the user is in any of the roles, false otherwise.
     */
    @Secured('IS_AUTHENTICATED_FULLY')
    boolean hasAnyRole(List<String> roles) {
        try {
            boolean hasAnyRole = roles.any { role -> springSecurityService.principal?.hasRole(role) }
            return hasAnyRole
        } catch (Exception e) {
            // Log the exception and return false
            log.error("Failed to check any role: ${e.message}", e)
            return false
        }
    }
}
