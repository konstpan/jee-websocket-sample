package com.konstpan.jee.samples.websocket;

import java.util.UUID;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

public class PortalMessageProducer {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Resource(mappedName = "/ConnectionFactory")
	ConnectionFactory factory;
	
	@Resource(mappedName = "/jms/queue/test")
	Queue testQueue;

	public void sendMessage() {
		Connection connection = null;
		Session session = null;
		
		try {
			connection = factory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			MessageProducer producer = session.createProducer(testQueue);
			Message message = session.createTextMessage(UUID.randomUUID().toString());
			producer.send(message);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
