package com.ibm.clientengineering.restsubflowimplementation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ibm.broker.javacompute.MbJavaComputeNode;
import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbJSON;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbOutputTerminal;
import com.ibm.broker.plugin.MbUserException;

public class GetDatetime_JavaCompute extends MbJavaComputeNode {

	public void evaluate(MbMessageAssembly inAssembly) throws MbException {

		MbOutputTerminal out = getOutputTerminal("out");

		MbMessage outMessage = new MbMessage();
		MbMessageAssembly outAssembly = new MbMessageAssembly(inAssembly, outMessage);

		try {

			MbElement rootElement = outMessage.getRootElement();

			MbElement outJsonRoot = rootElement.createElementAsLastChild(MbJSON.PARSER_NAME);

			MbElement outJsonData = outJsonRoot.createElementAsLastChild(MbElement.TYPE_NAME, MbJSON.DATA_ELEMENT_NAME,
					null);
			
			
			Date now = new Date();
			
			DateFormat timeFormat = new SimpleDateFormat("h:mm:ss a");
			DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy");
			
			
			ElementUtils.addJsonChildToElement(outJsonData, "date", dateFormat.format(now));

			ElementUtils.addJsonChildToElement(outJsonData, "time", timeFormat.format(now));
			

		} catch (MbException e) {
			throw e;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new MbUserException(this, "evaluate()", "", "", e.toString(), null);
		}
		out.propagate(outAssembly);
	}
}