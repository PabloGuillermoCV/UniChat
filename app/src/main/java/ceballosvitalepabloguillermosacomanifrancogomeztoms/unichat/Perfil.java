package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat;

/**
 * Created by Lark Digital on 13/3/2018.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class Perfil extends AppCompatActivity implements View.OnClickListener {

    //Objeto Firebase Auth
    private FirebaseAuth firebaseAuth;

    //Objetos del View
    private TextView textViewUserEmail;
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
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonRooms = (Button) findViewById(R.id.Ira_Salas);
        //Muestro el nombre del usuario logueado
        textViewUserEmail.setText("Bienvenido "+user.getEmail());

        //Seteo oyente para el botón, como la actividad es un oyente en si misma, esto es legal
        buttonLogout.setOnClickListener(this);
    }

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
        if(view == buttonRooms){
            //cierro la actividad
            finish();
            //me voy a la actividad de SalasChat
            startActivity(new Intent(this, SalasChat.class));
        }

    }
}
