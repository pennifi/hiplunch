package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.MenuProvider;
import com.soikea.hiplunch.provider.Provider;
import com.soikea.hiplunch.util.ContentUtil;
import com.soikea.hiplunch.util.FeedCutter;
import com.soikea.hiplunch.util.StringHelper;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@MenuProvider
public class SamruaiProvider extends Provider {

    public static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(" d.MM");

    @Override
    protected String processFeed() {
        String feed = ContentUtil.getUrlContents(getMessageUrl());

        String today = StringUtils.capitalize(StringHelper.getWeekdayName(0)) + dateFormat.format(LocalDate.now().plusDays(0)); // Maanantai 14.10
        String tomorrow = StringUtils.capitalize(StringHelper.getWeekdayName(1)) + dateFormat.format(LocalDate.now().plusDays(1)); // Perjantai 18.10

        feed = FeedCutter.builder(feed)
            .withStartPoints(today, "SAMRUAIN LOUNAS&nbsp;on tarjolla&nbsp;noutopöydästä arkisin kello 10.30-15.00.")
            .withEndPoints(tomorrow, "M =maidoton, G = gluteeniton")
            .withRemovables("\\n", "&nbsp;", "<.+?>")
            .withSpaceables("\\s\\s+?", "<br><br><br>")
            .startProcess()
            .cleanUpReverse()
            .toString();

        return feed;
    }

    @Override
    public String getId() {
        return "samruai";
    }

    @Override
    protected String getMessageUrl() {
        return "https://samruai.fi/lounas/";
    }

    @Override
    public String getName() {
        return "Samruai";
    }
}
