package biblies;

public class ListaEnlazada {
    Nodo cabeza;
    Nodo cola;
    int tamano;
    public ListaEnlazada(){    
        cabeza=null;    
        cola=null;  
        tamano=0;
    } 
    
    
    public void add(Nodo nodo){
    
        tamano++;
         
         
         if(cabeza!=null){
                          

             cola.siguiente = nodo;
             nodo.anterior = cola;
             
             cola=nodo;
             
         }
         
         else
         
         {
             cola=nodo;
             cabeza=nodo;
         }
    }
    
   //metodo cuando se borra y se quiere recuperrar
    public void addIdOrdenado(Nodo nodo){
        Nodo temp = cabeza;
        for(int i=1;i<=tamano;i++){
            if(temp.textoActual.getCodigo()+1==nodo.textoActual.getCodigo()){
                nodo.siguiente=temp.siguiente;
                temp.siguiente=nodo; 
                break;
            }
            temp=temp.siguiente;
        }
        add(nodo);
    }
    
    public void add(ListaEnlazada lista){
        
        
         
         
         if(cabeza!=null){
             
             
             
             Nodo iterador = lista.cabeza;

        
        
            while (iterador !=null){


                if (!this.contains(iterador.textoActual) ){
                    
                    Nodo nuevo = new Nodo(iterador.textoActual);
                    this.add(nuevo);
                                        
                }


                    iterador = iterador.siguiente;

            }                          

                    
         }
         
         else
         
         {
             cabeza = lista.cabeza;
             cola = lista.cola;
             tamano = lista.tamano;
         }
    }
    
    public void addTextoOrdenado(Nodo nodo, String condicion){
        
        Texto libro = nodo.textoActual;
        
                
        tamano++;
         if(cabeza!=null){
             
             Nodo apuntador = cabeza;
                          
             if(libro.getCondicion(condicion).compareToIgnoreCase(apuntador.textoActual.getCondicion(condicion)) <= 0){
                 
                 cabeza = nodo;
                 nodo.siguiente = apuntador;
                 apuntador.anterior = nodo;
                 
             }
             else{       
                while( apuntador.siguiente !=null && libro.getCondicion(condicion).compareToIgnoreCase(apuntador.siguiente.textoActual.getCondicion(condicion)) > 0  ){

                    apuntador = apuntador.siguiente;

                }
                if(apuntador.siguiente != null){

                    apuntador.siguiente.anterior = nodo;
                    nodo.siguiente = apuntador.siguiente;
                    apuntador.siguiente = nodo;
                    nodo.anterior = apuntador;

                }
                else{

                    apuntador.siguiente = nodo;
                    nodo.anterior = apuntador;

                    cola=nodo;

                }
            }
         }

         else
         
         {
             add(nodo);
         }
         
        
        
    }
    
    public ListaEnlazada containsPalabra(String palabra){
        
        ListaEnlazada listam = new ListaEnlazada();
        
        
        
        Nodo iterador = this.cabeza;

        
        
        while (iterador != null){
            
            
            if (iterador.textoActual.getTitulo().toUpperCase().contains(palabra.toUpperCase()) || iterador.textoActual.getAutor().toUpperCase().contains(palabra.toUpperCase()) ){
                
                Nodo nuevo = new Nodo(iterador.textoActual);
                listam.add(nuevo);
                iterador = iterador.siguiente;
            }
            
            else
            {
                iterador = iterador.siguiente;
            }
            
            
            
        }
        
        
        return listam;
    }
    
    public boolean contains(Texto texto){
        
        Nodo iterador = cabeza;

        
        
        while (iterador !=null){
            
            
            if (iterador.textoActual.equals(texto) ){
                
                return true;
            }
            
            
                iterador = iterador.siguiente;
            
        }
        
        return false;
    }
    
    public boolean contains(int codigo){
        
        Nodo iterador = cabeza;

        
        
        while (iterador !=null){
            
            
            if (iterador.textoActual.getCodigo()==codigo ){
                
                return true;
            }
            
            
                iterador = iterador.siguiente;
            
        }
        
        return false;
    }
    
    public Texto getCodigo(int codigo){
        
        Nodo iterador = cabeza;

        
        
        while (iterador !=null){
            
            
            if (iterador.textoActual.getCodigo()==codigo ){
                
                return iterador.textoActual;
            }
            
            
                iterador = iterador.siguiente;
            
        }
        
        return null;
      
        
    }

    public int size(){
        return tamano;
    }
    
    public int remove (Texto texto){
        
        Nodo iterador = cabeza;
        int id=0;

        
        
        while (iterador !=null){
            
            
            if (iterador.textoActual.equals(texto) ){
                id=iterador.textoActual.getCodigo();
                iterador.anterior.siguiente = iterador.siguiente;
            }
            
            
                iterador = iterador.siguiente;
            
        }
        return id;
        
    }
    
    public void remove (int codigo){
        
        Nodo iterador = cabeza;

        if(iterador.textoActual.getCodigo()==codigo){
            
            cabeza = iterador.siguiente;
        }
        else{
        
        while (iterador !=null){
            
            
            if (iterador.textoActual.getCodigo() == codigo ){
                
                iterador.anterior.siguiente = iterador.siguiente;
                break;
            }
            
            
                iterador = iterador.siguiente;
            
        }
        
        }
    }
    
    public boolean empty(){
        return(cabeza==null && cola==null);
    }
    
    public ListaArrayDinamico toListaArrayDinamico(){
        
        ListaArrayDinamico lista = new ListaArrayDinamico(this.size());
        try{
        
        if(cabeza!=null){
             
             
             
             Nodo iterador = cabeza;
            int i=0;
            
            
           
                while (iterador != null) {
                    //System.out.print("["+i+"]"+iterador.textoActual.toString());

                    lista.add(iterador.textoActual);
                    
                    iterador = iterador.siguiente;
                    
                }
           
        

                    
         }
          } catch (Exception e) {
                System.out.print("ERRRRRRRRORR: "+e);
                
            }
                 
        return lista;
    }

    public void clean(){
        
    }
  
}