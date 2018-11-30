package ec.edu.uce.basedatossqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ec.edu.uce.conexion.ConexionBD;
import ec.edu.uce.conexion.Utilidades;

public class ConsultarUsuarios extends AppCompatActivity {

    EditText campoId;
    EditText campoNombre;
    EditText campoApellido;
    EditText campoCedula;

    ConexionBD conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuarios);

        conexion = new ConexionBD(getApplicationContext(),"USUARIOS_BD", null,1);

        campoId = (EditText) findViewById(R.id.idConsUsuario);
        campoNombre = (EditText) findViewById(R.id.nombreConsUsuario);
        campoApellido = (EditText) findViewById(R.id.apellidoConsUsuario);
        campoCedula = (EditText) findViewById(R.id.cedulaConsUsuario);
    }

    public void btnConsultarUsuario(View view){
        consultarUsuarios();
    }

    public void btnActualizarUsuario(View view){
        actualizarUsario();
    }

    public void btnEliminarUsuario(View view){
        eliminarUsuario();
    }

    private void eliminarUsuario() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        String [] parametros = {campoId.getText().toString()};
        db.delete(Utilidades.TABLA_USUARIOS, Utilidades.CAMPO_ID + "=?",parametros);
        Toast.makeText(getApplicationContext(), "USUARIO ELIMINADO", Toast.LENGTH_SHORT).show();
        campoId.setText("");
        limpiar();
        db.close();
    }

    private void actualizarUsario() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        String [] parametros = {campoId.getText().toString()};

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE, campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_APELLIDO, campoApellido.getText().toString());
        values.put(Utilidades.CAMPO_CEDULA, campoCedula.getText().toString());

        db.update(Utilidades.TABLA_USUARIOS, values, Utilidades.CAMPO_ID + "=?",parametros);
        Toast.makeText(getApplicationContext(), "USUARIO ACTUALIZADO", Toast.LENGTH_SHORT).show();
        campoId.setText("");
        limpiar();
        db.close();
    }

    private void consultarUsuarios() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        String [] parametros = {campoId.getText().toString()};
        String [] campos =  {Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_APELLIDO, Utilidades.CAMPO_CEDULA};
        try{
            //Los parametros enviados como null corresponden a datos String asociados a GroupBy, Having y OrderBY
            Cursor cursor = db.query(Utilidades.TABLA_USUARIOS, campos,Utilidades.CAMPO_ID + "=?",parametros,null,null,null);
            cursor.moveToFirst();
            campoNombre.setText(cursor.getString(0));
            campoApellido.setText(cursor.getString(1));
            campoCedula.setText(cursor.getString(2));
            cursor.close();

        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "NO EXISTE USUARIO", Toast.LENGTH_LONG).show();
            limpiar();
        }
        
    }

    private void limpiar() {
        campoNombre.setText("");
        campoApellido.setText("");
        campoCedula.setText("");
    }
}
