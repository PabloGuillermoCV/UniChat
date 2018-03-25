package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat;

import android.content.Context;
import android.view.View;


/**
 * Uno de los Listeners que por Herencia determinará a que sala de chat debo entrar
 */
public class BotonAyG extends BotonSala {

    public BotonAyG(Context context) {
        super("Elementos de Álgebra y de Geometria",context); //subo al constructor de mi padre con un nombre provisiorio que será modificado más tarde
    }

    @Override
    public void onClick(View view) {
        irSalaX(view,sendString());
    }
}
