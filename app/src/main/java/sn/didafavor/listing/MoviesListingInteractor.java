package sn.didafavor.listing;

import java.util.List;

import io.reactivex.Observable;
import sn.didafavor.data.Movie;

/**
 * Created by pc on 2018/1/22.
 */

public interface MoviesListingInteractor {

    Observable<List<Movie>> fetchMovies();
}
