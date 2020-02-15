package com.soikea.hiplunch.slack;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SlackMessage {

    private String text;

    public SlackMessage(String header, List<String> menus) {
        text = StringUtils.join(menus, "\n");
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
