// 代码生成时间: 2025-08-09 20:40:20
package com.example

import grails.transaction.Transactional
import grails.web.mapping.LinkGenerator

/**
 * Service for handling theme switching functionality.
 *
 * @author Your Name
 */
@Transactional
class ThemeSwitchingService {

    // LinkGenerator is used to generate URLs
    LinkGenerator grailsLinkGenerator

    /**
     * Switches the theme for the current user.
     *
     * @param username The username of the user
     * @param theme The theme to switch to
     * @return A map containing the result of the operation
     */
    Map switchTheme(String username, String theme) {
        // Validate the theme
        if (!isValidTheme(theme)) {
            return [status: 'error', message: "Theme '$theme' is not valid"]
        }

        try {
            // Find the user by username
            User user = User.findByUsername(username)
            if (!user) {
                return [status: 'error', message: "User '$username' not found"]
            }

            // Update the user's theme
            user.theme = theme
            user.save(flush: true)

            // Return a success response
            return [status: 'success', message: "User '$username' theme switched to '$theme'"]
        } catch (Exception e) {
            // Return an error response in case of any exceptions
            return [status: 'error', message: "An error occurred while switching theme: ${e.message}"]
        }
    }

    /**
     * Checks if the provided theme is valid.
     *
     * @param theme The theme to validate
     * @return true if the theme is valid, false otherwise
     */
    private boolean isValidTheme(String theme) {
        // List of valid themes (could be fetched from a config file or database)
        List<String> validThemes = ['light', 'dark', 'colorful']
        return validThemes.contains(theme)
    }
}

/**
 * Domain class representing a user.
 *
 * @author Your Name
 */
class User {
    String username
    String theme
    
    static constraints = {
        username blank: false, unique: true
        theme blank: false
    }
}
