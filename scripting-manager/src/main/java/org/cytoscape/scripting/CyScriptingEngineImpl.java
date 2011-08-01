package org.cytoscape.scripting;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Icon;

import cytoscape.Cytoscape;
import cytoscape.logger.CyLogger;

public class CyScriptingEngineImpl implements CyScriptingEngine, PropertyChangeListener {
	
	private final String id;
	private final String displayName;
	private final Icon icon;

	public CyScriptingEngineImpl(final String id, final String displayName, final Icon icon) {
		this.id = id;
		this.displayName = displayName;
		this.icon = icon;
		
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
