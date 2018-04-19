package sn.didafavor.listing;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.util.List;

import sn.didafavor.Constants;
import sn.didafavor.R;
import sn.didafavor.data.Movie;
import sn.didafavor.fragments.MovieDetailFragment;
import sn.didafavor.listing.MovieListingFragemnt;
import sn.didafavor.listing.sorting.SortingDialogFragment;

import static sn.didafavor.Constants.Detail_Fragment;

/**
 * Created by pc on 2018/1/16.
 */

public class MovieListingActivity extends AppCompatActivity implements MovieListingFragemnt.CallBack{

    private static  final int CONTENT_ID = 111;
    private boolean tabPanelModel = false;  // check the land oriention



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        FrameLayout frameLayout = new FrameLayout(getBaseContext());
//        frameLayout.setId(CONTENT_ID);
        setContentView(R.layout.movie_main);
        setToolbar();
        if (findViewById(R.id.movie_details_container) != null){
              tabPanelModel = true;
              if (savedInstanceState == null){
                  // load the movie details fragment;
                  MovieDetailFragment detailFragment = new MovieDetailFragment();
                  FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                  transaction.replace(R.id.movie_details_container,detailFragment);
                  transaction.commit();
              }
        } else {
            tabPanelModel = false;
        }

//        MovieListingFragemnt movieListingFragemnt = new MovieListingFragemnt();
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(CONTENT_ID, movieListingFragemnt);
//        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_sort,menu);
        return true;
    }

    private void setToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar_movie);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.movie_title);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }



    @Override
    public void onMovieLoaded(Movie movie) {
        if (tabPanelModel){
            loadDetailFragment(movie);
        } else {
            // single panel don need to load detail
        }
    }

    @Override
    public void onMovieClick(Movie movie) {
        if (tabPanelModel){
            loadDetailFragment(movie);
        } else {
            startDetailActivity(movie);
        }
    }

    private void loadDetailFragment(Movie movie){
        MovieDetailFragment detailFragment = new MovieDetailFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.movie_details_container,detailFragment,Detail_Fragment);
        transaction.commit();
    }

    private void startDetailActivity(Movie movie){
        Intent intent = new Intent();
        intent.setAction("com.movie.detail_activity");
        Bundle bundle = new Bundle();
        bundle.putParcelable(Detail_Fragment,movie);
        intent.putExtra(Constants.MOVIE,bundle);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }
}
