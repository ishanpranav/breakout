package breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import pong.Entity;

/**
 * Represents a physical particle.
 *
 * @author Ishan Pranav
 */
public class Particle implements Entity, Inflatable, Terminable
{
    private final Color _color;
    private final Rectangle _rectangle;
    private final int _lifetime;

    private Table _table;
    private int _horizontalVelocity;
    private int _verticalVelocity;
    private int _duration;

    /**
     * Initializes a new instance of the {@link Particle} class.
     *
     * @param color              The color of the particle.
     * @param horizontalPosition The starting horizontal position of the particle.
     * @param verticalPosition   The starting vertical position of the particle.
     * @param size               The size of the particle.
     */
    public Particle(Color color, int horizontalPosition, int verticalPosition, int size)
    {
        this._color = color;
        this._rectangle = new Rectangle(horizontalPosition, verticalPosition, size, size);
        this._lifetime = (int)(Math.random() * 480) + 120;
    }

    /** {@inheritDoc} */
    @Override
    public final void bounceHorizontal()
    {
        this._horizontalVelocity *= -1;
    }

    /** {@inheritDoc} */
    @Override
    public final void bounceVertical()
    {
        this._verticalVelocity *= -1;
    }

    /** {@inheritDoc} */
    @Override
    public final void draw(Graphics2D graphics2D)
    {
        graphics2D.setColor(this._color);
        graphics2D.fill(this._rectangle);
    }

    /** {@inheritDoc} */
    @Override
    public final int getHorizontalVelocity()
    {
        return this._horizontalVelocity;
    }

    /** {@inheritDoc} */
    @Override
    public final int getVerticalVelocity()
    {
        return this._verticalVelocity;
    }

    final void hit(Brick brick)
    {
        brick.hit(this, this._rectangle, false);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean isTerminated()
    {
        return this._duration > this._lifetime;
    }

    /** {@inheritDoc} */
    @Override
    public final void reset()
    {
    }

    /**
     * Serves the ball.
     */
    public final void serve()
    {
        this._horizontalVelocity = (int)Randomizer.getDefault().createVelocity();
        this._verticalVelocity = (int)Randomizer.getDefault().createVelocity();
    }

    final void setTable(Table value)
    {
        this._table = value;
    }

    /** {@inheritDoc} */
    @Override
    public final void terminate()
    {
        this._duration = this._lifetime;
    }

    /** {@inheritDoc} */
    @Override
    public final void update()
    {
        this._rectangle.translate(this._horizontalVelocity, this._verticalVelocity);

        if (this._rectangle.getMaxX() >= this._table.getWidth() || this._rectangle.getX() < 0)
        {
            this.bounceHorizontal();
        }

        if (this._rectangle.getMaxY() >= this._table.getHeight() || this._rectangle.getY() < 0)
        {
            this.bounceVertical();
        }

        this._duration++;
    }
}