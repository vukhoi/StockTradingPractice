package com.example.stocktrainingpractice.Model.News;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item")
public class Item {
    @Element(name = "description")
    String description;
    @Element(name = "guid")
    String guid;
    @Element(name = "link")
    String link;
    @Element(name = "pubDate")
    String pubDate;
    @Element(name = "title")
    String title;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
