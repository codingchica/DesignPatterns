package codingchica.patterns.creational.factory.enums;

import java.util.Arrays;
import java.util.Optional;

/**
 * In Java, an enum is a class where factory and execution logic may co-exist.
 * In this example, the creation of HTTP Status Code objects is delegated to the enum/factory, as is the logic to
 * interpret that status code's meaning.  Only a few values are listed here, as examples, where a real HTTP status code
 * enum would need to be exhaustive.
 *
 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Status">Mozilla Developer Network Web Docs: HTTP response status codes</a>
 */
public enum HttpStatusCode {
    /**
     * 100 - Continue
     */
    CONTINUE(100),
    /**
     * 200 - OK
     */
    OK(200),
    /**
     * 301 - Moved Permanently
     */
    MOVED_PERMANENTLY(301),
    /**
     * 404 - Not Found
     */
    NOT_FOUND(404),
    /**
     * 500 - Internal Server Error
     */
    INTERNAL_SERVER_ERROR(500);

    /**
     * The underlying HTTP status code value represented by this object.
     */
    private int code;

    /**
     * Constructor for HttpStatusCode objects.
     *
     * @param code The numeric HTTP status code.
     */
    HttpStatusCode(int code) {
        this.code = code;
    }

    /**
     * Getter for the code field.
     *
     * @return The value of the code field.
     */
    public int getCode() {
        return code;
    }

    /**
     * Indicates whether this Http status code is informational.
     *
     * @return Returns a boolean if and only if the code is in the range <pre>100 &lt;= code &lt; 200</pre>.
     */
    public boolean isInformational() {
        // 100 is the current minimum value
        return code <= 199;
    }

    /**
     * Indicates whether this Http status code is successful.
     *
     * @return Returns a boolean if and only if the code is in the range <pre>200 &lt;= code &lt; 300</pre>.
     */
    public boolean isSuccessful() {
        return code >= 200 && code <= 299;
    }

    /**
     * Indicates whether this Http status code is a redirection.
     *
     * @return Returns a boolean if and only if the code is in the range <pre>300 &lt;= code &lt; 400</pre>.
     */
    public boolean isRedirection() {
        return code >= 300 && code <= 399;
    }

    /**
     * Indicates whether this Http status code is a client error.
     *
     * @return Returns a boolean if and only if the code is in the range <pre>400 &lt;= code &lt; 500</pre>.
     */
    public boolean isClientError() {
        return code >= 400 && code <= 499;
    }

    /**
     * Indicates whether this Http status code is a server error.
     *
     * @return Returns a boolean if and only if the code is in the range <pre>500 &lt;= code &lt; 600</pre>.
     */
    public boolean isServerError() {
        // 599 is the current maximum value
        return code >= 500;
    }

    /**
     * Retrieve the corresponding HttpStatusCode based upon the int code value provided.
     *
     * @param code The int value representing the Http status code.
     * @return An Optional containing the HttpStatusCode object, if one was found.
     */
    public static Optional<HttpStatusCode> fromCode(int code) {
        return Arrays.stream(HttpStatusCode.values()).filter(value -> value.code == code).findFirst();
    }
}