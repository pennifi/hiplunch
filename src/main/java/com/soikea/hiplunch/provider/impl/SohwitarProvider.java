package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.MenuProvider;
import com.soikea.hiplunch.provider.Provider;
import com.soikea.hiplunch.util.ContentUtil;
import com.soikea.hiplunch.util.FeedCutter;
import com.soikea.hiplunch.util.StringHelper;

@MenuProvider
public class SohwitarProvider extends Provider {

    @Override
    protected String processFeed() {

        String feed = ContentUtil.getUrlContents(getMessageUrl());

        String origfeed = new String(feed);


        String today = StringHelper.getWeekdayName(0) + "na"; // Maanantaina
        String tomorrow = StringHelper.getWeekdayName(1) + "na"; // Tiistaina

        System.out.println(today + "  " + tomorrow);

        feed = FeedCutter.builder(feed)
            .withStartPoints(today, "Viikon lounaslista", "Seniorilounas klo 13-14 9€ eläkeläiskorttia näyttämällä")
            .withEndPoints(tomorrow, "LOUNAAN HINTA", "LÄMPIMÄSTI TERVETULOA!")
            .withSpaceables("\\s\\s+?", "\\(.+?\\)", "\\*")
            .withRemovables("\\n", "&nbsp;", "<.+?>", "\\d+\\.\\d+\\.?")
            .startProcess()
            .cleanUp()
            .toString().trim();

        String vegeAdd = FeedCutter.builder(origfeed)
                .withStartPoints("vaihtoehdot:")
                .withEndPoints("Sohwitar &#8211; Bar &amp; Bistro", "LOUNAAN HINTA", "LÄMPIMÄSTI TERVETULOA!")
                .withSpaceables("\\s\\s+?", "\\(.+?\\)", "\\*")
                .withRemovables("Viikon \\d+? kasvisruoka", "\\n", "&nbsp;", "<.+?>", "\\d+\\.\\d+\\.?")
                .startProcess()
                .cleanUp()
                .toString().trim();

        return feed + ", " + vegeAdd;
    }

    @Override
    public String getId() {
        return "sohwitar";
    }

    @Override
    protected String getMessageUrl() {
        return "https://sohwitar.fi/#lounasmenu";
    }

    @Override
    public String getName() {
        return "Sohwitar";
    }
}
