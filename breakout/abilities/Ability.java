package breakout.abilities;

import java.awt.Color;

import breakout.Table;

/**
 * Defines the core behavior of special abilities and provides a base for
 * derived classes. This class defines the Command Design Pattern.
 *
 * @author Ishan Pranav
 */
public abstract class Ability
{
    /**
     * Called from constructors in derived classes to initialize the {@link Ability}
     * class.
     */
    protected Ability()
    {
    }

    /**
     * Performs the operation associated with the ability.
     *
     * @param table The table.
     */
    public void execute(Table table)
    {
        table.getPaddle().setColor(this.getColor());
    }

    /**
     * Gets the color associated with the ability.
     *
     * @return The color.
     */
    public abstract Color getColor();
}
