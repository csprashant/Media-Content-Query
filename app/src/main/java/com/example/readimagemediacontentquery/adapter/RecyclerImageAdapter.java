package com.example.readimagemediacontentquery.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readimagemediacontentquery.R;
import com.example.readimagemediacontentquery.model.Image;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecyclerImageAdapter extends RecyclerView.Adapter<RecyclerImageAdapter.ViewHolder> {

    List<Image> imageList1;
    public RecyclerImageAdapter(List<Image> imageList1) {
        this.imageList1 = imageList1;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return  holder;
    }
    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerImageAdapter.ViewHolder holder, int position) {
        Image image=imageList1.get(holder.getAdapterPosition());
        holder.imageView.setImageURI(Uri.parse(image.getUrl()));
    }

    @Override
    public int getItemCount() {
        return imageList1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
