package io.gs2.model;

public class RequestError {

	String component;
	String message;

	public RequestError() {}
	
	public RequestError(String component, String message) {
		this.component = component;
		this.message = message;
	}
	
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
