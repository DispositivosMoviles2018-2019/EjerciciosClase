package ec.edu.uce.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;

import ec.edu.uce.modelo.Usuario;
import ec.edu.uce.service.ApiService;
import ec.edu.uce.service.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText etNombre, etApellido, etEdad;
    private Button botonAgregar, botonIrListado;
    private ApiService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = (EditText) findViewById(R.id.txtNombre);
        etApellido = (EditText) findViewById(R.id.txtApellido);
        etEdad = (EditText) findViewById(R.id.txtEdad);
        botonAgregar = (Button) findViewById(R.id.btnAgregar);
        botonIrListado = (Button) findViewById(R.id.btnListado);
        service = ApiUtils.getAPIService();
        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario us= new Usuario();
                us.setId(20);
                us.setNombre(etNombre.getText().toString());
                us.setApellido(etApellido.getText().toString());
                us.setEdad(Integer.parseInt(etEdad.getText().toString()));
                sendPost(us);
                Intent intent = new Intent(MainActivity.this, ListadoActivity.class);
                startActivity(intent);
            }
        });


    }

    public void sendPost(Usuario usuario) {
            service.savePost(usuario).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {

            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });
    }

    public void listado(View view){
        Intent intent = new Intent(MainActivity.this, ListadoActivity.class);
        startActivity(intent);
    }

}
