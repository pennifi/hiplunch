package com.soikea.hiplunch.domain;

import com.soikea.hiplunch.Constants;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by penni on 19/08/14.
 */
public class HipchatMessage {

	private String from = Constants.HIP_FROM;
	private HipchatEnums.Format format = Constants.HIP_MESSAGE_FORMAT;
	private boolean notify = Constants.HIP_NOTIFY;
	private HipchatEnums.Color color = Constants.HIP_COLOR;
	private String message = "Default message: Not initialized.";

	public HipchatMessage() {}

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
	public HipchatEnums.Format getFormat() {
		return format;
	}

	public void setFormat(HipchatEnums.Format format) {
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
	public HipchatEnums.Color getColor() {
		return color;
	}

	public void setColor(HipchatEnums.Color color) {
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
