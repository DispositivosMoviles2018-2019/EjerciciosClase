package ec.edu.uce.service;

import java.util.List;

import ec.edu.uce.modelo.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {


    @GET("product/read.php")
    Call<List<Usuario>> getPost();

    @POST("product/create.php")
    Call<Usuario> savePost(@Body Usuario usuario);
}
