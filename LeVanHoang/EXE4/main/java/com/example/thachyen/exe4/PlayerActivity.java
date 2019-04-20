package com.example.thachyen.exe4;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;

import org.w3c.dom.Text;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.example.thachyen.exe4.R.anim.action_image;

public class PlayerActivity extends AppCompatActivity {
    Button btn_next, btn_previous, btn_pause;
    TextView songTextLabel;
    SeekBar songSeekbar;
    String sname;
    static MediaPlayer myMediaPlayer;
    int position;

    ArrayList<File> mySongs;
    Thread updateseekBar;

    TextView startMusicLabel;
    TextView endMusicLabel;
    int totalTime;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        btn_next = (Button) findViewById(R.id.next);
        btn_previous = (Button) findViewById(R.id.previous);
        btn_pause = (Button) findViewById(R.id.pause);

        songTextLabel = (TextView) findViewById(R.id.songLabel);
        songSeekbar = (SeekBar) findViewById(R.id.SeekBar);

        ImageView imageMusic = (ImageView)findViewById(R.id.imageView);

        startMusicLabel = (TextView) findViewById(R.id.startMusicLabel);
        endMusicLabel = (TextView) findViewById(R.id.endMusicLabel);

        @SuppressLint("ResourceType") AnimatorSet imageMusicSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.anim.action_image);
        imageMusicSet.setTarget(imageMusic);
        imageMusicSet.start();



        getSupportActionBar().setTitle("Now Playing");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        updateseekBar = new Thread(){

            @Override
            public void run(){
                myMediaPlayer.setLooping(true);
                myMediaPlayer.seekTo(0);
                totalTime = myMediaPlayer.getDuration();
                songSeekbar.setMax(totalTime);


                while(myMediaPlayer != null){
                    try{
                        Message message = new Message();
                        message.what = myMediaPlayer.getCurrentPosition();
                        handler.sendMessage(message);
                        sleep(1000);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

            }

        };


        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        mySongs = (ArrayList) bundle.getParcelableArrayList("songs");

        sname = mySongs.get(position).getName().toString();

        String songName = i.getStringExtra("songname");

        songTextLabel.setText(songName);
        songTextLabel.setSelected(true);

        position = bundle.getInt("pos",0);

        Uri u = Uri.parse(mySongs.get(position).toString());

        myMediaPlayer = MediaPlayer.create(getApplicationContext(), u);

        myMediaPlayer.start();
        songSeekbar.setMax(myMediaPlayer.getDuration());

        updateseekBar.start();

        songSeekbar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
        songSeekbar.getThumb().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);

        songSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                myMediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                songSeekbar.setMax(myMediaPlayer.getDuration());

                if(myMediaPlayer.isPlaying()){
                    btn_pause.setBackgroundResource(R.drawable.ic_play);
                    myMediaPlayer.pause();
                }
                else{
                    btn_pause.setBackgroundResource(R.drawable.ic_pause);
                    myMediaPlayer.start();
                }
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myMediaPlayer.stop();
                myMediaPlayer.release();
                position = (position+1)%mySongs.size();

                Uri u = Uri.parse(mySongs.get(position).toString());

                myMediaPlayer = MediaPlayer.create(getApplicationContext(), u);

                sname = mySongs.get(position).getName().toString();
                songTextLabel.setText(sname);
                myMediaPlayer.start();
            }
        });

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myMediaPlayer.stop();
                myMediaPlayer.release();

                position = ((position-1) < 0)?(mySongs.size()-1):(position-1);
                Uri u = Uri.parse(mySongs.get(position).toString());
                myMediaPlayer = MediaPlayer.create(getApplicationContext(), u);

                sname = mySongs.get(position).getName().toString();
                songTextLabel.setText(sname);
                myMediaPlayer.start();
            }
        });
    }


    //viet them ham tinh thoi gian bai hat
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message) {
            int currentPosition = message.what;
            songSeekbar.setProgress(currentPosition);

            String startMusic = createTimeLabel(currentPosition);
            startMusicLabel.setText(startMusic);

            String endMusic = createTimeLabel(totalTime-currentPosition);
            endMusicLabel.setText(endMusic);
        }
    };
    private String createTimeLabel(int time){
        String timeLabel = "";
        int min = time/1000/60;
        int sec = time/1000%60;

        timeLabel = min + ":";
        if(sec < 10)
            timeLabel += "0";
            timeLabel += sec;

        return timeLabel;
    }



}
