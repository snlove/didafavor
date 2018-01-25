package sn.didafavor.listing;

import java.util.List;

import io.reactivex.Observable;
import sn.didafavor.data.Movie;
import sn.didafavor.data.MoviesWraper;
import sn.didafavor.listing.sorting.SortType;
import sn.didafavor.listing.sorting.SortingOptionStore;
import sn.didafavor.network.TmdbWebService;


/**
 * Created by pc on 2018/1/22.
 */

public class MoviesListingInteractorImp implements MoviesListingInteractor {

    private TmdbWebService tmdbWebService;
    private SortingOptionStore sortingOptionStore;



    public MoviesListingInteractorImp(TmdbWebService tmdbWebService) {
        this.tmdbWebService = tmdbWebService;
    }

    public MoviesListingInteractorImp(TmdbWebService tmdbWebService, SortingOptionStore sortingOptionStore) {
        this.tmdbWebService = tmdbWebService;
        this.sortingOptionStore = sortingOptionStore;
    }

    @Override
    public Observable<List<Movie>> fetchMovies() {
        Observable<MoviesWraper>  wrapper;
        if (sortingOptionStore != null){
            if (sortingOptionStore.getSortOption() == SortType.MOST_POPULAR.getValue()){
               wrapper = tmdbWebService.popularMovies();
            } else if(sortingOptionStore.getSortOption() == SortType.HIGHEST_RATED.getValue()){
                 wrapper = tmdbWebService.highestRatedMovies();
            } else {
                wrapper = tmdbWebService.popularMovies();
            }
        } else {
            wrapper = tmdbWebService.popularMovies();
        }

        return wrapper.map(MoviesWraper::getMovieList);
    }
}
