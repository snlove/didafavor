package sn.didafavor.details;

import java.util.List;

import sn.didafavor.data.Movie;
import sn.didafavor.data.Review;
import sn.didafavor.data.Video;

/**
 * Created by pc on 2018/3/16.
 */

public interface MovieDetailView {

    void showDetails(Movie movie);
    void showTralers(List<Video> videos);
    void showReviews(List<Review> reviews);
    void showFavorited();
    void showUnFavorite();

}
