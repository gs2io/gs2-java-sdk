package io.gs2.core.model;

public class RequestError {

	String component;
	String message;
	String code;

	public RequestError() {}
	
	public RequestError(String component, String message, String code) {
		this.component = component;
		this.message = message;
		this.code = code;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
