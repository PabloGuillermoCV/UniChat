package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SalasChat extends ActividadBase {

    private Button[] Botones; //Botones de la actividad
    private BotonSala[] BotonesList; //Listeners para cada botón, luego seteo cada botón con un For (ahora lo hice a mano porque hice una sola clase del árbol de herencia)

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salas_chat);
        Botones = new Button[3];
        BotonesList = new BotonSala[3];
        BotonesList[0] = new BotonAyG(getApplicationContext());
        Botones[0].findViewById(R.id.materia_2);
        Botones[0].setOnClickListener(BotonesList[0]); //vinculo el botón con su correspondiente OnCLickListener
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

}

