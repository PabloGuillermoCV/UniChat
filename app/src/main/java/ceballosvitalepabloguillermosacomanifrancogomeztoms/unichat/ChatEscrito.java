package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;


public class ChatEscrito extends AppCompatActivity {

    private ChatArrayAdapter adp;
    private ListView vistaChat;
    private EditText textoAEnviar;
    private Button enviar,adjunto; //adjunto seria para adjuntar archivos, hay que ver como hacer para que el boton despliegue un menú como hace Whatsapp
    private boolean lado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enviar = (Button) findViewById(R.id.send_message_button);
        adjunto =(Button) findViewById(R.id.add_image_button);
        vistaChat = (ListView) findViewById(R.id.chat_list_view);
        textoAEnviar = (EditText) findViewById((R.id.chat_message));
        lado = false;
        Intent i = getIntent();
        adp = new ChatArrayAdapter(getApplicationContext(), R.layout.chat);
        vistaChat.setAdapter(adp);
        enviar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg){
                enviarMensaje();
            }
        });
        vistaChat.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL); //seteo que la vista de los mensajes siempre haga scroll
        adp.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                vistaChat.setSelection(adp.getCount() -1);
            }
        });
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

    public boolean enviarMensaje(){
        adp.add(new ChatMessage(lado,textoAEnviar.getText().toString()));
        textoAEnviar.setText("");
        lado = !lado;
        return true; //retorno que el mensaje se envió correctamente
    }

    private void irASalas () {
        // TODO Deberia destruirse esta actividad?
        startActivity (new Intent (ChatEscrito.this, SalasChat.class));
    }
}