package org.cytoscape.scripting.groovy;

import java.beans.PropertyChangeEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.cytoscape.scripting.CyScriptingEngine;
import org.cytoscape.scripting.ScriptEngineManagerPlugin;

import cytoscape.Cytoscape;
import cytoscape.logger.CyLogger;
import cytoscape.plugin.CytoscapePlugin;

public class GroovyEnginePlugin extends CytoscapePlugin implements CyScriptingEngine {
	
	private static final String ENGINE_NAME = "groovy";
	private static final String ENGINE_DISPLAY_NAME = "Groovy Engine (v 1.8.1)";
	
	private final String id;
	private final String displayName;
	private final Icon icon;

	public GroovyEnginePlugin() {
		this.id = ENGINE_NAME;
		this.displayName = ENGINE_DISPLAY_NAME;
		this.icon = new ImageIcon(this.getClass().getClassLoader().getResource("images/groovy22x22.png"));;
		
		Cytoscape.getPropertyChangeSupport().addPropertyChangeListener(this);
	}
	
	@Override
	public String getDisplayName() {
		return displayName;
	}

	
	@Override
	public Icon getIcon() {
		return icon;
	}


	@Override
	public String getIdentifier() {
		return id;
	}
	

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		if (ScriptEngineManagerPlugin.getManager().getEngine(id) != null)
			return;

		if (e.getPropertyName().equals(Cytoscape.CYTOSCAPE_INITIALIZED))
			register();
	}
	
	protected void register() {
		ScriptEngineManagerPlugin.getManager().registerEngine(id, this);
		CyLogger.getLogger().info(id + " engine registered to Script Engine Manager.");
	}
}
