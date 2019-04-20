package com.example.thachyen.a2048;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;
import java.util.Scanner;

public class Game2048Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_2048_activity);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuItemAdd) {
            Intent intent=new Intent(Game2048Activity.this, NewActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            return true;
        }

        return true;
    }

    public void buttonClick(View view){
        Intent mintent = new Intent(Game2048Activity.this, NewActivity.class);
        startActivity(mintent);

    }


    int[][] board = new int[4][4];  //the size of the board is 4x4
    Scanner input = new Scanner(System.in);
    String horizontal = "+----+----+----+----+";
    int randomNumber = (new Random().nextDouble() < 0.9) ? 2 : 4;
    String vertical = "\u2502";
    //to print out the grid 4x4
    // Top line

    for (int i = 0; i < 4; i++)
    {
        System.out.print(vertical); // first column layout
        for (int j = 0; j < 4; j++)
        {
            if (board[i][j] != 0)
            {
                String sized = Integer.toString(board[i][j]);
                int check = 4 - sized.length();
                for (int k = 0; k < check; k++)
                {
                    System.out.print(" "); // Top line
                }
                System.out.print(board[i][j]);
            } else System.out.print("    ");
            if (j < 3) System.out.print(vertical);
            else System.out.println(vertical);
        }
        System.out.print(horizontal + "\n"); // Bottom line
    }



}
