package com.example.bibliies;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuPrincipal extends Fragment {

    public static arbolAVL Acodigo;
    public static arbolAVL Atitulo;
    public static arbolAVL Aautor;
    public static Cliente defaultCliente;
    public static Admin defaultAdmin;

    DatabaseReference BasedeDatos = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mRootReference;
    RecyclerView recycler;
    Button salir;
    Texto[] textos;

    public MenuPrincipal() {
        mRootReference= FirebaseDatabase.getInstance().getReference();
        Acodigo= new arbolAVL("codigo");
        Acodigo.activarRepetidos();
        Atitulo= new arbolAVL("titulo");
        Atitulo.activarRepetidos();
        Aautor= new arbolAVL("autor");
        Aautor.activarRepetidos();
        solicitarDatosFirebase();




    }

    private Context mContext;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_menu_principal, container, false);
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        salir = view.findViewById(R.id.button_salir);
        recycler = view.findViewById(R.id.listaTextos);
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getContext(), String.valueOf(Acodigo.getSize()),Toast.LENGTH_SHORT);
                toast.show();
            }
        });






        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textos = Acodigo.toListaArrayDinamico().getArreglo();
                AdapterDatos adapter = new AdapterDatos(textos);
                recycler.setAdapter(adapter);
                recycler.setLayoutManager(new GridLayoutManager(requireActivity(),1));
                adapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast toast = Toast.makeText(getContext(), textos[(recycler.getChildAdapterPosition(v))].getTitulo(),Toast.LENGTH_SHORT);
                        toast.show();

                        //Navigation.findNavController(v).navigate(R.id.action_menuPrincipal_to_menuTextos);

                        MenuPrincipalDirections.ActionMenuPrincipalToMenuTextos action = MenuPrincipalDirections.actionMenuPrincipalToMenuTextos(textos[(recycler.getChildAdapterPosition(v))]);

                        Navigation.findNavController(v).navigate(action);




                    }
                });




            }
        }, 2000);









    }

    //salir

    private void solicitarDatosFirebase() {
        Acodigo.FORMATNodos();
        Atitulo.FORMATNodos();
        Aautor.FORMATNodos();
        mRootReference.child("Textos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(final DataSnapshot snapshot : dataSnapshot.getChildren()){

                    mRootReference.child("Textos").child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Texto temp = snapshot.getValue(Texto.class);
                            Log.e("codigo:",""+temp.getCodigo());
                            Log.e("Titulo:",""+temp.getTitulo());
                            Log.e("Autor:",""+temp.getAutor());
                            Log.e("Fecha publicacion:",""+temp.getFechaPublicacion());
                            Log.e("Numero de paginas:",""+temp.getNumeroPaginas());
                            Log.e("Idioma:",""+temp.getIdioma());
                            Log.e("Tema:",""+temp.getTema());
                            if( temp.getTitulo()!=null && temp.getAutor()!=null){
                                Texto libro = new Texto(temp.getCodigo(),temp.getTitulo(),temp.getAutor(),temp.getFechaPublicacion(),temp.getNumeroPaginas(),temp.getIdioma(),temp.getTema());
                                Acodigo.insert(libro);
                                Atitulo.insert(libro);
                                Aautor.insert(libro);

                            }
                            Log.e("Datos:",""+snapshot.getValue());


                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }

                    });



                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }



}