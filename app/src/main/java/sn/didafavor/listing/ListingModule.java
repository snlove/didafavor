package sn.didafavor.listing;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import sn.didafavor.listing.sorting.SortingOptionStore;
import sn.didafavor.network.TmdbWebService;

/**
 * Created by pc on 2018/1/22.
 */


@Module
public class ListingModule {

    @Provides
    MoviesListingInteractor provideInteractor(TmdbWebService tmdbWebService, SortingOptionStore sortingOptionStore){
        return new MoviesListingInteractorImp(tmdbWebService,sortingOptionStore);
    }

    @Provides
    MoviewListingPresenter providePresenter(MoviesListingInteractor interactor){
        return new MoviesListingPresenterImp(interactor);
    }

}
