package com.funproject.domain;

public interface AppNetworkCallback<T> {
    void onSuccess(T response);
    void onError(String message);

}
