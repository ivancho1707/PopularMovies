package mx.ivn.popularmovies;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by ivancho on 7/31/16.
 *
 */
public class Movie implements Serializable {
    public String posterUrl;
    public String bannerUrl;
    public String synopsis;
    public String releaseDate;
    public String originalTitle;
    public String title;
    public Double userRating;

    final String IMG_BASE_URL = "http://image.tmdb.org/t/p/";
    final String POSTER_SIZE = "w185";
    final String BANNER_SIZE = "w500";

    public Movie(String posterUrl, String bannerUrl, String synopsis, String releaseDate,
                 String originalTitle, String title, Double userRating) {
        Uri posterUri = Uri.parse(IMG_BASE_URL).buildUpon()
                .appendPath(POSTER_SIZE)
                .appendPath(posterUrl.replaceFirst("/", ""))
                .build();
        Uri bannerUri = Uri.parse(IMG_BASE_URL).buildUpon()
                .appendPath(BANNER_SIZE)
                .appendPath(bannerUrl.replaceFirst("/", ""))
                .build();
        this.posterUrl = posterUri.toString();
        this.bannerUrl = bannerUri.toString();
        this.synopsis = synopsis;
        this.releaseDate = releaseDate;
        this.originalTitle = originalTitle;
        this.title = title;
        this.userRating = userRating;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getUserRating() {
        return userRating;
    }

    public void setUserRating(Double userRating) {
        this.userRating = userRating;
    }
}
