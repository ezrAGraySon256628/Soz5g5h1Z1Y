// 代码生成时间: 2025-08-22 21:23:27
package com.example.security

import grails.web.mapping.LinkGenerator
import grails.web.mapping.UrlMappings
import groovy.util.logging.Slf4j
import org.springframework.web.util.HtmlUtils

/**
 * Controller that handles XSS protection for the application.
 */
@Slf4j
class XssProtectionController {

    /**
     * Link generator for generating URLs.
     */
    LinkGenerator linkGenerator

    /**
     * Adds an action to sanitize input to prevent XSS attacks.
     *
     * @param input The input string to sanitize.
     * @return The sanitized string.
     */
    def sanitizeInput() {
        try {
            // Get the input parameter from the request.
            String input = params.input

            // Sanitize the input to prevent XSS attacks.
            String sanitizedInput = HtmlUtils.htmlEscape(input)

            // Return the sanitized input as a JSON response.
            render(sanitizedInput as JSON)

        } catch (Exception e) {
            log.error('Error sanitizing input', e)
            render(status: 500, text: 'Internal Server Error')
        }
    }

    /**
     * URL mappings for the controller.
     */
    static UrlMappings urlMappings = {
        mappings {
            "/xss/sanitize": controller: 'xssProtection', action: 'sanitizeInput'
        }
    }
}
