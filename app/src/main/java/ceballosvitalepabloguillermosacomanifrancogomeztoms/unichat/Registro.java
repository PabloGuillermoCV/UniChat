package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    //defino vistas de  componentes gráficas
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextName;
    private Button buttonSignup;

    private TextView textViewSignin;

    private ProgressDialog progressDialog;


    //defino el objeto FirebaseAuth
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        //inicializo el objeto FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        //Si getCurrentUser() me devuelve algo que NO es null
        //significa que se intentó registrar a un usuario YA EXISTENTE
        if(firebaseAuth.getCurrentUser() != null){
            //asi que cierro esta actividad
            finish();

            //y me voy a la actividad que muestra el perfil del usuario
            startActivity(new Intent(getApplicationContext(), Perfil.class));
        }

        //Inicializo vistas de componentes gráficas
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextName = findViewById(R.id.editTextName);
        textViewSignin = findViewById(R.id.textViewSignin);

        buttonSignup = findViewById(R.id.buttonSignup);

        progressDialog = new ProgressDialog(this);

        //vinculo oyentes
        buttonSignup.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }

    /**
     * método que se encargará de registrar al nuevo usuario
     */
    private void registerUser(){

        //obtengo mail, contraseña y Nombre de Usuario de los campos de texto
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();
        final String Name = editTextName.getText().toString().trim();

        //chequeo que el mail, contraseña y Nombre de Usuario no sean vacias, sino, envió un pop-up de error
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Por favor, ingrese un E-Mail",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Por favor, ingrese una Contraseña",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(Name)){
            Toast.makeText(this, "Por favor, ingrese un Nombre de Usuario", Toast.LENGTH_LONG).show();
        }


        //si los campos no son vacios
        //muestro un cuadrito de progreso y  permito que Firebase Registre al nuevo usuario

        progressDialog.setMessage("Registrando Usuario, por favor, espere...");
        progressDialog.show();

        //creo un nuevo usuario
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //me fijo si se completó el proceso correctamente, si así fue, cierro el registro y me voy a las salas de chat directo
                        if(task.isSuccessful()){
                            //Lo que hago aquí es seterar el Nombre de Usuario del Usuario que acaba de Registrarse
                            //para despues recuperarlo desde Firebase
                            //basicamente Actualizo los datos del Usuario con el Nombre de Usuario
                            //proveido por el TextView del Nombre de Usuario
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            if(user != null) {
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(Name).build();
                                user.updateProfile(profileUpdates);
                            }
                            finish();
                            startActivity(new Intent(getApplicationContext(), SalasChat.class));
                        }else{
                            //sino, muestro un error
                            Toast.makeText(Registro.this,"Error en el proceso de Registro",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    /**
     * método onClick de la Actividad para Registro y saltado entre salas
     * @param view Vista actual de la App
     */
    @Override
    public void onClick(View view) {

        //si el botón tocado es el de Registro, comienzo el proceso de Registrado del nuevo usuario en el sistema
        if(view == buttonSignup){
            registerUser();
        }

        if(view == textViewSignin){
            //me voy a la actividad de Login si el usuario toca el texto de "ya estas registrado?" en el view
            startActivity(new Intent(this, Login.class));
        }

    }
}
