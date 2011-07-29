package org.cytoscape.scripting.javascript;

import java.beans.PropertyChangeEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.cytoscape.scripting.CyScriptingEngine;
import org.cytoscape.scripting.ScriptEngineManagerPlugin;

import cytoscape.Cytoscape;
import cytoscape.logger.CyLogger;
import cytoscape.plugin.CytoscapePlugin;

public class JavaScriptEnginePlugin extends CytoscapePlugin implements CyScriptingEngine {
	
	private static final CyLogger logger = CyLogger.getLogger(JavaScriptEnginePlugin.class);

	private static final String ENGINE_NAME = "js";
	private static final String ENGINE_DISPLAY_NAME = "JavaScript Engine (Standard in Java 6 and later)";
	private static final Icon ICON = new ImageIcon(JavaScriptEnginePlugin.class.getResource("/images/rhino32.png"));

	private static final JavaScriptEnginePlugin engine = new JavaScriptEnginePlugin();

	
	public JavaScriptEnginePlugin() {
		Cytoscape.getPropertyChangeSupport().addPropertyChangeListener(this);
	}

	@Override
	public String getDisplayName() {
		return ENGINE_DISPLAY_NAME;
	}

	
	@Override
	public Icon getIcon() {
		return ICON;
	}


	@Override
	public String getIdentifier() {
		return ENGINE_NAME;
	}


	private void register() {
		// JavaScript is standard in Java SE 6 and later.  So we do not have to register actual engine.
		ScriptEngineManagerPlugin.getManager().registerEngine(ENGINE_NAME, engine);
		logger.info("JavaScript engine registered to Script Engine Manager.");
	}


	@Override
	public void propertyChange(PropertyChangeEvent e) {
		if (ScriptEngineManagerPlugin.getManager().getEngine(ENGINE_NAME) != null)
			return;

		if (e.getPropertyName().equals(Cytoscape.CYTOSCAPE_INITIALIZED))
			register();
	}
}
