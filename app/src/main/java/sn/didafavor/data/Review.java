package sn.didafavor.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pc on 2018/4/13.
 */

public class Review implements Parcelable{

    private String id;
    private String author;
    private String content;
    private String url;

    public Review(){

    }

    public Review(Parcel source) {
        this.id = source.readString();
        this.author = source.readString();
        this.content = source.readString();
        this.url = source.readString();
    }

    public  static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel source) {
            return new Review(source);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(id);
        dest.writeString(author);
        dest.writeString(content);
        dest.writeString(url);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static Creator<Review> getCREATOR() {
        return CREATOR;
    }
}
