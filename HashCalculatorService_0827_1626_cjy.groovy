// 代码生成时间: 2025-08-27 16:26:42
package com.example

import groovy.transform.CompileStatic
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

/**
 * A service class that provides functionality to calculate hash values of strings using various algorithms.
 * @author Your Name
 */
@CompileStatic
class HashCalculatorService {

    /**
     * Calculate the hash value of a given string using the specified algorithm.
     *
     * @param input The string to be hashed.
     * @param algorithm The hashing algorithm to use. (e.g., 'SHA-256', 'MD5')
     * @return The hash value as a hexadecimal string.
     * @throws NoSuchAlgorithmException If the specified algorithm is not available.
     * @throws IllegalArgumentException If the input is null or empty.
     */
    String calculateHash(String input, String algorithm) {
        if (!input) {
            throw new IllegalArgumentException('Input string cannot be null or empty.')
        }

        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm)
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8))
            return bytesToHex(hash)
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException("Algorithm '$algorithm' is not available.", e)
        }
    }

    /**
     * Convert a byte array to a hexadecimal string.
     *
     * @param bytes The byte array to convert.
     * @return The hexadecimal string representation of the byte array.
     */
    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder()
        for (byte b : bytes) {
            sb.append(String.format('%02x', b))
        }
        return sb.toString()
    }
}
