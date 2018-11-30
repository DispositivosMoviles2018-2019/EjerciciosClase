package ec.edu.uce.conexion;

public class Utilidades {

    //Constantes campos de tabla USUARIOS
    public static final String TABLA_USUARIOS="USUARIOS";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_APELLIDO="apellido";
    public static final String CAMPO_CEDULA="cedula";



    public static final String CREATE_TABLE_USUARIOS = "CREATE TABLE " + TABLA_USUARIOS + " (" + CAMPO_ID +
            " INTEGER," + CAMPO_NOMBRE +" TEXT, " + CAMPO_APELLIDO + " TEXT, " + CAMPO_CEDULA + " TEXT)";

}
