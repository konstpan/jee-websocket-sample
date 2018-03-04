package com.konstpan.jee.samples.websocket;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;

/**
 * Message-Driven Bean implementation class for: PortalConsumer
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "testQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class PortalMessageConsumer implements MessageListener {

	@Inject
	@Push(channel = "notifications")
	private PushContext notifications;

	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public void onMessage(Message message) {
		try {
			String msg = message.getBody(String.class);
			logger.info("Received message " + msg);
			
			Map<String, String> payload = new HashMap<>();
			payload.put("not", msg);
			
			notifications.send(payload);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
