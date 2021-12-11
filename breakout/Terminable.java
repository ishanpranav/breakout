package breakout;

/**
 * Defines a method for terminating an operation or object.
 *
 * @author Ishan Pranav
 */
public interface Terminable
{
    /**
     * Gets a value indicating whether the object or operation is terminated.
     *
     * @return {@code true} if the object or operation is terminated; otherwise,
     *         {@code false}.
     */
    boolean isTerminated();

    /**
     * Terminates the object or operation.
     */
    void terminate();
}
