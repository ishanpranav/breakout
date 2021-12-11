package pong;

/**
 * Specifies the direction of a one-dimensional vector.
 *
 * @author Ishan Pranav
 *
 */
public enum Direction
{
    /**
     * The negative direction.
     */
    NEGATIVE(-1),

    /**
     * No direction.
     */
    NONE(0),

    /**
     * The positive direction.
     */
    POSITIVE(1);

    private final int _value;

    Direction(int value)
    {
        this._value = value;
    }

    /**
     * Gets the integer value representing the direction.
     *
     * @return -1 if the direction is negative, 1 if the direction is positive, and
     *         0 if the direction is neither negative or positive.
     */
    public int getValue()
    {
        return this._value;
    }

    /**
     * Retrieves the direction that corresponds to the relative direction of a
     * destination to a source.
     *
     * @param source      The source.
     * @param destination The destination.
     * @return The direction.
     */
    public static Direction fromDistance(double source, double destination)
    {
        return fromVector(destination - source);
    }

    /**
     * Retrieves the direction of a vector.
     *
     * @param value The vector.
     * @return The direction.
     */
    public static Direction fromVector(double value)
    {
        return values()[(int)Math.signum(value) + 1];
    }
}
