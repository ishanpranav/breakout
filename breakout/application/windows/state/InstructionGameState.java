package breakout.application.windows.state;

import java.awt.Graphics2D;

import pong.Drawable;
import pong.application.ResourceProvider;
import pong.application.windows.CenteredStringDrawingContext;

/**
 * Represents the Instruction game state. This class implements the State Design
 * Pattern as defined by the {@link GameContext} and {@link Drawable}
 * interfaces.
 *
 * @author Ishan Pranav
 */
public class InstructionGameState implements Drawable
{
    private final GameContext _context;

    /**
     * Initializes a new instance of the {@link InstructionGameState} class.
     *
     * @param context The game context.
     */
    public InstructionGameState(GameContext context)
    {
        this._context = context;
    }

    /** {@inheritDoc} */
    @Override
    public final void draw(Graphics2D graphics2D)
    {
        final ResourceProvider resources = this._context.getResources();
        final CenteredStringDrawingContext stringDrawingContext = new CenteredStringDrawingContext(graphics2D,
                this._context.getWidth(), 40);

        stringDrawingContext.setSize(100);
        stringDrawingContext.setMargin(1d / 3);
        stringDrawingContext.setSize(20);

        stringDrawingContext.draw(resources.getInstructions());
    }

    /** {@inheritDoc} */
    @Override
    public final void update()
    {
    }
}
