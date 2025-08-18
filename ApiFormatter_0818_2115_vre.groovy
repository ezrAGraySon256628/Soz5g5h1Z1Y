// 代码生成时间: 2025-08-18 21:15:20
 * It includes error handling and aims for high readability and maintainability.
 */

import groovy.json.JsonBuilder

class ApiFormatter {
    // Creates a success API response with a payload
    static Map<String, Object> successResponse(Object payload) {
        Map<String, Object> response = new HashMap<>()
        response.status = 'success'
        response.data = payload
        return response
    }

    // Creates an error API response with an optional message and error code
    static Map<String, Object> errorResponse(String message = 'An error occurred', int errorCode = 0) {
        Map<String, Object> response = new HashMap<>()
        response.status = 'error'
        response.message = message
        response.code = errorCode
        return response
    }

    // Converts a Map to a JSON string, handling any serialization issues
    static String toJson(Map responseMap) {
        try {
            // Using JsonBuilder to create a JSON string from the map
            return new JsonBuilder(responseMap).toString()
        } catch (Exception e) {
            // Handling any exceptions during JSON serialization
            return errorResponse("Failed to serialize response.", 500)
        }
    }

    // Main method for testing the ApiFormatter functionality
    static void main(String[] args) {
        // Example of a successful API response with a payload
        Map<String, Object> successPayload = [key1: 'value1', key2: 'value2']
        String successJson = toJson(successResponse(successPayload))
        println(successJson)

        // Example of an error API response
        String errorJson = toJson(errorResponse('Invalid request', 400))
        println(errorJson)
    }
}
