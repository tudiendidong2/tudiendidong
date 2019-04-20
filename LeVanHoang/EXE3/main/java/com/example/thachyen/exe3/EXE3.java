package com.example.thachyen.exe3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.Random;


public class EXE3 extends AppCompatActivity {

    public static final Random RANDOM = new Random();
    private Button rollDices;
    private ImageView imageView1, imageView2;


    public static int randomDiceValue(){
        return RANDOM.nextInt(6) + 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exe3_activity);

        rollDices = (Button) findViewById(R.id.rollDices);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);

        rollDices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation anim1 = AnimationUtils.loadAnimation(EXE3.this, R.anim.shake);
                final Animation anim2 = AnimationUtils.loadAnimation(EXE3.this, R.anim.shake);
                final Animation.AnimationListener animationListener = new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        int value = randomDiceValue();
                        int res = getResources().getIdentifier("dice_" + value, "drawable", "com.ssaurel.dicer");

                        if(animation == anim1)
                        {
                            imageView1.setImageResource(res);
                        }
                        else if(animation == anim2){
                            imageView2.setImageResource(res);
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                };

                anim1.setAnimationListener(animationListener);
                anim2.setAnimationListener(animationListener);

                imageView1.startAnimation(anim1);
                imageView2.startAnimation(anim2);

            }
        });
    }


    public void clockwise(View view){
        ImageView image1 = (ImageView)findViewById(R.id.imageView1);
        ImageView image2 = (ImageView)findViewById(R.id.imageView2);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.myanimation);


        image1.startAnimation(animation);
        image2.startAnimation(animation);
    }

    public void zoom(View view){
        ImageView image1 = (ImageView)findViewById(R.id.imageView1);
        ImageView image2 = (ImageView)findViewById(R.id.imageView2);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.clockwise);

        image1.startAnimation(animation);
        image2.startAnimation(animation);
    }

    public void fade(View view){
        ImageView image1 = (ImageView)findViewById(R.id.imageView1);
        ImageView image2 = (ImageView)findViewById(R.id.imageView2);
        Animation animation =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fade);

        image1.startAnimation(animation);
        image2.startAnimation(animation);
    }

    public void blink(View view){
        ImageView image1 = (ImageView)findViewById(R.id.imageView1);
        ImageView image2 = (ImageView)findViewById(R.id.imageView2);

        Animation animation =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.blink);


        image1.startAnimation(animation);
        image2.startAnimation(animation);
    }

    public void move(View view){
        ImageView image1 = (ImageView)findViewById(R.id.imageView1);
        ImageView image2 = (ImageView)findViewById(R.id.imageView2);

        Animation animation =
                AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);

        image1.startAnimation(animation);
        image2.startAnimation(animation);
    }

    public void slide(View view){
        ImageView image1 = (ImageView)findViewById(R.id.imageView1);
        ImageView image2 = (ImageView)findViewById(R.id.imageView2);

        Animation animation =
                AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);

        image1.startAnimation(animation);
        image2.startAnimation(animation);
    }





}
