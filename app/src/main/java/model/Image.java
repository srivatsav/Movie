package model;

import java.io.Serializable;

/**
 * Created by SRiGorthi on 07-12-2016.
 */
public class Image implements Serializable {
    public Image() {

    }

    String movie_title;
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }
}
