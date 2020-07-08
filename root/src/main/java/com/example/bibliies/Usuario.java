/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bibliies;

/**
 *
 * @author deste
 */

import java.util.*;
import java.io.*;
import java.util.logging.Logger;

public class Usuario{
    
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private String clave;

    @Override
    public String toString() {
        return "Usuario" +
                "\n ID: " + id +
                "\n Nombres: " + nombre + 
                "\n Apellidos: " + apellido +
                "\n Correo: " + correo + 
                "\n Clave: *********";
    }

    public Usuario(int id, String nombre, String apellido, String correo, String clave) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.clave = clave;
    }

    
    
    public Usuario(){
     
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
     //BUSCA POR EL TITULO EXACTO DEL LIBRO
    public Texto buscarPorTituloExactoQuickSearch(String nombre , ListaArrayDinamico lista){
        
        
        Texto [] arreglo = lista.getArreglo();
        Texto retorno = null;
        int max = lista.getUltimo();
        int min = 0;
        int posicion;
        int comparador = -1;
        
  
        while  (comparador != 0 && min!=max-1){
            
                posicion = (max+min)/2;
            
        
                comparador = arreglo[posicion].compararSegunCondicion(nombre,"titulo");
        
        
                switch (comparador){

                    case 1:


                        max = posicion;


                        break;
                    case 0:

                        retorno = arreglo[posicion];
                        break;

                    case -1:

                        min = posicion;

                        break;

                }
            
            }
        
         if (retorno == null)
            System.out.println("No hay ningun libro con ese Titulo");
   
        return retorno;
                   
    }
    
     //BUSCA POR EL CODIGO EXACTO DEL LIBRO
    public Texto buscarPorCodigoQuickSearch(int codigo , ListaArrayDinamico lista){
        
        
        Texto [] arreglo = lista.getArreglo();
        Texto retorno = null;
        int max = lista.getUltimo();
        int min = 0;
        int posicion;
        int comparador = -1;
        
  
        while  (comparador != 0 && min!=max-1 && codigo<= lista.getUltimo()){
            
                posicion = (max+min)/2;
            
        
                comparador = arreglo[posicion].compararSegunCondicion(codigo);
                
        
                switch (comparador){

                    case 1:


                        max = posicion;


                        break;
                    case 0:

                        retorno = arreglo[posicion];
                        break;

                    case -1:

                        min = posicion;

                        break;

                }
            
            }
        
        if (retorno == null)
            System.out.println("No hay ningun libro con ese codigo");
   
        return retorno;
                   
    }
    
    //BUSCA POR EL NOMBRE EXACTO DEL AUTOR
    public ListaArrayDinamico buscarPorAutorExactoQuickSearch(String autor , ListaArrayDinamico lista){
        
        
        Texto [] arreglo = lista.getArreglo();
        ListaArrayDinamico retorno= new ListaArrayDinamico();
        int max = lista.getUltimo();
        int min = 0;
        int posicion=0;
        int comparador = -1;
        
  
        while  (comparador != 0 && min!=max-1 ){
            
                posicion = (max+min)/2;
            
        
                comparador = arreglo[posicion].compararSegunCondicion(autor,"autor");
        
        
                switch (comparador){

                    case 1:


                        max = posicion;


                        break;
                    case 0:
                        
                                                
                        
                        break;

                    case -1:

                        min = posicion;

                        break;

                }
            
            }
        
       
        while (arreglo[posicion-1].getAutor().toUpperCase().matches(autor.toUpperCase()))
        {
            posicion--;
        }
        
        
        

        while(arreglo[posicion].getAutor().toUpperCase().matches(autor.toUpperCase())){
            
            
            retorno.add(arreglo[posicion]);

            
            posicion++;

        }
        
         if (retorno.getUltimo()==0)
            System.out.println("No hay ningun libro con ese Autor");
       
   
        return retorno;
                   
    }
    
    //BUSCA POR EL NOMBRE EXACTO DEL AUTOR
    public ListaArrayDinamico buscarEmpiezaPorQuickSearch(String titulo , ListaArrayDinamico lista){
        
        
        Texto [] arreglo = lista.getArreglo();
        ListaArrayDinamico retorno= new ListaArrayDinamico();
        int max = lista.getUltimo();
        int min = 0;
        int posicion=1;
        int comparador = -1;
       
            
            while (comparador != 0 && min != max - 1) {
                
                posicion = (max + min) / 2;
                
                comparador = arreglo[posicion].compararSegunCondicion(titulo, "titulo");
                
                switch (comparador) {
                    
                    case 1:
                        
                        max = posicion;
                        
                        break;
                    case 0:
                        
                        ///System.out.println("[" + arreglo[posicion].getCodigo() + "] " + arreglo[posicion].getTitulo() + "---titulo exacto---" + titulo);
                        break;
                    
                    case -1:
                        
                        min = posicion;
                        
                        break;
                    
                }
                
            }
           // System.out.println("arreglo.lenght--> "+arreglo.length+"\nposicion: "+posicion);
        
        while (arreglo[posicion-1].getTitulo().toUpperCase().replaceAll("\\s","").startsWith(titulo.toUpperCase().replaceAll("\\s","")))
        {
            posicion--;
        }
        
        
                    //System.out.println(arreglo[posicion].getTitulo()+"- El Anterior--"+titulo);


        while(arreglo[posicion].getTitulo().toUpperCase().replaceAll("\\s","").startsWith(titulo.toUpperCase().replaceAll("\\s",""))){
            
            //System.out.println(arreglo[posicion].getTitulo()+"---"+titulo);
            retorno.add(arreglo[posicion]);

            
            posicion++;

        }
        
         if (retorno.getUltimo()==0){
            ///System.out.println("No hay ningun libro que comience por las palabras: "+ titulo);
            return null;
         }
   
        return retorno;
                   
    }
    
      //BUSCA POR EL NOMBRE DEL AUTOR
    public void buscarPorAutor(String autor,ListaEnlazada lista){
        String resultadoBusqueda=null;
        Nodo recorredor=lista.cabeza;
        while(recorredor!=null){
            Texto texto=recorredor.textoActual;
           if(texto.getAutor().contains(autor)){               
               resultadoBusqueda=texto.getCodigo()+" "+texto.getTitulo()+" ";//faltaria el resto
               System.out.println(resultadoBusqueda);
           }
         recorredor=recorredor.siguiente;
        }
        if(resultadoBusqueda==null){
           resultadoBusqueda="NINGUN AUTOR TIENE ESTE NOMBRE";
           System.out.println(resultadoBusqueda);
        }          
    }

    //BUSCAR POR CODIGO BUSQUEDA BINARIA
    public Texto buscarPorCodigoAVL(int codigo, arbolAVL acodigo){
        Texto t= acodigo.find(codigo);
        if(t==null){
            System.out.println("\n//Texto no encontrado//");
        }
        return t;
    }

    //BUSCAR POR TITULO BUSQUEDA BINARIA

    public Texto buscarPorTituloAVL(String titulo, arbolAVL atitulo){
        Texto t= atitulo.find(titulo);
        if(t==null){
            System.out.println("\n//Texto no encontrado//");
        }
        return t;
    }
    //BUSCAR POR AUTOR BUSQUEDA BINARIA

    public Texto buscarPorAutorAVL(String autor, arbolAVL atitulo){
        Texto t= atitulo.find(autor);
        if(t==null){
            System.out.println("\n//Texto no encontrado//");
        }
        return t;
    }









    
   //IMPRIMER LOS TEXTOS
    public void imprimirTextos(ListaEnlazada lista){
        
          String imprimir;
          Nodo recorredor=lista.cabeza;
          while(recorredor!=null){
            Texto texto=recorredor.textoActual;
            imprimir=texto.getCodigo()+") Titulo: "+texto.getTitulo()+"\n   Autor: "+texto.getAutor();
            System.out.println(imprimir);
            recorredor=recorredor.siguiente;
            
          }

    }
    
   



                     
    }

