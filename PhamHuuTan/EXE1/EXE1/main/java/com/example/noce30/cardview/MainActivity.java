package com.example.noce30.cardview;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    FoodAdapter foodAdapter;
    List<Food> foodList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        foodList = new ArrayList<>();
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        foodList.add(
                new Food(
                        1,
                        "Beef hamburgers",
                        "attractive",
                        5.0,
                        70000,
                        R.drawable.hamperger));

        foodList.add(
                new Food(
                        1,
                        "Italian Restaurant",
                        "4 feed",
                        5.0,
                        6000000,
                        R.drawable.kingcrap));

        foodList.add(
                new Food(
                        1,
                        "salad",
                        "nutritious",
                        5.0,
                        100000,
                        R.drawable.salad));


        foodAdapter= new FoodAdapter(this,foodList);
        recyclerView.setAdapter(foodAdapter);
        initRecycleView();

    }
//hang ngang
    private void initRecycleView()
    {
       LinearLayoutManager layoutManager= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
       RecyclerView recyclerView= findViewById(R.id.recyclerView);
       recyclerView.setLayoutManager(layoutManager);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuItemAdd) {
            Intent intent=new Intent(MainActivity.this, AddActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            return true;
        }

        return true;
    }
}
