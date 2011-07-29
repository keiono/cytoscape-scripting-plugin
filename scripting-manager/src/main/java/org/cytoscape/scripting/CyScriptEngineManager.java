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
package org.cytoscape.scripting;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.cytoscape.scripting.ui.SelectScriptDialog;

import cytoscape.Cytoscape;
import cytoscape.logger.CyLogger;
import cytoscape.util.CytoscapeMenuBar;

/**
 * Wrapper class for Java scripting framework.
 * 
 */
public final class CyScriptEngineManager implements PropertyChangeListener {
	
	private static final CyLogger logger = CyLogger.getLogger(CyScriptEngineManager.class);
	
	// Manager for JSR-223 Scripting Engines
	private static final ScriptEngineManager manager = new ScriptEngineManager();
	
	private static final Icon SCRIPT_ICON = new ImageIcon(
			CyScriptEngineManager.class.getResource("/images/stock_run-macro.png"));
	private static final Icon CONSOLE_ICON = new ImageIcon(
			CyScriptEngineManager.class.getResource("/images/gnome-terminal.png"));
	
	private static final String PLUGIN_MENU = "Plugins";
	
	private final Map<String, CyScriptingEngine> registeredNames;
	
	private final JMenu menu;
	private final JMenu consoleMenu;


	/**
	 * Creates a new ScriptEngineManager object.
	 */
	protected CyScriptEngineManager() {
		
		registeredNames = new ConcurrentHashMap<String, CyScriptingEngine>();
		
		final CytoscapeMenuBar menuBar = Cytoscape.getDesktop().getCyMenus().getMenuBar();
		
		menu = new JMenu("Execute Scripts");
		menu.setIcon(SCRIPT_ICON);
		menuBar.getMenu(PLUGIN_MENU).add(menu);

		consoleMenu = new JMenu("Scripting Language Consoles");
		consoleMenu.setIcon(CONSOLE_ICON);
		menuBar.getMenu(PLUGIN_MENU).add(consoleMenu);
	}


	protected ScriptEngineManager getManager() {
		return manager;
	}

	
	/**
	 * 
	 * @param id
	 * @param engine
	 */
	public void registerEngine(final String id, final CyScriptingEngine engine) {
		registeredNames.put(id, engine);

		menu.add(new JMenuItem(new AbstractAction(engine.getDisplayName()) {
			
			private static final long serialVersionUID = 7526383423845300394L;

			public void actionPerformed(ActionEvent e) {
				SelectScriptDialog.showDialog(id);
			}
		}));
		
		logger.info("Scripting Engine Registered: " + id);
	}

	
	/**
	 * Add console menu item if available.
	 * 
	 * @param consoleMenuItem
	 */
	public void addConsoleMenu(final JMenuItem consoleMenuItem) {
		consoleMenu.add(consoleMenuItem);
	}


	/**
	 * 
	 * @param engineID
	 * @return
	 */
	public CyScriptingEngine getEngine(String engineID) {
		return registeredNames.get(engineID);
	}


	/**
	 * 
	 * @param engineID
	 * @param scriptFileName
	 * @param arguments
	 * @throws ScriptException
	 */
	public Object execute(final String engineID, final String scriptFileName, final Map<String, String> arguments)
			throws ScriptException {
		
		final ScriptEngine engine = manager.getEngineByName(engineID);
		
		if (engine == null)
			throw new IllegalArgumentException("Could not find scripting engine runtime: " + engineID);
		
		final Object returnVal;
		
		try {
			// This is a hack... I need to decide which version of Scripting
			// System is appropriate for Cytoscape 3.
			if (engineID != "jython")
				returnVal = engine.eval(new FileReader(scriptFileName));
			else {
				// Jython uses special console to execute script.

				final Class<?> engineClass = Class.forName("edu.ucsd.bioeng.idekerlab.pythonengine.PythonEnginePlugin");
				Method method = engineClass.getMethod("executePythonScript", new Class[] { String.class });
				returnVal = method.invoke(null, new Object[] { scriptFileName });
			}
		} catch (Exception e) {
			throw new ScriptException(e);
		}
		
		if (returnVal != null)
			logger.info("Return Val = [" + returnVal + "]");
		
		return returnVal;
	}

	
	public void propertyChange(PropertyChangeEvent pce) {
		// TODO Auto-generated method stub
	}
}
