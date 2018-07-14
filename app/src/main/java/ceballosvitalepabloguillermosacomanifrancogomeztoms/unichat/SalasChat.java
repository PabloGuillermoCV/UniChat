package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SalasChat extends ActividadBase implements View.OnClickListener {

    private Button [] Materias;
    private Button Configuracion;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salas_chat);
        String TextoMateria;
        int resID;
        Materias = new Button [4];
        for (int I = 0; I < 3; I++) {
            TextoMateria = "Materia" + I;
            resID = getResources().getIdentifier (TextoMateria, "id", getPackageName());
            Materias [I] = findViewById (resID);
            Materias [I].setOnClickListener (this);
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
            Button aux = findViewById(view.getId());
            String text = aux.getText().toString();
            irSala(text);
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

