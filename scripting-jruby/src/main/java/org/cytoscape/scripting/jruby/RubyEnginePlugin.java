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
package org.cytoscape.scripting.jruby;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.cytoscape.scripting.CyScriptingEngineImpl;

import cytoscape.plugin.CytoscapePlugin;

/**
 * Simplified version of JRuby Engine Plugin.
 */
public class RubyEnginePlugin extends CytoscapePlugin {

	private static final String ENGINE_NAME = "jruby";
	private static final String ENGINE_DISPLAY_NAME = "Ruby Scripting Engine (JRuby v1.6.3)";

	/**
	 * Creates a new RubyEnginePlugin object.
	 */
	public RubyEnginePlugin() {
		final Icon icon = new ImageIcon(this.getClass().getClassLoader().getResource("images/ruby.png"));
		new CyScriptingEngineImpl(ENGINE_NAME, ENGINE_DISPLAY_NAME, icon);
	}
}
