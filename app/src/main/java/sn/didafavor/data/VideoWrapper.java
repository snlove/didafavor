package sn.didafavor.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pc on 2018/4/13.
 */

public class VideoWrapper {


    @SerializedName("results")
    private List<Video> videoList;

    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }
}
