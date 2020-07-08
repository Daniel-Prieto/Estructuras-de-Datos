/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bibliies;

/**
 *
 * @author Cesar Rincón
 */
public class arbolAVL {
    private static Admin anonimus=new Admin(); //se implementan metodos de la clase admin, se necesitaban a fin de cuentas/

    nodoBinario root;
    private boolean repetir;
     String condicion;
    private int size, n; //EL n, cuando es 1 permite que el nodo borre sus repetidos,o que tambien evite hacer balanceo si solo se insertó un repetido.
    //Despues de esto se vuelve cero, asi que solo se ejecuta 1 vez dentro de
    // la funcion recursiva deleteNode();

    public arbolAVL() {
        root = null;
        repetir = false;
        size = 0;
        n = 1;
    }

    public arbolAVL(String c) {
        this();
        this.condicion = c;
    }

    public void activarRepetidos() {
        this.repetir = true;
    }

    public int getSize() {
        return size;
    }
    
    public int getSize(nodoBinario root){
        if(root==null)
            return 0;
            int rep=0;
        if(this.repetir && root.repetidos!=null){
            rep=root.n_rep;


            }
        return (1+rep) + getSize(root.left) + getSize(root.right);
    }

    //Clase nodoBinario
    public class nodoBinario {

        public Texto data;
        private int ht;
        private nodoBinario left;
        private nodoBinario right;
        private miniNodo repetidos;
        int n_rep;
        public String prueba = "prueba";

        nodoBinario(Texto t) {
            data = t;
            ht = 1;
            n_rep = 0;
        }

        void setRepetido(Texto r) {

            miniNodo n = new miniNodo(r);
            if (repetidos == null) {
                repetidos = new miniNodo(r);
            } else {
                n.next = this.repetidos;
                repetidos = n;
            }
            n_rep++;
        }
        Texto[] getRepetidos(){
            Texto[] txts;
            if(repetidos!=null){
                txts = new Texto[n_rep];
                miniNodo hd= repetidos;
                for(int i=0; i<n_rep;i++){
                    txts[i]=repetidos.data;
                    repetidos=repetidos.next;
                }
                this.repetidos=hd;
                return txts;
            }
            return null;
        }

        String printRepetidos() {
            String s="";
            s+="\n"+data.getTitulo() + "Tiene "+this.n_rep+" repetidos: ";
            s+="\n ===========================REPETIDOS========================";
            repetidos.print(repetidos,s);
            s+="\n===========================================================";
            return s;

        }
        
       /* Texto compareRepetidos(Object d){
            int comp;
            String comp1;
            if(condicion.equals("codigo") || condicion.equals("") ){
                comp= (int) d;
                while()
            }else{
                comp1=(String)d;
            }
            
            
            
        }*/
    }

    //Clase mini-nodoBinario, una clase nodo minimalista que consume menos espacio y que sirve para almacenar repetidos
    private class miniNodo { //ESTE miniNodo es una mini lista enlazada que contiene los repetidos;

        Texto data;
        miniNodo next;

        miniNodo(Texto d) {
            data = d;
        }

        String print(miniNodo n,String s) {
            if (data != null) {
                counter++;
                s+="\n["+counter+"]"+data.toString();
            }
            if (n.next != null) {
                s+=print(n.next,s);
            }
            return s;
        }
    }

    private int max(int a, int b) {
        return (a > b)?a:b;
    }

    //=========================================================================================================================================
    private nodoBinario getMin(nodoBinario root) {
        if (root != null) {
            while (root.left != null) {
                root = root.left;
            }
        }
        return root;
    }

    private nodoBinario getMax(nodoBinario root) {
        if (root != null) {
            while (root.right != null) {
                root = root.right;
            }
        }
        return root;
    }

    private boolean isLeaf(nodoBinario n) {
        return (n.left == null && n.right == null);
    }

