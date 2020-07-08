package com.example.bibliies;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Fragment implements View.OnClickListener {
private EditText usuario,contra;
private TextView registrarse;
private  Button ingresar;


        public Login() {
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

                return inflater.inflate(R.layout.fragment_login,container,false);

        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
                super.onViewCreated(view, savedInstanceState);


                usuario=(EditText)view.findViewById(R.id.txt_usuario);
                contra=(EditText)view.findViewById(R.id.txt_contrasena);
                ingresar=(Button)view.findViewById(R.id.button_ingresar);

                ingresar.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View v){
                                Navigation.findNavController(v).navigate(R.id.action_login3_to_menuPrincipal);
                        }
                });

                registrarse=(TextView)view.findViewById(R.id.txtview_registrarse);

                registrarse.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v){
                                Navigation.findNavController(v).navigate(R.id.action_login3_to_registro);

                        }
                });

        }

        @Override
    public void onClick(View v) {

    }
}