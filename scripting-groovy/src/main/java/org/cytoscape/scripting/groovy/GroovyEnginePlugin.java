package org.cytoscape.scripting.groovy;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.cytoscape.scripting.CyScriptingEngineImpl;

import cytoscape.plugin.CytoscapePlugin;

public class GroovyEnginePlugin extends CytoscapePlugin {

	private static final String ENGINE_NAME = "groovy";
	private static final String ENGINE_DISPLAY_NAME = "Groovy Engine (v 1.8.1)";

	public GroovyEnginePlugin() {
		final Icon icon = new ImageIcon(this.getClass().getClassLoader().getResource("images/groovy22x22.png"));
		new CyScriptingEngineImpl(ENGINE_NAME, ENGINE_DISPLAY_NAME, icon);
	}
}