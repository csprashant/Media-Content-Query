package com.example.readimagemediacontentquery.model;

public class Image {
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

    private String title;
    private String url;
    public Image(String title, String url) {
        this.title = title;
        this.url = url;
    }
}
