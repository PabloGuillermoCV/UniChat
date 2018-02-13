package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat.ElementosBaseDeDatos;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {Usuario.class}, version = 1) //codigo especial para avisar
                                                   //que esto hará referencia a una base de datos Room

public abstract class BaseDeDatos extends RoomDatabase {

    public abstract UsuarioDao usuarioDao();

    //AppDatabase db = Room.databaseBuilder(getApplicationContext(),
   //AppDatabase.class, "database-name").build();
    //esto creará una base de datos como definí en UsuarioDao, aunque hay que Usar Singleton
    //ya que una Base de Datos Room es un toque costosa de crear y solo necesitamos una base de datos
}
