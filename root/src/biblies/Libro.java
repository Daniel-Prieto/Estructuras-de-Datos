/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblies;

/**
 *
 * @author deste
 */
public class Libro extends Texto{
    String editorial;
    int edicion;
    double dificultad;
    
    public Libro(){  
    }

    public Libro(String editorial,int edicion, double dificultad){
        this.editorial=editorial;
        this.edicion=edicion;
        this.dificultad=dificultad;
    }


    public String getEditorial() {
        return editorial;
    }

    public int getEdicion() {
        return edicion;
    }

    public double getDificultad() {
        return dificultad;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    public void setDificultad(double dificultad) {
        this.dificultad = dificultad;
    }
    
}
