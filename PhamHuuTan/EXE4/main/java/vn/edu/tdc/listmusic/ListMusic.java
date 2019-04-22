package vn.edu.tdc.listmusic;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class ListMusic extends AppCompatActivity {

   TextView txtTitle, txtTimeSong, txtTimeTotal;
   SeekBar skSong;
   ImageButton btnPre, btnPlay, btnStop, btnNext;
    ArrayList<Song> arraySong;
    int position=0;
    MediaPlayer mediaPlayer;
    //khai bao co so du lieu db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_music_activity);

        AnhXa();
        AddSong();
        KhoiTaoMedia();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position ++;
                if(position>arraySong.size()-1)
                {
                    position=0;
                }
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.stop();
                }
                KhoiTaoMedia();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.ic_pause);
                setTxtTimeTotal();
            }
        });

        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position --;
                if (position<0)
                {
                    position=arraySong.size()-1;
                }
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.stop();
                }
                KhoiTaoMedia();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.ic_pause);
                setTxtTimeTotal();
            }
        });
        //su kien bam nut stop
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                btnPlay.setImageResource(R.drawable.ic_play);
                KhoiTaoMedia();
            }
        });
        //su kien bam nut play
       btnPlay.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (mediaPlayer.isPlaying())
               {
                   //neu dang phat->pause->doi hinh play
                   mediaPlayer.pause();
                   btnPlay.setImageResource(R.drawable.ic_play);
               }
               else
               {
                   mediaPlayer.start();
                   btnPlay.setImageResource(R.drawable.ic_pause);
               }
               setTxtTimeTotal();
               UpdateTimeSong();
           }
       });
       //Keo thoi gian bai hat den dau tinh thoi gian
       skSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {

           }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(skSong.getProgress());
           }
       });
    }
    //update thoi gian bai hat chay
    private void UpdateTimeSong()
    {
        final Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhdanggio= new SimpleDateFormat("mm:ss");
                txtTimeSong.setText(dinhdanggio.format(mediaPlayer.getCurrentPosition()));
                skSong.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this,500);
            }
        },100);
    }
    //tinh tong thoi gian bai hat
    private void setTxtTimeTotal()
    {
        SimpleDateFormat dinhDangGio= new SimpleDateFormat("mm:ss");
        txtTimeTotal.setText(dinhDangGio.format(mediaPlayer.getDuration()));
        //
        skSong.setMax(mediaPlayer.getDuration());
    }

    private void KhoiTaoMedia()
    {
        mediaPlayer= MediaPlayer.create(ListMusic.this, arraySong.get(position).getFile());

        txtTitle.setText(arraySong.get(position).getTilte());
    }
//add bai hat
    private void AddSong() {
        arraySong = new ArrayList<>();
        arraySong.add(new Song("Dung nguoi dung thoi diem", R.raw.dung_nguoi_dung_thoi_diem));
        arraySong.add(new Song("Em se la co dau", R.raw.em_se_la_co_dau));
        arraySong.add(new Song("Khong phai em dung khong",R.raw.khong_phai_em_dung_khong));


    }

    private void AnhXa() {
        txtTimeSong = (TextView) findViewById(R.id.txt_timeSong);
        txtTimeTotal= (TextView) findViewById(R.id.txt_timeTotal);
        txtTitle    =(TextView) findViewById(R.id.txtTitle);
        skSong      = (SeekBar) findViewById(R.id.seebar);
        btnNext     =(ImageButton) findViewById(R.id.imgButtonNext) ;
        btnPlay     =(ImageButton) findViewById(R.id.imgButtonPlay) ;
        btnPre      =(ImageButton) findViewById(R.id.imgButtonPre) ;
        btnStop     =(ImageButton) findViewById(R.id.imgButtonStop) ;
    }
}
