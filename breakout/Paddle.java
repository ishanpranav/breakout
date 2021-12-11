package breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import pong.Direction;
import pong.Entity;

/**
 * Represents a table tennis paddle.
 *
 * @author Ishan Pranav
 */
public class Paddle implements Entity
{
    private final Rectangle _rectangle;
    private final int _speed;
    private final int _initialHorizontalPosition;
    private final int _initialVerticalPosition;

    private Color _color = Color.WHITE;
    private Table _table;
    private Direction _direction;

    /**
     * Initializes a new instance of the {@link Paddle} class.
     *
     * @param horizontalPosition The starting horizontal position of the paddle.
     * @param verticalPosition   The starting vertical position of the paddle.
     * @param width              The width of the paddle.
     * @param height             The height of the paddle.
     * @param speed              The speed of the paddle.
     */
    public Paddle(int horizontalPosition, int verticalPosition, int width, int height, int speed)
    {
        this._rectangle = new Rectangle(horizontalPosition, verticalPosition, width, height);
        this._speed = speed;
        this._initialHorizontalPosition = horizontalPosition;
        this._initialVerticalPosition = verticalPosition;
    }

    /** {@inheritDoc} */
    @Override
    public final void draw(Graphics2D graphics2D)
    {
        graphics2D.setColor(this._color);
        graphics2D.fill(this._rectangle);
    }

    /**
     * Tracks the position of a ball and follows it.
     *
     * @param ball The ball.
     */
    public final void follow(Ball ball)
    {
        final int x = (int)(ball.getHorizontalPosition() - this._rectangle.getWidth() / 2);

        this._rectangle.setLocation(x, this._rectangle.y);

        this._direction = Direction.fromDistance(x, this._rectangle.getY());
    }

    final void hit(Pill pill, Rectangle rectangle)
    {
        if (this._rectangle.intersects(rectangle))
        {
            pill.terminate();
        }
    }

    /**
     * Moves the paddle left.
     */
    public final void moveLeft()
    {
        if (this._rectangle.getX() >= 0)
        {
            this._rectangle.translate(-this._speed, 0);
        }

        this._direction = Direction.NEGATIVE;
    }

    /**
     * Moves the paddle right.
     */
    public final void moveRight()
    {
        if (this._rectangle.getX() <= this._table.getWidth() - this._rectangle.getWidth())
        {
            this._rectangle.translate(this._speed, 0);
        }

        this._direction = Direction.POSITIVE;
    }

    /** {@inheritDoc} */
    @Override
    public final void reset()
    {
        this._rectangle.setLocation(this._initialHorizontalPosition, this._initialVerticalPosition);
    }

    /**
     * Scales the width of the paddle by a given factor.
     *
     * @param factor The scale factor.
     */
    public final void scaleWidth(double factor)
    {
        this._rectangle.width *= factor;
    }

    /**
     * Sets the color of the paddle.
     *
     * @param value The value.
     */
    public final void setColor(Color value)
    {
        this._color = value;
    }

    final void setTable(Table value)
    {
        this._table = value;
    }

    /** {@inheritDoc} */
    @Override
    public final void update()
    {
        if (this._rectangle.getMinY() < 0)
        {
            this._rectangle.setLocation((int)this._rectangle.getX(), 0);
        }
        else if (this._rectangle.getMaxY() > this._table.getHeight())
        {
            this._rectangle.setLocation((int)this._rectangle.getX(),
                    (int)(this._table.getHeight() - this._rectangle.getHeight()));
        }

        this._table.getBall().hit(this._rectangle, this._direction);
    }
}