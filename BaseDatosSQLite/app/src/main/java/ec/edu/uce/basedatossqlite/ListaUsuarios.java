package ec.edu.uce.basedatossqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ec.edu.uce.conexion.ConexionBD;
import ec.edu.uce.conexion.Utilidades;
import ec.edu.uce.entities.Usuario;

public class ListaUsuarios extends AppCompatActivity {

    ListView listaViewUsuarios;
    ArrayList<String> listaInformacion;
    ArrayList<Usuario> listaUsuarios;
    ConexionBD conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);
        conexion = new ConexionBD(this,"USUARIOS_BD", null, 1);
        listaViewUsuarios = (ListView) findViewById(R.id.listViewUsuarios);
        consultarListaUsuarios();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInformacion);
        listaViewUsuarios.setAdapter(adaptador);
    }

    private void consultarListaUsuarios() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Usuario usuario = null;
        listaUsuarios = new ArrayList<Usuario>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIOS, null);
        while (cursor.moveToNext()){
            usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setApellido(cursor.getString(2));
            usuario.setCedula(cursor.getString(3));

            listaUsuarios.add(usuario);

        }

        obtenerLista();

    }

    private void obtenerLista() {

        listaInformacion = new ArrayList<String>();

        for (int i = 0; i<listaUsuarios.size(); i++){
            listaInformacion.add(listaUsuarios.get(i).getId() + " - "
                    + listaUsuarios.get(i).getNombre() + " "
                    + listaUsuarios.get(i).getApellido() + " - "
                    + listaUsuarios.get(i).getCedula()
            );
        }

    }
}
