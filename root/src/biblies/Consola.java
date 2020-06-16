/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author
 */
public class Consola {

    String medida="milisegundos";
long div=  /*MEDIDA-->*/ 1000000;

    Usuario[] Admins = new Usuario[6];
    Usuario[] Clientes = new Usuario[21];
    private int numero;
    private String S, error, textos[];
    Admin defaultAdmin;
    Cliente defaultCliente;
    // private ListaEnlazada LTextos;
    private ListaEnlazada listacodigo;
    private ListaEnlazada listatitulo;
    private ListaEnlazada listaautor;
    private ListaArrayDinamico listacodigoarray;
    private ListaArrayDinamico listatituloarray;
    private ListaArrayDinamico listaautorarray;

    private arbolAVL Acodigo;
    private arbolAVL Atitulo;
    private arbolAVL Aautor;

    private Scanner teclado;
    BufferedReader input;
    private boolean power;
    private int nivel;
    long TInicio, TFin, tiempo;

    public Consola() {
        input = new BufferedReader(new InputStreamReader(System.in));
        Admins[0] = defaultAdmin;
        Clientes[0] = defaultCliente;

        defaultAdmin = new Admin();
        defaultCliente = new Cliente();
        listacodigo = new ListaEnlazada();
        listatitulo = new ListaEnlazada();
        listaautor = new ListaEnlazada();

        Acodigo=new arbolAVL("codigo");
        Atitulo=new arbolAVL("titulo");
        Aautor=new arbolAVL("autor");
        

        teclado = new Scanner(System.in);
        textos = new String[10];
        power = false;

        error = "ERROR: INGRESE UN DATO VÁLIDO";

        textos[0] = "[1]--> SI\n" + "[0]--> NO\n" + "------> ";

        textos[1] = "[1]--> Ingresar como Administrador\n" + "[2]--> Ingresar como Usuario(Cliente)\n"
                + "[3]--> ADMINISTRAR TEXTOS\n" + "[0]--> Terminar programa\n" + "------> ";

        textos[2] = "%ADMINISTRADOR%\n" + "[1]--> Ver información del administrador\n" + "[2]--> Crear libro\n"
        // + "[3]--> Listar todos los autores\n"
        + "[4]--> Ver ultima accion realizada\n" + "[0]--> SALIR\n" + "------> ";

        textos[3] = "%CLIENTE%\n" + "[1]--> Ver informacion del usuario\n" + "[2]--> Ver #MisFavoritos\n"
                + "[3]--> Ver Libros por Leer\n"
                + "[0]--> SALIR\n" + "------> ";

        textos[4] = "%ADMINISTRADOR DE TEXTOS%\n" + "[1]--> Listar todos los textos\n" // LEnlazada
                + "[2]--> Buscar texto por codigo (QUICK)\n" + "[3]--> Buscar texto por titulo exacto (QUICK)\n"
                + "[4]--> Buscar texto por autor\n" // LEnlazada
                + "[5]--> Buscar texto por primeras palabras exactas\n" // LEnlazada
                + "[6]--> Buscar texto si contiene la palabra...\n" 
                + "[7]--> Listar todos los textos (AVL)\n"
                + "[8]--> Buscar texto por titulo (AVL)\n"
                + "[9]--> Buscar texto por autor (AVL)\n"
                + "[10]--> Buscar texto por codigo (AVL)\n"
                + "[0]--> SALIR\n" + "------> ";

        textos[5] = "%TEXTO%\n" + "[1]--> Modificar(Admin)\n" + "[2]--> Borrar(Admin)\n" + "[3]--> Comentar(Cliente)\n"
                + "[4]--> Puntuar(Cliente)\n" + "[5]--> Agregar a favoritos (Cliente)\n"
                + "[6]--> Eliminar de favoritos (Cliente)\n" + "[7]--> Listar todos los textos de este autor(Cliente)\n"
                + "[0]--> SALIR\n" + "------> ";
        textos[6] = "%Que desea modificar?%\n" + "[1]--> Titulo\n" + "[2]--> Autor\n" + "[3]--> Numero de paginas\n"
                + "[4]--> Idioma\n" + "[5]--> Tema\n" + "[0]--> SALIR\n" + "------> ";

    }
    public String timestamp(long i, long f){
        return "TIEMPO ["+medida+"] de operacion: "+ (f-i)/div +" "+medida;
    }
  

    public void encender() {
        power = true;
        System.out.println("");
        System.out.println("              Bienvenido a BiblIES");
        System.out.println("||===============================================||");
        nivel = 0;
        menu();
    }

