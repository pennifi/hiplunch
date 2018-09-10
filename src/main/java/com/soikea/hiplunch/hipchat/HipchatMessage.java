package com.soikea.hiplunch.hipchat;

import com.soikea.hiplunch.domain.HipLunchMessage;
import org.codehaus.jackson.annotate.JsonProperty;

public class HipchatMessage extends HipLunchMessage {

    public HipchatMessage(String message) {
        this.message = message;
    }

    @JsonProperty(value = "from")
    public String getFrom() {
        return from;
    }

    @JsonProperty(value = "message_format")
    public HipchatEnums.Format getFormat() {
        return format;
    }

    @JsonProperty(value = "notify")
    public boolean isNotify() {
        return notify;
    }

    @JsonProperty(value = "color")
    public HipchatEnums.Color getColor() {
        return color;
    }

    @Override
    @JsonProperty(value = "message")
    public String getMessage() {
        return message;
    }
}
