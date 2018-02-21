package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SalasChat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salas_chat);
    }

    /**
     * método que se ejecuta cuando se toca el botón de configuración, esto cambia actividades
     * dejando en segundo plano las salas de chat y yendo a la actividad de preferencias
     * @param view Vista actual (Contexto actual de la App)
     */
    public void irPreferencias(View view){
        Intent Pref = new Intent(this, Preferencias.class);
        startActivity(Pref); //creo la nueva actividad y la inicio, SalasChat queda atras en segundo plano, esta NO la destruyo
    }

    /**
     * método que se encargaria de entrar a la sala de chat correspondiente con lo que eligió el usuario
     * @param view Contexto actual de la App
     */
    public void irSalaX (View view) {
        int buttonID = view.getId ();
        if (buttonID == 1) {
            Intent Sala = new Intent (this, ChatEscrito.class);
            startActivity(Sala);
            // TODO
            //Esto es por poner un ejemplo de la seleccion de salas (quiza un case seria mejor).
            //Tambien se podria utilizar el string del boton
        }
    }
}