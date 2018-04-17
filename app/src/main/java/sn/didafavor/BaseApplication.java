package sn.didafavor;

import android.app.Application;
import android.os.StrictMode;

import sn.didafavor.details.DetailComponent;
import sn.didafavor.details.DetailsModule;
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
    private DetailComponent detailComponent;

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

    public  DetailComponent createDetailComponent(){
        detailComponent = appComponent.plus(new DetailsModule());
        return  detailComponent;
    }

    public  void  releaseDetailComponent(){
        detailComponent = null;
    }

    public  DetailComponent getDetailComponent() {
        return  detailComponent;
    }
}
