package com.mawuli.dev.response;

public class GoodResponse<T> implements MyResponse{
    private final T response;
    public GoodResponse(T response){
        this.response = response;
    }

    public T getResponse(){
        return response;
    }
}
