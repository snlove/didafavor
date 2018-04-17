package sn.didafavor.details;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.IllegalFormatCodePointException;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sn.didafavor.BaseApplication;
import sn.didafavor.Constants;
import sn.didafavor.R;
import sn.didafavor.data.Movie;
import sn.didafavor.data.Review;
import sn.didafavor.data.Video;
import sn.didafavor.listing.MoviewListingPresenter;

/**
 * Created by pc on 2018/3/16.
 */

public class MovieDetailFragment extends Fragment implements MovieDetailView, View.OnClickListener {


    @Inject
    MovieDetailPresenter moviewDetailsPresenter;
    // use the butterkinfe to bind the view id
    @BindView(R.id.iv_movie_poster)
    ImageView movie_poster;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;
    @BindView(R.id.tv_movie_title)
    TextView movie_title;
    @BindView(R.id.tv_movie_release_date)
    TextView movie_year;
    @BindView(R.id.tv_movie_rate)
    TextView movie_rate;
    @BindView(R.id.tv_movie_summary_label)
    TextView movie_summary;
    @BindView(R.id.tv_movie_summary_content)
    TextView movie_description;
    @BindView(R.id.tv_movie_trailers_label)
    TextView movie_trailers;
    @BindView(R.id.trailers)
    LinearLayout trailers;
    @BindView(R.id.tv_movie_reviews_label)
    TextView movie_reviews;
    @BindView(R.id.reviews)
    LinearLayout reviews;




    private Movie movie;
    private Unbinder unbinder;

    public MovieDetailFragment() {

    }

    public MovieDetailFragment getInstance(Movie movie) {
        Bundle args = new Bundle();
        args.putParcelable(Constants.MOVIE,movie);
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        movieDetailFragment.setArguments(args);
        return  movieDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ((BaseApplication) getActivity().getApplication()).createDetailComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       View contentView  = inflater.inflate(R.layout.fragment_movie_detials,container,false);
        unbinder = ButterKnife.bind(this, contentView);
        setToolBar();
       return  contentView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            Movie movie = bundle.getParcelable(Constants.MOVIE);
            if (movie != null) {
                moviewDetailsPresenter.setView(this);
                moviewDetailsPresenter.showDetails(movie);
            }
        }
    }

    /**
    * define to set toolbar style
    * @author sn  2018/4/17  15:42
    *
    */



    private void setToolBar() {

        collapsingToolbar.setContentScrimColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        collapsingToolbar.setTitle(getString(R.string.movie_details));
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedToolbar);
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedToolbar);
        collapsingToolbar.setTitleEnabled(true);

        collapsing_toolbar.setContentScrimColor(ContextCompat.getColor(getActivity(),R.color.colorPrimary));
        collapsing_toolbar.setTitle(getString(R.string.movie_details));
        collapsing_toolbar.setCollapsedTitleTextAppearance(R.style.CollapsedToolbar);
        collapsing_toolbar.setExpandedTitleTextAppearance(R.style.ExpandedToolbar);
        collapsing_toolbar.setTitleEnabled(true);

        if (toolbar != null){
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
            ActionBar actionBar  = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        } else {
            //do nothing
        }
    }

    @Override
    public void showDetails(Movie movie) {

    }

    @Override
    public void showTralers(List<Video> videos) {

    }

    @Override
    public void showReviews(List<Review> reviews) {

    }

    @Override
    public void showFavorited() {

    }

    @Override
    public void showUnFavorite() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((BaseApplication) getActivity().getApplication()).releaseDetailComponent();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        moviewDetailsPresenter.destroy();
        unbinder.unbind();
    }
}
