/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bibliies;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author deste
 */
public class Cliente extends Usuario {
    private String nivelAcademico;
    private ListaEnlazada favoritos = new ListaEnlazada();
    private Cola librosPorLeer = new Cola();
    int tamaño = 1;
    Texto[] favritos1 = new Texto[2 * tamaño];
    private int textosRevisados;

    public Cliente(String nivelAcademico, int textosRevisados, int id, String nombre, String apellido, String correo,
            String clave) {
        super(id, nombre, apellido, correo, clave);
        this.nivelAcademico = nivelAcademico;
        this.textosRevisados = textosRevisados;
    }

    public Cliente(String nivelAcademico, int textosRevisados) {
        this.favoritos = null;
        this.nivelAcademico = nivelAcademico;
        this.textosRevisados = textosRevisados;
    }

    public Cliente() {
    }

    @Override
    public String toString() {
        // el problema son los arrays dinamicos, que no se dejan meter un libro
        return super.toString() + "\nNivel Academico: " + nivelAcademico;
    }

    public String getNivelAcademico() {
        return nivelAcademico;
    }

    public int getTextosRevisados() {
        return textosRevisados;
    }

    public ListaEnlazada getFavoritos() {
        return favoritos;
    }

    public Cola getLibrosPorLeer() {
        return librosPorLeer;
    }

    public void setLibrosPorLeer(Cola xleer) {
        this.librosPorLeer = xleer;
    }

    public void setFavoritos(ListaEnlazada favoritos) {
        this.favoritos = favoritos;
    }

    public void setNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public void setTextosRevisados(int textosRevisados) {
        this.textosRevisados = textosRevisados;
    }

    public void comentarTexto(Texto texto, String comentario) {

        ListaArrayDinamico lista = new ListaArrayDinamico();
        if (texto.getComentarios() != null) {
            lista.setDato(texto.getComentarios().getDato());
        }
        Date objDate = new Date();
        String strDateFormat = "hh: mm: ss a dd-MMM-aaaa";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        System.out.println();

        lista.add(getNombre() + " " + getApellido() + "(" + nivelAcademico + ") [" + objSDF.format(objDate) + "]: \n"
                + comentario);

        texto.setComentarios(lista);

    }

    public void calificarTexto(Texto texto, double calificacionNueva) {
        if(calificacionNueva<0 || calificacionNueva>5)
            return;
        int numeroCalificaciones = texto.getNumeroCalificaciones();
        double calificacion = texto.getCalificacion();

        if (numeroCalificaciones != 0) {

            double nuevaCal = ((calificacion * numeroCalificaciones + calificacionNueva) / (numeroCalificaciones + 1));

            texto.setCalificacion(nuevaCal);

        }

        else {

            texto.setCalificacion(calificacionNueva);
        }

        texto.setNumeroCalificaciones(numeroCalificaciones + 1);

    }

    public void agregarFavorito(Texto texto) {

        ListaEnlazada favoritoslist = this.getFavoritos();

        if (!favoritoslist.contains(texto)) {

            Nodo nodo = new Nodo(texto);
            favoritoslist.add(nodo);

        }
        

        this.librosPorLeer.enColar(texto);

        this.setFavoritos(favoritoslist);

    }

    public void agregarFavorito(ListaEnlazada lista) {

        ListaEnlazada favoritoslist = this.getFavoritos();

        favoritoslist.add(lista);

        this.setFavoritos(favoritoslist);

    }

    public void eliminarFavorito(int codigo) {

        ListaEnlazada favoritoslist = this.getFavoritos();

        if (favoritoslist.contains(codigo)) {

            favoritoslist.remove(codigo);
        }

        this.setFavoritos(favoritoslist);

    }

    public ListaEnlazada buscarTextoPalabra(ListaEnlazada lista, String nombre) {

        Nodo iterador = lista.cabeza;

        ListaEnlazada listanueva = new ListaEnlazada();

        while (iterador != null) {

            if (iterador.textoActual.getTitulo().contains(nombre)) {

                listanueva.add(iterador);
            }

            else
                iterador = iterador.siguiente;

        }

        return listanueva;

    }

}
