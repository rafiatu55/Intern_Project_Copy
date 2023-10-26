package com.mawuli.dev.response;

public class BadResponse<T> implements MyResponse{
    private final T response;
    public BadResponse(T response){
        this.response = response;
    }
    public T getResponse(){
        return this.response;
    }
}
