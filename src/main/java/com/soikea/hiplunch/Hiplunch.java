package com.soikea.hiplunch;

import com.soikea.hiplunch.provider.Provider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 19/08/14.
 */
public class Hiplunch {

    private static ProviderStorage providerStorage = new ProviderStorage();

    private static final String CMD_RUN_DEFAULT = "default";
    private static final String CMD_RUN_HELP = "help";


    public static void main(String[] args) {

        HipChatter hipChatter = new HipChatter();
        List<String> arguments = Arrays.asList(args);
        List<Provider> runProviders = new ArrayList<>();

        if (!arguments.isEmpty()) {

            if (arguments.contains(CMD_RUN_DEFAULT)) {
                runProviders = providerStorage.getConfiguredDefaultProviders();

            } else if (arguments.contains(CMD_RUN_HELP)) {
                outputHelp();

            } else {
                for (String arg : arguments) {
                    Provider provider = providerStorage.getProviderById(arg);
                    if (provider != null) {
                        runProviders.add(provider);
                    } else {
                        output("Error: Unknown provider [" + arg + "]!\n");
                    }
                }
            }
        } else {
            outputHelp();
        }

        for (Provider provider : runProviders) {
            hipChatter.sendMessage(provider.processMessage());
        }
    }

    private static void outputHelp() {
        output("Usage:\n\tjava -jar hiplunch.jar <provider...|"+CMD_RUN_DEFAULT+"|"+CMD_RUN_HELP+">\n");
        output("\n");
        output("Providers:\n");
        for (Provider provider : providerStorage.getAllProviders()) {
            output(provider.getId() + "\t- " + provider.getName() + "\n");
        }
        output("\nDefault providers (run with argument \""+CMD_RUN_DEFAULT+"\"):\n\t");
        for (Provider provider : providerStorage.getConfiguredDefaultProviders()) {
            output(provider.getId() + " ");
        }
        output("\n\n");

    }

    private static void output(String output) {
        System.out.print(output);
    }
}
