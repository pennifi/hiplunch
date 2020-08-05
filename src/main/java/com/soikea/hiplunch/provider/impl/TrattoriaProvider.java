package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.MenuProvider;
import com.soikea.hiplunch.provider.Provider;
import com.soikea.hiplunch.util.ContentUtil;
import com.soikea.hiplunch.util.FeedCutter;
import com.soikea.hiplunch.util.StringHelper;
import org.apache.commons.lang3.StringUtils;

@MenuProvider
public class TrattoriaProvider extends Provider {

    @Override
    protected String processFeed() {

        String feed = ContentUtil.getUrlContents(getMessageUrl());

        String today = StringUtils.capitalize(StringHelper.getWeekdayName(0));
        String tomorrow = StringUtils.capitalize(StringHelper.getWeekdayName(1));

        String result = FeedCutter.builder(feed)
                .withStartPoints(today + "</h2>",
                        "VL = vähälaktoosinen, L = laktoositon, G = Gluteeniton, VE = vegaaninen, T = tulinen, M = maidoton, K = kasviruoka, PÄ = sisältää pähkinää")
                .withEndPoints(tomorrow + "</h2>", "slidingcrossselling_WAR_raflaamoliferayportlets")
                .withRemovables("\\n", "&nbsp;", "<.+?>", "&euro;", "\\d\\d?\\,\\d\\d?", "\\(.*?\\)", "Runsas salaattibuffet ja päivän keitto", "Runsas salaattibuffet ja päivän keitto alkuruokana", "Trattoria Aukiossa on kello 10:30-14:00 tarjolla runsas salaattibuffet sekä päivittäin vaihtuvat lounasannokset. Lisäksi tarjoamme viikoittain vaihtuvan kasvisannoksen.")
                .withSpaceables("\\t", "\\s\\s*")
                .fullProcess().trim();

        return result;
    }

    @Override
    public String getId() {
        return "trattoria";
    }

    @Override
    protected String getMessageUrl() {
        return "https://www.raflaamo.fi/fi/jyvaskyla/trattoria-aukio-jyvaskyla/menu";
    }

    @Override
    public String getName() {
        return "Trattoria Aukio";
    }

}
