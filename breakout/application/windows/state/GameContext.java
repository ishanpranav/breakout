package breakout.application.windows.state;

import breakout.Table;
import pong.Drawable;
import pong.application.ResourceProvider;

/**
 * Defines a game context. This interface defines the State Design Pattern
 * alongside the {@link Drawable} interface.
 *
 * @author Ishan Pranav
 */
public interface GameContext
{
    /**
     * Creates a new table.
     *
     * @return A new instance of the {@link Table} class.
     */
    Table createTable();

    /**
     * Gets the resource provider.
     *
     * @return The resource provider.
     */
    ResourceProvider getResources();

    /**
     * Gets the window width.
     *
     * @return The window width.
     */
    int getWidth();

    /**
     * Determines whether a key is pressed.
     *
     * @param keyCode The key code.
     * @return {@code true} if the key is pressed; otherwise {@code false}.
     */
    boolean isPressed(int keyCode);

    /**
     * Sets the game state.
     *
     * @param value The game state.
     */
    void setState(Drawable value);
}