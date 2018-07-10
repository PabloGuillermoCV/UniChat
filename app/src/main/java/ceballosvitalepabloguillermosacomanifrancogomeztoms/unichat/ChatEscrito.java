package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class ChatEscrito extends ActividadBase {

    //objeto especial que se encargará de adaptar la vista del historial de mensajes
    private FirebaseListAdapter<ChatMessage> adp;

    private ListView vistaChat;
    private FloatingActionButton enviar,adjunto; //adjunto seria para adjuntar archivos, hay que ver como hacer para que el boton despliegue un menú como hace Whatsapp
    private String value;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            value = extras.getString("NOMBRE"); //obtengo el nombre de la sala de chat a buscar su historial
        }
        enviar = findViewById(R.id.fab);
        enviar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg){
                enviarMensaje();
            }
        });
        setContentView(R.layout.chat);
        //chequeo adicional por las dudas
        if(value != null)
            mostrarMensajes(value);
    }

    /**
     * Método que se ejecuta al presionar el botón de Atras. Cambia la pantalla a la
     * actividad de Lista De Salas. Llama al método privado irASalas.
     * @param vista Vista actual (Contexto actual de la App)
     */
    public void onBack (View vista) {
        irASalas ();
    }

    public void enviarMensaje(){
        EditText input = (EditText)findViewById(R.id.input);

        // leo el texto que aparece sobre el campo "input", lo subo y lo guardo en la Base de Datos
        //como una instancia nueva de "ChatMessage"
        FirebaseDatabase.getInstance()
                .getReference()
                .push() //este push() hay que sacarlo, ya que genera una nueva clave, acá es donde entra en juego el String que obtuve con el Strategy
                        //debemos, de alguna manera, decirle a Firebase que guarde este mensaje enviado en el subárbol correspondiente a la materia en cuestión
                .setValue(new ChatMessage(input.getText().toString(),
                        FirebaseAuth.getInstance()
                                .getCurrentUser()
                                .getDisplayName())
                );

        // Vacio el input
        input.setText("");
    }

    private void irASalas () {
        // TODO Deberia destruirse esta actividad?
        startActivity (new Intent (ChatEscrito.this, SalasChat.class));
    }

    /**
     * Método que se encargará de mosrtar el historial de mensajes
     * @param materia determinante para saber QUÉ historial buscar
     */
    //Ver si es iterativo++++++++++++++++++++++++++++++++++++++++
    private void mostrarMensajes(String materia){
        vistaChat = (ListView) findViewById(R.id.list_of_messages);
        //en la parte de "getReference()" del Firebase es donde deberiamos ver como decirle a Firebase que buscamos un chat en particular
        adp = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                R.layout.mensaje, FirebaseDatabase.getInstance().getReference()) {
            @Override
            /**
             * Método especial que se encargará de llenar el historial de mensajes, buscando los mismos en la Base de Datos provista por Firebase
             */
            protected void populateView(View v, ChatMessage model, int position) {
                // Obtengo las referencias a "mensaje.xml" para mostrar los mensajes y popular la sala de chat
                TextView messageText = (TextView)v.findViewById(R.id.message_text);
                TextView messageUser = (TextView)v.findViewById(R.id.message_user);
                TextView messageTime = (TextView)v.findViewById(R.id.message_time);

                // Seteo los textos
                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());

                // Le doy formato a los datos sobre cuando se enviaron los mensajes antes de mostrarlos
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
            }
        };
        //seteo el adaptador
        vistaChat.setAdapter(adp);

    }
}