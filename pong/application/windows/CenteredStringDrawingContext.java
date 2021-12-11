package pong.application.windows;

import java.awt.FontMetrics;
import java.awt.Graphics2D;

/**
 * A {@link StringDrawingContext} used to draw centered strings.
 *
 * @author Ishan Pranav
 */
public class CenteredStringDrawingContext extends StringDrawingContext
{
    private final int _width;
    private final int _horizontalPosition;

    private int _verticalPosition;
    private double _margin = 0.25;

    /**
     * Initializes a new instance of the {@link CenteredStringDrawingContext} class.
     *
     * @param graphics2D       The two-dimensional graphics.
     * @param width            The width of the text area.
     * @param verticalPosition The vertical position form which to begin drawing.
     */
    public CenteredStringDrawingContext(Graphics2D graphics2D, int width, int verticalPosition)
    {
        this(graphics2D, width, 0, verticalPosition);
    }

    /**
     * Initializes a new instance of the {@link CenteredStringDrawingContext} class.
     *
     * @param graphics2D         The two-dimensional graphics.
     * @param width              The width of the text area.
     * @param horizontalPosition The horizontal position from which to begin
     *                           drawing.
     * @param verticalPosition   The vertical position form which to begin drawing.
     */
    public CenteredStringDrawingContext(Graphics2D graphics2D, int width, int horizontalPosition, int verticalPosition)
    {
        super(graphics2D);

        this._width = width;
        this._horizontalPosition = horizontalPosition;
        this._verticalPosition = verticalPosition;
    }

    /** {@inheritDoc} */
    @Override
    protected void drawCore(String value)
    {
        final Graphics2D graphics2D = this.getGraphics2D();
        final FontMetrics fontMetrics = graphics2D.getFontMetrics();

        final int height = fontMetrics.getHeight();

        this._verticalPosition += height;

        graphics2D.drawString(value, (this._width - fontMetrics.stringWidth(value)) / 2 + this._horizontalPosition,
                this._verticalPosition);

        this._verticalPosition += height * this._margin;
    }

    /**
     * Sets the margin.
     *
     * @param value The margin. This is the amount of whitespace following a line of
     *              text. The margin value is a coefficient multiplied by the text
     *              height. A margin value of 1 indicates whitespace equal in height
     *              to the text. The default is 0.25.
     */
    public final void setMargin(double value)
    {
        this._margin = value;
    }
}
