package com.example.noce30.cardview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GaleryActivity  extends AppCompatActivity {
    private Context context;
    private List<Food> foodList;

    private static final String TAG = "GaleryActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getIcomingIntent();

    }
    private void getIcomingIntent()
    {
        Log.d(TAG, "getIcomingIntent: check aaaaaaaaaa");
        if(getIntent().hasExtra("img_url")&& getIntent().hasExtra("img_title")
                &&getIntent().hasExtra("img_desc")&& getIntent().hasExtra("img_rating")
                && getIntent().hasExtra("img_price"))
        {
            Log.d(TAG, "getIcomingIntent:found intent extra ");
            int imageUrl= getIntent().getIntExtra("img_url", 0);
            String imageName= getIntent().getStringExtra("img_title");
            String imageDesc= getIntent().getStringExtra("img_desc");
            double imageRating= getIntent().getDoubleExtra("img_rating",0.0);
            double imagePrice= getIntent().getDoubleExtra("img_price",0.0);

            setImage(imageUrl,imageName,imageDesc,imageRating,imagePrice);
        }
    }
    private void setImage(int imageUrl, String imgName, String imgDesc,double imgRating, double imgPrice)
    {
        Log.d(TAG, "setImage: setting to img and name to widget");
        TextView name=findViewById(R.id.detail_title);
        name.setText(imgName);
        ImageView imageView=findViewById(R.id.detail_image);
        imageView.setImageResource(imageUrl);
        TextView desc=findViewById(R.id.desc);
        desc.setText(imgDesc);
        TextView rating=findViewById(R.id.rating);
        rating.setText(String.valueOf(imgRating));
        TextView price=findViewById(R.id.price);
        price.setText(String.valueOf(imgPrice));

    }
//    public void onBindViewHolder(@NonNull FoodAdapter.FoodViewHolder productViewHolder, final int i) {
//        Food food= foodList.get(i);
//        productViewHolder.txtTitle.setText(food.getTitle());
//        productViewHolder.txtDesc.setText(food.getDesc());
//        productViewHolder.txtRating.setText(String.valueOf(food.getRating()));
//        productViewHolder.txtPrice.setText(String.valueOf(food.getPrice()));
//        productViewHolder.imageView.setImageDrawable(context.getResources().getDrawable(food.getImage()));
//
//    }
//
//    class FoodViewHolder extends RecyclerView.ViewHolder{
//
//        ImageView imageView;
//        TextView txtTitle, txtDesc, txtRating, txtPrice;
//        public FoodViewHolder(@NonNull View itemView) {
//            super(itemView);
//            imageView = itemView.findViewById(R.id.imageView);
//            txtTitle=itemView.findViewById(R.id.textViewTitle);
//            txtDesc=itemView.findViewById(R.id.textViewShortDesc);
//            txtRating=itemView.findViewById(R.id.textViewRating);
//            txtPrice=itemView.findViewById(R.id.textViewPrice);
//        }
//    }
}
