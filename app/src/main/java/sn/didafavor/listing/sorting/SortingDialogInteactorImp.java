package sn.didafavor.listing.sorting;

/**define a class to handle the data with SharedPreferences
 * Created by pc on 2018/1/25.
 */

public class SortingDialogInteactorImp implements SortingDialogInteactor {

    private SortingOptionStore sortingOptionStore;

    public SortingDialogInteactorImp(SortingOptionStore sortingOptionStore) {
        this.sortingOptionStore = sortingOptionStore;
    }

    @Override
    public int getSortingOption() {
        return sortingOptionStore.getSortOption();
    }

    @Override
    public void setSortingOption(SortType sortType) {
        sortingOptionStore.saveSortOption(sortType);
    }
}
