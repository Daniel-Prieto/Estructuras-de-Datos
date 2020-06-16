package biblies;

import java.util.*;
import java.io.*;

public class Texto {

   private static int contador = 1;

    public static int getContador() {
        return contador;
    }

    public static void setContador(int aContador) {
        contador = aContador;
    }
   private int codigo;
   private String titulo;
   private String autor;
   private String fechaPublicacion;
   private int numeroPaginas;
   private String idioma;
   private String tema;
   private int numeroCalificaciones;
   private double calificacion;
   private int tamaño=1;
   private int posicionador=0;
   private ListaArrayDinamico comentarios;

    public Texto(){       
    }
    
    
    public int compararSegunCondicion(Texto texto,String condicion){
        
        int comparacion;
        
        comparacion = getCondicion(condicion).compareToIgnoreCase(texto.getCondicion(condicion));
        
        if(comparacion<0)
            comparacion = -1;
        else if (comparacion>0)
            comparacion = 1;
        else 
            comparacion = 0;
        
        
        return comparacion;
    }
    
    public int compararSegunCondicion(String palabraComparadora,String condicion){
        
        int comparacion;
        
        comparacion = getCondicion(condicion).compareToIgnoreCase(palabraComparadora);
        
        if(comparacion<0)
            comparacion = -1;
        else if (comparacion>0)
            comparacion = 1;
        else 
            comparacion = 0;
        
        
        return comparacion;
    }
    
    public int compararSegunCondicion(int codigo){
        
        int retorno;
        
        
        if(this.codigo < codigo)
            retorno = -1;
        else if (this.codigo > codigo)
            retorno = 1;
        else 
            retorno = 0;
        
        
        return retorno;
    }
    //CONSOLA
    public Texto(String titulo, String autor,String fechaPublicacion, int numeroPaginas, String idioma, String tema) {
        this.codigo = contador;
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
        this.numeroPaginas = numeroPaginas;
        this.idioma = idioma;
        this.tema = tema;
        this.numeroCalificaciones = 0;
        this.calificacion = 0;
        this.comentarios = new ListaArrayDinamico();
        String temp [] = {"No hay comentarios"};
       this.comentarios.setDato(temp);
        contador++;
    }
    //MYSQL
    public Texto(int codigo,String titulo, String autor,String fechaPublicacion, int numeroPaginas, String idioma, String tema) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
        this.numeroPaginas = numeroPaginas;
        this.idioma = idioma;
        this.tema = tema;
        this.numeroCalificaciones = 0;
        this.calificacion = 0;
        this.comentarios = new ListaArrayDinamico();
        String temp [] = {"No hay comentarios"};
        this.comentarios.setDato(temp);
        contador=codigo+1;
    }

    @Override
    public String toString() {
        return "\nId: " + codigo + "\nTitulo: " + titulo + "\nAutor: " + autor 
                + "\nAño: " + fechaPublicacion + "\nCalificacion: "
                + calificacion
                +"\nComentarios: "
                + comentarios.getDatoString()
                
                
                ;
    }

   
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    
    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getIdioma() {
        return idioma;
    }

    public String getTema() {
        return tema;
    }

    public double getCalificacion() {
        return calificacion;
    }

  
    public int getNumeroCalificaciones() {
        return numeroCalificaciones;
    }

    public void setNumeroCalificaciones(int numeroCalificaciones) {
        this.numeroCalificaciones = numeroCalificaciones;
    }
    
    public String getCondicion(String s) {
        
        
       switch (s) {
           case "codigo":
               return String.valueOf(codigo);
           case "autor":
               return autor;
           case "titulo":
               return titulo;
           case "tema":
               return tema;
           default:
               return "";
       }
                        
        
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public int getPosicionador() {
        return posicionador;
    }

    public void setPosicionador(int posicionador) {
        this.posicionador = posicionador;
    }

    public ListaArrayDinamico getComentarios() {
        return comentarios;
    }

    public void setComentarios(ListaArrayDinamico comentarios) {
        this.comentarios = comentarios;
    }

 

}