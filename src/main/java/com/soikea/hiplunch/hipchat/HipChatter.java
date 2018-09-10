package com.soikea.hiplunch.hipchat;

import com.soikea.hiplunch.Constants;
import com.soikea.hiplunch.util.JerseyConnection;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class HipChatter {

    private static final String HIP_ROOM_VARNAME = "HIPCHAT_ROOM";

    private static final String HIP_TOKEN_VARNAME = "HIPCHAT_TOKEN";

    private String HIPCHAT_RESOURCE = null;

    private String HIPCHAT_ROOM = null;

    private String HIPCHAT_TOKEN = null;

    public HipChatter() {
        HIPCHAT_ROOM = Constants.getSystemVariable(HIP_ROOM_VARNAME);
        HIPCHAT_TOKEN = Constants.getSystemVariable(HIP_TOKEN_VARNAME);

        HIPCHAT_RESOURCE = "https://api.hipchat.com/v2/room/" + HIPCHAT_ROOM + "/notification?auth_token=" + HIPCHAT_TOKEN;
    }

    public void sendMessage(HipchatMessage hipchatMessage) throws IOException {
        JerseyConnection.sendMessage(new ObjectMapper().writeValueAsString(hipchatMessage), HIPCHAT_RESOURCE);
    }

    public boolean canSendMessage() {
        return HIPCHAT_ROOM != null && HIPCHAT_TOKEN != null;
    }
}
