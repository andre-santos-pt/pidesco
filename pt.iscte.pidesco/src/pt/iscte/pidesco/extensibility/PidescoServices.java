package pt.iscte.pidesco.extensibility;

import java.io.File;
import java.util.List;

import org.eclipse.swt.graphics.Image;


/**
 * Service containing operations offered by the Pidesco infrastructure.
 */
public interface PidescoServices {

	/**
	 * Name of the images folder from which plugin images will be indexed.
	 */
	public static final String IMAGES_FOLDER = "images";
	
	/**
	 * View id of a Pidesco view (common to all views, which will have a secondary id)
	 */
	public static final String VIEW_ID = "pt.iscte.pidesco.view";
	
	/**
	 * Opens Pidesco view.
	 * @param viewId view id
	 */
	void openView(String viewId);

	
	/**
	 * Returns the active view id.
	 * @return (non-null)
	 */
	String getActiveView();
	
	
	/**
	 * Resets the view placement.
	 * @param viewLocations (non-null) list: view locations that depend on others
	 * should appear at a list position that is after their dependent view locations
	 */
	void layout(List<ViewLocation> viewLocations);
	
	/**
	 * Obtains image from plugin, assuming that the image is contained in the "images" folder.
	 * @param pluginId plugin id
	 * @param fileName name of the image file
	 * @return the image, or null if cannot be found
	 */
	Image getImageFromPlugin(String pluginId, String fileName);
	
	/**
	 * Runs a tool given its id.
	 * @param toolId tool id
	 * @param activate if true turns the tool active (if applicable, otherwise is ignored), false turns the tool inactive.
	 */
	void runTool(String toolId, boolean activate);
	
	/**
	 * Returns the workspace root directory.
	 * @return non-null reference to a File object that matches a directory in the filesystem
	 */
	File getWorkspaceRoot();
}