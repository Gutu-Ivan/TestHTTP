package com.funproject.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.funproject.R;
import com.funproject.data.NetworkManager;
import com.funproject.domain.AppNetworkCallback;
import com.funproject.domain.Post;

import java.util.List;

public class PostDetailFragment extends Fragment implements AppNetworkCallback<Post> {
    TextView textViewUserId;
    TextView textViewPostId;
    TextView textViewPostTitle;
    TextView textViewPostBody;

    public PostDetailFragment() {
    }

    public static PostDetailFragment newInstance(int id) {
        PostDetailFragment fragment = new PostDetailFragment();
        Bundle arg = new Bundle();
        arg.putInt("id", id);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.post_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewUserId = view.findViewById(R.id.text_view_user_id);
        textViewPostId = view.findViewById(R.id.text_view_post_id);
        textViewPostTitle = view.findViewById(R.id.text_view_post_title);
        textViewPostBody = view.findViewById(R.id.text_view_post_body);

        if (getArguments() != null) {
            int id = requireArguments().getInt("id");
            NetworkManager.getInstance().getPost(this, id);
        }
    }

    @Override
    public void onSuccess(Post response) {
        textViewPostId.setText(String.valueOf(response.id));
        textViewUserId.setText(String.valueOf(response.userId));
        textViewPostTitle.setText(response.title);
        textViewPostBody.setText(response.body);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}