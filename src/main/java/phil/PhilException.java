package phil;

/** Exception which is a runtime exception, specially for Phil.
 *
 */
public class PhilException extends RuntimeException {

    /** Constructor of PhilException.
     *
     * @param message representing message of exception.
     */
    public PhilException(String message) {
        super(message);
    }
}
