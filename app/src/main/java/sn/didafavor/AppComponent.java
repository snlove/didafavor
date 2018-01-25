package sn.didafavor;



import javax.inject.Singleton;

import dagger.Component;
import sn.didafavor.listing.ListingComponent;
import sn.didafavor.listing.ListingModule;
import sn.didafavor.network.NetWorkModule;

/**
 * Created by pc on 2018/1/22.
 */
@Singleton
@Component(modules={AppModule.class,NetWorkModule.class})
public interface AppComponent {

//    DetailsComponent plus(DetailsModule detailsModule);

    ListingComponent plus(ListingModule listingModule);
}
