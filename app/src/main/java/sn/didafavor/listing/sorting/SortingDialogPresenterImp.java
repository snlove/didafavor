package sn.didafavor.listing.sorting;

/**
 * Created by pc on 2018/1/25.
 */

public class SortingDialogPresenterImp implements SortingDialogPresenter {

    private SortingDialogInteactor sortingDialogInteactor;
    private SortingDialogView sortingDialogView;

    public SortingDialogPresenterImp(SortingDialogInteactor sortingDialogInteactor) {
        this.sortingDialogInteactor = sortingDialogInteactor;
    }

    @Override
    public void setView(SortingDialogView view) {
           this.sortingDialogView = view;
    }

    @Override
    public void destory() {
         sortingDialogView = null;
    }

    @Override
    public void onHightPolularSelect() {
        if (isAttached()){
            sortingDialogView.setHightPopularSelected();
            sortingDialogInteactor.setSortingOption(SortType.MOST_POPULAR);
            sortingDialogView.dismissDialog();
        }
    }

    @Override
    public void onFavoriteSelect() {
        if (isAttached()){
            sortingDialogView.setFavoriteSelected();
            sortingDialogInteactor.setSortingOption(SortType.FAVORITES);
            sortingDialogView.dismissDialog();
        }
    }

    @Override
    public void onHightRoateSelect() {
        if (isAttached()){
            sortingDialogView.setHightRoatedSelected();
            sortingDialogInteactor.setSortingOption(SortType.HIGHEST_RATED);
            sortingDialogView.dismissDialog();
        }
    }

    @Override
    public void setLastSaveOption() {
        if (isAttached()){
            int sortType = sortingDialogInteactor.getSortingOption();
            if (sortType == SortType.HIGHEST_RATED.getValue()){
                sortingDialogView.setHightRoatedSelected();
            } else if(sortType == SortType.MOST_POPULAR.getValue()){
               sortingDialogView.setHightPopularSelected();
            } else {
                sortingDialogView.setFavoriteSelected();
            }
    }
    }

    private boolean isAttached(){
        return  sortingDialogView != null;
    }
}
