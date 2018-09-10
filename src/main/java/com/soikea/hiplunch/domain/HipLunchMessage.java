package com.soikea.hiplunch.domain;

import com.soikea.hiplunch.hipchat.HipchatEnums;

public class HipLunchMessage {

    protected String from = "LunchBot";

    protected HipchatEnums.Format format = HipchatEnums.Format.html;

    protected boolean notify = false;

    protected HipchatEnums.Color color = HipchatEnums.Color.green;

    protected String message = "Default message: Not initialized.";

    public void setNotify(boolean notify) {
        this.notify = notify;
    }

    public void setColor(HipchatEnums.Color color) {
        this.color = color;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return message;
    }
}
