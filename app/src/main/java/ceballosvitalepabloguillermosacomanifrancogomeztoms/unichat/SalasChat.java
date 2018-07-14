package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SalasChat extends ActividadBase implements View.OnClickListener {

    //mandé este botón aca arriba por órden de Android Studio para evitar redundancia
    private Button Configuracion;


    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salas_chat);
        //materias = getResources().getStringArray(R.array.materias);
        //get an inflater to be used to create single pages
        //paginas = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Reference ViewPager defined in activity
        //vp = (ViewPager)findViewById(R.id.viewPager);
        //set the adapter that will create the individual pages
        //vp.setAdapter(new AdaptadorPaginas());
        String TextoMateria;
        int resID;
        for (int I = 0; I < 3; I++) {
            //Modifiqué para que el String sea "button(I)" ya que en el XML aparece así
            //sino, me tiraba error porque no encontraba la referencia al buscarla en el XML
            TextoMateria = "button" + I;
            resID = getResources().getIdentifier (TextoMateria, "id", getPackageName());
            //se habian olvidado de inicializar el arreglo de botones, sin esto, tira NullPointerException
            Button[] materias = new Button[4];

            materias[I] = findViewById (resID);
            materias[I].setOnClickListener (this);
        }
        Configuracion = findViewById (R.id.config_button);
        Configuracion.setOnClickListener(this);
    }

    @Override
    public void onClick (View view) {
        if (view == Configuracion) {
            irPerfil (view);
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
     * @param view Vista actual (Contexto actual de la App)
     */
    private void irPerfil (View view) {
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

/*private class AdaptadorPaginas extends PagerAdapter{

    /**
     * Return the number of views available.
     */
    /*@Override
    public int getCount() {
        return materias.length;
    }

    /**
     * Determines whether a page View is associated with a specific key object
     * as returned by {@link #instantiateItem(ViewGroup, int)}. This method is
     * required for a PagerAdapter to function properly.
     *
     * @param view   Page View to check for association with <code>object</code>
     * @param object Object to check for association with <code>view</code>
     * @return true if <code>view</code> is associated with the key object <code>object</code>
     */
    /*@Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }*/

    /*@Override
    public Object instantiateItem(ViewGroup container, int position){
        View page = paginas.inflate(R.layout.pagina, null);
        ((Button)page.findViewById(R.id.BotonMateria)).setText(materias[position]); //seteo el texto del botón del view nuevo, esto es esencial
        //Add the page to the front of the queue
        ((ViewPager) container).addView(page, 0);
        return page;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
        object=null;
    }
}*/
}

