package pong;

import java.awt.Graphics2D;

/**
 * Defines methods for drawing entities.
 *
 * @author Ishan Pranav
 */
public interface Drawable
{
    /**
     * Draws the entity on the graphical canvas. This method is called once per
     * rendering operation.
     *
     * @param graphics2D The two-dimensional graphics.
     */
    void draw(Graphics2D graphics2D);

    /**
     * Updates the state of the entity. This method is called once per frame.
     */
    void update();
}
