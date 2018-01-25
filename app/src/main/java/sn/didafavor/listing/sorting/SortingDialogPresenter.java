package sn.didafavor.listing.sorting;

/**
 * Created by pc on 2018/1/25.
 */

public interface SortingDialogPresenter {

    void setView(SortingDialogView view);

    void destory();

    void onHightPolularSelect();

    void onFavoriteSelect();

    void onHightRoateSelect();

    void setLastSaveOption();
}
