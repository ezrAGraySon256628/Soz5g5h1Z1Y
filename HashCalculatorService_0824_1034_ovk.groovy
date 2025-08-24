// 代码生成时间: 2025-08-24 10:34:27
package services\
\
import groovy.transform.CompileStatic\
import org.springframework.stereotype.Service\
import javax.crypto.Mac\
import javax.crypto.spec.SecretKeySpec\
import java.security.InvalidKeyException\
import java.security.NoSuchAlgorithmException\
\
// Service for calculating hash values using various algorithms\
@Service\
@CompileStatic\
class HashCalculatorService {\
\
    // Calculates hash using HMAC-SHA256 algorithm\
    public String calculateHmacSha256(String data, String secretKey) {\
        try {\
            // Create a new instance of the HMAC-SHA256 algorithm\
            Mac mac = Mac.getInstance("HmacSHA256")\
            // Initialize the HMAC with the secret key\
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256")\
            mac.init(keySpec)\
            // Compute the hash\
            byte[] hash = mac.doFinal(data.getBytes())\
            // Return the hash as a hex string\
            return bytesToHex(hash)\
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {\
            // Handle exceptions by logging and returning null\
            e.printStackTrace()\
            return null\
        }\
    }\
\
    // Helper method to convert bytes to hex string\
    private static String bytesToHex(byte[] bytes) {\
        StringBuilder hexString = new StringBuilder("")\
        for (byte b : bytes) {\
            hexString.append(String.format("%02x", b))\
        }\
        return hexString.toString()\
    }\
\
    // Main method for testing purposes\
    public static void main(String[] args) {\
        HashCalculatorService service = new HashCalculatorService()\
        String data = "Hello, World!"\
        String secretKey = "mySecretKey"\
        String hash = service.calculateHmacSha256(data, secretKey)\
        println "Calculated HMAC-SHA256 hash: \${hash}"\
    }\
}