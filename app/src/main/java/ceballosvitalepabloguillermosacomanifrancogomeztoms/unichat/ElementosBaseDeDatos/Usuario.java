package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat.ElementosBaseDeDatos;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

import lombok.NonNull;


/**
 * clase ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat.ElementosBaseDeDatos.Usuario que será usada por una base de Datos Room para manejo de SQLite
 *
 * por lo que he estado Leyendo, usar SQLite a lo negro trae varios problemas a la larga
 * y es poco eficiente, usar un Room nos facilitará las cosas ya que enmascara un par de cosas de SQLite
 * que tendriamos que andar tocando en medio de la App si no usamos Room
 */
//esto es un código especial para decir que la clase ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat.ElementosBaseDeDatos.Usuario es una entidad que pertenecerá a una Base de Datos
@Entity
public class Usuario {

    @ColumnInfo(name = "Nombre_US") //primera columna, Nombre del usuario
    private String NombreUS;

    @PrimaryKey //la contraseña del Usuario deberia ser única e irrepetible
    @ColumnInfo(name = "Contrasenia") //segunda columna, Contraseña del Usuario
    @android.support.annotation.NonNull //como la contraseña es llave primaria, NO PUEDE ser Nula, entonces se denota que DEBE contener algo
    private String contraseniaUS;

    @Ignore
    public Usuario(){}

    /**
     * Constructor del objeto Usuario
     * @param NombreUS nombre del nuevo usuario
     * @param contraseniaUS contraseña ligada al nuevo usuario
     */
    public Usuario(String NombreUS, String contraseniaUS){
        this.NombreUS = NombreUS;
        this.contraseniaUS = contraseniaUS;
    }

    //Setters y Getters necesarios para el correcto funcionamiento del Room
    public void setNombre(String NombreUS){
        this.NombreUS = NombreUS;
    }

    public void setContrasena(String ContraseniaUs){
        this.contraseniaUS = ContraseniaUs;
    }

    public String getNombreUS(){
        return NombreUS;
    }

    public String getContraseniaUS(){
        return contraseniaUS;
    }



}
