package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat.ElementosBaseDeDatos;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

//esto es un código especial para decir que la clase ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat.ElementosBaseDeDatos.Usuario es una entidad que pertenecerá a una Base de Datos
@Entity

/**
 * clase ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat.ElementosBaseDeDatos.Usuario que será usada por una base de Datos Room para manejo de SQLite
 *
 * por lo que he estado Leyendo, usar SQLite a lo negro trae varios problemas a la larga
 * y es poco eficiente, usar un Room nos facilitará las cosas ya que enmascara un par de cosas de SQLite
 * que tendriamos que andar tocando en medio de la App si no usamos Room
 */
public class Usuario {

    @ColumnInfo(name = "Nombre_US") //primera columna, Nombre del usuario
    private String NombreUS;

    @PrimaryKey //la contraseña del Usuario deberia ser única e irrepetible
    @ColumnInfo(name = "Contrasenia") //segunda columna, Contraseña del Usuario
    private String contraseniaUS;

    /**
     * Constructor del objeto Usuario
     * @param nom nombre del nuevo usuario
     * @param con contraseña ligada al nuevo usuario
     */
    public Usuario(String nom, String con){
        NombreUS = nom;
        contraseniaUS = con;
    }

    //Setters y Getters necesarios para el correcto funcionamiento del Room
    public void setNombre(String Nom){
        NombreUS = Nom;
    }

    public void setContrasena(String Con){
        contraseniaUS = Con;
    }

    public String getNombreUS(){
        return NombreUS;
    }

    public String getContraseniaUS(){
        return contraseniaUS;
    }



}
