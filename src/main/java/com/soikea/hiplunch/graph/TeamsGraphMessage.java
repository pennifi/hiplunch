package com.soikea.hiplunch.graph;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class TeamsGraphMessage {

    private String type = "MessageCard";

    private String context = "http://schema.org/extensions";

    private String summary;

    private List<Section> sections = new ArrayList<>();

    public TeamsGraphMessage(String header, List<String> menus) {
        summary = header;
        sections.add(new Section(summary, StringUtils.join(menus, "\n")));
    }

    @JsonProperty(value = "@type")
    public String getType() {
        return type;
    }

    @JsonProperty(value = "@context")
    public String getContext() {
        return context;
    }

    @JsonProperty(value = "sections")
    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    @JsonProperty(value = "summary")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    private class Section {

        private String text;
        private String markdown = "true";
        private String activityTitle;

        public Section(String title, String text) {
            this.text = text;
            this.activityTitle = title;
        }

        @JsonProperty(value = "markdown")
        public String getMarkdown() {
            return markdown;
        }

        @JsonProperty(value = "text")
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
        @JsonProperty(value = "activityTitle")
        public String getActivityTitle() {
            return activityTitle;
        }

        public void setActivityTitle(String activityTitle) {
            this.activityTitle = activityTitle;
        }
    }
}
