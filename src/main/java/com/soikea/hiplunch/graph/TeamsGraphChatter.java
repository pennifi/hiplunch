package com.soikea.hiplunch.graph;

import com.soikea.hiplunch.Constants;
import com.soikea.hiplunch.util.JerseyConnection;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class TeamsGraphChatter {

    private static final String TEAMS_HOOK_VARNAME = "TEAMS_HOOK";

    private String WEBHOOK = null;
    private String TEAMS_WEBHOOK = null;

    public TeamsGraphChatter() {
        WEBHOOK = Constants.getSystemVariable(TEAMS_HOOK_VARNAME);
        TEAMS_WEBHOOK = "https://outlook.office.com/webhook/" + WEBHOOK;
    }

    public void sendMessage(TeamsGraphMessage message) throws IOException {
        JerseyConnection.sendMessage(new ObjectMapper().writeValueAsString(message), TEAMS_WEBHOOK);
    }

    public boolean canSendMessage() {
        return WEBHOOK != null;
    }
}
