// 代码生成时间: 2025-08-21 11:15:44
package com.example.webcontent

import groovy.net.http.RESTClient
import groovy.json.JsonSlurper
import org.apache.http.impl.client.HttpClients
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.apache.http.HttpResponse

/**
 * A simple web content fetcher using the GRAILS framework.
 * It fetches content from a web page and returns it as a String.
 *
 * @author Your Name
 * @date Today
 */
class WebContentFetcher {

    /**
     * Fetches the content of a webpage.
     *
     * @param url The URL of the webpage to fetch.
     * @return The content of the webpage as a String.
     * @throws Exception If there is an error fetching the webpage.
     */
    String fetchWebContent(String url) {
        // Use Apache HttpClient for better performance and connection management
        def connectionManager = new PoolingHttpClientConnectionManager()
        def httpClient = HttpClients.custom().setConnectionManager(connectionManager).build()
        def request = new HttpGet(url)

        try {
            def response = httpClient.execute(request)
            if (response.statusLine.statusCode == 200) {
                return response.entity.content.text
            } else {
                throw new Exception("Failed to fetch content, HTTP status: ${response.statusLine.statusCode}")
            }
        } catch (Exception e) {
            throw new Exception("Error fetching web content: ${e.message}")
        } finally {
            // Make sure to release the connection
            request.releaseConnection()
        }
    }

    /**
     * Main method for testing the fetcher.
     */
    static void main(String[] args) {
        def fetcher = new WebContentFetcher()
        try {
            def content = fetcher.fetchWebContent("https://example.com")
            println "Fetched content: 
${content}"
        } catch (Exception e) {
            println "Error: ${e.message}"
        }
    }
}
