package sn.didafavor.listing;

import android.view.View;

/**
 * Created by pc on 2018/1/22.
 */

public interface MoviewListingPresenter {
    void setView(MoviesListingView view);
    void displayMovies();
    void destory();
}

