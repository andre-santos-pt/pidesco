package pt.iscte.pidesco.extensibility;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;

import pt.iscte.pidesco.internal.PidescoActivator;

public enum PidescoExtensionPoint {
	VIEW,
	TOOL;

	private IExtension[] extensions;
	
	private PidescoExtensionPoint() {
		extensions = Platform.getExtensionRegistry()
				.getExtensionPoint(getId()).getExtensions();
	}
	
	public String getId() {
		return PidescoActivator.PLUGIN_ID + "." + name().toLowerCase();
	}

	public IExtension[] getExtensions() {
		return extensions;
	}
	
	public boolean existsId(String id) {
		for(IExtension e : extensions)
			if(e.getUniqueIdentifier().equals(id))
				return true;
		
		return false;
	}
	
	public void checkId(String id) {
		if(!existsId(id))
			throw new IllegalArgumentException("invalid id '" + id + "' for a " + name().toLowerCase());
	}
}