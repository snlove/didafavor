package sn.didafavor.listing;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.security.auth.callback.Callback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sn.didafavor.BaseApplication;
import sn.didafavor.R;
import sn.didafavor.adapter.MovieRecyclerAdapter;
import sn.didafavor.data.Movie;
import sn.didafavor.listeners.MovieItemListeners;
import sn.didafavor.listing.sorting.SortingDialogFragment;

/**
 * Created by pc on 2018/1/16.
 */

public class MovieListingFragemnt extends Fragment implements MoviesListingView {


    @Inject
    MoviewListingPresenter moviewListingPresenter;
    private Unbinder unbinder;
    private CallBack callback;

    // butterknife to nation to bind the

    @BindView(R.id.rv_movie_list)
    RecyclerView rv;


    private MovieRecyclerAdapter movieRecyclerAdapter;
    private View contentView;
    private ArrayList<Movie> lists = new ArrayList<>(20);


    // use to dagger to inject
    public MovieListingFragemnt() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (CallBack) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // later to init the inject component
        setHasOptionsMenu(true);
        setRetainInstance(true);
        ((BaseApplication) getActivity().getApplication()).createListingComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.movie_content, container, false);
        unbinder = ButterKnife.bind(this, contentView);
        initView();
        return contentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        initDatas();
        initListeners();
    }

    private void initListeners() {
        rv.addOnItemTouchListener(new MovieItemListeners(getContext(), new MovieItemListeners.MovieItemClick() {
            @Override
            public void onClick(View view, int postion) {
                //Toast.makeText(getContext(), "enter movie detail", Toast.LENGTH_SHORT).show();
                onMovieClicked(lists.get(postion));
            }
        }));
    }

    private void initDatas() {
        moviewListingPresenter.setView(this);
    }

    private void initView() {
        if (contentView != null) {
//            rv = contentView.findViewById(R.id.rv_movie_list);
            movieRecyclerAdapter = new MovieRecyclerAdapter(getContext(), lists);
            rv.setAdapter(movieRecyclerAdapter);
            int columns;
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                columns = 1;
            } else {
                columns = getResources().getInteger(R.integer.portrait_columns);
            }
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), columns);
            rv.setLayoutManager(gridLayoutManager);
            rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
            rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        }
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.movie_menu_sort:
                // show a sort dialog option
                displaySortOption();
                break;

        }
        return super.onOptionsItemSelected(item);


    }

    private void displaySortOption() {
        SortingDialogFragment sortingDialogFragment = SortingDialogFragment.newInstance(moviewListingPresenter);
        sortingDialogFragment.show(getFragmentManager(), "Sort_Movie_Option");
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((BaseApplication) getActivity().getApplication()).releaseListingComponent();
    }

    @Override
    public void onDetach() {
        callback = null;
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        moviewListingPresenter.destory();
        unbinder.unbind();
    }

    @Override
    public void showMovies(List<Movie> movies) {

        lists.clear();
        lists.addAll(movies);
        if (movieRecyclerAdapter != null) {
            movieRecyclerAdapter.notifyDataSetChanged();
        }
        rv.setVisibility(View.VISIBLE);
        callback.onMovieLoaded(lists.get(0));
    }

    @Override
    public void loadingStart() {
        Snackbar.make(contentView, R.string.loading_movies, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void loadingFailed(String errorMsg) {
        Snackbar.make(contentView, errorMsg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onMovieClicked(Movie movie) {
        if (callback != null) {
            callback.onMovieClick(movie);
        }
    }

    public interface CallBack {
        void onMovieLoaded(Movie movie);

        void onMovieClick(Movie movie);
    }
}
