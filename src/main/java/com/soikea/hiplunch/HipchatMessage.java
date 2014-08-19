package com.soikea.hiplunch;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by penni on 19/08/14.
 */
public class HipchatMessage {

	private String from = Constants.HIP_FROM;
	private String format = Constants.HIP_MESSAGE_FORMAT;
	private boolean notify = Constants.HIP_NOTIFY;
	private String color = Constants.HIP_COLOR;
	private String message = "";

	public HipchatMessage(String message) {
		this.message = message;
	}

	@JsonProperty(value = "from")
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	@JsonProperty(value = "message_format")
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	@JsonProperty(value = "notify")
	public boolean isNotify() {
		return notify;
	}

	public void setNotify(boolean notify) {
		this.notify = notify;
	}

	@JsonProperty(value = "color")
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@JsonProperty(value = "message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
