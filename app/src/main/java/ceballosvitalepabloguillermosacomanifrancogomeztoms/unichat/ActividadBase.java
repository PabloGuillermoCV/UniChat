package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat;

import android.app.Activity;
import android.os.Bundle;

/**
 * clase especial que hace de placeholder para poder setear el strategy de los botones de las salas correctamente
 * lo que hago es que cualquier actividad que herede de esto, ahora sepa su propia referencia y pueda ser manipulable y obtenible
 */
public class ActividadBase extends Activity {
    protected Study mMyApp;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMyApp = (Study)this.getApplicationContext();
    }
    protected void onResume() {
        super.onResume();
        mMyApp.setCurrentActivity(this);
    }
    protected void onPause() {
        clearReferences();
        super.onPause();
    }
    protected void onDestroy() {
        clearReferences();
        super.onDestroy();
    }

    /**
     * metodo para limpiar la referencia actual de actividad y así evitar pisarme cuando necesito saber en que actividad estoy
     */
    private void clearReferences(){
        Activity currActivity = mMyApp.getCurrentActivity();
        if (this.equals(currActivity))
            mMyApp.setCurrentActivity(null);
    }
}
