// 代码生成时间: 2025-08-14 13:07:13
package com.example

import groovy.transform.CompileStatic
import org.springframework.stereotype.Service
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * A service class for calculating hash values using various algorithms.
 *
 * @author Your Name
 * @since 1.0.0
 */
@Service
@CompileStatic
class HashCalculatorService {

    private static final List<String> SUPPORTED_ALGORITHMS = ['SHA-256', 'SHA-1', 'MD5']

    /**
     * Calculates the hash value for the given input with the specified algorithm.
     *
     * @param input The input string to calculate the hash for.
     * @param algorithm The hash algorithm to use.
     * @return The hash value as a hex string.
     * @throws IllegalArgumentException If the algorithm is not supported.
     */
    String calculateHash(String input, String algorithm) {
        if (!SUPPORTED_ALGORITHMS.contains(algorithm)) {
            throw new IllegalArgumentException("Unsupported algorithm: ${algorithm}. Supported algorithms are: ${SUPPORTED_ALGORITHMS.join(', ')}")
        }
        try {
            return MessageDigest.getInstance(algorithm).digest(input.bytes).encodeHex().toString()
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Algorithm not found: ${algorithm}", e)
        }
    }
}
