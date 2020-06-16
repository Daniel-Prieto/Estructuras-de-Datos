package biblies;

public class Cola {

    private ListaEnlazada lista = new ListaEnlazada();

    public Cola() {
        this.lista = new ListaEnlazada();
    }

    public void setLista(ListaEnlazada lista) {
        this.lista = lista;
    }

    public void enColar(Texto texto) {
        Nodo nodo = new Nodo(texto);
        this.lista.add(nodo);

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
    @Override
    public String toString(){
       return lista.toListaArrayDinamico().toString();
    }

}
