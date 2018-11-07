package pt.iscte.pidesco.projectbrowser.internal;

import pt.iscte.pidesco.projectbrowser.model.PackageElement;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserListener;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;

public class ProjectBrowserServicesImpl implements ProjectBrowserServices {

	
	public PackageElement getRootPackage() {
		return ProjectBrowserActivator.getInstance().getRoot();
	}
	
	public void refreshTree() {
		ProjectBrowserActivator.getInstance().refreshWorkspace();
		activateView();
		ProjectBrowserView.getInstance().refresh();
	}
	
	public void activateFilter(String id) {
		activateView();
		ProjectBrowserView.getInstance().activateFilter(id);
	}
	
	public void deactivateFilter(String id) {
		activateView();
		ProjectBrowserView.getInstance().deactivateFilter(id);
	}
	
	public void addListener(ProjectBrowserListener listener) {
		ProjectBrowserActivator.getInstance().addListener(listener);
	}
	
	public void removeListener(ProjectBrowserListener listener) {
		ProjectBrowserActivator.getInstance().removeListener(listener);
	}
	
	private void activateView() {
		ProjectBrowserActivator.getInstance().getPidescoServices().openView(ProjectBrowserView.VIEW_ID);
	}
	
}
