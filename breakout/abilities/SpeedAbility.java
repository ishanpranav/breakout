package breakout.abilities;

import java.awt.Color;

import breakout.Table;

/**
 * Represents the ability to change the speed of the ball.
 *
 * @author Ishan Pranav
 */
public class SpeedAbility extends Ability
{
    private final double _factor;

    /**
     * Initializes a new instance of the {@link SpeedAbility} class.
     *
     * @param factor The scale factor of the speed.
     */
    public SpeedAbility(double factor)
    {
        this._factor = factor;
    }

    /** {@inheritDoc} */
    @Override
    public void execute(Table table)
    {
        table.getBall().scaleSpeed(this._factor);

        super.execute(table);
    }

    /** {@inheritDoc} */
    @Override
    public Color getColor()
    {
        final double abs = Math.abs(this._factor);
        final double signum = Math.signum(this._factor);

        if (abs < 1)
        {
            if (signum < 0)
            {
                return Color.GRAY;
            }
            else
            {
                return Color.BLUE;
            }
        }
        else if (signum < 0)
        {
            return Color.MAGENTA;
        }
        else
        {
            return Color.ORANGE;
        }
    }
}
