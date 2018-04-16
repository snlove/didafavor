package sn.didafavor.details;

import java.util.List;

import io.reactivex.Observable;
import sn.didafavor.data.MoviesWraper;
import sn.didafavor.data.Review;
import sn.didafavor.data.ReviewWrapper;
import sn.didafavor.data.Video;
import sn.didafavor.data.VideoWrapper;
import sn.didafavor.network.TmdbWebService;

/**
 * Created by pc on 2018/4/13.
 */

public class MovieDetailInteractorImp implements  MovieDetailInteractor {

    private  TmdbWebService tmdbWebService;

    public MovieDetailInteractorImp(TmdbWebService tmdbWebService){
        this.tmdbWebService = tmdbWebService;
    }


    @Override
    public Observable<List<Review>> getReviews(String id) {
        if (id == null || id.length() == 0){
            return  null;
        }
        Observable<ReviewWrapper>  wrapper;
        wrapper = tmdbWebService.reviews(id);
        return wrapper.map(ReviewWrapper::getReviewList);
    }

    @Override
    public Observable<List<Video>> getTrailers(String id) {
        if (id == null || id.length() == 0){
            return  null;
        }
        Observable<VideoWrapper> wrapperObservable;
        wrapperObservable = tmdbWebService.trailers(id);
        if (wrapperObservable != null){
            return wrapperObservable.map(VideoWrapper::getVideoList);
        }
        return  null;
    }
}
