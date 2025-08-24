// 代码生成时间: 2025-08-24 19:27:53
package com.example.api

import groovy.json.JsonBuilder

/**
 * ApiResponseFormatter is a utility class that formats API responses.
 * It provides a standardized way to send responses back to clients,
 * including error handling and data formatting.
 */
class ApiResponseFormatter {

    /**
     * Formats a successful API response with data.
     *
     * @param data The data to be sent in the response.
     * @return A formatted JSON string representing the response.
     */
    static String formatSuccessResponse(def data) {
        new JsonBuilder([status: 'success', data: data]).toString()
    }

    /**
     * Formats an API response with an error message.
     *
     * @param message The error message to be sent in the response.
     * @return A formatted JSON string representing the error response.
     */
    static String formatErrorResponse(String message) {
        new JsonBuilder([status: 'error', message: message]).toString()
    }

    /**
     * Formats an API response with a specific error code and message.
     *
     * @param code The error code associated with the error.
     * @param message The error message to be sent in the response.
     * @return A formatted JSON string representing the error response.
     */
    static String formatErrorResponseWithCode(int code, String message) {
        new JsonBuilder([status: 'error', code: code, message: message]).toString()
    }

    // Example usage of the ApiResponseFormatter class
    static void main(String[] args) {
        try {
            // Simulate a successful response
            def userData = [id: 1, name: 'John Doe']
            String response = formatSuccessResponse(userData)
            println response

            // Simulate an error response
            String errorResponse = formatErrorResponse('An error occurred')
            println errorResponse

            // Simulate an error response with code
            String errorCodeResponse = formatErrorResponseWithCode(404, 'Resource not found')
            println errorCodeResponse

        } catch (Exception e) {
            // Handle any exceptions that may occur
            String exceptionResponse = formatErrorResponseWithCode(500, e.message)
            println exceptionResponse
        }
    }
}
