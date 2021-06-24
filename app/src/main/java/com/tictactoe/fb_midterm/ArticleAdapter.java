package com.tictactoe.fb_midterm;


import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tictactoe.fb_midterm.R;
import com.tictactoe.fb_midterm.ArticleList;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    List<ArticleList>articleLists;
    Context ct;

    public ArticleAdapter(List<ArticleList> articleLists, Context ct) {
        this.articleLists = articleLists;
        this.ct = ct;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.article_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArticleList articleList=articleLists.get(position);
        Glide.with(ct)
                .load(articleList.getImageUrl())
                .into(holder.articleimg);

        holder.articlename.setText(articleList.getArticleName());

    }

    @Override
    public int getItemCount() {
        return articleLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView articleimg;
        TextView articlename;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            articleimg=itemView.findViewById(R.id.article_image);
            articlename=itemView.findViewById(R.id.article_name);
        }
    }
}
