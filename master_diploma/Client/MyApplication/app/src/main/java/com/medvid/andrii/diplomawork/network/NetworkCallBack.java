package com.medvid.andrii.diplomawork.network;

import com.google.common.base.Preconditions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCallback<T> implements Callback<T> {

    private Callback<T> mCallback;

    public NetworkCallback(Callback<T> callback)    {
        mCallback = Preconditions.checkNotNull(callback);
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        mCallback.onResponse(call, response);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        mCallback.onFailure(call, t);
    }
}
