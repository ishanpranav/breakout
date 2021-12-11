package breakout;

/**
 * Defines methods for bouncing entities.
 *
 * @author Ishan Pranav
 */
public interface Inflatable
{
    /**
     * Causes the inflatable object to bounce horizontally.
     */
    void bounceHorizontal();

    /**
     * Causes the inflatable object to bounce vertically.
     */
    void bounceVertical();

    /**
     * Gets the horizontal velocity of the inflatable object.
     *
     * @return The horizontal velocity of the inflatable object.
     */
    int getHorizontalVelocity();

    /**
     * Gets the vertical velocity of the inflatable object.
     *
     * @return The vertical velocity of the inflatable object.
     */
    int getVerticalVelocity();
}
