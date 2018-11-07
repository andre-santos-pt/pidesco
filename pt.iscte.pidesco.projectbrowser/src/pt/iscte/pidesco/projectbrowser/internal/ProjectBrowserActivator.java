package pt.iscte.pidesco.projectbrowser.internal;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import pt.iscte.pidesco.extensibility.PidescoServices;
import pt.iscte.pidesco.projectbrowser.model.ClassElement;
import pt.iscte.pidesco.projectbrowser.model.PackageElement;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserListener;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;

public class ProjectBrowserActivator implements BundleActivator {

	private static ProjectBrowserActivator instance;
	private Set<ProjectBrowserListener> listeners;
	private ServiceRegistration<ProjectBrowserServices> service;
	private PidescoServices pidescoServices;

	private PackageElement invisibleRoot;

	@Override
	public void start(BundleContext context) throws Exception {
		instance = this;
		listeners = new HashSet<ProjectBrowserListener>();
		service = context.registerService(ProjectBrowserServices.class, new ProjectBrowserServicesImpl(), null);

		ServiceReference<PidescoServices> ref = context.getServiceReference(PidescoServices.class);
		pidescoServices = context.getService(ref);
		
		scanRoot();
	}
	
	private void scanRoot() {
		invisibleRoot = scan(pidescoServices.getWorkspaceRoot());
	}


	@Override
	public void stop(BundleContext context) throws Exception {
		instance = null;
		service.unregister();
		listeners.clear();
	}

	public static ProjectBrowserActivator getInstance() {
		return instance;
	}

	public PidescoServices getPidescoServices() {
		return pidescoServices;
	}
	
	public Set<ProjectBrowserListener> getListeners() {
		return listeners;
	}

	public void addListener(ProjectBrowserListener l) {
		listeners.add(l);
	}

	public void removeListener(ProjectBrowserListener l) {
		listeners.remove(l);
	}


	private static PackageElement scan(File root) {
		PackageElement pack = new PackageElement(null, "", root);
		for(File child : root.listFiles()) {
			if(!child.getName().startsWith("."))
				scanRec(child, pack);
		}
		return pack;
	}

	private static void scanRec(File f, PackageElement p) {
		if(f.isFile() && f.getName().endsWith(".java")) {
			new ClassElement(p, f);
		}
		else if(f.isDirectory()) {
			PackageElement childPack = new PackageElement(p, f.getName(), f);
			for(File child : f.listFiles()) {
				scanRec(child, childPack);
			}
		}
	}

	public PackageElement getRoot() {
		return invisibleRoot;
	}
	
	public void refreshWorkspace() {
		scanRoot();
	}
}
