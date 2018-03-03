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
 * el Nombre de la tabla DEBE SER "Usuario" ya que sino tira error en ejecución
 */
public interface UsuarioDao {

    @Query("SELECT * FROM Usuario")
    List<Usuario> getAll();



    @Query("SELECT * FROM Usuario WHERE Usuario.Nombre_US = (:NombreUS)")
    /**
     * método que busca si existe un usuario con el mismo nombre en la base de datos
     * en caso afirmativo, devuelve el Usuario ligado a ese nombre
     * @param NombreUS nombre del usuario a buscar
     * @return el Usuario ligado al nombre o Null si no existia usuario con ese nombre
     */
    Usuario findByName(String NombreUS);

    @Query("SELECT * FROM Usuario WHERE Usuario.Contrasenia = (:contraseniaUS) ")
    /**
     * metodo que determina si existe una contraseña idéntica a la pasada por parámetro
     * @param contra contraseña a buscar
     * @return valor de verdad de si la contraseña existe dentro de la base de datos para algún usuario
     */
    boolean contraUnica(String contraseniaUS);


    @Insert
    void insertUser(Usuario user);

    @Insert //Consulta para insertar usuarios a la Base de Datos
    void insertAll(Usuario... users);

    @Delete //Consulta para eliminar usuarios de la Base de Datos
    void delete(Usuario user);

}
