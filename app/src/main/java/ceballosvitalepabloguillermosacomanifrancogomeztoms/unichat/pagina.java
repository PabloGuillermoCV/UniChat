package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat;

import android.os.Bundle;
import android.widget.Button;

/**
 * Clase que hace de placeholder para las páginas que manejará SalasChat, mudo la lóigca del botón acá
 */
public class pagina extends ActividadBase{

    BotonSala oyente;
    Button entrar;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina);
        entrar = findViewById(R.id.BotonMateria);
        oyente = new BotonAyG(getApplicationContext());
        entrar.setOnClickListener(oyente);
        modify();
    }

    /**
     * método para modificar el texto del OYENTE del botón para poder llamar al historiald e la sala correcta
     */
    private void modify(){
        oyente.setNombre(entrar.getText().toString());
    }
}
