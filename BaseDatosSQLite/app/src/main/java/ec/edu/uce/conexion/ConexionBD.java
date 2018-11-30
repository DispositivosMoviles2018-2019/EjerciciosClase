package ec.edu.uce.conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionBD extends SQLiteOpenHelper {

    public ConexionBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Genera los scripts de nuestra entidad
        db.execSQL(Utilidades.CREATE_TABLE_USUARIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Verifica si existe una version antigua de nuestra BD
        db.execSQL("DROP TABLE IF EXISTS USUARIOS");
        onCreate(db);
    }
}
