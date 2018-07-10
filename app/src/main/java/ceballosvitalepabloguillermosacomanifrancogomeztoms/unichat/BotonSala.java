package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

/**
 * Clase abstracta para el Strategy de los botones
 * ES UN OnCLickListener! NO UN BOTÓN!
 */
public abstract class BotonSala implements View.OnClickListener {

    protected String Nombre;
    protected Activity padre; //los botones deben conocer a que actividad pertenecen

    public BotonSala(String nombre,Context context) {
        Nombre = nombre;
        padre = ((Study)context.getApplicationContext()).getCurrentActivity(); //obtengo en que actividad estoy (Deberia ser SalasChat)
    }


    public String sendString(){
        return Nombre;
    }

    /**
     * método que se encargaria de entrar a la sala de chat correspondiente con lo que eligió el usuario
     * @param view Contexto actual de la App
     * @param s Nombre del botón presionado, usado para enviar data extra a la sala y poder setearla correctamente con el chat correspondiente
     */
    public void irSalaX(View view, String s) {
        Intent Sala = new Intent (padre, Perfil.class);
        //Sala.putExtra("NOMBRE",s); //antes de iniciar la actividad, le digo al Intent que guarde un extra, esto ayudará a setear el chat correspondiente
        padre.startActivity(Sala); //le digo a la actividad a la cual pertenezco que inicie el intent de actividad por mi
    }

    public void setNombre(String nom){
        Nombre = nom;
    }
}
