package breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import breakout.abilities.Ability;
import pong.Entity;
import pong.application.windows.CenteredStringDrawingContext;

/**
 * Represents a table used for table tennis. This class defines the Composite
 * Design Pattern.
 *
 * @author Ishan Pranav
 */
public class Table implements Entity, Terminable
{
    private static final int PARTICLE_ROWS = 4;
    private static final int PARTICLE_COLUMNS = 12;

    private final Ball _ball;
    private final int _height;
    private final Paddle _paddle;
    private final Brick _brick;
    private final Pill _pill;
    private final int _width;
    private final ArrayList<Entity> _entities = new ArrayList<Entity>(2);
    private final ArrayList<Pill> _pills = new ArrayList<Pill>();
    private final int _columns;
    private final int _margin;

    private int _rows;
    private int _destroyed;
    private int _particleIndex;
    private Brick[] _bricks;
    private Particle[] _particles;
    private int _score;
    private boolean _terminated;

    /**
     * Initializes a new instance of the {@link Table} class.
     *
     * @param ball    The ball.
     * @param paddle  The paddle.
     * @param brick   The brick prototype.
     * @param pill    The pill prototype.
     * @param width   The width.
     * @param height  The height.
     * @param columns The number of columns of bricks.
     * @param margin  The margin.
     */
    public Table(Ball ball, Paddle paddle, Brick brick, Pill pill, int width, int height, int columns, int margin)
    {
        this._entities.add(ball);
        this._entities.add(paddle);

        ball.setTable(this);

        this._ball = ball;

        paddle.setTable(this);

        this._paddle = paddle;

        brick.setTable(this);

        this._brick = brick;

        pill.setTable(this);

        this._pill = pill;
        this._width = width;
        this._height = height;
        this._columns = columns;
        this._margin = margin;

        this.advance();
    }

    /**
     * Advances the difficulty level of the game.
     */
    public final void advance()
    {
        this.reset();

        this._ball.serve();

        this._destroyed = 0;
        this._particleIndex = 0;
        this._rows += 2;
        this._bricks = new Brick[this._rows * this._columns];
        this._particles = new Particle[this._bricks.length * PARTICLE_ROWS * PARTICLE_COLUMNS];

        int i = 0;

        final int centerMargin = (this._width - this._columns * (this._margin + this._brick.getWidth())) / 2;
        final int penultimateRow = this._rows - 1;
        final int startRed = 255;
        final int stepRed = -startRed / penultimateRow;
        final int stepGreen = 255 / penultimateRow;
        final int stepBlue = 255 / penultimateRow;

        for (int row = 0; row < this._rows; row++)
        {
            for (int column = 0; column < this._columns; column++)
            {
                final Brick brick = this._brick.clone(row,
                        new Color(startRed + row * stepRed, row * stepGreen, row * stepBlue),
                        centerMargin + column * (this._margin + this._brick.getWidth()),
                        (row + 2) * (this._margin + this._brick.getHeight()));

                this._bricks[i] = brick;
                this._entities.add(brick);

                i++;
            }
        }
    }

    /**
     * Destroys the brick the given brick.
     *
     * @param brick The brick to destroy
     */
    public final void destroy(Brick brick)
    {
        for (int i = 0; i < this._bricks.length; i++)
        {
            if (this._bricks[i] == brick)
            {
                this._bricks[i] = null;
                this._score += (this._rows - i / this._columns) * 2 - 1;
                this._entities.remove(brick);
                this._destroyed++;

                if (this._destroyed == this._bricks.length)
                {
                    this.advance();
                }
                else
                {
                    final Ability ability = Randomizer.getDefault().createAbility();

                    if (ability != null)
                    {
                        final Pill pill = this._pill.clone(ability,
                                brick.getHorizontalPosition() + (brick.getWidth() - this._pill.getWidth()) / 2,
                                brick.getVerticalPosition() + brick.getHeight());

                        this._pills.add(pill);
                        this._entities.add(pill);
                    }

                    final Color brickColor = brick.getColor();
                    final int particleSize = brick.getWidth() / PARTICLE_COLUMNS;
                    final int initialHorizontalPosition = brick.getHorizontalPosition();
                    int horizontalPosition = 0;
                    int verticalPosition = brick.getVerticalPosition() - particleSize;

                    for (int j = 0; j < PARTICLE_ROWS * PARTICLE_COLUMNS; j++)
                    {
                        if (j % PARTICLE_COLUMNS == 0)
                        {
                            horizontalPosition = initialHorizontalPosition;
                            verticalPosition += particleSize;
                        }

                        final Particle particle = new Particle(brickColor, horizontalPosition, verticalPosition,
                                particleSize);

                        particle.setTable(this);
                        particle.serve();

                        this._entities.add(particle);

                        this._particles[this._particleIndex] = particle;

                        horizontalPosition += particleSize;

                        this._particleIndex++;
                    }
                }

                break;
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public final void draw(Graphics2D graphics2D)
    {
        for (final Entity entity : this._entities)
        {
            entity.draw(graphics2D);
        }

        graphics2D.setColor(Color.WHITE);

        final CenteredStringDrawingContext stringDrawingContext = new CenteredStringDrawingContext(graphics2D,
                this.getWidth(), 0, 250);

        stringDrawingContext.setSize(50);
        stringDrawingContext.draw(this._score);
    }

    /**
     * Gets the ball.
     *
     * @return The ball.
     */
    public final Ball getBall()
    {
        return this._ball;
    }

    final double getHeight()
    {
        return this._height;
    }

    /**
     * Gets the left paddle.
     *
     * @return The left paddle.
     */
    public final Paddle getLeft()
    {
        return this._paddle;
    }

    /**
     * Gets the paddle.
     *
     * @return The paddle.
     */
    public final Paddle getPaddle()
    {
        return this._paddle;
    }

    final int getWidth()
    {
        return this._width;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isTerminated()
    {
        return this._terminated;
    }

    /** {@inheritDoc} */
    @Override
    public final void reset()
    {
        this._terminated = false;

        for (final Entity entity : this._entities)
        {
            entity.reset();
        }

        if (this._particles != null)
        {
            for (int i = 0; i < this._particles.length; i++)
            {
                final Particle particle = this._particles[i];

                if (particle != null)
                {
                    this._particles[i] = null;

                    this._entities.remove(particle);
                }
            }
        }

        for (final Pill pill : this._pills)
        {
            this._entities.remove(pill);
        }

        this._pills.clear();
    }

    /** {@inheritDoc} */
    @Override
    public final void terminate()
    {
        this._terminated = true;
    }

    /** {@inheritDoc} */
    @Override
    public final void update()
    {
        for (final Entity entity : this._entities)
        {
            entity.update();
        }

        for (int i = 0; i < this._particles.length; i++)
        {
            final Particle particle = this._particles[i];

            if (particle != null && particle.isTerminated() && this._entities.remove(particle))
            {
                this._particles[i] = null;
            }
        }

        for (final Brick brick : this._bricks)
        {
            if (brick != null)
            {
                this._ball.hit(brick);

                for (final Particle particle : this._particles)
                {
                    if (particle != null)
                    {
                        particle.hit(brick);
                    }
                }
            }
        }

        for (final Pill pill : this._pills)
        {
            pill.hit(this._paddle);
        }
    }
}
