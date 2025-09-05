// 代码生成时间: 2025-09-05 18:24:48
package com.example.api

import grails.web.api.*

/**
 * ApiResponseFormatter class to format API responses.
 */
class ApiResponseFormatter {

    // Method to format API response
    Map<String, Object> formatResponse(Map<String, Object> originalResponse, String status) {
        // Check if the original response is null
        if (originalResponse == null) {
            throw new IllegalArgumentException('Original response cannot be null')
        }

        // Check if status is valid
        if (!['success', 'error'].contains(status)) {
            throw new IllegalArgumentException('Invalid status value. It should be either success or error.')
        }

        // Create a new response map
        Map<String, Object> formattedResponse = new HashMap<>()
        formattedResponse.put('status', status)
        formattedResponse.put('data', originalResponse)

        // Return the formatted response
        return formattedResponse
    }

    // Method to format error response
    Map<String, Object> formatErrorResponse(Map<String, Object> originalResponse, String errorMessage) {
        // Check if the original response is null
        if (originalResponse == null) {
            throw new IllegalArgumentException('Original response cannot be null')
        }

        // Check if errorMessage is valid
        if (!errorMessage) {
            throw new IllegalArgumentException('Error message cannot be null or empty')
        }

        // Create a new error response map
        Map<String, Object> formattedErrorResponse = new HashMap<>()
        formattedErrorResponse.put('status', 'error')
        formattedErrorResponse.put('errorMessage', errorMessage)
        formattedErrorResponse.put('data', originalResponse)

        // Return the formatted error response
        return formattedErrorResponse
    }

    // Method to format success response
    Map<String, Object> formatSuccessResponse(Map<String, Object> originalResponse) {
        // Check if the original response is null
        if (originalResponse == null) {
            throw new IllegalArgumentException('Original response cannot be null')
        }

        // Create a new success response map
        Map<String, Object> formattedSuccessResponse = new HashMap<>()
        formattedSuccessResponse.put('status', 'success')
        formattedSuccessResponse.put('data', originalResponse)

        // Return the formatted success response
        return formattedSuccessResponse
    }

}