    //=========================================================================================================================================
    // Insertar Texto
    public void insert(Texto data) {
        if (data != null) {

            size++;

            this.root = insert(root, data);

        }
    }
    private nodoBinario insert(nodoBinario root, Texto data) {

        // INSERCCION //
        if (root == null) {

            return new nodoBinario(data);
        } else {
            int comparador;
            if (condicion.equals("codigo") || condicion.equals("")) { //si se comparan numeros se restan los numeros
                comparador = Integer.parseInt(data.getCondicion(condicion)) - Integer.parseInt(root.data.getCondicion(condicion));
            } else { //si se comparan caracteres se usa el compareTo
                comparador = data.compararSegunCondicion(root.data.getCondicion(condicion), condicion); //COMPARADOR APLICADO 
            }
            if (comparador < 0) {// se incluye repetidos
                root.left = insert(root.left, data);

            } else if (comparador > 0) {
                root.right = insert(root.right, data);

            } else {
                if (this.repetir) { //ESTA FUNCION BOOLEANA PERMITE/BLOQUEA REPETIDOS
                    //System.out.println("..aqui va un repetido..");
                    root.setRepetido(data);

                    this.n = 2;
                }// NOTA: SI SE INSERTA UN REPETIDO, NO HAY NECESIDAD DE BALANCEAR
            }

        }
        root.ht = max(getHeight(root.left), getHeight(root.right)) + 1;
        // BALANCEO //
        if (this.n != 2) {
            switch (getBalanceFactor(root)) {
                case 2:
                    switch (getBalanceFactor(root.left)) {
                        case 0:
                        case 1:
                            return balanceLL(root);
                        case -1:
                            return balanceLR(root);
                    }
                case -2:
                    switch (getBalanceFactor(root.right)) {
                        case 0:
                        case -1:
                            return balanceRR(root);
                        case 1:
                            return balanceRL(root);
                    }
            }
        } else {
            this.n = 1;
        }

        return root;
    }
    //=========================================================================================================================================
    //Borrar en el arbol el Texto ingresado
    public void remove(Texto data) {
        if (root != null) {
            deleteNode(root, data);
        }

    }
    private nodoBinario deleteNode(nodoBinario root, Texto data) { //NOTA: ya se sabe que el this.root no es null
        int comparador;
        // SE BUSCA EL NODO

        if (root == null) { //SI ES NULO, ENTONCES NO EXISTE EN EL ARBOL. NOTA: Para llegar hasta aqui minimo tuvo que haber pasado una recursion
            return root;
        } else {

            if (condicion.equals("codigo") || condicion.equals("")) //si se comparan numeros se restan los numeros
            {
                comparador = Integer.parseInt(data.getCondicion(condicion)) - Integer.parseInt(root.data.getCondicion(condicion));
            } else //si se comparan caracteres se usa el compareTo
            {
                comparador = data.compararSegunCondicion(root.data.getCondicion(condicion), condicion); //COMPARADOR APLICADO 
            }
            //comparador=data-root.data;
            if (comparador < 0) {
                root.left = deleteNode(root.left, data);
            } else if (comparador > 0) {
                root.right = deleteNode(root.right, data);
            } else // AQUI SE ENCONTRÓ EL DATO, SE PROCEDE A ELIMINAR
            {
                System.out.print("Se eliminó el texto: \n"+root.data);
                
                if (this.repetir && this.n == 1) { //AQUI SE ELIMINAN LOS REPETIDOS SI SE ACTIVO LA OPCION DE PERMITIR REPETIDOS
                    this.size -= root.n_rep - 1;
                    root.repetidos = null;
                    System.out.print("\n CON REPETIDOS INCLUIDOS");
                    this.n = 0;
                }
                System.out.println();
                if (root.left == null || root.right == null) {
                    if(root.left==null && root.right==null)
                        root=null;
                    else if (root.left == null) //SI EL NODO SOLO TIENE UN HIJO
                        root = root.right;
                    else
                        root = root.left;
                   
                } else { // SI TIENE DOS HIJOS, SE BUSCA EL MAYOR DE LOS MENORES O EL MENOR DE LOS MAYORES 

                    nodoBinario temp = getMin(root.right);  //menor de los mayores en este caso, llamemosle dato X

                    root.data = temp.data;  //copy-paste

                    root.right = deleteNode(root.right, temp.data);  //borramos el nodo que contenia el dato X
                }
            }
            
            
        }

        if (root == null) //EN EL CASO DE QUE NO SE HAYA ENCONTRADO EL DATO Y SE HAYA RETORNADO NULL.... apague y vamonos
        {
            return root;
        }

        // BALANCEO
        root.ht = max(getHeight(root.left), getHeight(root.right)) + 1;

        if (this.n != 2) {
            switch (getBalanceFactor(root)) {
                case 2:
                    switch (getBalanceFactor(root.left)) {
                        case 0:
                        case 1:
                            return balanceLL(root);
                        case -1:
                            return balanceLR(root);
                    }
                case -2:
                    switch (getBalanceFactor(root.right)) {
                        case 0:
                        case -1:
                            return balanceRR(root);
                        case 1:
                            return balanceRL(root);
                    }
            }
        } else {
            this.n = 1;
        }
        this.n = 1;
        this.size--;
        return root;
    }


