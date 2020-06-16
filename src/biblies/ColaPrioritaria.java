

package biblies;

public class ColaPrioritaria {
    
    private ListaEnlazada lista = new ListaEnlazada();

    public void setLista(ListaEnlazada lista) {
        this.lista = lista;
    }

    public void enColar(Texto texto, int level) {

        Nodo nodo = new Nodo(texto);
        nodo.prioridad = level;

        if (lista.empty())
            lista.add(nodo);
        
        else if(lista.cola.prioridad >= nodo.prioridad)
            lista.add(nodo);
        else if (lista.cabeza.prioridad < nodo.prioridad){
            
            lista.cabeza.anterior = nodo;
            nodo.siguiente = lista.cabeza;

            lista.cabeza = nodo;
            lista.tamano++;
        }
        

        else {
            Nodo temp = lista.cabeza;

            while (nodo.prioridad<=temp.prioridad && temp != lista.cola){
                temp = temp.siguiente;
            }
                       
            nodo.anterior = temp.anterior;
            temp.anterior = nodo;
            nodo.siguiente = temp; 
            nodo.anterior.siguiente = nodo;
        }     

    }

    public Nodo desEncolar() {

        Nodo nodo = lista.cabeza;

        lista.cabeza = lista.cabeza.siguiente;
        lista.cabeza.anterior = null;

        return nodo;
    }

    public Nodo miraVeTu(){

        Nodo nodo = lista.cabeza;

        return nodo;


    }

    public int getTamano() {

        return lista.tamano;
    }



}       