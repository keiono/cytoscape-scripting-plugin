package org.cytoscape.scripting;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CyScriptingEngineManager {
	
	private static final String JAVASCRIPT_ENGINE = "js";
	
	private CyScriptEngineManager manager;

	@Mock
	private CyScriptingEngine jsEngine;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		manager = ScriptEngineManagerPlugin.getManager();
		manager.registerEngine(JAVASCRIPT_ENGINE, jsEngine);
	}

	@Test
	public void testRegisterEngine() throws Exception {
		assertNotNull(manager.getEngine(JAVASCRIPT_ENGINE));
		assertEquals(jsEngine, manager.getEngine(JAVASCRIPT_ENGINE));
	}
	
	@Test
	public void testExecute() throws Exception {
		// Test with the default engine: Rhino
		
		final URL sample = CyScriptEngineManager.class.getResource("/sample1.js");
		assertNotNull(sample);
		final String sampleJavaScriptFile = sample.getPath();
		System.out.println("Sample File = " + sampleJavaScriptFile);
		Object result = manager.execute(JAVASCRIPT_ENGINE, sampleJavaScriptFile, null);
		
		assertNull(result);
	}
}
