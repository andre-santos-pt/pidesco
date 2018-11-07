package pt.iscte.pidesco.javaeditor;

import java.io.File;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import pt.iscte.pidesco.extensibility.PidescoServices;
import pt.iscte.pidesco.javaeditor.internal.JavaEditorActivator;
import pt.iscte.pidesco.javaeditor.service.JavaEditorListener;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.projectbrowser.extensibility.ProjectBrowserFilter;
import pt.iscte.pidesco.projectbrowser.model.PackageElement;
import pt.iscte.pidesco.projectbrowser.model.SourceElement;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;

public class PackageFocusFilter implements ProjectBrowserFilter {

	private JavaEditorServices services;
	private File file;

	public PackageFocusFilter() {
		BundleContext context = JavaEditorActivator.getInstance().getContext();

		ServiceReference<PidescoServices> ref = context.getServiceReference(PidescoServices.class);
		final PidescoServices pidescoServices = context.getService(ref);	

		services = JavaEditorActivator.getInstance().getServices();
		services.addListener(new JavaEditorListener.Adapter() {
			@Override
			public void fileOpened(File f) {
				file = f;
				pidescoServices.runTool(ProjectBrowserServices.REFRESH_TOOL_ID, true);
			}
		});
	}

	@Override
	public boolean include(SourceElement element, PackageElement parent) {
		if(file == null)
			file = services.getOpenedFile();
		if(file == null)
			return true;
		
		if(element.isPackage() && file != null && ((PackageElement) element).hasChild(file, true))
			return true;
		else if (element.isClass() && parent.hasChild(file, false))
			return true;

		return false;
	}

}
