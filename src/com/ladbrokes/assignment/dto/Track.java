package com.ladbrokes.assignment.dto;
public class Track {

    private Long id;
    private String title;
    private String permaLinkUrl; ///TODO: maybe switch to Url
    private Long user;

    public Track() {
    }

    public Track(Long id, String title, String permaLinkUrl, Long user) {
        this.id = id;
        this.title = title;
        this.permaLinkUrl = permaLinkUrl;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPermaLinkUrl() {
        return permaLinkUrl;
    }

    public void setPermaLinkUrl(String permaLinkUrl) {
        this.permaLinkUrl = permaLinkUrl;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", permaLinkUrl='" + permaLinkUrl + '\'' +
                ", user=" + user +
                '}';
    }
}