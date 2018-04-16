package sn.didafavor.details;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sn.didafavor.data.Movie;
import sn.didafavor.data.Review;
import sn.didafavor.data.Video;
import sn.didafavor.utils.RxUtil;

/**
 * Created by pc on 2018/4/13.
 */

public class MovieDetailPresenterImp implements MovieDetailPresenter {

    private MovieDetailInteractor movieDetailInteractor;
    private MovieDetailView movieDetailView;
    private Disposable fetchReviews;
    private Disposable fetchTralers;

    public MovieDetailPresenterImp(MovieDetailInteractor movieDetailInteractor){
        this.movieDetailInteractor = movieDetailInteractor;
    }
    @Override
    public void showDetails(Movie movie) {
        if (isVeiwAttached()){
            movieDetailView.showDetails(movie);
        }

    }

    @Override
    public void showTralers(Movie movie) {
        if (isVeiwAttached()){
            fetchTralers = movieDetailInteractor.getTrailers(movie.getId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this:: OnTralersSuccess,this::OnTralerError);
        }


    }

    private void OnTralersSuccess(List<Video> videos) {
        if (isVeiwAttached() && videos.size() > 0){
            movieDetailView.showTralers(videos);
        }

    }

    private void OnTralerError(Throwable throwable) {
    }

    private void onReviewsSuccess(List<Review> reviews) {
        if (isVeiwAttached() && reviews .size() > 0){
            movieDetailView.showReviews(reviews);
        }
    }

    private void onReviewsError(Throwable throwable) {
    }

    @Override
    public void showReviews(Movie movie) {
        if (isVeiwAttached()){
            fetchReviews = movieDetailInteractor.getReviews(movie.getId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onReviewsSuccess,this::onReviewsError);
        }
    }

    @Override
    public void showFavorited() {

    }

    @Override
    public void showUnFavorite() {

    }

    @Override
    public void setView(MovieDetailView detailView) {
        this.movieDetailView = detailView;
    }

    @Override
    public void destroy() {
//        if (fetchReviews != null){
//            RxUtil.unsubscribe(fetchReviews);
//        }
//        if (fetchTralers != null) {
//            RxUtil.unsubscribe(fetchTralers);
//        }
        RxUtil.unsubscribe(fetchReviews,fetchTralers);
        if (isVeiwAttached()){
            movieDetailView = null;
        }

    }

    private boolean isVeiwAttached(){
        return  movieDetailView != null;
    }
}
