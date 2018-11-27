package ec.edu.uce.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ec.edu.uce.modelo.Usuario;
import ec.edu.uce.service.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListadoActivity extends AppCompatActivity {
    ListView list;
    ArrayList<String> titles = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    private ApiService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titles);
        list = findViewById(R.id.list);
        list.setAdapter(arrayAdapter);
        getPosts();

    }

    private void getPosts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://odmontalvo.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService postService = retrofit.create(ApiService.class);
        Call<List<Usuario>> call = postService.getPost();

        call.enqueue(new Callback<List<Usuario>>() {

            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                for(Usuario usuario : response.body()) {
                    titles.add(usuario.getNombre());
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {

            }
        });
    }

}
