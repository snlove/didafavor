package sn.didafavor.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pc on 2018/4/13.
 */

public class ReviewWrapper {

    @SerializedName("results")
    private List<Review> reviewList;

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }
}
