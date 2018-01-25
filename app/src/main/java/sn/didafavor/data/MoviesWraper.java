package sn.didafavor.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pc on 2018/1/18.
 */

public class MoviesWraper {


    @SerializedName("results")
    private List<Movie> movieList;
    public List<Movie> getMovieList(){
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
