package org.xtext.example.mydsl.ui.wizard;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.xtext.example.mydsl.ui.wizard.messages"; //$NON-NLS-1$
	
	public static String HelloWorldProject_Label;
	public static String HelloWorldProject_Description;
	public static String AddTwoIntegersProject_Label;
	public static String AddTwoIntegersProject_Description;
	public static String FunctionCallProject_Label;
	public static String FunctionCallProject_Description;
	
	static {
	// initialize resource bundle
	NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
