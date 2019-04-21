package com.example.noce30.cardview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private Context context;
    private List<Food> foodList;

    public FoodAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.list_item_layout,null);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder productViewHolder, final int i) {
        final Food food= foodList.get(i);
        productViewHolder.txtTitle.setText(food.getTitle());
        productViewHolder.txtDesc.setText(food.getDesc());
        productViewHolder.txtRating.setText(String.valueOf(food.getRating()));
        productViewHolder.txtPrice.setText(String.valueOf(food.getPrice()));
        productViewHolder.imageView.setImageDrawable(context.getResources().getDrawable(food.getImage()));

        final int currentPosition=i;
        //click img show detail
        productViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,GaleryActivity.class);
                intent.putExtra("img_url",food.getImage());
                intent.putExtra("img_title",food.getTitle());
                intent.putExtra("img_desc",food.getDesc());
                intent.putExtra("img_rating",food.getRating());
                intent.putExtra("img_price",food.getPrice());
                context.startActivity(intent);

            }
        });
//click dai keo them item
        productViewHolder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //add item
                addItem(currentPosition,food);
                Toast.makeText(context, "Add item at possion"+ i, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
//phuong thuc add data in RecycleView
    private void addItem(int i, Food food) {
        foodList.add(i, food);
        notifyItemInserted(i);
    }

    @Override
    public int getItemCount() {
        return foodList.size() ;
    }

    class FoodViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView txtTitle, txtDesc, txtRating, txtPrice;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            txtTitle=itemView.findViewById(R.id.textViewTitle);
            txtDesc=itemView.findViewById(R.id.textViewShortDesc);
            txtRating=itemView.findViewById(R.id.textViewRating);
            txtPrice=itemView.findViewById(R.id.textViewPrice);

        }
    }
}
