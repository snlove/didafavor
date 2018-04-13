package sn.didafavor.details;

import sn.didafavor.network.TmdbWebService;

/**
 * Created by pc on 2018/4/13.
 */

public class MovieDetailInteractorImp implements  MovieDetailInteractor {

    private  TmdbWebService tmdbWebService;

    public MovieDetailInteractorImp(TmdbWebService tmdbWebService){
        this.tmdbWebService = tmdbWebService;
    }
}
