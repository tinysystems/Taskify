package org.xtext.example.mydsl.debug.ui.pda;

import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

public class DebugUIPlugin extends AbstractUIPlugin {
	//The shared instance.
	private static DebugUIPlugin plugin;
	//Resource bundle.
	private ResourceBundle resourceBundle;
	
//	private ImageRegistry imageRegistry;
	
	private final static String ICONS_PATH = "icons/";//$NON-NLS-1$
	
	/**
	 * The id of the plugin
	 *
	 * @since 1.4.200
	 */
	public static final String PLUGIN_ID = "org.xtext.example.mydsl.debug.ui.pda"; //$NON-NLS-1$
	
	/**
     * PDA program image
     */
    public final static String IMG_OBJ_PDA = "IMG_OBJ_PDA";
	
    public DebugUIPlugin() {
    	super();
    	plugin = this;
    }
    
    /**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
		resourceBundle = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static DebugUIPlugin getDefault() {
		return plugin;
	}
	
	/**
	 * Returns the string from the plugin's resource bundle,
	 * or 'key' if not found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = DebugUIPlugin.getDefault().getResourceBundle();
		try {
			return (bundle != null) ? bundle.getString(key) : key;
		} catch (MissingResourceException e) {
			return key;
		}
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		try {
			if (resourceBundle == null)
				resourceBundle = ResourceBundle.getBundle("org.xtext.example.mydsl.debug.ui.pda.DebugUIPluginResources");
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
		return resourceBundle;
	}
	
	protected void initializeImageRegistry(ImageRegistry reg) {
		declareImage(IMG_OBJ_PDA, ICONS_PATH + "dsl_view.gif");
	}
	
	 /**
     * Declares a workbench image given the path of the image file (relative to
     * the workbench plug-in). This is a helper method that creates the image
     * descriptor and passes it to the main <code>declareImage</code> method.
     * 
     * @param symbolicName the symbolic name of the image
     * @param path the path of the image file relative to the base of the workbench
     * plug-ins install directory
     * <code>false</code> if this is not a shared image
     */
    private void declareImage(String key, String path) {
    	ImageDescriptor desc = ImageDescriptor.getMissingImageDescriptor();
		Bundle bundle = Platform.getBundle(PLUGIN_ID);
		URL url = null;
		if (bundle != null) {
			url = FileLocator.find(bundle, new Path(path), null);
			if (url != null) {
				desc = ImageDescriptor.createFromURL(url);
			}
		}
		getImageRegistry().put(key, desc);
    }
	
    /**
	 * retry imageRegistry initializing
	 */
	/*@Override
	public ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
            initializeImageRegistry(imageRegistry);
        }
        return imageRegistry;
	}*/
	
}
