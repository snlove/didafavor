package sn.didafavor.details;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import sn.didafavor.R;
import sn.didafavor.data.Movie;

import static sn.didafavor.Constants.Detail_Fragment;
import static sn.didafavor.Constants.MOVIE;

/**
 * Created by pc on 2018/3/16.
 */

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        if (savedInstanceState == null){
            Bundle bundle = getIntent().getBundleExtra(MOVIE);
            if (bundle != null){
                Movie movie = bundle.getParcelable(Detail_Fragment);
                MovieDetailFragment movieDetailFragment = MovieDetailFragment.getInstance(movie);
                getFragmentManager().beginTransaction().add(R.id.fl_activity_movie_detail,movieDetailFragment).commit();
            }
        }
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
