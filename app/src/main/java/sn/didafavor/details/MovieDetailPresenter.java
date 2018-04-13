package sn.didafavor.details;

/**
 * Created by pc on 2018/3/16.
 */

public interface MovieDetailPresenter {

    void showDetails();
    void showTralers();
    void showReviews();
    void showFavorited();
    void showUnFavorite();
    void setView();
    void destroy();
}
