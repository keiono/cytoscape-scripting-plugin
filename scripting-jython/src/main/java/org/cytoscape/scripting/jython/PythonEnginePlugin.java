/*
 Copyright (c) 2006, 2007, The Cytoscape Consortium (www.cytoscape.org)

 The Cytoscape Consortium is:
 - Institute for Systems Biology
 - University of California San Diego
 - Memorial Sloan-Kettering Cancer Center
 - Institut Pasteur
 - Agilent Technologies

 This library is free software; you can redistribute it and/or modify it
 under the terms of the GNU Lesser General Public License as published
 by the Free Software Foundation; either version 2.1 of the License, or
 any later version.

 This library is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
 MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
 documentation provided hereunder is on an "as is" basis, and the
 Institute for Systems Biology and the Whitehead Institute
 have no obligations to provide maintenance, support,
 updates, enhancements or modifications.  In no event shall the
 Institute for Systems Biology and the Whitehead Institute
 be liable to any party for direct, indirect, special,
 incidental or consequential damages, including lost profits, arising
 out of the use of this software and its documentation, even if the
 Institute for Systems Biology and the Whitehead Institute
 have been advised of the possibility of such damage.  See
 the GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation,
 Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 */
package org.cytoscape.scripting.jython;

import java.beans.PropertyChangeEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.cytoscape.scripting.CyScriptingEngine;
import org.cytoscape.scripting.ScriptEngineManagerPlugin;

import cytoscape.Cytoscape;
import cytoscape.logger.CyLogger;
import cytoscape.plugin.CytoscapePlugin;

public class PythonEnginePlugin extends CytoscapePlugin implements CyScriptingEngine {

	private static final String ENGINE_NAME = "python";
	private static final String ENGINE_DISPLAY_NAME = "Python Scripting Engine (based on Jython v2.5.2)";
	private static final Icon ICON = new ImageIcon(PythonEnginePlugin.class.getResource("images/python.png"));

	private final String id;
	private final String displayName;
	private final Icon icon;

	public PythonEnginePlugin() {
		this.id = ENGINE_NAME;
		this.displayName = ENGINE_DISPLAY_NAME;
		this.icon = ICON;

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
