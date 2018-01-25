package sn.didafavor.listing.sorting;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by pc on 2018/1/25.
 */

public class SortingOptionStore {
    private static final String PREF_NAME = "SORTING";
    private static final String PRFE_OPTION = "MOVIE_SORT";

    private SharedPreferences preferences;
    private Context context;


    @Inject
    public SortingOptionStore(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
    }

    public void saveSortOption(SortType sortType){
        synchronized (SortingOptionStore.class){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(PRFE_OPTION,sortType.getValue());
            editor.apply();
        }
    }

    public int getSortOption(){
        if (preferences != null){
            return preferences.getInt(PRFE_OPTION,0);
        }
        return  0;
    }
}
