package com.avans.AvansMovieApp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.avans.AvansMovieApp.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ProductViewHolder> {
    private static final String TAG = "RecycleViewAdapter";

    private ArrayList<Product> products;
    private Context context;

    public RecycleViewAdapter(ArrayList<Product> products, Context context){
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        ProductViewHolder productViewHolder = new ProductViewHolder(view);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, int position) {
        Glide.with(this.context)
                .asBitmap()
                .load(this.products.get(position).getImageURL().get(0))
                .into(holder.image);
        holder.productName.setText(products.get(position).getTitle());
//        holder.specsTag.setText(products.get(position).);
        holder.summaryText.setText(products.get(position).getSummary());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "On Click");
                Toast.makeText(context, holder.productName.getText().toString(), Toast.LENGTH_LONG);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(this.products == null){
            return 0;
        }
        return this.products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView productName;
        TextView specsTag;
        TextView summaryText;
        ConstraintLayout parentLayout;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_product_item_image);
            productName = itemView.findViewById(R.id.tv_product_item_name);
            specsTag = itemView.findViewById(R.id.tv_product_item_specstag);
            summaryText = itemView.findViewById(R.id.tv_product_item_summary);
            parentLayout = itemView.findViewById(R.id.cv_product_item_layout);
        }
    }
}
