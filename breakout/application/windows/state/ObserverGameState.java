package breakout.application.windows.state;

import breakout.Ball;
import breakout.Table;
import pong.Drawable;

/**
 * Represents the Observer game state. This class implements the State Design
 * Pattern as defined by the {@link GameContext} and {@link Drawable}
 * interfaces.
 *
 * @author Ishan Pranav
 */
public class ObserverGameState extends GameState
{
    /**
     * Initializes a new instance of the {@link ObserverGameState} class.
     *
     * @param context The game context.
     */
    public ObserverGameState(GameContext context)
    {
        super(context);
    }

    /** {@inheritDoc} */
    @Override
    protected void moveLeft()
    {
    }

    /** {@inheritDoc} */
    @Override
    protected void moveRight()
    {
    }

    /** {@inheritDoc} */
    @Override
    public void update()
    {
        final Table table = this.getTable();
        final Ball ball = table.getBall();

        table.getPaddle().follow(ball);

        super.update();
    }
}
