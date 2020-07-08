package com.example.bibliies;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Registro extends Fragment {
    private static int contador=1;
    private EditText nombre;
    private EditText correo;
    private EditText contrasena;
    private EditText rol;
    private Spinner nivelAca;
    private EditText edad;
    private EditText usuario;
   Button registrar;

    public Registro() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_registro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nombre=(EditText) view.findViewById(R.id.Txt_nombre);
        correo=(EditText) view.findViewById(R.id.txt_correo);
        contrasena=(EditText) view.findViewById(R.id.txt_contra);
        nivelAca=(Spinner) view.findViewById(R.id.spinner_nivelAcademico);
        usuario=(EditText) view.findViewById(R.id.txt_usuario);
        edad=(EditText)view.findViewById(R.id.txt_edad);
        registrar=(Button)  view.findViewById(R.id.Boton_registrar);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}