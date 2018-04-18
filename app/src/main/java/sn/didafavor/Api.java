package sn.didafavor;

import java.io.Serializable;

/**
 * Created by pc on 2018/1/22.
 */

public class Api implements Serializable {
    public static final String BASE_POSTER_PATH = "http://image.tmdb.org/t/p/w342";
    public static final String BASR_BACKDROP_PATH = "http://image.tmdb.org/t/p/w780";
    public static final String YOUTUBE_VIDEO_URL = "http://www.youtube.com/watch?v=%1$s";
    public  static final String YOUTUBE_THUMBNAIL_URL = "http://img.youtube.com/vi/%1$s/0.jpg";


    public static String getPosterPath(String posterpath){
        return  BASE_POSTER_PATH + posterpath;
    }

    public static String getBackDropPath(String backDropPath) {
        return  BASR_BACKDROP_PATH + backDropPath;
    }



}
