package org.xtext.example.mydsl.debug.ui.pda.launcher;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
//import org.eclipse.debug.ui.CommonTab;
//import org.eclipse.debug.ui.sourcelookup.SourceLookupTab;

/**
 * Tab group for a PDA application
 */
public class PDATabGroup extends AbstractLaunchConfigurationTabGroup {

	@Override
	public void createTabs(ILaunchConfigurationDialog arg0, String arg1) {
		setTabs(new ILaunchConfigurationTab[] {
				new PDAMainTab()
//				new SourceLookupTab(),
//				new CommonTab()
		});
	}

}
