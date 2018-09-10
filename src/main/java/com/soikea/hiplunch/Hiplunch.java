package com.soikea.hiplunch;

import com.soikea.hiplunch.graph.TeamsGraphChatter;
import com.soikea.hiplunch.graph.TeamsGraphMessage;
import com.soikea.hiplunch.hipchat.HipChatter;
import com.soikea.hiplunch.provider.Provider;
import com.soikea.hiplunch.util.ContentUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hiplunch {

    private static final ProviderStorage providerStorage = new ProviderStorage();

    private static final String CMD_RUN_DEFAULT = "default";

    private static final String CMD_RUN_HELP = "help";

    private static final HipChatter hipChatter = new HipChatter();

    private static final TeamsGraphChatter teamsChatter = new TeamsGraphChatter();

    public static void main(String[] args) {

        List<String> arguments = Arrays.asList(args);
        List<Provider> runProviders = new ArrayList<>();

        if (!arguments.isEmpty()) {

            if (arguments.contains(CMD_RUN_DEFAULT)) {
                runProviders = providerStorage.getConfiguredDefaultProviders();
            } else if (arguments.contains(CMD_RUN_HELP)) {
                outputHelp();
            } else {
                for (String arg : arguments) {
                    if (!arg.startsWith("-")) {
                        try {
                            Provider provider = providerStorage.getProviderById(arg);
                            runProviders.add(provider);
                        } catch (Exception e) {
                            output("Error: Unknown provider [" + arg + "]!\n");
                        }
                    }
                }
            }
        } else {
            outputHelp();
        }

        if (hipChatter.canSendMessage()) {
            for (Provider provider : runProviders) {
                try {
                    hipChatter.sendMessage(provider.processMessageForHipchat());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (teamsChatter.canSendMessage()) {
            List<String> menus = new ArrayList<>();
            for (Provider provider : runProviders) {
                    menus.add(provider.processMessageForTeams());
            }
            try {
                String header = ContentUtil.formatDate();
                teamsChatter.sendMessage(new TeamsGraphMessage(header, menus));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Only console
            output("No external messaging configured, only console output: \n\n");
            for (Provider provider : runProviders) {
                output(provider.processMessageForHipchat().toString() + "\n");
            }
        }
    }

    private static void outputHelp() {
        output("Usage:\n\tjava -jar hiplunch.jar <provider...|" + CMD_RUN_DEFAULT + "|" + CMD_RUN_HELP + ">\n");
        output("\n");
        output("Providers:\n");
        for (Provider provider : providerStorage.getAllProviders()) {
            output(provider.getId() + "\t- " + provider.getName() + "\n");
        }
        output("\nDefault providers (run with argument \"" + CMD_RUN_DEFAULT + "\"):\n\t");
        for (Provider provider : providerStorage.getConfiguredDefaultProviders()) {
            output(provider.getId() + " ");
        }
        output("\n\n");
        output("To enable sending messages, you need to add two vars either to system Env or as -D properties to the command, eg:\n");
        output("\tjava -DHIPCHAT_ROOM=***** -DHIPCHAT_TOKEN=******* -jar build/libs/hiplunch.jar bittipannu");
        output("\n\n");
    }

    private static void output(String output) {
        System.out.print(output);
    }
}
