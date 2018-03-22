package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat;

/**
 * La idea de esta clase es tener en ejecucion un objeto que represente al usuario con todos sus datos,
 * y a la hora de necesitar esos datos no tener que acceder a la base de datos constantemente si no
 * solicitar al objeto los datos y ahorrar tiempo.
 */

public class UsuarioNormal implements Usuario {
    private String nombreUsuario;
    private String email;
    private String carrera;
    private UsuarioNormal yo = null;

    public UsuarioNormal(String n, String e, String c){
        nombreUsuario=n;
        email=e;
        carrera=c;
    }
    public UsuarioNormal getInstancia(String n, String e, String c){
        if (yo==null)
            yo = new UsuarioNormal(n,e,c);
        return yo;
    }

    @Override
    public String getNombre() {
        return nombreUsuario;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getCarrera() {
        return carrera;

    }
}
