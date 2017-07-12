package com.pabi.miwokmediaplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 7/12/2017.
 */

public class MainActivity extends Activity {

    private ImageButton pause,play,next,previous;
    private MediaPlayer mediaPlayer;

    private double startTime = 0;
    private double finalTime = 0;

    private Handler myHandler = new Handler();
    private SeekBar seekbar;
    private TextView tx1,tx2;
    private TextView txt;

    int nextBtn;
    int previousBtn;

    public static int oneTimeOnly = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pause = (ImageButton) findViewById(R.id.pause);
        play = (ImageButton)findViewById(R.id.play);
        next = (ImageButton)findViewById(R.id.next);
        previous = (ImageButton) findViewById(R.id.previous);

        tx1 = (TextView)findViewById(R.id.textView1);
        tx2 = (TextView)findViewById(R.id.textView2);
        txt = (TextView)findViewById(R.id.textView);
        txt.setText("Song.mp3");

        final ArrayList<Songs> song = new ArrayList<Songs>();
        song.add(new Songs("Miss Pru- Phumelela", R.drawable.miss_pru, R.raw.phumelela));
        song.add(new Songs("Rihanna-Love on Brains", R.drawable.rihanna_pic1, R.raw.rihana_loveon));
        song.add(new Songs("Rihanna-Cheers", R.drawable.rihanna_cheers, R.raw.rihanna_chears));
        song.add(new Songs("Davido", R.drawable.davido, R.raw.davido_love));
        song.add(new Songs("kwesta-Ngiyazifela", R.drawable.kwesta_pic, R.raw.kwesta_ngiyazifela));
        song.add(new Songs("Tony Braxton-ReUnited", R.drawable.love_marry, R.raw.reunited));
        song.add(new Songs("House Vol", R.drawable.house_vol, R.raw.house_vol1));
        song.add(new Songs("Chris Brown", R.drawable.chrisbrown, R.raw.chrisbrown));
        song.add(new Songs("Boys 2 men_Revoluton", R.drawable.boys_men2, R.raw.boys2men_water));
        song.add(new Songs("Tony Braxton- Take it Back", R.drawable.love_marry, R.raw.take_back));

        SongsAdapter adapter = new SongsAdapter(this, song);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Songs songs = song.get(position);

                mediaPlayer = MediaPlayer.create(MainActivity.this, songs.getAudioResourceId());
            }
        });
        seekbar = (SeekBar)findViewById(R.id.seekBar);
        seekbar.setClickable(false);
        pause.setEnabled(false);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Playing sound", Toast.LENGTH_SHORT).show();
                mediaPlayer.start();

                finalTime = mediaPlayer.getDuration();
                startTime = mediaPlayer.getCurrentPosition();

                if (oneTimeOnly == 0) {
                    seekbar.setMax((int) finalTime);
                    oneTimeOnly = 1;
                }

                tx2.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        finalTime)))
                );

                tx1.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        startTime)))
                );

                seekbar.setProgress((int)startTime);
                myHandler.postDelayed(UpdateSongTime,100);
                pause.setEnabled(true);
                play.setEnabled(false);
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Pausing sound",Toast.LENGTH_SHORT).show();
                mediaPlayer.pause();
                pause.setEnabled(false);
                play.setEnabled(true);
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Playing next next", Toast.LENGTH_SHORT).show();
        if(mediaPlayer !=null && mediaPlayer.isPlaying()){
        mediaPlayer.stop();
        }
        Songs songs = song.get(nextBtn++);
        mediaPlayer = mediaPlayer.create(MainActivity.this, songs.getAudioResourceId());
        mediaPlayer.start();
        }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Playing previous song", Toast.LENGTH_SHORT).show();
                mediaPlayer.stop();
                if(previousBtn >= 0 ){
                    Songs songs = song.get(previousBtn++);
                    mediaPlayer = mediaPlayer.create(MainActivity.this, songs.getAudioResourceId());
                    mediaPlayer.start();
                }
            }
        });
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            tx1.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            seekbar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };


}
