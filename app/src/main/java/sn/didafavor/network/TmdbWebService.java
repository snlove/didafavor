package sn.didafavor.network;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import sn.didafavor.data.Movie;
import sn.didafavor.data.MoviesWraper;
import sn.didafavor.data.ReviewWrapper;
import sn.didafavor.data.VideoWrapper;

/** the web request
 * Created by pc on 2018/1/18.
 */

public interface TmdbWebService {
    @GET("3/discover/movie?language=en&sort_by=popularity.desc")
    Observable<MoviesWraper> popularMovies();

    @GET("3/discover/movie?vote_count.gte=500&language=en&sort_by=vote_average.desc")
    Observable<MoviesWraper> highestRatedMovies();


    @GET("3/movie/{movieId}/videos")
    Observable<VideoWrapper> trailers(@Path("movieId")String movieId);

    @GET("3/movie/{movieId}/reviews")
    Observable<ReviewWrapper> reviews(@Path("movieId")String movieId);
}