    public void apagar() {
        System.out.println("");
        System.out.println("       Cerrando sesión, apagando el equipo... :v       ");
        System.out.println("||===============================================||");
        System.out.println("");
        power = false;
    }

    /*
     * private void pausa(){ try { Thread.sleep(150); } catch (Exception e) {
     * System.out.println(e); } }
     */
    private int controlador(String texto) { // funciona como un while entre cada menú, ahorra resto de codigo
        boolean pass = false;
        int resultado = 0;
        while (!pass) {
            System.out.println();
            System.out.print(texto);
            S = teclado.next();
            if (S == "\n") {

            }
            if (esNumero(S)) {
                resultado = Integer.parseInt(S);
                pass = true;
            } else {
                System.out.println(error);
            }
        }
        return resultado;
    }

    private String controladorNextLine(String s) { // Evita errores al ingresar texto de una linea completa
        boolean pass = false;
        String resultado = "";
        while (!pass) {
            System.out.print(s);
            try {
                resultado = input.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Consola.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println();
            if (S != "") {
                pass = true;
            }
        }
        return resultado;
    }

    public void menu() {
        while (power && nivel == 0) {
            numero = controlador(textos[1]);
            switch (numero) {
                case 0:
                    apagar();
                    break;
                case 1:
                    nivel = 1;
                    System.out.println("Ahora es Administrador");
                    menuAdmin();
                    break;
                case 2:
                    nivel = 1;
                    System.out.println("Ahora es Cliente");
                    menuCliente();
                    break;
                case 3:
                    nivel = 1;
                    administrarTextos();
                    break;
                case 99:
                    imprimir3listas();
                    break;
                case 999:
                    imprimir3listasdinamicas();
                    break;
                default:
                    System.out.println("Opción no existente!");
                    menu();
                    break;
            }
        }
    }

    public void menuCliente() {
        while (power && nivel == 1) {

            System.out.println();
            numero = controlador(textos[3]);
            switch (numero) {
                case 0:
                    nivel = 0;
                    break;
                case 1:
                    clienteInfo();
                    break;
                case 2:
                    nivel = 2;
                    verFavoritos();
                    break;
                case 3:
                    nivel = 2;
                    verLibrosPorLeer();
                    break;
                default:
                    System.out.println("Opción no existente!");
                    menu();
                    break;
            }
        }
    }

    public void clienteInfo() {
        try {
            String inf = defaultCliente.toString();
            System.out.println(inf);
            System.out.println();
            menuCliente();
        } catch (NullPointerException e) {
            System.out.println("***Este cliente no tiene informacion***");
            menuCliente();
        }
    }

    public void menuAdmin() {
        while (power && nivel == 1) {

            System.out.println();
            numero = controlador(textos[2]);
            switch (numero) {
                case 0:
                    nivel = 0;
                    break;
                case 1:
                    adminInfo();
                    break;
                case 2:
                    crearTexto();
                    break;
                case 3:// nivel=2;
                       // getListaDeAutores();
                       // break;
                case 4: 
                    nivel=2;
                    menuUltimaAccion();
                    break;
                default:
                    System.out.println("Opción no existente!");
                    break;
            }
        }
    }

    public void menuUltimaAccion() {
        while (nivel == 2) {
            if(!defaultAdmin.accionesHechas.isEmpty()){
                Object[] x=defaultAdmin.accionesHechas.pop();
                String ultimaAccion =(String)x[1];
                Nodo temp = (Nodo)x[0];
                int dato=(int)x[2];
               // String ultimaAccion = defaultAdmin.identificadorAccion.pop();
               // Nodo temp = defaultAdmin.accionesHechas.pop();
                System.out.println("la ultima opcion realizada fue " + ultimaAccion + " un libro " + temp.textoActual.getTitulo());
                numero = controlador("Desea devolver esta accion\n" + "[1]--> SI\n" + "[2]--> NO\n" + "[3]--> BORRAR HISTORIAL\n" + "[0]--> Salir\n" + "-----> ");

                switch (numero) {
                    case 0:
                        defaultAdmin.accionesHechas.push(temp,ultimaAccion,dato);
                        nivel = 1;
                        break;
                    case 1:
                        if (ultimaAccion.equals("BORRADO")) {
                            defaultAdmin.crearLibroBorrado(temp, listacodigo, listatitulo, listaautor);
                        }
                        if (ultimaAccion.equals("MODIFICADO")) {
                            defaultAdmin.modificarTextoBorrrado(listacodigo, dato, temp);
                        }
                        if (ultimaAccion.equals("CREADO")) {
                            defaultAdmin.borrarTexto(listacodigo, temp.textoActual);
                            defaultAdmin.borrarTexto(listatitulo, temp.textoActual);
                            defaultAdmin.borrarTexto(listaautor, temp.textoActual);
                            listacodigoarray = listacodigo.toListaArrayDinamico();
                            listaautorarray = listaautor.toListaArrayDinamico();
                            listatituloarray = listatitulo.toListaArrayDinamico();
                        }
                        break;

                    case 2:                        
                        defaultAdmin.accionesHechas.push(temp,ultimaAccion,dato);
                        break;

                    case 3: //eliminar historial

                        while (!defaultAdmin.accionesHechas.isEmpty()) {                            
                            defaultAdmin.accionesHechas.pop();                            
                        }
                        break;
                }
            }else{
                while(nivel!=1){
                    System.out.println("Ya no hay \"acciones hechas\" (pila vacia)\n");
                    numero=controlador("[0]--> Salir ");
                    if(numero==0)
                        nivel=1;
                }
            }
        }
    }

    public void adminInfo() {
        try {
            String inf = defaultAdmin.toString();
            System.out.println(inf);
            System.out.println();

        } catch (NullPointerException e) {
            System.out.println("***Este Administrador no tiene informacion***");

        }
    }

    public void crearTexto() {
        System.out.println("|| Ingrese los datos del libro ||");
        System.out.println();
        String nombreL = controladorNextLine("Nombre del libro: ");// Para que el nextline no se trage el pinche salto
                                                                   // de linea :v
        System.out.println();
        String nombreA = controladorNextLine("Nombre del autor: ");
        System.out.println();
        String fecha1 = controladorNextLine("Fecha de publicación (formato [DD/MM/YYYY] numérico): ");
        System.out.println();
        int npag = controlador("Numero de paginas: ");
        System.out.println();
        String idiom = controladorNextLine("Idioma: ");
        System.out.println();
        String tema = controladorNextLine("Tema (String): ");
        System.out.println();
        System.out.println("***DATOS DEL TEXTO CREADO*** \n \n" + "Nombre: " + nombreL + "\n" + "Autor : " + nombreA
                + "\n" + "Fecha de publicacion: " + fecha1 + "\n" + "Número de páginas: " + npag + "\n" + "Idioma: "
                + idiom + "\n" + "Tema: " + tema + "\n" + "================================");
        numero = controlador("Guardar texto?\n" + textos[0]);
        if (numero == 1) {
            TInicio = System.nanoTime();
            defaultAdmin.crearLibro(nombreL, nombreA, fecha1, numero, idiom, tema, listacodigo, listatitulo,
                    listaautor);

            /*
             * Texto t1= defaultAdmin.buscarPorTituloExactoQuickSearch(nombreL,
             * listatitulo.toListaArrayDinamico()); listatituloarray.add(t1); t1=
             * defaultAdmin.buscarPorCodigoQuickSearch(t1.getCodigo(),
             * listacodigo.toListaArrayDinamico()); listacodigoarray.add(t1); t1=
             * defaultAdmin.buscarPorTituloExactoQuickSearch(nombreL,
             * listaautor.toListaArrayDinamico()); listaautorarray.add(t1);
             */
            listatituloarray = listatitulo.toListaArrayDinamico();
            listacodigoarray = listacodigo.toListaArrayDinamico();
            listaautorarray = listaautor.toListaArrayDinamico();
            if (defaultAdmin.buscarPorTituloExactoQuickSearch(nombreA, listaautorarray) == null) {
                System.out.println("EL TEXTO EN listaautorarray NO FUE AGREGADO!!");
            }
            defaultAdmin.crearLibroSQL(nombreL, nombreA, fecha1, numero, idiom, tema);// perro pa que lo borra :v per
            // pero al crearLibro() ennese metodo se agrega el metodo crear librosql() esoo
            // es lo que hace explotar el rpograma se vuelve un ciclo
            // ya lo quite de alla
            TFin = System.nanoTime(); // Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable
                                      // T
            tiempo = (TFin - TInicio) / 1000000; // Calculamos los milisegundos de diferencia
            System.out.println("Tiempo de ejecución en milisegundos: " + tiempo); // Mostramos en pantalla el tiempo de
                                                                                  // ejecución en segundos

            System.out.println();
            System.out.println("***Texto guardado***\n" + "=================================");

        } else if (numero == 0) {
            System.out.println("***Texto no guardado***");

        }

    }

    public void getListaDeTextos(ListaEnlazada list) {
        while (nivel == 2) {
            if (listacodigo.empty()) {
                System.out.println("");
                System.out.println("¡LISTA VACÍA!");
                System.out.println("");

            } else {
                defaultAdmin.imprimirTextos(listacodigo);
                nivel = 3;
                elegirTexto(list);

            }

        }
    }

    public void getListaDeClientes() {

    }

    public void getListaDeAutores() {
        while (nivel == 2) {
            String imprimir;
            Nodo puntero = listacodigo.cabeza;
            for (int i = 1; i < listacodigo.size(); i++) {
                System.out.println("i: " + i);
                if (!(puntero.textoActual.getAutor() == null)) {
                    imprimir = "[" + i + "] Nombre: " + puntero.textoActual.getAutor();
                    System.out.println(imprimir);
                }
                puntero = puntero.siguiente;
            }

            numero = controlador("Seleccione un autor o ingrese 0 para salir: ");
            if (numero == 0) {
                nivel = 1;
            } else {
                if (numero > listacodigo.size()) {
                    System.out.println("Ese autor con ese indice no existe!\n");

                } else {
                    opcionesAutores(listacodigoarray.get(numero - 1));
                }
            }
        }

    }

    public void opcionesAutores(Texto texto) {
        numero = controlador("Que desea hacer con este autor?\n"
                + "[1]--> Borrar toda su existencia (Autor y todo texto relacionado con él)\n"
                + "[2]--> Agregar todos sus textos a mis favoritos (Cliente)\n" + "[0]--> Salir\n" + "-----> ");
        switch (numero) {
            case 0:
                nivel = 1;
            case 1:
                numero = controlador("SEGURO? TODO SERA BORRADO, INCLUYENDO AL AUTOR!\n" + "[1]--> SI\n" + "[2]--> NO\n"
                        + "-----> ");
                if (numero == 1) {
                    borrarExistencia(texto.getAutor());
                }
                break;
            case 2:
                defaultCliente.agregarFavorito(listacodigo.containsPalabra(texto.getAutor()));
                System.out.println("Todo se agregó a favoritos!\n");
                numero = 2;
        }

    }

    public void borrarExistencia(String autor) {
        ListaEnlazada borrado = listacodigo.containsPalabra(autor);
        Nodo recorredor = borrado.cabeza;
        while (recorredor != null) {
            listacodigo.remove(recorredor.textoActual.getCodigo());
            defaultCliente.eliminarFavorito(recorredor.textoActual.getCodigo());
            recorredor = recorredor.siguiente;
        }
        System.out.println("SE BORRÓ TODO RASTRO DE " + autor);
        nivel = 1;

    }

    public void verFavoritos() {
        while (nivel == 2) {
            try {
                ListaEnlazada fav = defaultCliente.getFavoritos();
                if (fav.size() == 0) {

                    System.out.println(
                            "==============================\nNo hay favoritos\n==============================");
                    nivel = 1;
                } else {
                    System.out
                            .println("==============================\n#MisFavoritos\n==============================\n");
                    defaultCliente.imprimirTextos(fav);
                    System.out.println();
                    System.out
                            .println("=============================================================================\n");
                    numero = controlador("Seleccione el texto ingresando su codigo. 0 para salir\n" + "---->");
                    switch (numero) {
                        case 0:
                            nivel = 1;
                            break;
                        default:
                            if (fav.contains(numero)) {
                                nivel = 3;
                                opcionesFavoritos(fav, numero);
                            }
                            break;
                    }
                }
            } catch (NullPointerException e) {
            }
        }
    }

    public void opcionesFavoritos(ListaEnlazada fav, int codigo) {
        while (nivel == 3) {
            numero = controlador("[1]--> Eliminar de favoritos\n[0]--> Salir\n-----> ");
            if (numero == 0) {
                nivel = 1;
            }
            if (numero == 1) {
                defaultCliente.eliminarFavorito(codigo);
                nivel = 2;
            }
        }
    }

    public void verLibrosPorLeer() {
        while (nivel == 2) {
            try {
                Cola xleer = defaultCliente.getLibrosPorLeer();
                if (xleer.miraVeTu() == null) {

                    System.out.println(
                            "==============================\nNo hay Libros Por Leer\n==============================");
                    nivel = 1;
                } else {
                    System.out
                            .println("==============================\n#MisLibrosPorLeer\n==============================\n");
                    System.out.println(xleer.miraVeTu().textoActual);
                    System.out.println();
                    System.out
                            .println("=============================================================================\n");
                    numero = controlador("¿Ya leyo este Texto? ¿Desea seguir con el siguiente?. \n"
                    + "[1]--> Ya lo lei, muestrame el siguiente texto\n"
                    + "[2]--> No aun no\n" 
                    + "[0]--> Salir\n" 
                    + "---->");
                    switch (numero) {
                        case 0:
                            nivel = 1;
                            break;
                        case 1 :
                            Nodo nodoleer = xleer.desEncolar();
                            System.out.println(
                                "Texto Leído :) :\n" 
                                + nodoleer.textoActual
                                + "\nSiguiente Texto por leer:\n"
                                + xleer.miraVeTu().textoActual

                            );
                            break;

                        case 2 :
                        
                        System.out.println(
                            "Ah bueno :) :\n" 
                            + "Siguiente Texto por leer:\n"
                            + xleer.miraVeTu().textoActual

                        );
                        break;

                        
                    }
                }
            } catch (NullPointerException e) {
            }
        }
    }
    

    public void setAdmin(Admin a) {
        defaultAdmin = a;

    }

    public void setCliente(Cliente c) {
        defaultCliente = c;
    }

    public void setTextos(ListaEnlazada texts) {

    }

    public static Date convertirAFecha(String fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        return fechaDate;
    }

    public static boolean esNumero(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }

    // =====================================================================//
    public void insertarListas(ListaEnlazada codigos, ListaEnlazada titulos, ListaEnlazada autores) {
        listacodigo = codigos;
        listaautor = autores;
        listatitulo = titulos;

        listacodigoarray = codigos.toListaArrayDinamico();
        listaautorarray = autores.toListaArrayDinamico();
        listatituloarray = titulos.toListaArrayDinamico();
        // this.LTextos=LTextos;
    }


    public void insertarArboles(arbolAVL codigos, arbolAVL titulos, arbolAVL autores) {
        Acodigo = codigos;
        Aautor = autores;
        Atitulo = titulos;
    }

    public void administrarTextos() {
        // ListaEnlazada busqueda= new ListaEnlazada();
        while (power && nivel == 1) {
            numero = controlador(textos[4]);
            switch (numero) {
                case 0:
                    nivel = 0; // back
                    break;
                case 1:
                    nivel = 2;
                    getListaDeTextos(listacodigo); // *listar* todos los textos

                    break;
                case 2:
                    numero = controlador("Ingrese el codigo: "); // POR *CODIGO*

                    TInicio = System.nanoTime();
                    Texto t = defaultAdmin.buscarPorCodigoQuickSearch(numero, listacodigoarray);
                    TFin = System.nanoTime();
                    tiempo = (TFin - TInicio);
                    System.out.println("Tiempo de ejecución en microsegundos: " + tiempo);
                    System.out.print("=======================");
                    if (t != null) {
                        System.out.println(t.toString());
                        nivel = 4;
                        opcionesTexto(encapsularTexto(t));
                        System.out.print("=======================");
                    }

                    break;
                case 3:
                    S = controladorNextLine("Ingrese con precision el titulo exacto: "); // POR *TITULO EXACTO*

                    TInicio = System.nanoTime();
                    Nodo n2 = new Nodo(defaultAdmin.buscarPorTituloExactoQuickSearch(S, listatituloarray));
                    TFin = System.nanoTime();
                    tiempo = (TFin - TInicio) / 1000;
                    System.out.println("Tiempo de ejecución en microsegundos: " + tiempo);
                    if (n2.textoActual == null) {
                        System.out.println("No se encontró el texto.");

                    } else {
                        System.out.println("Texto encontrado!\nTitulo: " + n2.textoActual.getTitulo() + "\nAutor: "
                                + n2.textoActual.getAutor());
                        nivel = 4;
                        opcionesTexto(n2);
                    }

                    break;
                case 4:
                    S = controladorNextLine("Escriba el nombre completo del autor: "); // POR *AUTOR*
                    TInicio = System.nanoTime();
                    ListaArrayDinamico l = defaultCliente.buscarPorAutorExactoQuickSearch(S, listaautorarray);
                    TFin = System.nanoTime();
                    tiempo = (TFin - TInicio) / 1000000;
                    System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
                    if (l.isEmpty()) {
                        System.out.println("Ningun autor coincide con su busqueda\n");

                    }

                    System.out.println(l.toString());
                    nivel = 3;
                    elegirTexto(l.toListaEnlazada()); // POR AUTOR
                    break;
                case 5:
                    S = controladorNextLine("Ingrese las primeras palabras: ");// POR PRIMERAS PALABRAS EXACTAS
                    TInicio = System.nanoTime();
                    ListaArrayDinamico p = defaultAdmin.buscarEmpiezaPorQuickSearch(S,
                            listatitulo.toListaArrayDinamico());
                    TFin = System.nanoTime();
                    tiempo = (TFin - TInicio) / 1000000;
                    System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
                    nivel = 3;
                    elegirTexto(p.toListaEnlazada());
                    break;
                case 6:

                    TInicio = System.nanoTime();
                    S = controladorNextLine("Escriba la palabra a buscar: "); // POR CONTIENE LA PALABARA....
                    ListaEnlazada l1 = listacodigo.containsPalabra(S);
                    TFin = System.nanoTime();
                    tiempo = (TFin - TInicio) / 1000000;
                    System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
                    System.out.println("Estos son los textos que contienen esa palabra: ");
                    defaultAdmin.imprimirTextos(l1);
                    nivel = 3;
                    elegirTexto(l1);
                    break;
                    case 7: //AVL listar textos
                        System.out.println("TEXTOS AVL: \n");
                        System.out.println("======================================================================================================CODIGO -numero de elementos: "+Acodigo.getSize());
                        Acodigo.inOrder();
                        System.out.println("======================================================================================================TITULO -numero de elementos: "+Atitulo.getSize());
                        Atitulo.inOrder();
                        System.out.println("======================================================================================================AUTOR: -numero de elementos: "+Aautor.getSize());
                        Aautor.inOrder();
                        System.out.println("======================================================================================================");
                        
                    break;
                    case 8: //AVL titulo
                    S = controladorNextLine("Escriba el nombre completo del titulo: "); // POR *AUTOR*

                    TInicio = System.nanoTime();
                    Texto t1 = defaultAdmin.buscarPorTituloAVL(S, Atitulo);
                    TFin=System.nanoTime();
                    System.out.println(timestamp(TInicio, TFin));

                    if(t1!=null){
                        System.out.println("Texto encontrado!\n" + t1.toString());
                        nivel = 4;
                        opcionesTexto(new Nodo(t1));
                    }

                    break;
                    case 9: //AVL autor
                    S = controladorNextLine("Escriba el nombre completo del autor: "); // POR *AUTOR*

                    TInicio = System.nanoTime();
                    Texto t2 = defaultAdmin.buscarPorAutorAVL(S, Aautor);
                    TFin=System.nanoTime();
                    System.out.println(timestamp(TInicio, TFin));

                    if(t2!=null){
                        System.out.println("Texto encontrado!\n " + t2.toString());
                        nivel = 4;
                        opcionesTexto(new Nodo(t2));
                    }
                    
                    break;
                    case 10: //AVL codigo
                    int COD = controlador("Ingrese el codigo del libro: "); // POR *AUTOR*

                    TInicio = System.nanoTime();
                    Texto t3 = defaultAdmin.buscarPorCodigoAVL(COD,Acodigo);
                    TFin=System.nanoTime();
                    System.out.println(timestamp(TInicio, TFin));

                    if(t3!=null){
                        System.out.println("Texto encontrado!\n" + t3.toString());
                        nivel = 4;
                        opcionesTexto(new Nodo(t3));
                    }
                    
                    break;
                default:
                    System.out.println("Indice no valido!"); 
                    break;
            }
        }
    }

    public void elegirTexto(ListaEnlazada lista) {
        while (nivel == 3) {
            numero = controlador("Seleccione el texto ingresando su codigo. 0 para salir\n" + "---->");
            switch (numero) {
                case 0:
                    nivel = 1;
                    break;
                default:
                    if (lista.contains(numero)) {
                        Nodo n = new Nodo(lista.getCodigo(numero));
                        System.out.println("***TEXTO " + n.textoActual.getCodigo() + " ELEGIDO***\n"
                                + n.textoActual.toString() + "\n");
                        nivel = 4;
                        opcionesTexto(n);
                        break;
                    } else {
                        System.out.print("Ese texto no existe, ingrese un numero dentro del rango.");
                        break;
                    }
            }
        }
    }

    public void opcionesTexto(Nodo t) {
        while (nivel == 4) {
            numero = controlador(textos[5]);
            switch (numero) {
                case 0:
                    nivel = 1;// back
                    break;
                case 1:
                    nivel = 5;
                    //defaultAdmin.identificadorAccion.push("MODIFICADO");
                    //defaultAdmin.accionesHechas.push(t);
                    modificarTexto(t); // modificar                    
                    break;
                case 2:
                    TInicio = System.nanoTime();
                    defaultAdmin.accionesHechas.push(t,"BORRADO",0);
                    defaultAdmin.borrarTexto(listacodigo, t.textoActual);// borrar
                    defaultAdmin.borrarTexto(listatitulo, t.textoActual);
                    defaultAdmin.borrarTexto(listaautor ,t.textoActual);

                    listacodigoarray = listacodigo.toListaArrayDinamico();
                    listaautorarray = listaautor.toListaArrayDinamico();
                    listatituloarray = listatitulo.toListaArrayDinamico();
                    System.out.println("Texto borrado");
                    TFin = System.nanoTime();
                    tiempo = (TFin - TInicio) / 1000000;
                    System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);

                    long ti=System.nanoTime();
                    Acodigo.remove(t.textoActual);
                    Atitulo.remove(t.textoActual);
                    Aautor.remove(t.textoActual);
                    long tf=System.nanoTime();
                    System.out.println("BORRADO CON AVL: " +timestamp(ti, tf));



                    if (defaultCliente.buscarPorCodigoQuickSearch(t.textoActual.getCodigo(),
                        defaultCliente.getFavoritos().toListaArrayDinamico()) != null) {
                        defaultCliente.eliminarFavorito(t.textoActual.getCodigo());
                    }

                    nivel = 1;
                    break;
                case 3:
                    S = controladorNextLine("Ingrese el comentario, presione Enter para guardarlo: "); // comentar

                    TInicio = System.nanoTime();
                    defaultCliente.comentarTexto(t.textoActual, S);
                    TFin = System.nanoTime();
                    tiempo = (TFin - TInicio) / 1000000;
                    System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
                    System.out.println("Buen comentario\n");
                    nivel = 1;
                    break;
                case 4:
                    S = controladorNextLine("Ingrese su calificacion (entero), presione Enter para guardarlo: "); // puntuar
                    defaultCliente.calificarTexto(t.textoActual, Integer.parseInt(S));
                    System.out.println("Texto calificado!\n");
                    nivel = 1; // puntuar
                    break;
                case 5:
                    defaultCliente.agregarFavorito(t.textoActual); // agregar a fav
                    System.out.println("Texto agregado a favoritos");
                    nivel = 1;

                    break;
                case 6:
                    if (defaultCliente.getFavoritos().contains(t.textoActual.getCodigo())) {
                        defaultCliente.eliminarFavorito(t.textoActual.getCodigo()); // eliminar de fav
                        System.out.println("Texto " + t.textoActual.getTitulo() + " Eliminado de favoritos");
                        nivel = 1;
                    } else {
                        System.out.println("Este texto no esta en su lista de favoritos");
                        nivel = 1;
                    }
                    break;
                case 7: // LISTAR TODOS LOS TEXTOS DE ESTE AUTOR

                    ListaArrayDinamico o = defaultAdmin.buscarPorAutorExactoQuickSearch(t.textoActual.getAutor(),
                            listaautorarray);
                    System.out.println("\n" + o.toString() + "\n\n");

                    nivel = 3;
                    elegirTexto(o.toListaEnlazada());

                    break;
                default:
                    System.out.println("Esa opcion no existe!");// cambiar ToDO
                    nivel = 1;
                    break;
            }
        }
    }

