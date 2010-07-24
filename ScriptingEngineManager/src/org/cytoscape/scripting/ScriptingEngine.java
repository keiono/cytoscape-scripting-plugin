package org.cytoscape.scripting;

import javax.swing.Icon;

/**
 * Wrapper interface for Script Engines.
 * 
 * @author kono
 *
 */
public interface ScriptingEngine {
	
	public String getIdentifier();
	
	public String getDisplayName();
	
	public Icon getIcon();

}
