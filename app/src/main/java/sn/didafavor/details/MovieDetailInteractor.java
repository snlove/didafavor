package sn.didafavor.details;

import java.util.List;

import io.reactivex.Observable;
import sn.didafavor.data.Movie;
import sn.didafavor.data.Review;
import sn.didafavor.data.Video;

/**
 * Created by pc on 2018/3/16.
 */

public interface MovieDetailInteractor {

    public Observable<List<Review>> getReviews(String id);

    public Observable<List<Video>> getTrailers(String id);
}
