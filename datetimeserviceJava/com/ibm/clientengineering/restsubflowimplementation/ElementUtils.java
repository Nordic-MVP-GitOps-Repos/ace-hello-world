package com.ibm.clientengineering.restsubflowimplementation;

import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;

public class ElementUtils {

	public static void addJsonChildToElement(MbElement element, String name, String value) throws MbException {
		MbElement child = element.createElementAsLastChild(MbElement.TYPE_NAME_VALUE);
		child.setName(name);
		child.setValue(value);
	}
}
