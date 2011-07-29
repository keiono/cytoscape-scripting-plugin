package org.cytoscape.scripting;

import javax.swing.Icon;

/**
 * Wrapper interface for Script Engines.
 *
 */
public interface ScriptingEngine {
	
	public String getIdentifier();
	
	public String getDisplayName();
	
	public Icon getIcon();

}
