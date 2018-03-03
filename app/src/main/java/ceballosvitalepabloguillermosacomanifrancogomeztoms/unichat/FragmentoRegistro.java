package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat.ElementosBaseDeDatos.BaseDeDatos;
import ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat.ElementosBaseDeDatos.Usuario;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.widget.RelativeLayout.*;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentoRegistro.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentoRegistro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoRegistro extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "Nombre";
    private static final String ARG_PARAM2 = "Clave";
    private Button confirm,cancel;
    private EditText Nombre,Contra;
    // TODO: Rename and change types of parameters
    private String NuevoNombre;
    private String NuevaContra;

    private OnFragmentInteractionListener mListener;

    public FragmentoRegistro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentoRegistro.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentoRegistro newInstance(String param1, String param2) {
        FragmentoRegistro fragment = new FragmentoRegistro();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            NuevoNombre = getArguments().getString(ARG_PARAM1);
            NuevaContra = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View toReturn =  inflater.inflate(R.layout.fragment_registro, container, false);
        RelativeLayout.LayoutParams parametros = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        //le añado al Fragment una regla: debe posicionarse por debajo del botón de Registro de la activiadad de Login
        parametros.addRule(RelativeLayout.BELOW,R.id.register_button);
        //aplico los cambios al layout del Fragment
        toReturn.setLayoutParams(parametros);
        confirm = (Button)toReturn.findViewById(R.id.confirm_registration_button);
        cancel = (Button)toReturn.findViewById(R.id.cancel_registration_button);
        Nombre = (EditText)toReturn.findViewById(R.id.nombre_register);
        Contra = (EditText)toReturn.findViewById(R.id.password_register);

        agregarOyentes();

        return toReturn;
    }

    private void agregarOyentes(){
        confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //pido lo escrito en los campos
                String aux1 = Nombre.getText().toString();
                String aux2 = Contra.getText().toString();
                //delego la verificación y registro en el método especifico
                if(aux1  != "" && aux2 != "")
                    completarRegistro(aux1,aux2);
                //si alguno de lso campos esta vacio, tiro un error y pido que la aplicación concentre la Vista sobre el campo en error
                else{ //acá podria meter un mini Strategy para determinar QUE campo esta vacio,esto falla si AMBOS campos estan vacios
                    if(aux1 == "") {
                        Nombre.setError("Campo vacio!");
                        Nombre.requestFocus();
                    }
                    else{
                        Contra.setError("Campo Vacio!");
                        Contra.requestFocus();
                    }


                }
            }

            }

        );

        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //eliminar el fragment de alguna forma
            }

        });
    }

    /**
     * método que se encargará de completar el registro
     * Debe verificar que tanto el nombre elegido como la contraseña NO
     * Existan en la Base de Datos, una vez chequeado eso
     * agrega los datos a la Base como un nuevo usuario
     */
    public void completarRegistro(String Nom, String Con){
        Usuario in = null;
        //verificar supuestos, si se comprueban, agregar el nuevo usuario a la Base de Datos
        BaseDeDatos aux = BaseDeDatos.getInstancia(getContext());
        if(aux.usuarioDao().findByName(Nom) == null){
            if(aux.usuarioDao().contraUnica(Con)){
                //inserto al nuevo usuario en la base de datos
                in = new Usuario(Nom,Con);
                aux.usuarioDao().insertUser(in);
            }
            else{
                //la contraseña ya existe
                Contra.setError("Contraseña ya existente!");
                Contra.requestFocus();
            }
        }
        else{
            //el nombre ya existe
            Nombre.setError("Nombre de usuario ya existente!");
            Nombre.requestFocus();
        }


        if(in == null){
            //Decirle al usuario que ocurrió un error, lo haré a través de un PopUp
            //creo un Layout "inflado" que contendrá la ventana de error (Estoy creando un Fragment  al vuelo basicamente)
            LayoutInflater error = (LayoutInflater)getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            View popUp =  error.inflate(R.layout.popup, null);
            final PopupWindow ventana = new PopupWindow(popUp, RadioGroup.LayoutParams.WRAP_CONTENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT);
            Button close = (Button)popUp.findViewById(R.id.id_cerrar);
            close.setOnClickListener(new Button.OnClickListener(){

                @Override
                public void onClick(View v) {
                    ventana.dismiss(); //le digo al popup que se vaya al presionar su botón de cerrado
                }});


        }
        else
            //si salió bien, elimino el Fragment ya que no lo necesito más
            getFragmentManager().beginTransaction().remove(this).commit();


    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
