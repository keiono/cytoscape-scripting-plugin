package org.cytoscape.scripting.javascript;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.cytoscape.scripting.CyScriptingEngineImpl;

import cytoscape.plugin.CytoscapePlugin;

public class JavaScriptEnginePlugin extends CytoscapePlugin {
	
	private static final String ENGINE_NAME = "js";
	private static final String ENGINE_DISPLAY_NAME = "JavaScript Engine (Standard in Java 6 and later)";
	private static final Icon ICON = new ImageIcon(JavaScriptEnginePlugin.class.getResource("/images/rhino32.png"));

	public JavaScriptEnginePlugin() {
		new CyScriptingEngineImpl(ENGINE_NAME, ENGINE_DISPLAY_NAME, ICON);		
	}
}
