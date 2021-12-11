package pong.application.windows;

import java.awt.Font;
import java.awt.Graphics2D;

/**
 * Defines the core behavior of a string drawing context and provides a base for
 * derived classes.
 *
 * @author Ishan Pranav
 */
public abstract class StringDrawingContext
{
    private final Graphics2D _graphics2D;

    private String _fontName = "Segoe UI";
    private int _size = 24;

    /**
     * Called from constructors in derived classes to initialize the
     * {@link StringDrawingContext} class.
     *
     * @param graphics2D The two-dimensional graphics.
     */
    protected StringDrawingContext(Graphics2D graphics2D)
    {
        this._graphics2D = graphics2D;
    }

    /**
     * Draws the string representation of the value.
     *
     * @param value The value.
     */
    public void draw(int value)
    {
        this.draw(Integer.toString(value));
    }

    /**
     * Draws the string representation of the value.
     *
     * @param value The value.
     */
    public final void draw(String value)
    {
        this._graphics2D.setFont(new Font(this._fontName, Font.PLAIN, this._size));

        for (final String line : value.split("\n"))
        {
            this.drawCore(line);
        }
    }

    /**
     * When overridden in a derived class, draws the string value.
     *
     * @param value The value.
     */
    protected abstract void drawCore(String value);

    /**
     * Gets the two-dimensional graphics.
     *
     * @return The two-dimensional graphics.
     */
    protected final Graphics2D getGraphics2D()
    {
        return this._graphics2D;
    }

    /**
     * Sets the font name.
     *
     * @param value The font name. The default is {@code "Segoe UI"}.
     */
    public final void setFontName(String value)
    {
        this._fontName = value;
    }

    /**
     * Sets the font size.
     *
     * @param value The font size. The default is {@code 24}.
     */
    public final void setSize(int value)
    {
        this._size = value;
    }
}
