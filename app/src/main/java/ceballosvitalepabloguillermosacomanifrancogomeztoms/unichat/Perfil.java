package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class Perfil extends AppCompatActivity implements View.OnClickListener {

    //Objeto Firebase Auth
    private FirebaseAuth firebaseAuth;
    private Button buttonLogout,buttonRooms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);

        //inicializo un objeto de Autorización de Firebase
        firebaseAuth = FirebaseAuth.getInstance();

        //Si el usuario no se Logueó, "getCurrentUser()" me devolverá Null
        if(firebaseAuth.getCurrentUser() == null){
            //cierro esta actividad
            finish();
            //me voy a la actividad de Login
            startActivity(new Intent(this, Login.class));
        }

        //Obtengo el usuario actual
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //inicializo Views
        TextView textViewUserEmail = findViewById(R.id.textViewUserEmail);
        buttonLogout = findViewById(R.id.buttonLogout);
        buttonRooms = findViewById(R.id.Ira_Salas);

        //Evita que getDisplayName pueda ser nulo
        if (user != null) {
            //Muestro el nombre del usuario logueado
            String displayName = user.getDisplayName();
            for (UserInfo userInfo : user.getProviderData()) {
                if (displayName == null && userInfo.getDisplayName() != null) {
                    displayName = userInfo.getDisplayName();
                }
            }
            textViewUserEmail.setText(String.format("%s%s", getString(R.string.bienvenidoperfil), displayName));

            //Seteo oyente para los botones
            buttonLogout.setOnClickListener(this);
            buttonRooms.setOnClickListener(this);
        }
    }

    /**
     * método onClick para saltar a las distintas salas disponibles
     * @param view vista actual de la App
     */
    @Override
    public void onClick(View view) {
        //si se presiona el botón "LogOut"
        if(view == buttonLogout){
            //hago LogOff del usuario (en una sola línea)
            firebaseAuth.signOut();
            //cierro la actividad
            finish();
            //me voy a la actividad de Login
            startActivity(new Intent(this, Login.class));
        }
        //Si no, el botón deberia ser el de ir a las Salas, me voy a Salaschat
        if(view == buttonRooms){
            //cierro la actividad
            finish();
            //me voy a la actividad de SalasChat
            startActivity(new Intent(this, SalasChat.class));
        }

    }
}
