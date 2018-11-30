package ec.edu.uce.basedatossqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ec.edu.uce.conexion.ConexionBD;
import ec.edu.uce.conexion.Utilidades;

public class RegistroUsuarios extends AppCompatActivity {

    EditText campoId;
    EditText campoNombre;
    EditText campoApellido;
    EditText campoCedula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);
        campoId = (EditText) findViewById(R.id.idUsuario);
        campoNombre = (EditText) findViewById(R.id.nombreUsuario);
        campoApellido = (EditText) findViewById(R.id.apellidoUsuario);
        campoCedula = (EditText) findViewById(R.id.cedulaUsuario);
    }

    public void btnRegistrarUsuario (View view){
        registrarUsuarios();
        //registrarUsuariosSql();
    }

    private void registrarUsuariosSql() {
        ConexionBD conexion = new ConexionBD(this,"USUARIOS_BD", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String insert = "INSERT INTO " + Utilidades.TABLA_USUARIOS + " ( " + Utilidades.CAMPO_ID +
                ", " + Utilidades.CAMPO_NOMBRE + ", " + Utilidades.CAMPO_APELLIDO + ", " +
                Utilidades.CAMPO_CEDULA + ") values (" + campoId.getText().toString() +", '" +
                campoNombre.getText().toString() + "', '" + campoApellido.getText().toString() + "', '" +
                campoCedula.getText().toString()+"')";
        db.execSQL(insert);
        Toast.makeText(getApplicationContext(), "USUARIO REGISTRADO", Toast.LENGTH_SHORT).show();
        db.close();
    }

    private void registrarUsuarios() {
        ConexionBD conexion = new ConexionBD(this,"USUARIOS_BD", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_ID, campoId.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE, campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_APELLIDO, campoApellido.getText().toString());
        values.put(Utilidades.CAMPO_CEDULA, campoCedula.getText().toString());

        Long idResultado = db.insert(Utilidades.TABLA_USUARIOS, Utilidades.CAMPO_ID, values);
        Toast.makeText(getApplicationContext(), "Número de registro: " + idResultado, Toast.LENGTH_SHORT).show();
        limpiar();
        db.close();
    }

    public void limpiar(){
        campoId.setText("");
        campoNombre.setText("");
        campoApellido.setText("");
        campoCedula.setText("");
    }
}
