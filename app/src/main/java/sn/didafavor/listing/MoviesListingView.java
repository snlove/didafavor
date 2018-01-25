package sn.didafavor.listing;

import java.util.List;

import sn.didafavor.data.Movie;

/**
 * Created by pc on 2018/1/22.
 */

public interface MoviesListingView {

    void showMovies(List<Movie> movies);
    void loadingStart();
    void loadingFailed(String errorMsg);
    void onMovieClicked(Movie movie);
}
