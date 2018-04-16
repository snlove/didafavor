package sn.didafavor.details;

import sn.didafavor.data.Movie;

/**
 * Created by pc on 2018/3/16.
 */

public interface MovieDetailPresenter {

    void showDetails(Movie movie);
    void showTralers(Movie movie);
    void showReviews(Movie movie);
    void showFavorited();
    void showUnFavorite();
    void setView(MovieDetailView movieDetailView);
    void destroy();
}
