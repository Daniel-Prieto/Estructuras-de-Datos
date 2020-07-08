package com.example.bibliies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> implements View.OnClickListener {


    Texto[] textos;
    private View.OnClickListener listener;

    public AdapterDatos(Texto[]  ingreso) {
        this.textos = ingreso;
    }

    @NonNull
    @Override
    public AdapterDatos.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);

        view.setOnClickListener(this);


        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDatos.ViewHolderDatos holder, int position) {

        holder.asignarDatos(textos[position]);

    }

    @Override
    public int getItemCount() {
        return textos.length;
    }

    public void setOnClickListener(View.OnClickListener listener){

        this.listener = listener;

    }

    @Override
    public void onClick(View v) {

        if(listener!=null){

            listener.onClick(v);
        }

    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView autor,titulo,fecha,codigo,calificacion;


        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.menuTitulo);
            autor = itemView.findViewById(R.id.menuAutor);
            fecha = itemView.findViewById(R.id.menuFecha);
            codigo = itemView.findViewById(R.id.menuCodigo);
            calificacion = itemView.findViewById(R.id.menuCalificacion);
        }

        public void asignarDatos(Texto texto) {

            titulo.setText(texto.getTitulo());
            autor.setText("Por: "+ texto.getAutor());
            fecha.setText("("+texto.getFechaPublicacion()+")");
            codigo.setText("Codigo:" + texto.getCodigo());
            calificacion.setText("Calificacion: "+ texto.getCalificacion()+ "/10");

        }
    }
}
