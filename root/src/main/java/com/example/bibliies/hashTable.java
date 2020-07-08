/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblies;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author Cesar Rincón
 */
public class hashTable {
    Admin op;
    private String condición;
    private int CAP;
    private int n_elementos;
    private boolean repetidos;
    private int real_array_size;
    private ListaEnlazada[] harray;
    /**factor de division: para salvar memoria se divide el tamaño del array, costando en el aumento de colisiones pero manteniendo tiempo constante**/
    private static final int factDivision=2;

    public hashTable(int size, String condicion) {
        this.CAP=size;
        this.condición=condicion;
        this.n_elementos=0;
        this.repetidos=false;
        op = new Admin();
        int div = (size>hashTable.factDivision*hashTable.factDivision)? hashTable.factDivision : 1;
        this.real_array_size= ((this.CAP)/div);
        this.real_array_size+= (this.real_array_size%2==0)?1:0;
        harray = new ListaEnlazada[this.real_array_size];
        for (int i=0;i<this.real_array_size;i++){
            this.harray[i]= new ListaEnlazada();
        }
    }
    public hashTable activarRepetidos(){
        this.repetidos=true;
        return this;
    }
    
     //insertar dato en el hashTable
    public void insert(Texto t){
        if (t==null)
            return;
        
        if(this.getLoadFactor()>1)
            reHash();
        int index = this.hashFunction(t);
        if(this.repetidos){
            this.harray[index].addTextoOrdenado(new Nodo(t), this.condición);
            this.n_elementos++;
        }
        else{
            if(!(this.harray[index].contains(t))){ //.contains--> O(n)
                this.harray[index].addTextoOrdenado(new Nodo(t), this.condición);
                this.n_elementos++;
            }
        }
        
    }
    public void reHash(){
        long size = (this.real_array_size*hashTable.factDivision)*2;
        int realSize=0;
        if(size>= Integer.MAX_VALUE-5)
            realSize=Integer.MAX_VALUE-5;
        else
            realSize=(int)size;
        
        this.CAP=realSize;
        this.real_array_size=CAP/hashTable.factDivision;
        ListaEnlazada[] l = new ListaEnlazada[this.real_array_size];
        int index=0;
        if(this.condición.equals("codigo"))
            for (int i = 0; i <this.real_array_size; i++) {
                l[i]= new ListaEnlazada();
                if(i<this.harray.length)
                    if(this.harray[i].cabeza!=null){
                    index=this.IntegerH(this.harray[i].cabeza.textoActual.getCodigo());
                    l[index]= this.harray[i];
                }
            }
        else
            for (int i = 0; i < this.real_array_size; i++) {
                 l[i]= new ListaEnlazada();
                 if(i<this.harray.length)
                     if(this.harray[i].cabeza!=null){
                    index=this.StringH(this.harray[i].cabeza.textoActual.getCondicion(this.condición));
                    l[index]= this.harray[i];
                }
            }
        
        this.harray=l;
    }
    
    public boolean contains(Object t){
        return (this.find(t)!=null);
    }
    //find driver
    public Texto find(Object data){
        if(data instanceof Texto){
            return find((Texto)data);
            
        }else{
            try{
            if(this.condición.equals("codigo"))
                return findCod((int)data);
            else{
                return findString((String)data);
            }
            }catch(Exception e){
                System.out.println(e);
                    //print "INGRESE UN PARÁMETRO VÁLIDO"
            }
              
        }
        return null;
    }
    public ListaArrayDinamico findlist(Object data){
        int index=0;
        
        try{
            if(this.condición.equals("codigo")){
                index= this.IntegerH((int)data);
                return this.harray[index].toListaArrayDinamico();
            }
            else{
                    index= this.StringH((String)data);
                    return this.harray[index].toListaArrayDinamico();
            }
            }catch(Exception e){
                System.out.println(e);
                    //print "INGRESE UN PARÁMETRO VÁLIDO"
            }
        
        return null;
    }
    private Texto find(Texto data){
        System.out.println("por texto: ");
        int index=0;
         try{
            if(this.condición.equals("codigo")){
                index= this.IntegerH(data.getCodigo());
                return this.harray[index].getCodigo(data.getCodigo());
            }
            else{
                    index= this.StringH(data.getCondicion(condición));
                    return this.harray[index].getTextoPorCondicion(data.getCondicion(condición), condición);
            }
            }catch(Exception e){
                System.out.println(e);
                    //print "INGRESE UN PARÁMETRO VÁLIDO"
            }
        
        return data;
    }
    
      private Texto findCod(int data) {
           System.out.println("por codigo: ");
           if(this.harray[data%this.real_array_size].tamano==1)
               return this.harray[data%this.real_array_size].cabeza.textoActual;
          return this.harray[data%this.real_array_size].getCodigo(data);
    }

    private Texto findString(String s) {
        int index = this.StringH(s);
         if(this.harray[index].tamano==1)
             return this.harray[index].cabeza.textoActual;
         return this.harray[index].getTextoPorCondicion(s, condición);
    }
    
    
    
