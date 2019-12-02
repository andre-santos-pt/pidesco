package pt.iscte.pidesco.javaeditor.service;

import java.io.File;

import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserListener;

/**
 * Represents a listener for events in the Java Editor
 */
public interface JavaEditorListener {

	/**
	 * File open event.
	 * @param file (non-null) reference to the opened file
	 */
	default void fileOpened(File file) { }

	/**
	 * File save event.
	 * @param file (non-null) reference to the saved file
	 */
	default void fileSaved(File file) { }

	/**
	 * File close event.
	 * @param file (non-null) reference to the closed file
	 */
	default void fileClosed(File file) { }

	/**
	 * File selection changed event.
	 * @param file (non-null) reference to the file
	 * @param text selected text
	 * @param offset offset of the selection (index of the first selected character)
	 * @param length length of the selection
	 */
	default void selectionChanged(File file, String text, int offset, int length) { }


	/**
	 * Interface adapter for convenience. Default implementations do nothing.
	 *
	 * @deprecated use the interface directly.
	 */
	@Deprecated
	class Adapter implements JavaEditorListener { }

}