    public void modificarTexto(Nodo t) {
        while (nivel == 5) {
            numero = controlador(textos[6]);

            switch (numero) {
                case 0:
                    nivel = 4; // back
                    break;
                case 1:
                    S = controladorNextLine("Ingrese el nuevo titulo: ");
                    defaultAdmin.accionesHechas.push(t,"MODIFICADO",1);
                    defaultAdmin.modificarTexto(listacodigo, t.textoActual.getCodigo(), 1, S); // set titulo
                    defaultAdmin.modificarTexto(listatitulo, t.textoActual.getCodigo(), 1, S); 
                    defaultAdmin.modificarTexto(listaautor, t.textoActual.getCodigo(), 1, S); 
                    listacodigoarray = listacodigo.toListaArrayDinamico();
                    listaautorarray = listaautor.toListaArrayDinamico();
                    listatituloarray = listatitulo.toListaArrayDinamico();
                    break;
                case 2:
                    S = controladorNextLine("Ingrese el nuevo Autor: ");
                    defaultAdmin.accionesHechas.push(t,"MODIFICADO",2);
                    defaultAdmin.modificarTexto(listacodigo, t.textoActual.getCodigo(), 2, S); // set autor
                    defaultAdmin.modificarTexto(listatitulo, t.textoActual.getCodigo(), 2, S); // set autor
                    defaultAdmin.modificarTexto(listaautor, t.textoActual.getCodigo(), 2, S); // set autor
                    listacodigoarray = listacodigo.toListaArrayDinamico();
                    listaautorarray = listaautor.toListaArrayDinamico();
                    listatituloarray = listatitulo.toListaArrayDinamico();

                    break;
                case 3:
                    S = controladorNextLine("Ingrese el nuevo numero de paginas: ");
                    defaultAdmin.accionesHechas.push(t,"MODIFICADO",3);
                    defaultAdmin.modificarTexto(listacodigo, t.textoActual.getCodigo(), 3, S); // set num paginas
                    defaultAdmin.modificarTexto(listatitulo, t.textoActual.getCodigo(), 3, S); // set num paginas
                    defaultAdmin.modificarTexto(listaautor, t.textoActual.getCodigo(), 3, S); // set num paginas
                    listacodigoarray = listacodigo.toListaArrayDinamico();
                    listaautorarray = listaautor.toListaArrayDinamico();
                    listatituloarray = listatitulo.toListaArrayDinamico();
                    //numero = 5;
                    break;
                case 4:
                    S = controladorNextLine("Ingrese el nuevo idioma: ");
                    defaultAdmin.accionesHechas.push(t,"MODIFICADO",4);
                    defaultAdmin.modificarTexto(listacodigo, t.textoActual.getCodigo(), 4, S); // set idioma
                    defaultAdmin.modificarTexto(listatitulo, t.textoActual.getCodigo(), 4, S); // set idioma
                    defaultAdmin.modificarTexto(listaautor, t.textoActual.getCodigo(), 4, S); // set idioma
                    listacodigoarray = listacodigo.toListaArrayDinamico();
                    listaautorarray = listaautor.toListaArrayDinamico();
                    listatituloarray = listatitulo.toListaArrayDinamico();
                    break;
                case 5:
                    S = controladorNextLine("Ingrese el nuevo Tema: ");
                    defaultAdmin.accionesHechas.push(t,"MODIFICADO",5);
                    defaultAdmin.modificarTexto(listacodigo, t.textoActual.getCodigo(), 5, S); // set tema
                    defaultAdmin.modificarTexto(listatitulo, t.textoActual.getCodigo(), 5, S); // set tema
                    defaultAdmin.modificarTexto(listaautor, t.textoActual.getCodigo(), 5, S); // set tema
                    listacodigoarray = listacodigo.toListaArrayDinamico();
                    listaautorarray = listaautor.toListaArrayDinamico();
                    listatituloarray = listatitulo.toListaArrayDinamico();
                    break;
                default:
                    System.out.println("Esa opcion no existe!");

                    break;
            }
        }
    }
    // =======================================================================================================================

