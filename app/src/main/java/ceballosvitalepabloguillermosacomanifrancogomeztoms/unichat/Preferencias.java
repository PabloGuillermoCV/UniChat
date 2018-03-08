package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat;

import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class Preferencias extends ActividadBase{

    //tenemos que tener como datos lo referido a la configuración que pueda tocar el usuario
    private Button logOut;
    private Button Back;
    private CheckBox[] Opciones; //arreglo de checkBox para controlar las preferencias del usuario y poder guardar sus estados más tarde
    private SharedPreferences settings = getSharedPreferences("Configuracion",0); //para obtener la configuración
    private SharedPreferences.Editor editor = settings.edit(); //para editar configuraciones


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);
        logOut = findViewById(R.id.log_out_button);
        Back = findViewById(R.id.go_back_button);

        //EJEMPLO editor.putBoolean("NombreDeLoModificado",<ValorDeVerdad>);

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
        //TODO NO, esta actividad debe guardar sus modificaciones, tendremos que sobreescribir el método onPause() y hacer que se guarde la configuración
        this.onPause(); //ejecuto el método onPause() para guardar los datos antes de irme
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

    @Override
    public void onPause(){
        super.onPause();
        //TODO guardar configuración actual del ususario


    }

}