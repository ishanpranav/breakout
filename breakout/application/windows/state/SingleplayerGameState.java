package breakout.application.windows.state;

import pong.Drawable;

/**
 * Represents the Singleplayer game state. This class implements the State
 * Design Pattern as defined by the {@link GameContext} and {@link Drawable}
 * interfaces.
 *
 * @author Ishan Pranav
 */
public class SingleplayerGameState extends GameState
{
    /**
     * Initializes a new instance of the {@link SingleplayerGameState} class.
     *
     * @param context The game context.
     */
    public SingleplayerGameState(GameContext context)
    {
        super(context);
    }

    /** {@inheritDoc} */
    @Override
    protected void moveLeft()
    {
        this.getTable().getPaddle().moveLeft();
    }

    /** {@inheritDoc} */
    @Override
    protected void moveRight()
    {
        this.getTable().getPaddle().moveRight();
    }
}
