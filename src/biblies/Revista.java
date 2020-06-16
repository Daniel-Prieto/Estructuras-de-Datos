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
public class Revista extends Texto{
    int numero;
    String serie;
    
    public Revista(){
    }
    
    public Revista(int numero,String serie){
        this.numero=numero;
        this.serie=serie;

    }

    public int getNumero() {
        return numero;
    }

    public String getSerie() {
        return serie;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }
    
}
