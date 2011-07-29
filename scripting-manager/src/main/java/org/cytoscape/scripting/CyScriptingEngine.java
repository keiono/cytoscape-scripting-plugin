package org.cytoscape.scripting;

import javax.swing.Icon;

/**
 * Wrapper interface for Script Engines.
 *
 */
public interface CyScriptingEngine {
	
	/**
	 * Globally unique ID of the scripting engine.
	 * For example, Rhino JavaScript engine has ID ""
	 * 
	 * @return engine ID
	 */
	public String getIdentifier();


	/**
	 * Human readable name of this engine.
	 * 
	 * @return name of scripting engine
	 */
	public String getDisplayName();


	/**
	 * Returns icon for desktop application.
	 * 
	 * @return icon for this engine (language logo, etc.).
	 */
	public Icon getIcon();

}
