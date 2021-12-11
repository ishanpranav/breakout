package pong.application.windows;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import pong.Drawable;

/**
 * Defines the core behavior of a game engine and provides a base for derived
 * classes.
 *
 * @author Game Driver Contributors, Ishan Pranav
 */
public abstract class GameDriverSlim extends Canvas implements Runnable, KeyListener, Drawable
{
    private static final long serialVersionUID = -5325150234647333808L;

    /**
     * Specifies whether the canvas clears before rendering. This field is
     * serialized.
     */
    private final boolean _cleanCanvas;

    /**
     * Specifies the window frame. This field is serialized.
     */
    private final JFrame _frame = new JFrame();

    /**
     * Specifies the frame rate of the game engine in frames per second. This field
     * is serialized.
     */
    private final int _framesPerSecond;

    /**
     * Specifies the state of each key. This field is serialized.
     */
    private final boolean[] _keysPressed;

    /**
     * Called from constructors in derived classes. Called from constructors in
     * derived classes to initialize the {@link GameDriverSlim} class.
     */
    protected GameDriverSlim()
    {
        this(60, true);
    }

    /**
     * Called from constructors in derived classes to initialize the
     * {@link GameDriverSlim} class.
     *
     * @param framesPerSecond The frame rate of the game engine in frames per
     *                        second.
     * @param cleanCanvas     {@code true} if the canvas clears before each render
     *                        operation; otherwise {@code false}.
     */
    protected GameDriverSlim(int framesPerSecond, boolean cleanCanvas)
    {
        this._framesPerSecond = framesPerSecond;
        this._cleanCanvas = cleanCanvas;
        this._keysPressed = new boolean[KeyEvent.KEY_LAST];

        this.setSize(800, 600);
        this.addKeyListener(this);

        this.setBackground(Color.BLACK);
    }

    /**
     * Gets the title of the window.
     *
     * @return The title.
     */
    protected final String getTitle()
    {
        return this._frame.getTitle();
    }

    /** {@inheritDoc} */
    public final boolean isPressed(int keyCode)
    {
        return this._keysPressed[keyCode];
    }

    /** {@inheritDoc} */
    @Override
    public void keyPressed(KeyEvent e)
    {
        this._keysPressed[e.getKeyCode()] = true;
    }

    /** {@inheritDoc} */
    @Override
    public void keyReleased(KeyEvent e)
    {
        this._keysPressed[e.getKeyCode()] = false;
    }

    /** {@inheritDoc} */
    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    private void render()
    {
        BufferStrategy bufferStrategy = this.getBufferStrategy();

        if (bufferStrategy == null)
        {
            this.createBufferStrategy(3);
            bufferStrategy = this.getBufferStrategy();
        }

        final Graphics graphics = bufferStrategy.getDrawGraphics();

        if (this._cleanCanvas)
        {
            graphics.setColor(this.getBackground());
            graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        }

        this.draw((Graphics2D)graphics);

        graphics.dispose();

        bufferStrategy.show();
    }

    /** {@inheritDoc} */
    @Override
    public void run()
    {
        final double nanoSecondConversion = 999998888.0 / this._framesPerSecond;

        long lastTime = System.nanoTime();
        double changeInSeconds = 0;

        while (true)
        {
            final long now = System.nanoTime();

            changeInSeconds += (now - lastTime) / nanoSecondConversion;

            while (changeInSeconds >= 1)
            {
                this.update();

                changeInSeconds--;
            }

            this.render();

            lastTime = now;
        }
    }

    /**
     * Sets the title of the window.
     *
     * @param title The title.
     */
    protected final void setTitle(String title)
    {
        this._frame.setTitle(title);
    }

    /**
     * Starts the game engine on a new thread. This method should only be called
     * once.
     */
    public final void start()
    {
        this._frame.add(this);
        this._frame.pack();
        this._frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this._frame.setLocationRelativeTo(null);
        this._frame.setResizable(false);
        this._frame.setVisible(true);

        this.startThread();
    }

    private synchronized void startThread()
    {
        new Thread(this).start();

        this.setFocusable(true);
    }
}
