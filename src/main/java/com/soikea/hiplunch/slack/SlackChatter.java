package com.soikea.hiplunch.slack;

import com.soikea.hiplunch.Constants;

import com.soikea.hiplunch.util.JerseyConnection;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class SlackChatter {

    private static final String SLACK_HOOK_VARNAME = "SLACK_HOOK";

    private String WEBHOOK = null;
    private String SLACK_WEBHOOK = null;

    public SlackChatter() {
        WEBHOOK = Constants.getSystemVariable(SLACK_HOOK_VARNAME);
        SLACK_WEBHOOK = "https://hooks.slack.com/services/" + WEBHOOK;
    }

    public void sendMessage(SlackMessage message) throws IOException {
//        System.out.println(SLACK_WEBHOOK);
//        System.out.print(new ObjectMapper().writeValueAsString(message));
        JerseyConnection.sendMessage(new ObjectMapper().writeValueAsString(message), SLACK_WEBHOOK);
    }

    public boolean canSendMessage() {
        return WEBHOOK != null;
    }
}
