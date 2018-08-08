package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatEscrito extends ActividadBase {

    private FloatingActionButton adjunto; //adjunto seria para adjuntar archivos, hay que ver como hacer para que el boton despliegue un menú como hace Whatsapp
    private String value;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        setContentView(R.layout.chat);
        if(extras != null){
            value = extras.getString("NOMBRE"); //obtengo el nombre de la sala de chat a buscar su historial
        }
        FloatingActionButton enviar = findViewById(R.id.fab);
        enviar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg){
                enviarMensaje();
            }
        });

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
        EditText input = findViewById(R.id.input);

        // leo el texto que aparece sobre el campo "input", lo subo y lo guardo en la Base de Datos
        //como una instancia nueva de "ChatMessage"
        //Por ahora, con el push(), estoy creandole nuevos hijos a la Raíz del árbol de la Base de Datos de Firebase
        FirebaseDatabase.getInstance()
                .getReference()
                .push()
                .setValue(new ChatMessage(input.getText().toString(),
                        FirebaseAuth.getInstance()
                                .getCurrentUser()
                                .getDisplayName()),value
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
        ListView vistaChat = findViewById(R.id.list_of_messages);
        //mudé la Referencia aquí arriba para más tarde ver de separar los chats, si es que hacemos distintos chats
        DatabaseReference myref = FirebaseDatabase.getInstance().getReference();
        //en la parte de "getReference()" del Firebase es donde deberiamos ver como decirle a Firebase que buscamos un chat en particular
        //getReference() me posiciona en la Raíz del árbol de la base de datos de Firebase, de ahí habria que setaar un hijo para separar los chats
        FirebaseListAdapter<ChatMessage> adp = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                R.layout.mensaje, myref) {
            /**
             * Método especial que se encargará de llenar el historial de mensajes, buscando los mismos en la Base de Datos provista por Firebase
             */
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                // Obtengo las referencias a "mensaje.xml" para mostrar los mensajes y popular la sala de chat
                TextView messageText = v.findViewById(R.id.message_text);
                TextView messageUser = v.findViewById(R.id.message_user);
                TextView messageTime = v.findViewById(R.id.message_time);

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