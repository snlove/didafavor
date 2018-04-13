package sn.didafavor.details;

import dagger.Module;
import dagger.Subcomponent;
import sn.didafavor.listing.MovieListingFragemnt;

/**
 * Created by pc on 2018/4/13.
 */
@DetailScope
@Subcomponent(modules = {DetailsModule.class})
public interface DetailComponent {

    MovieDetailFragment inject(MovieDetailFragment target);
}
