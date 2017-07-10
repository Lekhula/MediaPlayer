package com.pabi.miwokmediaplayer;

/**
 * Created by Admin on 7/7/2017.
 */

public class Songs {

    private String mSongName;

    private int mAudioResourceId;

    private int mImageResourceId = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;

    public Songs(String songName, int audioResourceId){
        mSongName = songName;
        mAudioResourceId = audioResourceId;
    }

    public Songs(String songName, int imageResourceId, int audioResourceId){
        mSongName =songName;
        mAudioResourceId = audioResourceId;
        mImageResourceId = imageResourceId;
    }

    public String getSongName() {
        return mSongName;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }


    public boolean hasImage() {

        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getAudioResourceId() {

        return mAudioResourceId;
    }


}


