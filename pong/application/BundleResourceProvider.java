package pong.application;

import java.util.ResourceBundle;

/**
 * A {@link ResourceProvider} used to retrieve resources from a
 * {@link ResourceBundle}.
 *
 * @author Ishan Pranav
 */
public class BundleResourceProvider extends ResourceProvider
{
    private final ResourceBundle _resourceBundle;

    /**
     * Initializes a new instance of the {@link BundleResourceProvider} class.
     */
    public BundleResourceProvider(String name)
    {
        this._resourceBundle = ResourceBundle.getBundle(name);
    }

    /** {@inheritDoc} */
    @Override
    protected String getString(String key)
    {
        return this._resourceBundle.getString(key);
    }
}
