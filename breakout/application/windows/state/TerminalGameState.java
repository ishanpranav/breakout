package breakout.application.windows.state;

import java.awt.Graphics2D;

import pong.Drawable;
import pong.application.ResourceProvider;
import pong.application.windows.CenteredStringDrawingContext;

/**
 * Represents the terminal game state. This class implements the State Design
 * Pattern as defined by the {@link GameContext} and {@link Drawable}
 * interfaces.
 *
 * @author Ishan Pranav
 */
public class TerminalGameState implements Drawable
{
    private final GameContext _context;
    private final boolean _victory;

    /**
     * Initializes a new instance of the {@link TerminalGameState} class.
     *
     * @param context The game context.
     * @param victory {@code true} if the player won the game; otherwise,
     *                {@code false}.
     */
    public TerminalGameState(GameContext context, boolean victory)
    {
        this._context = context;
        this._victory = victory;
    }

    /** {@inheritDoc} */
    @Override
    public final void draw(Graphics2D graphics2D)
    {
        final ResourceProvider resources = this._context.getResources();

        final CenteredStringDrawingContext stringDrawingContext = new CenteredStringDrawingContext(graphics2D,
                this._context.getWidth(), 75);

        if (this._victory)
        {
            stringDrawingContext.draw(resources.getVictoryText());
        }
        else
        {
            stringDrawingContext.draw(resources.getDefeatText());
        }

        stringDrawingContext.draw(resources.getExitText());
    }

    /** {@inheritDoc} */
    @Override
    public final void update()
    {
    }
}
