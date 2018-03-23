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

public class SalasChat extends ActividadBase {

    private String[] materias;
    private Button Botones;
    private LayoutInflater paginas;
    private ViewPager vp;
    private BotonSala BotonesList; //Listeners para cada botón, luego seteo cada botón con un For (ahora lo hice a mano porque hice una sola clase del árbol de herencia)

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salas_chat);
        BotonesList = new BotonAyG(getApplicationContext());
        Botones.setOnClickListener(BotonesList); //vinculo el botón con su correspondiente OnCLickListener
        materias=getResources().getStringArray(R.array.materias);
        //get an inflater to be used to create single pages
        paginas = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Reference ViewPager defined in activity
        vp=(ViewPager)findViewById(R.id.viewPager);
        //set the adapter that will create the individual pages
        vp.setAdapter(new AdaptadorPaginas());

    }

    /**
     * método que se ejecuta cuando se toca el botón de configuración, esto cambia actividades
     * dejando en segundo plano las salas de chat y yendo a la actividad de Perfil del Usuario
     * @param view Vista actual (Contexto actual de la App)
     */
    public void irPerfil(View view){
        Intent Pref = new Intent(this, Perfil.class);
        startActivity(Pref); //creo la nueva actividad y la inicio, SalasChat queda atras en segundo plano, esta NO la destruyo
    }

private class AdaptadorPaginas extends PagerAdapter{

    /**
     * Return the number of views available.
     */
    @Override
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
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        View page = paginas.inflate(R.layout.pagina, null);
        ((Button)page.findViewById(R.id.BotonMateria)).setText(materias[position]);
        //Add the page to the front of the queue
        ((ViewPager) container).addView(page, 0);
        return page;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
        object=null;
    }
}
}

}