    //Buscar si comienza por X palabra(s)
    public ListaArrayDinamico findStartsWith(String palabra){
        palabra=palabra.toUpperCase().replaceAll("\\s","");
        ListaArrayDinamico l= new ListaArrayDinamico(1);
        Texto[] t=startsWith(this.root,palabra);
        if(t==null){
            System.out.println("startsWith(this.root,palabra) ES NULOOOO NUL NULL");
            return null;
        }
            
        l.setArreglo(t);
        
        return anonimus.buscarEmpiezaPorQuickSearch(palabra, l);
    }
    private Texto[] startsWith(nodoBinario root, String palabra){
        if(root!=null){
         
        String rootPalabra= root.data.getCondicion(this.condicion).toUpperCase().replaceAll("\\s","");
        
        if(rootPalabra.startsWith(palabra)){
            System.out.println("Se encontró STARTS WHITS=========================================================");
            int size= getSize(root);
            System.out.println("STARWITHS-->\n\n TAMAÑO DE ROOT: "+size);
            return  toArray(root, new Texto[size]);
        }else{
            int comparador = palabra.compareTo(rootPalabra);

            if (comparador < 0) {
                return startsWith(root.left, palabra);
            } else
                return startsWith(root.right, palabra);
            
        }
    }
    return null;
}

    //Buscar si contiene X palabra
    public ListaArrayDinamico findContains(String palabra){
        
        
        
        return findContains(this.root, palabra.toUpperCase());
    }
    private ListaArrayDinamico findContains(nodoBinario root, String palabra) {
        if (root != null) {
            String rootPalabra = root.data.getCondicion(this.condicion).toUpperCase();
            if (rootPalabra.contains(palabra)) {
                System.out.println("Se encontró CONTAINS");
                return toListaEnlazada(root, new ListaEnlazada()).containsPalabra(palabra).toListaArrayDinamico();
            } else {
                int comparador = palabra.compareTo(rootPalabra);

                if (comparador < 0) {
                    return findContains(root.left, palabra);
                } else {
                    return findContains(root.right, palabra);
                }

            }
        }
        return null;
    }
    
    
    //FIND PRINCIPAL, filtra el objeto ingresado, si es un Texto se busca por Texto, si es Integer se busca por numero, si es String se busca por String (titulo o autor)/
    public Texto find(Object data) {
     
        if (data != null) {
            nodoBinario n;
            try { // intentemos si es un texto
                n=find(this.root, (Texto) data);
            } catch (Exception e) { 
                //try {// intentemos si es un String
                    if (this.condicion.equals("codigo") || this.condicion.equals("")) {
                        
                        n=find(this.root, (int) data);
                        
                    } else {
                        n=find(this.root, (String) data);
                        
                    }

                //} catch (Exception f) {
                 //   System.out.println("\nIngrese un dato valido para usar en el find(data)\n");
               // }
            }
            if(n==null)
                return null;
            else{
                if(n.repetidos!=null && this.repetir)
                    System.out.println("TIENE "+n.n_rep+" REPETIDOS");
                return n.data;
            }
                
        }
        return null;
    }
    
     //busqueda binaria usando Objetos (Textos)
    private nodoBinario find(nodoBinario root, Texto data) {
        if (root != null) {
            int comparador = data.compararSegunCondicion(data, this.condicion);
            if (comparador < 0) {
                return find(root.left, data);
            } else if (comparador > 0) {
                return find(root.right, data);
            } else {
                return root; //se encontró
            }
        } else {
            return null; //no se encontró
        }
    }

     // Busqueda binaria usando Strings
    private nodoBinario find(nodoBinario root, String palabracontenedora) {
        if (root != null) {
            int comparador = palabracontenedora.compareToIgnoreCase(root.data.getCondicion(this.condicion));

            if (comparador < 0) {
                return find(root.left, palabracontenedora);
            } else if (comparador > 0) {
                return find(root.right, palabracontenedora);
            } else {
                return root; //se encontró
            }
        } else {
            return null; //no se encontró
        }
    }