    public Nodo encapsularTexto(Texto t) {
        Nodo n = new Nodo(t);
        return n;
    }

    // =======================================================================================================================

    private void imprimir3listas() {
        String lcodigo;
        int i = 1;
        Nodo recorredorcodigo = listacodigo.cabeza;
        Nodo recorredortitulo = listatitulo.cabeza;
        Nodo recorredorautor = listaautor.cabeza;
        System.out.println(
                "===========================================================================================================");
        while (recorredorcodigo != null) {
            Texto texto = recorredorcodigo.textoActual;
            lcodigo = "[" + i + "]ListaEnlazada codigos: " + texto.getCodigo() + ") Titulo: " + texto.getTitulo()
                    + "\n   Autor: " + texto.getAutor();
            System.out.println(lcodigo);
            recorredorcodigo = recorredorcodigo.siguiente;
            i++;
        }
        i = 1;
        System.out.println(
                "===========================================================================================================");
        while (recorredortitulo != null) {
            Texto texto = recorredortitulo.textoActual;
            lcodigo = "[" + i + "]ListaEnlazada titulos: " + texto.getCodigo() + ") Titulo: " + texto.getTitulo()
                    + "\n   Autor: " + texto.getAutor();
            System.out.println(lcodigo);
            recorredortitulo = recorredortitulo.siguiente;
            i++;
        }
        i = 1;
        System.out.println(
                "===========================================================================================================");
        while (recorredorautor != null) {
            Texto texto = recorredorautor.textoActual;
            lcodigo = "[" + i + "]ListaEnlazada autores: " + texto.getCodigo() + ") Titulo: " + texto.getTitulo()
                    + "\n   Autor: " + texto.getAutor();
            System.out.println(lcodigo);
            recorredorautor = recorredorautor.siguiente;
            i++;
        }
        System.out.println(
                "===========================================================================================================");
        menu();
    }

    private void imprimir3listasdinamicas() {
        System.out.println("TITULO======ELEMENTOS EN EL ARRAY: " + listatituloarray.getSize()
                + "=====================================================================================================");
        System.out.println(listatituloarray.toString());
        System.out.println("CODIGO======ELEMENTOS EN EL ARRAY: " + listacodigoarray.getSize()
                + "=====================================================================================================");
        System.out.println(listacodigoarray.toString());
        System.out.println("AUTOR=======ELEMENTOS EN EL ARRAY: " + listaautorarray.getSize()
                + "====================================================================================================");
        System.out.println(listaautorarray.toString());
        System.out.println(
                "===========================================================================================================");
        menu();
    }
}
