package sn.didafavor.listing;

import android.view.View;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sn.didafavor.data.Movie;
import sn.didafavor.utils.RxUtil;

/**
 * Created by pc on 2018/1/22.
 */

public class MoviesListingPresenterImp implements MoviewListingPresenter {

    private MoviesListingView moviesListingView;
    private MoviesListingInteractor moviesListingInteractor;
    private Disposable fetchSubscribe;

    public MoviesListingPresenterImp(MoviesListingInteractor moviesListingInteractor) {
        this.moviesListingInteractor = moviesListingInteractor;
    }

    @Override
    public void setView(MoviesListingView view) {
             this.moviesListingView = view;
             displayMovies();
    }

    @Override
    public void displayMovies() {
        showLoading();
        if (isVeiwAttached()){
            fetchSubscribe = moviesListingInteractor.fetchMovies()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onMovieSuccess,this::onMovieError);
        }
    }

   private void  showLoading(){
        if (isVeiwAttached()){
            moviesListingView.loadingStart();
        }
   }

    private void onMovieSuccess(List<Movie> movies) {
          if(isVeiwAttached() && movies.size() > 0){
              moviesListingView.showMovies(movies);
          }
    }

    private void onMovieError(Throwable throwable) {
        if (isVeiwAttached()){
            moviesListingView.loadingFailed("load failed " + throwable.getLocalizedMessage());
        }

    }

    @Override
    public void destory() {
        if (fetchSubscribe != null){
            RxUtil.unsubscribe(fetchSubscribe);
        }
    }

    private boolean isVeiwAttached(){
        return  moviesListingView != null;
    }




}
