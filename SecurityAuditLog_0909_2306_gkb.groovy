// 代码生成时间: 2025-09-09 23:06:58
import grails.transaction.Transactional
import org.springframework.stereotype.Service
import groovy.util.logging.Slf4j
import org.codehaus.groovy.grails.commons.GrailsApplication

@Slf4j
@Service
@Transactional
class SecurityAuditLogService {

    /**
     * Grails application reference for configuration access.
     */
    GrailsApplication grailsApplication

    /**
     * Logs an audit message for a particular event.
     *
     * @param username The username of the user who performed the action.
     * @param action The action that was performed.
     * @param details Additional details about the event.
     * @param success Whether the action was successful.
     * @param errorCode An error code if the action failed.
     */
    void logAudit(String username, String action, String details = '', boolean success = true, String errorCode = null) {
        try {
            // Create a new audit log entry
            AuditLogEntry auditLogEntry = new AuditLogEntry(
                username: username,
                action: action,
                details: details,
                success: success,
                errorCode: errorCode
            )

            // Save the audit log entry to the database
            auditLogEntry.save(flush: true, failOnError: true)
        } catch (Exception e) {
            // Log the error and handle it appropriately
            log.error("Error logging audit: ${e.message}")
            // Depending on the requirements, you may want to rethrow the exception
            // or handle it in a specific way for the application.
        }
    }
}

/**
 * Domain class for representing an audit log entry.
 */
class AuditLogEntry {

    /**
     * The username of the user who performed the action.
     */
    String username

    /**
     * The action that was performed.
     */
    String action

    /**
     * Additional details about the event.
     */
    String details

    /**
     * Whether the action was successful.
     */
    boolean success

    /**
     * An error code if the action failed.
     */
    String errorCode

    /**
     * Default constructor.
     */
    AuditLogEntry() {}

    /**
     * Constructor with all properties.
     *
     * @param username The username of the user.
     * @param action The action performed.
     * @param details Additional details.
     * @param success Whether the action was successful.
     * @param errorCode The error code if the action failed.
     */
    AuditLogEntry(String username, String action, String details, boolean success, String errorCode) {
        this.username = username
        this.action = action
        this.details = details
        this.success = success
        this.errorCode = errorCode
    }
}
