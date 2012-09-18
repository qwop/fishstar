package com.tan.util;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;

public class EclipseUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static IContainer findBestSourceFolderForRebelXml(
			final IJavaProject javaProject) throws JavaModelException {
		IPackageFragmentRoot[] roots = javaProject.getAllPackageFragmentRoots();
		IContainer foundResource = null;
		if (roots != null) {
			for ( int i = 0; i < roots.length; i++ ) {
				if ((roots[ i ].exists()) && (roots[ i ].getKind() == 1)) {
					IResource resource = roots[ i ].getResource();
					if ((resource instanceof IContainer)) {
						IContainer container = (IContainer) resource;

						IResource jarTxt = container.findMember("jars.txt");
						if ((jarTxt != null) && ((jarTxt instanceof IFile))) {
							return container;
						}

						if (new Path("src/main/resources").equals(resource
								.getProjectRelativePath())) {
							foundResource = (IContainer) resource;
						} else if (foundResource == null) {
							foundResource = (IContainer) resource;
						}
					}
				}
			}
		}

		return foundResource != null ? foundResource : javaProject
				.getProject();
	}
}
