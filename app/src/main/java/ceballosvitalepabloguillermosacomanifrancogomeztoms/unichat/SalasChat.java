package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.HashMap;
import java.util.Map;

public class SalasChat extends ActividadBase implements View.OnClickListener {

    private Button Configuracion;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salas_chat);
        String TextoMateria;
        int resID;
        ImageButton[] materias = new ImageButton[9];
        for (int I = 0; I <= 8; I++) {
            TextoMateria = "Materia" + I;
            resID = getResources().getIdentifier (TextoMateria, "id", getPackageName());
            materias[I] = findViewById (resID);
            materias[I].setOnClickListener (this);
        }
        Configuracion = findViewById (R.id.config_button);
        Configuracion.setOnClickListener(this);
    }

    @Override
    public void onClick (View view) {
        if (view == Configuracion) {
            irPerfil ();
        }

        else {
            /*
            Para saltar a ChatEscrito sin hacer un case con los botones
            Lo que hago es obtener la ID del botón que fue apretado y obtener su nombre
            para poder saltar a su sala de chat correspondiente
            (aunque ahora esto es poco efectivo ya que tenemos una sola sala de chat)
            El saltar a la Sala en sí lo delego a un método privado
             */

            String TextoId = view.getResources().getResourceName(view.getId());
            irSala (TextoId);

        }
    }

    /**
     * método que se ejecuta cuando se toca el botón de configuración, esto cambia actividades
     * dejando en segundo plano las salas de chat y yendo a la actividad de Perfil del Usuario
     */
    private void irPerfil () {
        Intent Pref = new Intent(this, Perfil.class);
        startActivity(Pref); //creo la nueva actividad y la inicio, SalasChat queda atras en segundo plano, esta NO la destruyo
    }

    /**
     * Método que se ejecuta al tocar calquier botón que deberia llevar a una sala de chat
     * @param sala nombre de la sala seleccionada, determinante para recuperar los mensajes correspondientes luego
     */
    private void irSala(String sala){
       /*
        Agrego al Bundle del Intent a ChatEscrito el nombre de la materia asociada al botón
        para poder usar dicho nombre en ChatEscrito
        */
        Intent Sala = new Intent(this, ChatEscrito.class);
        Sala.putExtra("NOMBRE", sala);
        startActivity(Sala);
    }
}