    //Busqueda binaria usando numeros
    private nodoBinario find(nodoBinario root, int numero) { 
        if (root != null) {
            int comparador = root.data.compararSegunCondicion(numero); // se compara al revez: root.data >< data ?
            if (comparador > 0) {
                return find(root.left, numero);
            } else if (comparador < 0) {
                return find(root.right, numero);
            } else {
                return root; //se encontró
            }
        } else {
            return null; //no se encontró
        }
    }

    
    //Balance casos
    private nodoBinario balanceLL(nodoBinario head) {
        nodoBinario left = head.left;
        nodoBinario subLeft = left.right;
        left.right = head;
        left.right.left = subLeft;
        head.ht -= 2;
        return left;
    }
    private nodoBinario balanceRR(nodoBinario head) {
        nodoBinario right = head.right;
        nodoBinario subRight = right.left;
        right.left = head;
        right.left.right = subRight;
        head.ht -= 2;
        return right;
    }
    private nodoBinario balanceLR(nodoBinario head) {
        nodoBinario left = head.left;
        nodoBinario subLeft = left.right.left;
        head.left = left.right;
        head.left.left = left;
        head.left.left.right = subLeft;
        head.left.ht++;
        head.left.left.ht--;
        return balanceLL(head);
    }
    private nodoBinario balanceRL(nodoBinario head) {
        nodoBinario right = head.right;
        nodoBinario subRight = right.left.right;
        head.right = right.left;
        head.right.right = right;
        head.right.right.left = subRight;
        head.right.ht++;
        head.right.right.ht--;
        return balanceRR(head);
    }

    
    
    private int getBalanceFactor(nodoBinario node) {
        if (node != null) {
            return getHeight(node.left) - getHeight(node.right);
        }
        return 0;
    }

    private int getHeight(nodoBinario node) {
        if (node == null) {
            return -1;
        }
        return node.ht;
    }
    
    public void inOrder() {
        if (this.root != null) {
            System.out.print("IndOrder");
            inOrder(this.root);
        }
        System.out.println();
    }

    public void inOrder(nodoBinario root) {
        if (root.left != null) {
            inOrder(root.left);
        }
        counter++;
        System.out.print("["+counter+"]"+root.data.toString());
        if (repetir && root.repetidos != null) {
            
            root.printRepetidos();
        }
        System.out.println();
        if (root.right != null) {
            inOrder(root.right);
        }
    }


    //=========================================================================================================================================
    //==========================================// MÉTODOS NUEVOS
    public Texto[] toArray() {
        arbolAVL.counter=0;
        if (this.root == null) {
            return null;
        }
        Texto[] array = new Texto[this.size];
        array= toArray(this.root, array);
        return array;
    }
    public static int counter = 0;

    public Texto[] toArray(nodoBinario root, Texto[] array) {
        
        //System.out.println("COUNTER: "+counter);
        if (counter < this.size) {
            if (root.left != null) {
                array = toArray(root.left, array);
            }
            try{
            if(this.repetir && root.repetidos!=null){  //si hay repetidos
            Texto[] rep = root.getRepetidos();
            for(Texto t: rep){
            array[counter]=t;
            counter++;
            }
            }
            }catch(Exception e){
                System.out.println("\n\n\n\nERROR \nCOUNTER: "+counter+"\narray.lenght--> "+array.length+"\n\n"+e);
            }
            array[counter]=root.data;
            counter++;
            if (root.right != null) {
                array = toArray(root.right, array);
            }
            return array;
        }
        counter=0;
        return array;
    }
    
    public ListaArrayDinamico toListaArrayDinamico(){
        Texto[] txt =this.toArray();
        ListaArrayDinamico lista =  new ListaArrayDinamico(txt.length);
        lista.setArreglo(txt);
        return lista;
    }
    public ListaEnlazada toListaEnlazada(){
    return toListaEnlazada(this.root, new ListaEnlazada());
    }
    private ListaEnlazada toListaEnlazada(nodoBinario root, ListaEnlazada l){
        if(root.left!=null)
            toListaEnlazada(root.left, l);
        l.add(new Nodo(root.data));
        if(root.right!=null)
            toListaEnlazada(root.right, l);
        
        return l;
    }
    
    public void FORMATNodos(){
        this.root=null;
        this.size=0;
    }
    
    

 

}
