// 代码生成时间: 2025-10-03 01:42:20
import groovyx.net.http.RESTClient
import org.codehaus.groovy.runtime.InvokerInvocationException

class RPCRemoteCallFramework {

    // The base URL for the remote service
    private String baseUrl

    private RESTClient client

    /**
     * Constructor for the RPC Remote Call Framework
     *
     * @param baseUrl The base URL of the remote service
     */
    public RPCRemoteCallFramework(String baseUrl) {
        this.baseUrl = baseUrl
        this.client = new RESTClient(baseUrl)
    }

    /**
     * Execute a remote call to the specified service method
     *
     * @param methodName The name of the method to call
     * @param params A Map containing the parameters for the method call
     * @return The result of the remote method call
     */
    public Object executeRemoteCall(String methodName, Map<String, Object> params) {
        try {
            // Construct the full URL for the remote method
            String fullUrl = this.baseUrl + '/' + methodName

            // Execute the remote call
            Object result = client.post(path: fullUrl, body: params).data

            // Return the result of the remote call
            return result
        } catch (InvokerInvocationException e) {
            // Handle Groovy invocation exceptions
            println 'Error executing remote call: ' + e.message
        } catch (Exception e) {
            // Handle any other exceptions
            println 'Error executing remote call: ' + e.message
        }

        return null
    }
}
