package sn.didafavor.listing;

import dagger.Component;
import dagger.Subcomponent;
import sn.didafavor.listing.sorting.SortingDialogFragment;
import sn.didafavor.listing.sorting.SortingModule;

/**define the listing component
 * Created by pc on 2018/1/22.
 */

@Subcomponent(modules = {ListingModule.class, SortingModule.class})
public interface ListingComponent {

    MovieListingFragemnt inject(MovieListingFragemnt fragment);
    SortingDialogFragment inject(SortingDialogFragment sortingDialogFragment);
}
