package breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import pong.Entity;

/**
 * Represents a brick. This class defines the Prototype Design Pattern.
 *
 * @author Ishan Pranav
 */
public class Brick implements Cloneable, Entity
{
    private Color _color;
    private int _row;
    private Rectangle _rectangle;
    private Table _table;

    private Brick(int width, int height)
    {
        this._rectangle = new Rectangle(0, 0, width, height);
    }

    /** {@inheritDoc} */
    @Override
    public final Brick clone()
    {
        try
        {
            return (Brick)super.clone();
        }
        catch (final CloneNotSupportedException exception)
        {
            return null;
        }
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @param row                The row of the clone.
     * @param color              The color of the clone.
     * @param horizontalPosition The horizontal position of the clone.
     * @param verticalPosition   The vertical position of the clone.
     * @return The clone.
     */
    public final Brick clone(int row, Color color, int horizontalPosition, int verticalPosition)
    {
        final Brick result = this.clone();

        result._row = row;
        result._color = color;
        result._rectangle = new Rectangle(horizontalPosition, verticalPosition, result._rectangle.width,
                result._rectangle.height);

        return result;
    }

    /** {@inheritDoc} */
    @Override
    public void draw(Graphics2D graphics2D)
    {
        graphics2D.setColor(this._color);
        graphics2D.fill(this._rectangle);
    }

    /**
     * Gets the color of the brick.
     *
     * @return The color of the brick.
     */
    public final Color getColor()
    {
        return this._color;
    }

    /**
     * Gets the height of the brick.
     *
     * @return The height.
     */
    public final int getHeight()
    {
        return this._rectangle.height;
    }

    /**
     * Gets the horizontal position of the brick.
     *
     * @return The horizontal position of the brick.
     */
    public final int getHorizontalPosition()
    {
        return this._rectangle.x;
    }

    /**
     * Gets the row of the brick.
     *
     * @return The row.
     */
    public final int getRow()
    {
        return this._row;
    }

    /**
     * Gets the vertical position of the brick.
     *
     * @return The vertical position of the brick.
     */
    public final int getVerticalPosition()
    {
        return this._rectangle.y;
    }

    /**
     * Gets the width of the brick.
     *
     * @return The width.
     */
    public final int getWidth()
    {
        return this._rectangle.width;
    }

    final void hit(Inflatable inflatable, Rectangle rectangle, boolean destroy)
    {
        if (this._rectangle.intersects(rectangle))
        {
            if (this.isHorizontal(rectangle, inflatable.getHorizontalVelocity(), inflatable.getVerticalVelocity()))
            {
                inflatable.bounceHorizontal();
            }
            else
            {
                inflatable.bounceVertical();
            }

            if (destroy)
            {
                this._table.destroy(this);
            }
        }
    }

    private final boolean isHorizontal(Rectangle projectile, int horizontalVelocity, int verticalVelocity)
    {
        final int previousHorizontalPosition = (int)projectile.getX() - horizontalVelocity;
        final int previousYPos = (int)projectile.getY() - verticalVelocity;
        final int height = (int)projectile.getHeight();
        final int width = (int)projectile.getWidth();

        if (previousYPos + height <= this._rectangle.getY() && projectile.getMaxY() >= this._rectangle.getY())
        {
            return false;
        }
        if (previousHorizontalPosition + width <= this._rectangle.getX()
                && projectile.getX() + width >= this._rectangle.getX()
                || previousYPos < this._rectangle.getY() + this._rectangle.getHeight()
                || projectile.getY() > this._rectangle.getY() + this._rectangle.getHeight())
        {
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public void reset()
    {
    }

    final void setTable(Table value)
    {
        this._table = value;
    }

    /** {@inheritDoc} */
    @Override
    public void update()
    {
    }

    /**
     * Creates a new prototypical instance of the {@link Brick} class.
     *
     * @param width  The width of the brick.
     * @param height The height of the brick.
     *
     * @return A new brick instance.
     */
    public static Brick createPrototype(int width, int height)
    {
        return new Brick(width, height);
    }
}
