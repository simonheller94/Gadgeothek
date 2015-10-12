package com.example.simon.gadgeothek.services;

public interface Callback<T> {
    void onCompletion(T input);
    void onError(String message);
}
