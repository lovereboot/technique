
package com.alonso.performance;


/** Example resource class hosted at the URI path "/myresource"
 */
public class MyResource {
    
    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    public String getIt() {
        return "Hi there!";
    }
}
