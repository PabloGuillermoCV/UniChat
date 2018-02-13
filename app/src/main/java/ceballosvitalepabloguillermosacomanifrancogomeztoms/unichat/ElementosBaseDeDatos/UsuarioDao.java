package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat.ElementosBaseDeDatos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao //Codigo Especial para determinar que Usuario es algo
    //que puede ser pedido como consulta en una Base de datos

/**
 * Interfaz especial tipo DAO (Data Access Object)  que contendrá las consultas
 * que se le podrán hacer a la Base de Datos
 */
public interface UsuarioDao {

    @Query("SELECT * FROM user")
    List<Usuario> getAll();

    @Query("SELECT * FROM user WHERE first_name LIKE :Nombre_US AND " //dudo de esto porque NO explota por ningun lado
            + "last_name LIKE :Contrasenia LIMIT 1")

    Usuario findByName(String NombreUS);

    @Insert //Consulta para insertar usuarios a la Base de Datos
    void insertAll(Usuario... users);

    @Delete //Consulta para eliminar usuarios de la Base de Datos
    void delete(Usuario user);

}
