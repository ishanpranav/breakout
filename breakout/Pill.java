package breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import breakout.abilities.Ability;
import pong.Entity;

/**
 * Represents a pill that provides special abilities. This class defines the
 * Prototype Design Pattern.
 *
 * @author Ishan Pranav
 */
public class Pill implements Cloneable, Entity, Terminable
{
    private boolean _terminated;
    private Rectangle _rectangle;
    private final int _speed;
    private Ability _ability;
    private Table _table;

    private Pill(int width, int height, int speed)
    {
        this._rectangle = new Rectangle(0, 0, width, height);
        this._speed = speed;
    }

    /** {@inheritDoc} */
    @Override
    public final Pill clone()
    {
        try
        {
            return (Pill)super.clone();
        }
        catch (final CloneNotSupportedException exception)
        {
            return null;
        }
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @param ability            The ability of the clone.
     * @param horizontalPosition The horizontal position of the clone.
     * @param verticalPosition   The vertical position of the clone.
     * @return The clone.
     */
    public final Pill clone(Ability ability, int horizontalPosition, int verticalPosition)
    {
        final Pill result = this.clone();

        result._ability = ability;
        result._rectangle = new Rectangle(horizontalPosition, verticalPosition, result._rectangle.width,
                result._rectangle.height);

        return result;
    }

    /** {@inheritDoc} */
    @Override
    public void draw(Graphics2D graphics2D)
    {
        if (!this._terminated)
        {
            if (this.isTerminated())
            {
                graphics2D.setColor(Color.WHITE);
            }
            else
            {
                graphics2D.setColor(this._ability.getColor());
            }

            graphics2D.fill(this._rectangle);
        }
    }

    /**
     * Gets the ability associated with the pill.
     *
     * @return The ability.
     */
    public final Ability getAbility()
    {
        return this._ability;
    }

    /**
     * Gets the width of the pill.
     *
     * @return The width of the pill.
     */
    public final int getWidth()
    {
        return this._rectangle.width;
    }

    final void hit(Paddle paddle)
    {
        if (!this.isTerminated())
        {
            paddle.hit(this, this._rectangle);
        }
    }

    /** {@inheritDoc} */
    @Override
    public final boolean isTerminated()
    {
        return this._terminated || this._rectangle.getMaxY() >= this._table.getHeight();
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
    public final void terminate()
    {
        this._terminated = true;

        this._ability.execute(this._table);
    }

    /** {@inheritDoc} */
    @Override
    public void update()
    {
        if (!this.isTerminated())
        {
            this._rectangle.translate(0, this._speed);
        }
    }

    /**
     * Creates a new prototypical instance of the {@link Pill} class.
     *
     * @param width  The width of the pill.
     * @param height The height of the pill.
     * @param speed  The speed of the pill.
     *
     * @return A new pill instance.
     */
    public static Pill createPrototype(int width, int height, int speed)
    {
        return new Pill(width, height, speed);
    }
}
