package sn.didafavor.listing.sorting;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pc on 2018/1/25.
 */

@Module
public class SortingModule {




    @Provides
    SortingDialogInteactor provideSortingInteactor(SortingOptionStore sortingOptionStore){
        return  new SortingDialogInteactorImp(sortingOptionStore);
    }

    @Provides
    SortingDialogPresenter provideSortingPresenter(SortingDialogInteactor sortingDialogInteactor){
        return new SortingDialogPresenterImp(sortingDialogInteactor);
    }


}
