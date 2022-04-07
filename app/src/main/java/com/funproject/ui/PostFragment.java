package com.funproject.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.funproject.R;
import com.funproject.data.NetworkManager;
import com.funproject.domain.AppNetworkCallback;
import com.funproject.domain.Post;

import java.util.List;

public class PostFragment extends Fragment implements AppNetworkCallback<List<Post>>, PostDetailDelegate {
    RecyclerView recyclerViewPost;
    PostAdapter postAdapter;

    public PostFragment() {
    }

    public static PostFragment newInstance() {
        PostFragment fragment = new PostFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postAdapter = new PostAdapter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewPost = view.findViewById(R.id.recycler_view_post);
        recyclerViewPost.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewPost.addItemDecoration(new DividerItemDecoration(getContext(), RecyclerView.VERTICAL));
        recyclerViewPost.setAdapter(postAdapter);

        NetworkManager.getInstance().getPosts(this);
    }

    @Override
    public void onSuccess(List<Post> response) {
        postAdapter.setDataSource(response);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void itemOnClick(int id) {
        getParentFragment()
                .beginTransaction()
                .replace(R.id.container, PostDetailFragment.newInstance(id))
                .addToBackStack(null)
                .commit();
    }
}