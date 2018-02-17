package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat.ElementosBaseDeDatos;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {Usuario.class}, version = 1) //codigo especial para avisar
                                                   //que esto hará referencia a una base de datos Room

public abstract class BaseDeDatos extends RoomDatabase {

    private static BaseDeDatos Instancia;

    public abstract UsuarioDao usuarioDao();

    /**
     * método Singleton para crear la Base de Datos Room
     * @param context Contexto actual de la App
     * @return Instancia de la Base de Datos
     */
    public static BaseDeDatos getInstancia(Context context){
        if (Instancia == null){
            Instancia = Room.databaseBuilder(context.getApplicationContext(),BaseDeDatos.class,"Base de Datos para Usuarios").build();

        }
        return Instancia;
    }

    public static void destroyInstance(){
        Instancia = null;
    }

    //AppDatabase db = Room.databaseBuilder(getApplicationContext(),
   //AppDatabase.class, "database-name").build();
    //esto creará una base de datos como definí en UsuarioDao, aunque hay que Usar Singleton
    //ya que una Base de Datos Room es un toque costosa de crear y solo necesitamos una base de datos
}
