package breakout.abilities;

import java.awt.Color;

import breakout.Table;

/**
 * Represents the ability to change the length of the paddle.
 *
 * @author Ishan Pranav
 */
public class LengthAbility extends Ability
{
    private final double _factor;

    /**
     * Initializes a new instance of the {@link LengthAbility} class.
     *
     * @param factor The scale factor of the length.
     */
    public LengthAbility(double factor)
    {
        this._factor = factor;
    }

    /** {@inheritDoc} */
    @Override
    public void execute(Table table)
    {
        table.getPaddle().scaleWidth(this._factor);

        super.execute(table);
    }

    /** {@inheritDoc} */
    @Override
    public Color getColor()
    {
        if (this._factor < 1)
        {
            return Color.RED;
        }
        else
        {
            return Color.GREEN;
        }
    }
}
