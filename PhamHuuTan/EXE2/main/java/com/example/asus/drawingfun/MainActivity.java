package com.example.asus.drawingfun;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import static android.os.Environment.DIRECTORY_PICTURES;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private DrawingView drawingView;
    private ImageButton currPaint;
    private  ImageButton newIB;
    private  ImageButton saveFileIB;
    private  ImageButton eraseIB;
    private  ImageButton drawIB;

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingView = (DrawingView)findViewById(R.id.drawing);
        LinearLayout paintLayout = (LinearLayout)findViewById(R.id.paint_colors);
        currPaint = (ImageButton)paintLayout.getChildAt(0);
        currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));

        //handle new paint
        newIB = (ImageButton)findViewById(R.id.new_btn);
        newIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.clearDrawing();
            }
        });

        //handle save file
        saveFileIB = (ImageButton)findViewById(R.id.save_btn);
        saveFileIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFile();
            }
        });

        //handle erase
        eraseIB = (ImageButton)findViewById(R.id.erase_btn);
        eraseIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView = (DrawingView)findViewById(R.id.drawing);
                drawingView.handelErase();
            }
        });

        drawIB = (ImageButton)findViewById(R.id.draw_btn);
        drawIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView = (DrawingView)findViewById(R.id.drawing);
                drawingView.setSize();
            }
        });

    }

    public void paintClicked(View view){
        //use chosen color
        if(view!=currPaint){
            //update color
            ImageButton imgView = (ImageButton)view;
            String color = view.getTag().toString();
            drawingView.setColor(color);

            imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
            currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
            currPaint=(ImageButton)view;
        }
    }
    public Bitmap viewToBitmap(View view, int w, int h)
    {
        Bitmap bitmap=Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas= new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    public void saveFile() {

        AlertDialog.Builder editalert;
        editalert = new AlertDialog.Builder(this);
        editalert.setTitle("Please Enter the name with which you want to Save");
        final EditText input = new EditText(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        input.setLayoutParams(lp);
        editalert.setView(input);
        editalert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichImageView) {
                drawingView = (DrawingView)findViewById(R.id.drawing);
                drawingView.setDrawingCacheEnabled(true);
                String name= input.getText().toString();
                Bitmap bitmap =drawingView.getDrawingCache();
                String root = getApplicationContext().getFilesDir().getPath().toString();
                File myDir = new File(root);
                File file = new File (myDir, name+".png");
                if (file.exists ()) file.delete ();
                try
                {
                    if(!file.exists())
                    {
                        file.createNewFile();
                    }
                    FileOutputStream ostream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 10, ostream);
                    // System.out.println("saving......................................................"+path);
                    ostream.close();
                    Toast.makeText(MainActivity.this, "Lưu file thanh công!", Toast.LENGTH_SHORT).show();
                    drawingView.invalidate();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }finally
                {
                    drawingView.setDrawingCacheEnabled(false);
                }
            }
        });
        editalert.show();
    }
}
