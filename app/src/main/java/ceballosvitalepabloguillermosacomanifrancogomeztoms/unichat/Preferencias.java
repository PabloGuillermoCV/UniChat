package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Preferencias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);
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
        startActivity (new Intent (Preferencias.this, SalasChat.class));
    }

    /**
     * Método que se ejecuta al presionar el botón de Log Out. Cambia la pantalla a la
     * actividad de Log In. Llama al método privado logOut.
     * @param vista Vista actual (Contexto actual de la App)
     */
    public void onLogout (View vista) {
        logOut ();
    }

    private void logOut () {
        // TODO Verificar que se destruyan las actividades que no sean la de log in
        startActivity (new Intent (Preferencias.this, LoginUniChat.class));
    }
}