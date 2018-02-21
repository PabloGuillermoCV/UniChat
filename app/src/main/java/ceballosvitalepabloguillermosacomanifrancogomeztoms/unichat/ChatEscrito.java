package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChatEscrito extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_escrito);
    }

    /**
     * Método que se ejecuta al presionar el botón de Atras. Cambia la pantalla a la
     * actividad de Lista De Salas. Llama al método privado irASalas.
     * @param vista Vista actual (Contexto actual de la App)
     */
    public void onBack (View vista) {
        irASalas ();
    }

    private void irASalas () {
        // TODO Deberia destruirse esta actividad?
        startActivity (new Intent (ChatEscrito.this, SalasChat.class));
    }
}