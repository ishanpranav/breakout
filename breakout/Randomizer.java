package breakout;

import java.util.Random;

import breakout.abilities.Ability;
import breakout.abilities.LengthAbility;
import breakout.abilities.SpeedAbility;

/**
 * Represents an extended random number generator. This class defines the
 * Singleton Design Pattern.
 *
 * @author Ishan Pranav
 */
public class Randomizer
{
    private static Randomizer instance;

    private final Random _random = new Random();

    private Randomizer()
    {
    }

    /**
     * Generates a random ability.
     *
     * @return The ability.
     */
    public Ability createAbility()
    {
        final int random = this._random.nextInt(8);

        switch (random)
        {
            case 0:
                return new LengthAbility(0.5);
                
            case 1:
                return new LengthAbility(2);

            case 2:
                return new SpeedAbility(0.6);

            case 3:
                return new SpeedAbility(-0.6);

            case 4:
                return new SpeedAbility(1.5);

            case 5:
                return new SpeedAbility(-1.5);
                
            default:
                return null;
        }
    }

    /**
     * Generates a random velocity.
     *
     * @return The velocity.
     */
    public double createVelocity()
    {
        int sign;

        if (this._random.nextInt(2) == 0)
        {
            sign = 1;
        }
        else
        {
            sign = -1;
        }

        return sign * (this._random.nextDouble() * 3 + 1);
    }

    /**
     * Gets the default {@link Randomizer} instance.
     *
     * @return The default instance.
     */
    public static Randomizer getDefault()
    {
        if (instance == null)
        {
            instance = new Randomizer();
        }

        return instance;
    }
}
