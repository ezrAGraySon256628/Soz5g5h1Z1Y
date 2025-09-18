// 代码生成时间: 2025-09-18 22:11:45
import org.springframework.util.Base64Utils;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * PasswordUtil is a utility class for encrypting and decrypting passwords.
 * It uses AES encryption algorithm to secure the password.
 */
public class PasswordUtil {

    private static final String ALGORITHM = "AES";
    private static final int AES_KEY_SIZE = 128;
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Encrypts a password using AES algorithm with a randomly generated key.
     * 
     * @param password the password to encrypt
     * @return a JSON object containing the encrypted password and the encryption key
     * @throws Exception if an error occurs during encryption
     */
    public static String encryptPassword(String password) throws Exception {
        // Generate a secure random key for AES encryption
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(AES_KEY_SIZE, RANDOM);
        SecretKey secretKey = keyGenerator.generateKey();

        // Convert the secret key to a byte array
        byte[] keyBytes = secretKey.getEncoded();

        // Encrypt the password
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyBytes, ALGORITHM));
        byte[] encryptedPassword = cipher.doFinal(password.getBytes());

        // Encode the key and encrypted password as Base64 to return
        String encodedKey = Base64Utils.encodeToString(keyBytes);
        String encodedPassword = Base64Utils.encodeToString(encryptedPassword);

        return String.format("{\"key\": \"%s\", \"password\": \"%s\"}", encodedKey, encodedPassword);
    }

    /**
     * Decrypts a password that was previously encrypted with the encryptPassword method.
     * 
     * @param encryptedJson the JSON object containing the encrypted password and the encryption key
     * @return the decrypted password
     * @throws Exception if an error occurs during decryption
     */
    public static String decryptPassword(String encryptedJson) throws Exception {
        // Parse the JSON object to extract the encrypted password and the encryption key
        String[] values = encryptedJson.split(":", 4);
        String encodedKey = values[1].split("\", \"