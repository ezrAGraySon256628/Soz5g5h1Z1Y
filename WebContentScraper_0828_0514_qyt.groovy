// 代码生成时间: 2025-08-28 05:14:07
package scraper

import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.ContentType.URLENC
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils

/**
 * A Grails service class to scrape web content.
 *
 * @author Your Name
 * @since 1.0
 */
class WebContentScraperService {

    static transactional = false

    /**
     * Scrapes content from a given URL and returns it as a String.
     *
     * @param url The URL to scrape content from.
     * @return The scraped content as a String.
     * @throws IOException If an I/O error occurs.
     */
    String scrapeContent(String url) {
        try {
            // Create a RESTClient instance for making HTTP requests
            RESTClient client = new RESTClient(url)

            // Execute a GET request to retrieve the web content
            def response = client.get(path: '', headers: ['Accept': 'text/html'])

            if (response.status == 200) {
                // Return the content of the response
                return response.data.text
            } else {
                // Handle unexpected status codes
                throw new RuntimeException("Failed to retrieve content: HTTP ${response.status}")
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during the scraping process
            throw new IOException("Error scraping content from ${url}: ${e.message}")
        } finally {
            // Ensure the RESTClient is closed to free up resources
            client?.close()
        }
    }
}
