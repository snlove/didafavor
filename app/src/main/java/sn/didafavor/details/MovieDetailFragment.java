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
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.w3c.dom.Text;

import java.util.IllegalFormatCodePointException;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sn.didafavor.Api;
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
    @BindView(R.id.trailers_containers)
    HorizontalScrollView tailers_containers;
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

    public static  MovieDetailFragment getInstance(Movie movie) {
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



        collapsing_toolbar.setContentScrimColor(ContextCompat.getColor(getActivity(),R.color.movie_colorPrimary));
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
        Glide.with(this).load(Api.getBackDropPath(movie.getBackdropPath())).into(movie_poster);
        movie_title.setText(movie.getTitle());
        movie_year.setText(String.format(getString(R.string.release_date),movie.getReleaseDate()));
        movie_rate.setText(String.format(getString(R.string.movie_rate),String.valueOf(movie.getVoteAverage())));
        movie_description.setText(movie.getOverview());
        moviewDetailsPresenter.showTralers(movie);
        moviewDetailsPresenter.showReviews(movie);


    }

    @Override
    public void showTralers(List<Video> videos) {
        if (!videos.isEmpty()) {
            trailers.setVisibility(View.VISIBLE);
            tailers_containers.setVisibility(View.VISIBLE);
            movie_trailers.setVisibility(View.VISIBLE);
            trailers.removeAllViews();

            RequestOptions options = new RequestOptions()
                    .placeholder(R.color.colorPrimary)
                    .centerCrop()
                    .override(150, 150);

            LayoutInflater inflater = getActivity().getLayoutInflater();

            for (Video video : videos) {
                View thumbContainers = inflater.inflate(R.layout.video, trailers, false);
                ImageView thumbView = ButterKnife.findById(thumbContainers, R.id.iv_thumb_video);
                Glide.with(getActivity()).load(Video.getThumbnailUrl(video)).apply(options).into(thumbView);
                trailers.addView(thumbView);

            }
        } else {
            trailers.setVisibility(View.GONE);
            tailers_containers.setVisibility(View.GONE);
            movie_trailers.setVisibility(View.GONE);
        }

    }

    @Override
    public void showReviews(List<Review> reviewList) {
        if (reviewList.isEmpty()) {
            movie_reviews.setVisibility(View.GONE);
            reviews.setVisibility(View.GONE);

        } else {
            movie_reviews.setVisibility(View.VISIBLE);
            reviews.setVisibility(View.VISIBLE);

            reviews.removeAllViews();

            LayoutInflater inflater = getActivity().getLayoutInflater();

            for(Review review : reviewList){

                ViewGroup reviewContainers = (ViewGroup) inflater.inflate(R.layout.reviews, reviews, false);
                TextView reviewAuthor = ButterKnife.findById(reviewContainers, R.id.tv_review_author);
                TextView reviewContent = ButterKnife.findById(reviewContainers, R.id.tv_review_content);
                reviewAuthor.setText(review.getAuthor());
                reviewContent.setText(review.getContent());
                reviewContent.setOnClickListener(this);
                reviews.addView(reviewContainers);

            }
        }

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
