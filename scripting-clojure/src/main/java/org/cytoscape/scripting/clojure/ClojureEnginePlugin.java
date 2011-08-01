package org.cytoscape.scripting.clojure;

import org.cytoscape.scripting.CyScriptingEngineImpl;

import cytoscape.plugin.CytoscapePlugin;

public class ClojureEnginePlugin extends CytoscapePlugin {
	
	private static final String ENGINE_NAME = "Clojure";
	private static final String ENGINE_DISPLAY_NAME = "Clojure Engine (v 1.2)";
	
	public ClojureEnginePlugin() {
			new CyScriptingEngineImpl(ENGINE_NAME, ENGINE_DISPLAY_NAME, null);		
	}
}