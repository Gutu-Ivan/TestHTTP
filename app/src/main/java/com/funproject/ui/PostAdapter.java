package com.funproject.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.funproject.R;
import com.funproject.domain.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private List<Post> postList;
    private PostDetailDelegate delegate;
    public PostAdapter(PostDetailDelegate delegate) {
        this.delegate = delegate;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Post post = postList.get(position);
    holder.textViewUserId.setText(String.valueOf(post.userId));
    holder.textViewPostId.setText(String.valueOf(post.id));
    holder.textViewPostTitle.setText(post.title);
    holder.textViewPostBody.setText(post.body);
    }

    @Override
    public int getItemCount() {
        return postList == null ? 0 : postList.size();
    }

    public void setDataSource(List<Post> postList) {
        this.postList = postList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewUserId;
        TextView textViewPostId;
        TextView textViewPostTitle;
        TextView textViewPostBody;
        ViewHolder(View itemView) {
            super(itemView);
            textViewUserId = itemView.findViewById(R.id.text_view_user_id);
            textViewPostId = itemView.findViewById(R.id.text_view_post_id);
            textViewPostTitle = itemView.findViewById(R.id.text_view_post_title);
            textViewPostBody = itemView.findViewById(R.id.text_view_post_body);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = postList.get(getLayoutPosition()).id;
                    view.setOnClickListener(this);
                }
            });
        }
    }

}
