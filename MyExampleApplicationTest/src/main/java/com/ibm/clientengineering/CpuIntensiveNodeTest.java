package com.ibm.clientengineering;

import org.junit.jupiter.api.Test;

import com.ibm.integration.test.v1.NodeSpy;
import com.ibm.integration.test.v1.SpyObjectReference;
import com.ibm.integration.test.v1.TestMessageAssembly;
import com.ibm.integration.test.v1.exception.TestException;

import static org.junit.jupiter.api.Assertions.*;

public class CpuIntensiveNodeTest {

	@Test
	public void test() throws TestException {
	
		SpyObjectReference httpInNode = new SpyObjectReference().application("MyExampleApplication")
				.messageFlow("CpuIntensiveFlow").node("HTTP Input");
		
		SpyObjectReference esqlComputeNode = new SpyObjectReference().application("MyExampleApplication")
				.messageFlow("CpuIntensiveFlow").node("ESQLCompute");

		NodeSpy httpInSpy = new NodeSpy(httpInNode);
		NodeSpy esqlSpy = new NodeSpy(esqlComputeNode);
		
		// Declare a new TestMessageAssembly object for the message being sent into the node
		TestMessageAssembly inputMessageAssembly = new TestMessageAssembly();
		inputMessageAssembly.environmentPath("FLOW.Context").setValue("anEnvironmentValue");
						
		// Call the message flow node with the Message Assembly
		httpInSpy.propagate(inputMessageAssembly, "out");
			
		TestMessageAssembly outputAssembly = esqlSpy.propagatedMessageAssembly("out", 1);
		
		String output = outputAssembly.getMessageBodyAsString();
		
		System.err.println(output);
		
		assertTrue(output.contains("GIT_COMMIT"));
		assertTrue(output.contains("anEnvironmentValue"));

	}
}
