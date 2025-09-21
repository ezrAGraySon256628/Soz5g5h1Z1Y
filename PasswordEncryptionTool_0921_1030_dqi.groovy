// 代码生成时间: 2025-09-21 10:30:30
class PasswordEncryptionTool {

    /**
     * Encrypts a password using a secret key.
     *
     * @param password The password to be encrypted.
     * @param secretKey The secret key used for encryption.
     * @return The encrypted password.
     * @throws Exception if encryption fails.
     */
    static String encryptPassword(String password, String secretKey) {
        try {
            // Use a secure algorithm for encryption, e.g., AES
            def cipher = 'AES'
            def salt = new SecureRandom().nextBytes(16)
            def keySpec = new SecretKeySpec(secretKey.bytes, 'AES')
            def cipherInstance = Cipher.getInstance(cipher)
            cipherInstance.init(Cipher.ENCRYPT_MODE, keySpec)
            return Base64.getEncoder().encodeToString(salt + cipherInstance.doFinal(password.bytes))
        } catch (Exception e) {
            // Handle encryption exceptions
            throw new Exception("Encryption failed: \${e.message}")
        }
    }

    /**
     * Decrypts a password using a secret key.
     *
     * @param encryptedPassword The encrypted password to be decrypted.
     * @param secretKey The secret key used for decryption.
     * @return The decrypted password.
     * @throws Exception if decryption fails.
     */
    static String decryptPassword(String encryptedPassword, String secretKey) {
        try {
            // Use the same algorithm as in encryption
            def cipher = 'AES'
            def encryptedBytes = Base64.getDecoder().decode(encryptedPassword)
            def salt = Arrays.copyOfRange(encryptedBytes, 0, 16)
            def encryptedData = Arrays.copyOfRange(encryptedBytes, 16, encryptedBytes.length)
            def keySpec = new SecretKeySpec(secretKey.bytes, 'AES')
            def cipherInstance = Cipher.getInstance(cipher)
            cipherInstance.init(Cipher.DECRYPT_MODE, keySpec)
            cipherInstance.update(salt)
            return new String(cipherInstance.doFinal(encryptedData))
        } catch (Exception e) {
            // Handle decryption exceptions
            throw new Exception("Decryption failed: \${e.message}")
        }
    }

    // Main method for demonstration purposes
    static void main(String[] args) {
        // Define a secret key
        def secretKey = 'your_secret_key'

        // Define a password to be encrypted
        def password = 'your_password'

        // Encrypt the password
        def encryptedPassword = encryptPassword(password, secretKey)
        println "Encrypted Password: \$encryptedPassword"

        // Decrypt the password
        def decryptedPassword = decryptPassword(encryptedPassword, secretKey)
        println "Decrypted Password: \$decryptedPassword"
    }
}