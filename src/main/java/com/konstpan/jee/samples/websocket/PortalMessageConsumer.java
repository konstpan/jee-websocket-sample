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
	@Push(channel = "appNotifications")
	private PushContext appNotifications;

	@Inject
	@Push(channel = "userNotifications")
	private PushContext userNotifications;

	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public void onMessage(Message message) {
		try {
			PushMessage msg = message.getBody(PushMessage.class);
			logger.info("Received message " + msg.getPayload() + " for user " + msg.getUser());

			Map<String, String> payload = new HashMap<>();
			payload.put("not", msg.getPayload());

			if (msg.getUser().length() > 0) {
				userNotifications.send(payload, msg.getUser());
			} else {
				appNotifications.send(payload);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
