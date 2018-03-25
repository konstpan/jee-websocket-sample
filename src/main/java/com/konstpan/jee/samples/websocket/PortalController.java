package com.konstpan.jee.samples.websocket;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@ViewScoped
public class PortalController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	HttpServletRequest request;

	private Logger logger = Logger.getLogger(this.getClass().getName());

	private String notificationReceived;
	private String recipientUser;

	@Inject
	private transient PortalMessageProducer portalMessageProducer;

	public void sendMesage() {
		logger.info("Sending message.");
		portalMessageProducer.sendMessage();
	}

	public void sendUserMesage() {
		logger.info("Sending message to user " + recipientUser);
		portalMessageProducer.sendUserMessage(recipientUser);
	}

	public void receiveNotification() {
		logger.info("Client side notification fired.");
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		notificationReceived = req.getParameter("not");

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Notification", notificationReceived));
	}

	public String getNotificationReceived() {
		return notificationReceived;
	}

	public void setNotificationReceived(String notificationReceived) {
		this.notificationReceived = notificationReceived;
	}

	public String getRemoteUser() {
		return request.getRemoteUser();
	}

	public String getRecipientUser() {
		return recipientUser;
	}

	public void setRecipientUser(String recipientUser) {
		this.recipientUser = recipientUser;
	}

}
