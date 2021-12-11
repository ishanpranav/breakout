package breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import pong.Direction;
import pong.Entity;

/**
 * Represents a table tennis ball.
 *
 * @author Ishan Pranav
 */
public class Ball implements Entity, Inflatable
{
    private static final double SMASH_FORCE_FACTOR = 1.2;

    private final int _initialHorizontalPosition;
    private final int _initialVerticalPosition;
    private final Rectangle _rectangle;
    private final int _speed;
    private final int _maxSpeed;

    private Table _table;
    private int _horizontalVelocity;
    private int _verticalVelocity;

    /**
     * Initializes a new instance of the {@link Ball} class.
     *
     * @param horizontalPosition The starting horizontal position of the ball.
     * @param verticalPosition   The starting vertical position of the ball.
     * @param size               The size of the ball.
     * @param speed              The speed of the ball.
     */
    public Ball(int horizontalPosition, int verticalPosition, int size, int speed)
    {
        this._rectangle = new Rectangle(horizontalPosition, verticalPosition, size, size);
        this._speed = speed;
        this._maxSpeed = speed * 2;
        this._initialHorizontalPosition = horizontalPosition;
        this._initialVerticalPosition = verticalPosition;
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
        graphics2D.setColor(Color.WHITE);
        graphics2D.fill(this._rectangle);
    }

    final double getHorizontalPosition()
    {
        return this._rectangle.getCenterX();
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
        brick.hit(this, this._rectangle, true);
    }

    final void hit(Rectangle rectangle, Direction horizontalDirection)
    {
        final Rectangle intersection = this._rectangle.intersection(rectangle);

        if (!intersection.isEmpty())
        {
            if (intersection.getWidth() > 0)
            {
                this._verticalVelocity *= -1;
            }

            if (intersection.getHeight() > this._rectangle.getWidth() / 5)
            {
                this._horizontalVelocity *= -1;
            }

            if (Direction.fromVector(this._horizontalVelocity) == horizontalDirection)
            {
                this._horizontalVelocity *= SMASH_FORCE_FACTOR;
                this._verticalVelocity *= SMASH_FORCE_FACTOR;
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public final void reset()
    {
        this._rectangle.setLocation(this._initialHorizontalPosition, this._initialVerticalPosition);

        this._horizontalVelocity = 0;
        this._verticalVelocity = 0;
    }

    /**
     * Scales the speed of the ball by a given factor.
     *
     * @param factor The scale factor.
     */
    public final void scaleSpeed(double factor)
    {
        this._verticalVelocity *= factor;
        this._horizontalVelocity *= factor;

        if (this._verticalVelocity == 0 || this._horizontalVelocity == 0)
        {
            this.serve();
        }
    }

    /**
     * Serves the ball.
     */
    public final void serve()
    {
        this._horizontalVelocity = this._speed;
        this._verticalVelocity = this._speed;
    }

    final void setTable(Table value)
    {
        this._table = value;
    }

    /** {@inheritDoc} */
    @Override
    public final void update()
    {
        if (Math.abs(this._horizontalVelocity) > this._maxSpeed)
        {
            this._horizontalVelocity = this._maxSpeed * (int)Math.signum(this._horizontalVelocity);
        }

        if (Math.abs(this._verticalVelocity) > this._maxSpeed)
        {
            this._horizontalVelocity = this._maxSpeed * (int)Math.signum(this._verticalVelocity);
        }

        this._rectangle.translate(this._horizontalVelocity, this._verticalVelocity);

        if (this._rectangle.getMaxX() >= this._table.getWidth() || this._rectangle.getX() < 0)
        {
            this.bounceHorizontal();
        }

        if (this._rectangle.getMaxY() >= this._table.getHeight())
        {
            this._table.terminate();
        }

        if (this._rectangle.getMinY() <= 0 || this._rectangle.getMaxY() >= this._table.getHeight())
        {
            this.bounceVertical();
        }
    }
}