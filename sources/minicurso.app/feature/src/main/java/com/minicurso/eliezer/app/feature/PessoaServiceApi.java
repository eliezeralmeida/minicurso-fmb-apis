package com.minicurso.eliezer.app.feature;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PessoaServiceApi {
    @Headers("Content-Type: application/json")
    @GET("pessoas")
    Call<List<PessoaApi>> listAll();

    @Headers("Content-Type: application/json")
    @POST("pessoas")
    Call<Void> post(@Body PessoaApi pessoa);
}
