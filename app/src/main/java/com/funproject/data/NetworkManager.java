package com.funproject.data;

import com.funproject.domain.AppNetworkCallback;
import com.funproject.domain.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkManager {
    private static NetworkManager instance;
    private static ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    private NetworkManager() {

    }
    private <T> void enqueueRequest(Call<T> httpRequest, AppNetworkCallback<T> callback) {
        httpRequest.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.body() == null || !response.isSuccessful()) {
                    callback.onError("Oops something went wrong...");
                } else {
                    callback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void getPosts(AppNetworkCallback<List<Post>> callback) {
        Call<List<Post>> httpRequest = apiInterface.getPosts();
        enqueueRequest(httpRequest, callback);
    }

    public void getPost(AppNetworkCallback<Post> callback, int id) {
        Call<Post> httpRequest = apiInterface.getPost(id);
        enqueueRequest(httpRequest, callback);
    }

    public static NetworkManager getInstance() {
        if (instance == null) {
            instance = new NetworkManager();
        }
        return instance;
    }
}