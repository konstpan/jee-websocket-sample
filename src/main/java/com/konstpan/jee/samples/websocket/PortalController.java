package com.konstpan.jee.samples.websocket;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@ViewScoped
public class PortalController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	private String notificationReceived;
	
	@Inject
	private transient PortalMessageProducer portalMessageProducer;
	
	public void sendMesage() {
		logger.info("Sending message.");
		portalMessageProducer.sendMessage();
	}
	
	public void receiveNotification() {
		logger.info("Client side notification fired.");
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
		notificationReceived = req.getParameter("not");
	}

	public String getNotificationReceived() {
		return notificationReceived;
	}

	public void setNotificationReceived(String notificationReceived) {
		this.notificationReceived = notificationReceived;
	}
	
}
