package com.bhagya.model;

/**
 * Created by brupasinghe on 7/14/2017.
 */
public class DataWrapper <T> {
    T response;

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
