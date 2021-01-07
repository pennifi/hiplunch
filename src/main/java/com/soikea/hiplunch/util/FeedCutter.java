package com.soikea.hiplunch.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FeedCutter {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String CUTMODE_START = "start";

    private static final String CUTMODE_END = "end";

    private String feed;

    private List<String> startPoints;

    private List<String> endPoints;

    private List<String> removables;

    private List<String> spaceables;

    private FeedCutter(String feed) {
        this.feed = feed;
    }

    public static FeedCutter builder(String feed) {
        return new FeedCutter(feed);
    }

    public FeedCutter withStartPoints(String... startPoints) {
        this.startPoints = Arrays.asList(startPoints);
        return this;
    }

    public FeedCutter withEndPoints(String... endPoints) {
        this.endPoints = Arrays.asList(endPoints);
        return this;
    }

    public FeedCutter withRemovables(String... removables) {
        this.removables = Arrays.asList(removables);
        return this;
    }

    public FeedCutter withSpaceables(String... spaceables) {
        this.spaceables = Arrays.asList(spaceables);
        return this;
    }

    public FeedCutter startProcess() {
        return this.cutBeginning().cutEnding();
    }

    public FeedCutter cleanUp() {
        return this.replaceRemoveables().replaceSpaceables();
    }
    public FeedCutter cleanUpReverse() {
        return this.replaceSpaceables().replaceRemoveables();
    }

    public String fullProcess() {
        return startProcess().cleanUp().toString();
    }

    private FeedCutter cutBeginning() {
        if ( startPoints != null && startPoints.size() > 0) {
            List<String> reverseStartPoints = startPoints.subList(0, startPoints.size());
            Collections.reverse(reverseStartPoints);
            this.feed = cut(CUTMODE_START, reverseStartPoints);
        }
        return this;
    }

    private FeedCutter cutEnding() {
        if (endPoints != null && endPoints.size() > 0) {
            this.feed = cut(CUTMODE_END, endPoints);
        }
        return this;
    }

    private String cut(String mode, List<String> list) {
        if (list.size() > 0) {
            String cutString = list.get(0);

            Matcher matcher = Pattern.compile(".*"+cutString+".*").matcher(feed);

            if (matcher.find()) {
                int cutPoint = matcher.start();

                if (cutPoint >= feed.length() || cutPoint < 0) {
                    cutPoint = feed.length();
                }
                feed = feed.replaceAll(cutString, "");
                if (CUTMODE_START.equals(mode)) {
                    feed = feed.substring(cutPoint);
                } else if (CUTMODE_END.equals(mode)) {
                    feed = feed.substring(0, cutPoint);
                }
            }
            return cut(mode, list.subList(1, list.size()));
        } else {
            return feed;
        }
    }

    public FeedCutter replaceSpaceables() {
        if (spaceables != null) {
            return this.replace(" ", spaceables);
        }
        return this;
    }

    public FeedCutter replaceRemoveables() {
        if (removables != null) {
            return this.replace("", removables);
        }
        return this;
    }

    public FeedCutter replace(String the, List<String> targets) {
        for (String target : targets) {
            this.feed = feed.replaceAll(target, the);
        }
        return this;
    }

    @Override
    public String toString() {
        return feed;
    }
}
