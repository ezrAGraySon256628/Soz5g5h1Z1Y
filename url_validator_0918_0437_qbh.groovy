// 代码生成时间: 2025-09-18 04:37:39
import groovy.json.JsonSlurper
import groovy.json.JsonBuilder
import java.net.HttpURLConnection
import java.net.URL

/**
 * URL Validator class that checks if a given URL is valid and accessible.
 */
class UrlValidator {

    /**
     * Method to validate URL.
     *
     * @param urlString The URL string to be validated.
     * @return A map containing validation result and response code.
     */
    def validateUrl(String urlString) {
        Map result = [:]
        try {
            URL url = new URL(urlString)
            HttpURLConnection connection = (HttpURLConnection) url.openConnection()
            connection.setRequestMethod("HEAD")
            connection.connectTimeout = 5000 // 5 seconds
            connection.readTimeout = 5000 // 5 seconds
            connection.connect()

            int responseCode = connection.responseCode
            // 200 means OK, we consider this as valid URL
            if (responseCode == HttpURLConnection.HTTP_OK) {
                result.put('isValid', true)
                result.put('responseCode', responseCode)
            } else {
                result.put('isValid', false)
                result.put('responseCode', responseCode)
            }

            connection.disconnect()
        } catch (Exception e) {
            result.put('isValid', false)
            result.put('error', e.message)
        }

        return result
    }
}

/**
 * Main class that can be used to run the URL validator.
 */
class MainClass {

    static void main(String[] args) {
        UrlValidator validator = new UrlValidator()
        String urlToTest = 'https://www.example.com'
        Map validationResult = validator.validateUrl(urlToTest)
        JsonSlurper slurper = new JsonSlurper()
        JsonBuilder builder = new JsonBuilder(validationResult)
        String jsonResponse = builder.toPrettyString()

        println("Validation Result: ${jsonResponse}")
    }
}