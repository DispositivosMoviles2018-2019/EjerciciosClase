package ec.edu.uce.basedatossqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ec.edu.uce.conexion.ConexionBD;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionBD conexion = new ConexionBD(this,"USUARIOS_BD", null, 1);
    }

    public void registrarUsuarios(View view){
        Intent registrar = new Intent(this, RegistroUsuarios.class);
        startActivity(registrar);
    }

    public void consultarUsuarios(View view){
        Intent consultar = new Intent(this, ConsultarUsuarios.class);
        startActivity(consultar);
    }

    public void listarUsuarios(View view){
        Intent listar = new Intent(this, ListaUsuarios.class);
        startActivity(listar);
    }
}
