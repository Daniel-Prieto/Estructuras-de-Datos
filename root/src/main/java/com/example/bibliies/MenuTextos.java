package com.example.bibliies;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuTextos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuTextos extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView autor,titulo,fecha,codigo,calificacion,numeroPag,idioma,tema,numeroCal;



    public MenuTextos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuTextos.
     */


    // TODO: Rename and change types and number of parameters
    public static MenuTextos newInstance(String param1, String param2) {
        MenuTextos fragment = new MenuTextos();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_textos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments()!=null){

            MenuTextosArgs args = MenuTextosArgs.fromBundle(getArguments());

            Texto texto = args.getTexto();

            titulo = view.findViewById(R.id.menuTitulo);
            autor = view.findViewById(R.id.menuAutor);
            fecha = view.findViewById(R.id.menuFecha);
            codigo = view.findViewById(R.id.menuCodigo);
            calificacion = view.findViewById(R.id.menuCalificacion);
            numeroPag = view.findViewById(R.id.menuPaginas);
            idioma = view.findViewById(R.id.menuIdioma);
            tema = view.findViewById(R.id.menuTema);
            numeroCal = view.findViewById(R.id.menuNumeroCal);

            titulo.setText(texto.getTitulo());
            autor.setText("Por: "+ texto.getAutor());
            fecha.setText("("+texto.getFechaPublicacion()+")");
            codigo.setText("Codigo:" + texto.getCodigo());
            calificacion.setText("Calificacion: "+ texto.getCalificacion()+ "/10");
            numeroPag.setText("Numero de Paginas: "+ texto.getNumeroPaginas());
            idioma.setText("Idioma: "+texto.getIdioma());
            tema.setText("Tema: " + texto.getTema());
            numeroCal.setText("Opiniones: "+ texto.getNumeroCalificaciones());

        }
    }
}