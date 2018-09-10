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
        // General jyväskylä
        // WEBHOOK = "02fbf409-fddb-4df2-a61c-9ee385a28aa0@1f23d6d3-b158-4e45-b7e1-7631cf28c804/IncomingWebhook/a97bcf1216764eef97abca2862d1ed31/d0fcdb77-e7ec-49d8-b3a7-338e4f5b02f1";

        // testi
//        WEBHOOK = "74d26e29-f02c-456f-a188-1e281bc0d87f@1f23d6d3-b158-4e45-b7e1-7631cf28c804/IncomingWebhook/b52648e4be6c4c99a4c5f09b33aa1b94/d0fcdb77-e7ec-49d8-b3a7-338e4f5b02f1";

        TEAMS_WEBHOOK = "https://outlook.office.com/webhook/" + WEBHOOK;
    }

    public void sendMessage(TeamsGraphMessage message) throws IOException {
        JerseyConnection.sendMessage(new ObjectMapper().writeValueAsString(message), TEAMS_WEBHOOK);
    }

    public boolean canSendMessage() {
        return WEBHOOK != null;
    }
}
