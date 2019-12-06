package org.xtext.example.mydsl.debug.ui.pda.launcher;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.xtext.example.mydsl.debug.core.pda.DebugCorePlugin;
import org.eclipse.ui.part.FileEditorInput;

/**
 * Launches a PDA file
 */
public class PDALaunchShortcut implements ILaunchShortcut {

	@Override
	public void launch(ISelection selection, String mode) {
		// must be a structured selection with one file selected
		IFile file = (IFile) ((IStructuredSelection) selection).getFirstElement();

		// check for an existing launch config for the pda file
		String path = file.getFullPath().toString();
		ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
		ILaunchConfigurationType type = launchManager
				.getLaunchConfigurationType(DebugCorePlugin.ID_PDA_LAUNCH_CONFIGURATION_TYPE);
		try {
			ILaunchConfiguration[] configurations = launchManager.getLaunchConfigurations(type);
			for (int i = 0; i < configurations.length; i++) {
				ILaunchConfiguration configuration = configurations[i];
				String attribute = configuration.getAttribute(DebugCorePlugin.ATTR_PDA_PROGRAM, (String) null);
				if (path.equals(attribute)) {
					DebugUITools.launch(configuration, mode);
					return;
				}
			}
		} catch (CoreException e) {
			return;
		}

		try {
			ILaunchConfigurationWorkingCopy workingCopy = type.newInstance(null, file.getName());
			workingCopy.setAttribute(DebugCorePlugin.ATTR_PDA_PROGRAM, path);
			ILaunchConfiguration configuration = workingCopy.doSave();
			DebugUITools.launch(configuration, mode);
		} catch (CoreException e1) {
		}
	}

	@Override
	public void launch(IEditorPart editor, String mode) {
//		find launch configuration and new make configuration from active editor.
		IEditorInput input = editor.getEditorInput();
		if (input instanceof FileEditorInput) {
			IFile ipath = ((FileEditorInput) input).getFile();
			String path = ipath.getFullPath().toString();

			ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
			ILaunchConfigurationType type = launchManager
					.getLaunchConfigurationType(DebugCorePlugin.ID_PDA_LAUNCH_CONFIGURATION_TYPE);
			try {
				ILaunchConfiguration[] configurations = launchManager.getLaunchConfigurations(type);
				for (int i = 0; i < configurations.length; i++) {
					ILaunchConfiguration configuration = configurations[i];
					String attribute = configuration.getAttribute(DebugCorePlugin.ATTR_PDA_PROGRAM, (String) null);
					if (path.equals(attribute)) {
						DebugUITools.launch(configuration, mode);
						return;
					}
				}
			} catch (CoreException e) {
				return;
			}

			try {
				Path p = Paths.get(path);
				String fileName = p.getFileName().toString();

				ILaunchConfigurationWorkingCopy workingCopy = type.newInstance(null, fileName);
				workingCopy.setAttribute(DebugCorePlugin.ATTR_PDA_PROGRAM, path);
				ILaunchConfiguration configuration = workingCopy.doSave();
				DebugUITools.launch(configuration, mode);
			} catch (CoreException e1) {
			}
		}
		
	}

}
