package com.emrekose.popularmoviesstage2.model.reviews;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class MovieReviewsResults implements Parcelable {
    @SerializedName("id")
    private String id;

    @SerializedName("author")
    private String author;

    @SerializedName("content")
    private String content;

    protected MovieReviewsResults(Parcel in) {
        id = in.readString();
        author = in.readString();
        content = in.readString();
    }

    public static final Creator<MovieReviewsResults> CREATOR = new Creator<MovieReviewsResults>() {
        @Override
        public MovieReviewsResults createFromParcel(Parcel in) {
            return new MovieReviewsResults(in);
        }

        @Override
        public MovieReviewsResults[] newArray(int size) {
            return new MovieReviewsResults[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(author);
        parcel.writeString(content);
    }
}
