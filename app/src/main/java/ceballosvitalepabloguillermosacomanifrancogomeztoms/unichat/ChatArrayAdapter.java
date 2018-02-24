package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que se encarga de organizar la vista del chat y agregar los mensajes a la vista a medida que se envian
 * NOTA: "bubble_a" y "bubble_b" deben ser imágenes de burbujas de texto vacias y con fondo transparenente, puse estas dos imágenes para que no explote
 *        pero habria que conseguir mejores imágenes
 */
class ChatArrayAdapter extends ArrayAdapter<ChatMessage> {

    private TextView viewChat;
    private List<ChatMessage> historial;
    private LinearLayout layout;

    public ChatArrayAdapter(Context context, int textViewResourceId){
        super(context,textViewResourceId);
        historial = new ArrayList<ChatMessage>();
    }

    public void agregarMensaje(ChatMessage msg){
        historial.add(msg);
        super.add(msg); //Esto me aprece raro... sigo sin saber a QUE hace super, si es que esto es un super en si
    }

    public int size(){
        return historial.size();
    }

    public ChatMessage getItem(int index){
        return historial.get(index);
    }

    public View getView(int pos, View convertView, ViewGroup padre){
        View v = convertView;
        if(v==null){

            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v =inflater.inflate(R.layout.chat, padre,false);

        }

        layout = (LinearLayout)v.findViewById(R.id.Message1);
        ChatMessage messageobj = getItem(pos);
        viewChat =(TextView)v.findViewById(R.id.SingleMessage);
        viewChat.setText(messageobj.getMensaje());
        viewChat.setBackgroundResource(messageobj.getLado()?R.drawable.bubble_a :R.drawable.bubble_b); //consulta ternaria! acá dibujo el mensaje con la burbuja correspondiente
        layout.setGravity(messageobj.getLado()?Gravity.LEFT:Gravity.RIGHT);
        return v;
    }


}
