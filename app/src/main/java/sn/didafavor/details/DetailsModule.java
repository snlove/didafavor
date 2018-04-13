package sn.didafavor.details;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pc on 2018/4/13.
 */
@Module
public class DetailsModule {

    @Provides
    @DetailScope
    public MovieDetailPresenter provideMovieDetailPresenter(){
        return new MovieDetailPresenterImp();
    }

    @Provides
    @DetailScope
    public  MovieDetailInteractor provideInteractor(){
        return  new MovieDetailInteractorImp();
    }
}
