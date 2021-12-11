package breakout.application.windows.state;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import breakout.Table;
import pong.Drawable;

/**
 * Defines the core behavior of a game state and provides a base for derived
 * classes.
 *
 * @author Ishan Pranav
 */
public abstract class GameState implements Drawable
{
    private final GameContext _context;
    private final Table _table;

    /**
     * Called from constructors in derived classes to initialize the
     * {@link GameState} class.
     *
     * @param context The game context.
     */
    protected GameState(GameContext context)
    {
        this._table = context.createTable();
        this._context = context;
    }

    /** {@inheritDoc} */
    @Override
    public final void draw(Graphics2D graphics2D)
    {
        this._table.draw(graphics2D);
    }

    /**
     * Gets the table.
     *
     * @return The table.
     */
    protected final Table getTable()
    {
        return this._table;
    }

    /**
     * Moves the paddle to the left.
     */
    protected abstract void moveLeft();

    /**
     * Moves the paddle to the right.
     */
    protected abstract void moveRight();

    /** {@inheritDoc} */
    @Override
    public void update()
    {
        this._table.update();

        if (this._table.isTerminated())
        {
            this._context.setState(new TerminalGameState(this._context, false));
        }

        if (this._context.isPressed(KeyEvent.VK_A) || this._context.isPressed(KeyEvent.VK_LEFT))
        {
            this._table.getPaddle().moveLeft();
        }
        else if (this._context.isPressed(KeyEvent.VK_D) || this._context.isPressed(KeyEvent.VK_RIGHT))
        {
            this._table.getPaddle().moveRight();
        }
    }
}
