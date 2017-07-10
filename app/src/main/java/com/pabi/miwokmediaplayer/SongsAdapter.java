package com.pabi.miwokmediaplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 7/7/2017.
 */

public class SongsAdapter extends ArrayAdapter<Songs> {

    public SongsAdapter(Context context, ArrayList<Songs> songs) {
        super(context, 0, songs);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.song_list, parent, false);
        }

        Songs currentSong = getItem(position);

        TextView txt = (TextView) listItemView.findViewById(R.id.textView);

        txt.setText(currentSong.getSongName());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        if (currentSong.hasImage()) {

            imageView.setImageResource(currentSong.getImageResourceId());

            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }


        return listItemView;
    }

}

