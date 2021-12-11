package breakout.application.windows;

import pong.application.BundleResourceProvider;

/**
 * Represents the application.
 *
 * @author Ishan Pranav
 */
public final class Program
{
    private Program()
    {
    }

    /**
     * Provides the main entry point for the application.
     *
     * @param args The application arguments.
     */
    public static void main(String[] args)
    {
        new Game(new BundleResourceProvider()).start();
    }
}
