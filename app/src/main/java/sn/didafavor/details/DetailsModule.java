package sn.didafavor.details;

import dagger.Module;
import dagger.Provides;
import sn.didafavor.network.TmdbWebService;

/**
 * Created by pc on 2018/4/13.
 */
@Module
public class DetailsModule {

    @Provides
    @DetailScope
    public MovieDetailPresenter providePresenter(MovieDetailInteractor movieDetailInteractor){
        return new MovieDetailPresenterImp(movieDetailInteractor);
    }

    MovieDetailInteractor provideInteractor(TmdbWebService tmdbWebService) {
        return new MovieDetailInteractorImp(tmdbWebService);
    }
}
