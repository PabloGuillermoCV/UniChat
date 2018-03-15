package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat;

/**
 * Created by Pablo Guillermo Ceballos Vitale on 13/3/2018.
 */
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

class Login extends AppCompatActivity implements View.OnClickListener{
    //defino vistas de componentes gráficas
    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;

    //Objeto FirebaseAuth para manejar el logueo
    private FirebaseAuth firebaseAuth;

    //dialogo que se mostrará en la ventanita de progreso
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //obtengo una instancia de un objeto tipo FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        //Si getCurrentUser() me devuelve algo que NO es null,
        //significa que el usuario ya está logueado
        //esto puede pasar, por ejemplo, si el usuario sale de la App al inicio
        //del telefono y vuelve a abrir la app
        if(firebaseAuth.getCurrentUser() != null){
            //cierro esta actividad
            finish();
            //me voy a la actividad de SalasChat
            startActivity(new Intent(getApplicationContext(), SalasChat.class));
        }

        //inicializo vistas de componentes gráficas
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonSignin);
        textViewSignup  = (TextView) findViewById(R.id.textViewSignUp);

        progressDialog = new ProgressDialog(this);

        //vinculo oyentes
        buttonSignIn.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
    }

    /**
     * Método que se encargará de Loguear al potencial usuario
     */
    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();


        //me fijo si los campos de usuario y contraseña estan vacios
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Ingrese un E-Mail, por favor",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Ingrese una Contraseña, por favor",Toast.LENGTH_LONG).show();
            return;
        }

        //si el mail y la contraseña no son vacios,
        //muestro un cartelito de progreso

        progressDialog.setMessage("Ingresando, por favor, espere...");
        progressDialog.show();

        //intento realizar un login con Firebase
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //si el Login fue correcto
                        if(task.isSuccessful()){
                            //elimino la actividad de Login y me voy a las Salas de Chat
                            finish();
                            startActivity(new Intent(getApplicationContext(), SalasChat.class));
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view == buttonSignIn){
            userLogin();
        }

        if(view == textViewSignup){
            finish();
            //me voy a la actividad de Registro si el usuario toca el texto "Registrarse" en la actividad
            startActivity(new Intent(this, Registro.class));
        }
    }
}