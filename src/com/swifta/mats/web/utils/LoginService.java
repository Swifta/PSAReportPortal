package com.swifta.mats.web.utils;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;

import com.swifta.mats.web.MatsWebPortalUI;

public class LoginService {
	// private ProvisioningStub provisioningStub;

	// static String esbendpoint =
	// "http://127.0.0.1:8280/services/Provisionservice";

	final static String esbendpoint = MatsWebPortalUI.conf.ESB;

	org.apache.axis2.client.ServiceClient _serviceClient = null;

	private static EndpointReference targetEPR = new EndpointReference(
			esbendpoint);

	public String authenticateUser(String username, String password)
			throws AxisFault {

		// boolean status = false;

		ServiceClient sender = null;

		OMFactory fac = OMAbstractFactory.getOMFactory();

		OMNamespace omNs = fac.createOMNamespace(
				"http://service.ws.um.carbon.wso2.org", "ser");

		OMElement authenticate = fac.createOMElement("authenticate", omNs);

		OMElement userNameElement = fac.createOMElement("userName", null);
		userNameElement.addChild(fac.createOMText(userNameElement, username));

		OMElement credential1Element = fac.createOMElement("credential", null);
		credential1Element.addChild(fac.createOMText(credential1Element,
				password));

		authenticate.addChild(userNameElement);
		authenticate.addChild(credential1Element);

		Options options = new Options();
		options.setTo(targetEPR);
		options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
		options.setAction("urn:authenticate");

		options.setProperty(HTTPConstants.CHUNKED, Constants.VALUE_FALSE);

		sender = new ServiceClient();
		sender.setOptions(options);
		OMElement result = sender.sendReceive(authenticate);

		// OMElement firstelement = result.getFirstElement();

		OMElement firstelement = result.getFirstElement();

		String statusMessage = firstelement.getFirstElement().getText();

		// System.out.println(firstelement.getText());

		System.out.println(statusMessage);

		// if (firstelement.getText().equals("true")) {
		// status = true;
		// }

		return statusMessage;

		// String statusCode = "", responseMessage = "";

		//
		// AuthenticateE authenticate = new AuthenticateE();
		//
		// Authenticate authenticateParam = new Authenticate();
		// logger.info("---------------Instantiate authenticate class");
		// authenticateParam.setPassword(password);
		// authenticateParam.setResourceid(username);
		// authenticate.setAuthenticate(authenticateParam);
		// logger.info("---------------After setting authenticate parameters");
		// AuthenticateResponseE authenticateResponse = null;
		// try {
		// logger.info("---------------After initiating the authenticate parameters");
		// provisioningStub = new ProvisioningStub(esbendpoint);
		// logger.info("---------------Calling the authenticate method in the provisioning class");
		// authenticateResponse = provisioningStub.authenticate(authenticate);
		// logger.info("---------------After Calling the authenticate method in the provisioning class");
		// } catch (RemoteException e) {
		// // TODO Auto-generated catch block
		// logger.info("---------------Exception occured while authenticating");
		// e.printStackTrace();
		// status = false;
		// }
		// if (authenticateResponse != null) {
		// logger.info("---------------AuthenticationresponseE is not null");
		// AuthenticateResponse response = authenticateResponse
		// .getAuthenticateResponse();
		// if (response != null) {
		// logger.info("---------------Authenticationresponse is not null");
		// Authenticationresponse formattedResponse = response
		// .get_return();
		// if (formattedResponse != null) {
		// logger.info("---------------formatted Response is not null "
		// + formattedResponse.getResponseMessage());
		//
		// responseMessage = formattedResponse.getResponsemessage();
		// logger.info("---------------Response is..."
		// + responseMessage);
		// } else {
		// logger.info("---------------Authenticationresponse is null");
		// }
		// } else {
		// logger.info("---------------AuthenticateResponse is null");
		// }
		// } else {
		// logger.info("---------------AuthenticateResponseE is null");
		// }
		// if (responseMessage.equalsIgnoreCase("true")) {
		// status = true;
		// }

		// return status;
	}

}