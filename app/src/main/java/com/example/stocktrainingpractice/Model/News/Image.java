package com.example.stocktrainingpractice.Model.News;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "image")
public class Image {
    @Element(name = "height")
    String height;
    @Element(name = "link")
    String link;
    @Element(name = "title")
    String title;
    @Element(name = "url")
    String url;
    @Element(name = "width")
    String width;

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
}
