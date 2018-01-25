package sn.didafavor.listing.sorting;

import android.app.Dialog;
;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sn.didafavor.BaseApplication;
import sn.didafavor.R;
import sn.didafavor.listing.ListingComponent;
import sn.didafavor.listing.MoviewListingPresenter;

/**
 * Created by pc on 2018/1/25.
 */

public class SortingDialogFragment extends DialogFragment implements SortingDialogView,RadioGroup.OnCheckedChangeListener {

    @Inject
    SortingDialogPresenter sortingDialogPresenter;
    private Unbinder unbinder;




    private View contentView;
    @BindView(R.id.sort_group)
    RadioGroup sort_group;
    @BindView(R.id.sort_mostPopular)
    RadioButton sort_mostPopular;
    @BindView(R.id.sort_highest_rated)
    RadioButton sort_highestRated;
    @BindView(R.id.sort_favorite)
    RadioButton sort_favorite;

    private  static MoviewListingPresenter moviewListingPresenter;

    public  static SortingDialogFragment newInstance(MoviewListingPresenter listingPresenter){
        moviewListingPresenter = listingPresenter;
        return  new SortingDialogFragment();
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);//not in the back control
        ((BaseApplication) getActivity().getApplication()).createListingComponent().inject(this);
        sortingDialogPresenter.setView(this);
    }



    private void initView(){
        sortingDialogPresenter.setLastSaveOption();
        sort_group.setOnCheckedChangeListener(this);
    }




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.sort_dialog,null);
        unbinder = ButterKnife.bind(this,view);
        initView();
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(view);
        dialog.setTitle(R.string.sort_by);
        dialog.show();

        return  dialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        sortingDialogPresenter.destory();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((BaseApplication) getActivity().getApplication()).releaseListingComponent();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.sort_mostPopular:
                sortingDialogPresenter.onHightPolularSelect();
                moviewListingPresenter.displayMovies();
                break;
            case R.id.sort_highest_rated:
                sortingDialogPresenter.onHightRoateSelect();
                moviewListingPresenter.displayMovies();
                break;
            case R.id.sort_favorite:
                sortingDialogPresenter.onFavoriteSelect();
                moviewListingPresenter.displayMovies();
                break;
           default:
                sortingDialogPresenter.onHightPolularSelect();
                moviewListingPresenter.displayMovies();
                break;
        }
    }

    @Override
    public void setHightPopularSelected() {
        sort_mostPopular.setChecked(true);
    }

    @Override
    public void setHightRoatedSelected() {
        sort_highestRated.setChecked(true);
    }

    @Override
    public void setFavoriteSelected() {
         sort_favorite.setChecked(true);
    }

    @Override
    public void dismissDialog() {
          dismiss();
    }
}
