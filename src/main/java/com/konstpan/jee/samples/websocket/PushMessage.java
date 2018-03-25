package com.konstpan.jee.samples.websocket;

import java.io.Serializable;

public class PushMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	String payload;
	String user;

	public PushMessage(String payload, String user) {
		super();
		this.payload = payload;
		this.user = user;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
