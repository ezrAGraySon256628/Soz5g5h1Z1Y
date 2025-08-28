// 代码生成时间: 2025-08-29 07:45:03
package com.example.security

import groovy.util.logging.Slf4j
import org.apache.commons.codec.digest.DigestUtils
import org.springframework.stereotype.Service

/**
 * PasswordEncryptionDecryption service class for encryption and decryption of passwords.
 */
@Slf4j
@Service
class PasswordEncryptionDecryption {

    /**
     * Encrypts a password using SHA-256.
     *
     * @param password The password to encrypt.
     * @return The encrypted password as a hexadecimal string.
     */
    String encryptPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException('Password cannot be null or empty.')
        }
        return DigestUtils.sha256Hex(password)
    }

    /**
     * Decrypts a password, however, since SHA-256 is a one-way hash function,
     * this method will only verify if the provided password matches the encrypted one.
     *
     * @param originalPassword The original password to check against.
     * @param encryptedPassword The encrypted password to compare with.
     * @return True if the passwords match, false otherwise.
     */
    boolean decryptPassword(String originalPassword, String encryptedPassword) {
        if (originalPassword == null || encryptedPassword == null || originalPassword.isEmpty() || encryptedPassword.isEmpty()) {
            throw new IllegalArgumentException('Passwords must not be null or empty.')
        }
        return encryptPassword(originalPassword) == encryptedPassword
    }
}