    public void delete(Object t){
         int index = 0;
        if (t instanceof Texto) {
            Texto txt = (Texto) t;
            if (this.condición.equals("codigo"))
                index = this.IntegerH(txt.getCodigo());
            else
                index = this.StringH(txt.getCondicion(condición));
            
            if((this.harray[index].remove(txt))!=0)
                this.n_elementos--;
            return;
        }
        if(t instanceof Integer){
            index=this.IntegerH((int)t);
            if((this.harray[index].remove((int)t)))
                this.n_elementos--;
        }
        else{
            index=this.StringH((String)t);
            if((this.harray[index].remove(this.findString((String)t)))!=0)
                this.n_elementos--;
        }
    }
    
    private int hashFunction(Texto t){
        //TODO hash funcion
        int hash=0;
        if(this.condición.equals("codigo")){
            hash=IntegerH(t.getCodigo());
        }else {
            hash=StringH(t.getCondicion(condición));
        }
     return hash;
    }
    /**Imprime todos los elementos que están en el HashTable**/
    public void printHash(){
        for(ListaEnlazada l:this.harray)
            System.out.println("==================================================================\nTamaño de la lista: "+l.tamano+"\n"+l.toString());
        
        System.out.println("===========================================\nEn este hash hay "+this.real_array_size+" listas");
    }
    
    private int StringH(String s){
        
        
        
        
        int sum=0;
        int hash = 31;
        for (int i = 0; i < s.length(); i++)
            sum=(sum*hash)+s.charAt(i);
        
        
        
        /* //FALLÓ
            char ch[];
            ch = s.toCharArray();
            int xlength = s.length();
            int i, sum;
            for (sum = 0, i = 0; i < s.length(); i++) {
                sum += ch[i];
            }
            
            */
            
            

     /*int intLength = s.length() / 4; //FALLÓ
     long sum = 0;
     for (int j = 0; j < intLength; j++) {
       char c[] = s.substring(j * 4, (j * 4) + 4).toCharArray();
       long mult = 1;
       for (int k = 0; k < c.length; k++) {
	 sum += c[k] * mult;
	 mult *= 256;
       }
     }
     char c[] = s.substring(intLength * 4).toCharArray();
     long mult = 1;
     for (int k = 0; k < c.length; k++) {
       sum += c[k] * mult;
       mult *= 256;
     }

*/
    return (Math.abs(sum) % this.real_array_size);
    }
    
    private int IntegerH(int i){
        return i%this.real_array_size;
    }
    
    //Getter - Setters

    public String getCondición() {
        return condición;
    }

    public int getCAP() {
        return CAP;
    }

    public int getN_elementos() {
        return n_elementos;
    }

    public ListaEnlazada[] getHarray() {
        return harray;
    }
    
    public float getLoadFactor(){
        return this.n_elementos/(this.real_array_size*hashTable.factDivision);
    }
    
    

    public void setCondición(String condición) {
        if(this.condición.isEmpty())
            this.condición = condición;
    }

    public static void main(String[] args) { //PRUEBAS 
        
        Scanner s = new Scanner(System.in);
        
        int size = 1000000; // tamaño
        
        
        hashTable hash = new hashTable(1000000, "titulo");
         System.out.println("TAMAÑO DEL HASHTABLE: "+size);
        
        //int valorEntero = (int)Math.floor(Math.random()*(500000+1)); numero aleatorio
        Texto t;
        long inicio = System.nanoTime();
        Random r=new Random();
        for (int i=1;  i<=size;i++){
            t=new Texto(i, "libro numero "+(char)(r.nextInt(122)+65), "autor num "+i, "fecha num "+i, i, "idioma numero "+i, "tema numero  "+i);
            hash.insert(t);
            if(i%1000 ==0)
                System.out.println(i+" textos ingresados");
                
            //System.out.println("libro numero "+i);
        }
        t=null;
        System.out.println("tiempo en mili: "+(System.nanoTime()-inicio)/1000000+"\nBUSQUEDA POR HASH\nIngrese el titulo del texto a buscar: \n");
        
        System.out.print("titulo: ");
        String titulo = s.nextLine();
       // int input = s.nextInt();
        
        inicio = System.nanoTime();
        t=hash.find(titulo);
        
        if(t!=null)
            System.out.println("Texto encontrado"+t.toString()+"\n tiempo en nano: "+(System.nanoTime()-inicio));
        else
            System.out.println("no encontrado");
        //hash.printHash();
         System.out.println("numero de elementos: "+hash.getN_elementos());
         
         System.out.println();
         System.out.println("numero de elementos de cada ListaEnlazada: ");
         ListaEnlazada[] lista= hash.getHarray();
         int i=0;
         for (ListaEnlazada li: lista){
             System.out.println("lista numero: "+i+ "|| elementos: "+(li.size())+"\n\n"+li.toString()+"\n====================================\n");
             i++;
         }
             
         
     
    }
  
    
}
