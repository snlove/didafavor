package sn.didafavor;

import android.app.Application;
import android.os.StrictMode;

import sn.didafavor.listing.ListingComponent;
import sn.didafavor.listing.ListingModule;
import sn.didafavor.network.NetWorkModule;


/**
 * @author arun
 */
public class BaseApplication extends Application
{
    private AppComponent appComponent;
    private ListingComponent listingComponent;

    @Override
    public void onCreate()
    {
        super.onCreate();
        StrictMode.enableDefaults();
        appComponent = createAppComponent();
    }

    private AppComponent createAppComponent()
    {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netWorkModule(new NetWorkModule())
                .build();
    }



    public ListingComponent createListingComponent()
    {
        listingComponent = appComponent.plus(new ListingModule());
        return listingComponent;
    }

    public void releaseListingComponent()
    {
        listingComponent = null;
    }

    public ListingComponent getListingComponent()
    {
        return listingComponent;
    }
}
