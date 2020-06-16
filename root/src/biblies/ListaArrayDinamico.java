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
public class ListaArrayDinamico {
    
    private int capacidad;
    private int ultimo=0;
    private int tamano;
    private Texto[] arreglo ;
    private String[] dato;

    public ListaArrayDinamico() {
        capacidad = 10;
        ultimo = 0;
        arreglo = new Texto[10];
        dato = new String[10];
    }
    public ListaArrayDinamico(int size) {
        capacidad = size;
        ultimo = 0;
        arreglo = new Texto[size];
        dato = new String[size];
    }
    
    
    public int getIndex(Texto texto){
        int retorno =0;
        
        
        
        
        
        
        return retorno;
        
    }
    
    public boolean isEmpty(){
        
        if(ultimo==0)
            return true;
        else 
            return false;
    }
    public void add(Texto objeto){
        
        if (ultimo == capacidad){
            
            Texto arreglo2[]= new Texto[capacidad*2];
            
            for (int i = 0;i<capacidad;i++){
                
                arreglo2[i]= arreglo[i];
                
            }
            capacidad = capacidad*2;
                
            arreglo = arreglo2;
            
        }
        
        arreglo[ultimo] = objeto;
        ultimo++;
                
        
    }
    public void add(String objeto){
        
        if (ultimo == capacidad){
            
            String arreglo2[]= new String[capacidad*2];
            
            for (int i = 0;i<capacidad;i++){
                
                arreglo2[i]= dato[i];
                
            }
            capacidad = capacidad*2;
                
            dato = arreglo2;
            
        }
        
        dato[ultimo] = objeto;
        ultimo++;
                
        
    }
    
    
    public void addEnLugar(Texto objeto, int lugar){
        
        if (lugar<=ultimo)
        
        arreglo[lugar] = objeto;
        
        
    }
    
    public Texto getUltimoTexto(){
        
        
        return arreglo[ultimo];
        
        
    }
    
    
    
    public Texto get (int lugar){
        
        if (lugar<=ultimo){
            
            return arreglo[lugar];
            
        }
        return null;
    }
   
    public void removeUltimoTexto(){
        
        ultimo--;
        
    }
    
    
    
      
    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

 
    public Texto[] getArreglo() {
        return arreglo;
    }
    
    public Texto[] getArregloPURO(){
        Texto[] txt = new Texto[this.tamano];
        for (int i = 0; i < this.tamano; i++) {
            if(this.arreglo[i]!=null)
                txt[i]=this.arreglo[i];
        }
        
        return txt;
    }

    public void setArreglo(Texto[] object){
        System.out.println("TAMAÑO DE TEXTO[] --> "+ object.length);
        
        this.arreglo=object;
        this.tamano=0;//======= Se ajusta el arreglo al array
        //de tal manera que se ajusta el size si hay elementos null, o sea {t1,t2,t3,t4,null,null,null} --> Size: 4
        for(int i=0; i<object.length;i++){
            //System.out.println("this.tamano --> "+this.tamano);
            if(this.arreglo[this.tamano]==null){
                i=object.length+999;
                break;
            }
            else
                this.tamano++;
        }
        this.ultimo=tamano;
        this.capacidad=object.length;
    }

         @Override
    
     public String toString(){
        
        
          String imprimir= "";
          
          for(int i = 0; i < ultimo;i++){
            
            imprimir += arreglo[i].toString()+"\n";
           
          }
          
          return imprimir;

    }

    public int getUltimo() {
        return ultimo;
    }

    public void setUltimo(int ultimo) {
        this.ultimo = ultimo;
    }

    public ListaEnlazada toListaEnlazada(){
        ListaEnlazada lista=new ListaEnlazada();
        
        for(int i=0;i<=tamano;i++){
            if(arreglo[i]!=null){
            Nodo nuevoNodo=new Nodo(arreglo[i]);
            lista.add(nuevoNodo);
            }
        }
        return lista;

    }  

    public String[] getDato() {
        return dato;
    }

    public void setDato(String[] dato) {
        this.dato = dato;
    }
    
    public String getDatoString(){
        String retorno = "";
        
        for (int i = 0; i<ultimo;i++){
            retorno+= dato[i];
        }
        
        return retorno;
    }
    public int getSize(){
        return ultimo;
    }
   
    
}
