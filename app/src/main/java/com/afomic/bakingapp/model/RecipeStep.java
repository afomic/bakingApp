package com.afomic.bakingapp.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.afomic.bakingapp.data.FoodStepContract;
import com.google.gson.annotations.SerializedName;

/**
 * Created by afomic on 6/15/17.
 *
 */

public class RecipeStep implements Parcelable {
    @SerializedName("description")
    private String mDesription;
    @SerializedName("shortDescription")
    private String mShortDescription;
    @SerializedName("videoURL")
    private String mVideoUrl;
    @SerializedName("thumbnailURL")
    private String mThumbnailUrl;
    private int mFoodID=0;
    private boolean watched=false;

    public RecipeStep(String mDesription, String mShortDescription, String mVideoUrl, String mThumbnailUrl) {
        this.mDesription = mDesription;
        this.mShortDescription = mShortDescription;
        this.mVideoUrl = mVideoUrl;
        this.mThumbnailUrl = mThumbnailUrl;
    }
    public RecipeStep(Cursor cursor){
        mFoodID=cursor.getInt(cursor.getColumnIndex(FoodStepContract.FoodStepEntry.COLUMN_FOOD_ID));
        mDesription=cursor.getString(cursor.getColumnIndexOrThrow(FoodStepContract.FoodStepEntry.COLUMN_DESCRIPTION));
        mShortDescription=cursor.getString(cursor.getColumnIndexOrThrow(FoodStepContract.FoodStepEntry.COLUMN_SHORT_DESCRIPTION));
        mThumbnailUrl=cursor.getString(cursor.getColumnIndexOrThrow(FoodStepContract.FoodStepEntry.COLUMN_THUMBNAIL_URL));
        mVideoUrl=cursor.getString(cursor.getColumnIndexOrThrow(FoodStepContract.FoodStepEntry.COLUMN_VIDEO_URL));
        watched=cursor.getInt(cursor.getColumnIndexOrThrow(FoodStepContract.FoodStepEntry.COLUMN_VIDEO_URL))==1;
    }

    protected RecipeStep(Parcel in) {
        mDesription = in.readString();
        mShortDescription = in.readString();
        mVideoUrl = in.readString();
        mThumbnailUrl = in.readString();
        mFoodID = in.readInt();
        watched = in.readByte() != 0;
    }

    public static final Creator<RecipeStep> CREATOR = new Creator<RecipeStep>() {
        @Override
        public RecipeStep createFromParcel(Parcel in) {
            return new RecipeStep(in);
        }

        @Override
        public RecipeStep[] newArray(int size) {
            return new RecipeStep[size];
        }
    };

    public String getDesription() {
        return mDesription;
    }

    public String getShortDescription() {
        return mShortDescription;
    }

    public String getVideoUrl() {
        return mVideoUrl;
    }

    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

    public int getFoodID(){
        return mFoodID;
    }
    public boolean isWatched(){
        return watched;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mDesription);
        parcel.writeString(mShortDescription);
        parcel.writeString(mVideoUrl);
        parcel.writeString(mThumbnailUrl);
        parcel.writeInt(mFoodID);
        parcel.writeByte((byte) (watched ? 1 : 0));
    }
}
