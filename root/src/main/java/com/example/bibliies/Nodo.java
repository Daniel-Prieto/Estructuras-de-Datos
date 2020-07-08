package com.example.bibliies;

public class Nodo {
    public Texto textoActual;
    public Nodo siguiente;
    public Nodo anterior;
    public int prioridad;
    public Nodo(Texto textoActual){
        this.textoActual=textoActual;
        this.siguiente=null;
        this.anterior=null;
        this.prioridad = 0;
    }
    public String imprimir(){
        return textoActual.getTitulo();
    }

}