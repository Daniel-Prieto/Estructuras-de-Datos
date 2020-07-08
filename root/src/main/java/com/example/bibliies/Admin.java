package com.example.bibliies;

import java.util.*;
import java.io.*;
import java.sql.*;

public class Admin extends Usuario {
    ConexionSQL cc = new ConexionSQL();
    Connection con = cc.conexion();




    Pila<Nodo,String,Integer> accionesHechas = new Pila<Nodo,String,Integer>(50);

    public Admin() {

    }

    // ORDENAR UNA LISTA SEGUN AUTOR

    public Admin(int id, String nombre, String apellido, String correo, String clave) {
        super(id, nombre, apellido, correo, clave);
    }

    public ListaEnlazada ordenarListaPorAutor(ListaEnlazada lista) {

        ListaEnlazada nuevaLista = new ListaEnlazada();

        Nodo iteradorAntigua = lista.cabeza;

        while (iteradorAntigua != null) {

            Texto libro = iteradorAntigua.textoActual;
            Nodo nuevoNodo = new Nodo(libro);

            nuevaLista.tamano++;
            if (nuevaLista.cabeza != null) {

                Nodo apuntador = nuevaLista.cabeza;

                if (libro.getAutor().compareToIgnoreCase(apuntador.textoActual.getAutor()) <= 0) {

                    nuevaLista.cabeza = nuevoNodo;
                    nuevoNodo.siguiente = apuntador;
                    apuntador.anterior = nuevoNodo;

                } else {
                    while (apuntador.siguiente != null
                            && libro.getAutor().compareToIgnoreCase(apuntador.siguiente.textoActual.getAutor()) > 0) {

                        apuntador = apuntador.siguiente;

                    }
                    if (apuntador.siguiente != null) {

                        apuntador.siguiente.anterior = nuevoNodo;
                        nuevoNodo.siguiente = apuntador.siguiente;
                        apuntador.siguiente = nuevoNodo;
                        nuevoNodo.anterior = apuntador;

                    } else {

                        apuntador.siguiente = nuevoNodo;
                        nuevoNodo.anterior = apuntador;

                        nuevaLista.cola = nuevoNodo;

                    }
                }
            }

            else

            {
                nuevaLista.cola = nuevoNodo;
                nuevaLista.cabeza = nuevoNodo;
            }

            iteradorAntigua = iteradorAntigua.siguiente;

        }

        return nuevaLista;

    }

    public void crearLibroBorrado(Nodo texto, ListaEnlazada listacodigo, ListaEnlazada listatitulo, ListaEnlazada listaautor) {
        Texto libro = texto.textoActual;
        Nodo nodoCodigo = new Nodo(libro);
        Nodo nodoAutor = new Nodo(libro);
        Nodo nodoTitulo = new Nodo(libro);

        /////////// SEGUN CODIGO
        listacodigo.addIdOrdenado(nodoCodigo);

        ///////// SEGUN TITULO

        listatitulo.addTextoOrdenado(nodoTitulo, "titulo");

        //////////// SEGUN AUTOR
        listaautor.addTextoOrdenado(nodoAutor, "autor");

    }

    // MYSQL
    public void crearLibro(int codigo, String titulo, String autor, String fechaPublicacion, int numeroPaginas,
            String idioma, String tema, ListaEnlazada listacodigo, ListaEnlazada listatitulo,
            ListaEnlazada listaautor) {
        Texto libro = new Texto(codigo, titulo, autor, fechaPublicacion, numeroPaginas, idioma, tema);
        Nodo nodoCodigo = new Nodo(libro);
        Nodo nodoAutor = new Nodo(libro);
        Nodo nodoTitulo = new Nodo(libro);

        /////////// SEGUN CODIGO
        listacodigo.add(nodoCodigo);

        ///////// SEGUN TITULO

        listatitulo.addTextoOrdenado(nodoTitulo, "titulo");

        //////////// SEGUN AUTOR
        listaautor.addTextoOrdenado(nodoAutor, "autor");

    }

    // CREAR UNA LISTA ORDENADA SEGUN TITULO //
    public void crearLibro(String titulo, String autor, String fechaPublicacion, int numeroPaginas, String idioma,
            String tema, ListaEnlazada listacodigo, ListaEnlazada listatitulo, ListaEnlazada listaautor) {
        Texto libro = new Texto(titulo, autor, fechaPublicacion, numeroPaginas, idioma, tema);
        Nodo nodoCodigo = new Nodo(libro);
        Nodo nodoAutor = new Nodo(libro);
        Nodo nodoTitulo = new Nodo(libro);

        /////////// SEGUN CODIGO
        listacodigo.add(nodoCodigo);
        
        ///////// SEGUN TITULO

        listatitulo.addTextoOrdenado(nodoTitulo, "titulo");
        

        //////////// SEGUN AUTOR
        listaautor.addTextoOrdenado(nodoAutor, "autor");
       
        accionesHechas.push(nodoCodigo,"CREADO",0);

    }

    public void crearLibro(Texto textoInsertar, ListaEnlazada listacodigo, ListaEnlazada listatitulo, ListaEnlazada listaautor) {
        Texto libro = textoInsertar;
        Nodo nodoCodigo = new Nodo(libro);
        Nodo nodoAutor = new Nodo(libro);
        Nodo nodoTitulo = new Nodo(libro);

        /////////// SEGUN CODIGO
        listacodigo.add(nodoCodigo);

        ///////// SEGUN TITULO

        listatitulo.addTextoOrdenado(nodoTitulo, "titulo");

        //////////// SEGUN AUTOR
        listaautor.addTextoOrdenado(nodoAutor, "autor");

    }

    public void crearLibroSQL(String titulo, String autor, String fechaPublicacion, int numeroPaginas, String idioma,
            String tema) {
        // String SQL="insert into libroshptas
        // (TITULO,AUTOR,PUBLICACION,NUMEROPAGINAS,IDIOMA,TEMA) values(?,?,?,?,?,?)";
        String SQL = "insert into 100mil (TITULO,AUTOR,PUBLICACION,NUMEROPAGINAS,IDIOMA,TEMA) values(?,?,?,?,?,?)";
        try {
            PreparedStatement pst = con.prepareStatement(SQL);
            pst.setString(1, titulo);
            pst.setString(2, autor);
            pst.setString(3, fechaPublicacion);
            pst.setInt(4, numeroPaginas);
            pst.setString(5, idioma);
            pst.setString(6, tema);
            pst.executeUpdate();
            // JOptionPane.showMessageDialog(null,"Registro exitoso");

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

    }


    public void crearLibroAVL(Texto textoInsertar, arbolAVL acodigo, arbolAVL atitulo, arbolAVL aautor) { //para los arbolitos :v
        /////////// SEGUN CODIGO
        acodigo.insert(textoInsertar);
        ///////// SEGUN TITULO
        atitulo.insert(textoInsertar);
        //////////// SEGUN AUTOR
        aautor.insert(textoInsertar);

    }

    public void borrarTexto(ListaEnlazada lista, Texto texto) {

        if (lista.contains(texto)) {//MK LOGRÉ ENTRAR JAJAJAJA :V 
            lista.remove(texto.getCodigo()); // yo tambien :V
            borrarLibroSQL(texto.getCodigo());
        } else
            System.out.println("NO EXISTE UN LIBRO CON ESTE NUMERO");

    }

    void borrarLibroSQL(int id) {
        // String SQL="DELETE FROM libroshptas WHERE ID='"+id+"'";//crear id en mysql
        String SQL = "DELETE FROM 10mil WHERE ID='" + id + "'";// crear id en mysql
        try {
            PreparedStatement pst = con.prepareStatement(SQL);
            pst.executeUpdate();
            // JOptionPane.showMessageDialog(null,"Registro exitoso");

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

    }

    public void modificarTexto(ListaEnlazada lista, int numeroDeLibro, int datoAmodificar, String datoCambiar) {
      
        Nodo apuntador = lista.cabeza;
        while (apuntador != null) {
            if (apuntador.textoActual.getCodigo() == numeroDeLibro) {
                //accionesHechas.push(apuntador,"MODIFICADO",datoAmodificar);
                if (datoAmodificar == 1) {
                    apuntador.textoActual.setTitulo(datoCambiar);
                    modificarLibroSQL(datoCambiar, datoAmodificar, apuntador.textoActual.getCodigo());
                }
                if (datoAmodificar == 2) {
                    apuntador.textoActual.setAutor(datoCambiar);
                    modificarLibroSQL(datoCambiar, datoAmodificar, apuntador.textoActual.getCodigo());
                }
                if (datoAmodificar == 3) {
                    apuntador.textoActual.setNumeroPaginas(Integer.parseInt(datoCambiar));
                    modificarLibroSQL(datoCambiar, datoAmodificar, apuntador.textoActual.getCodigo());
                }
                if (datoAmodificar == 4) {
                    apuntador.textoActual.setIdioma(datoCambiar);
                    modificarLibroSQL(datoCambiar, datoAmodificar, apuntador.textoActual.getCodigo());
                }
                if (datoAmodificar == 5) {
                    apuntador.textoActual.setTema(datoCambiar);
                    modificarLibroSQL(datoCambiar, datoAmodificar, apuntador.textoActual.getCodigo());
                }
                break;
            }
            apuntador = apuntador.siguiente;
        }
        // System.out.println("NO EXISTE UN LIBRO CON ESTE NUMERO");
    }

    public void modificarTextoBorrrado(ListaEnlazada lista, int numeroDeLibro, Nodo textoBase) {   

        Nodo apuntador = lista.cabeza;
        while (apuntador != null) {
            if (apuntador.textoActual.getCodigo() == numeroDeLibro) {               
                apuntador.textoActual=textoBase.textoActual;
                break;
            }
            apuntador = apuntador.siguiente;
        }
        // System.out.println("NO EXISTE UN LIBRO CON ESTE NUMERO");
    }

    void modificarLibroSQL(String dato, int datoAModificar, int id) {
        String SQL = null;
        try {
            if (datoAModificar == 1) {
                // SQL="UPDATE libroshptas SET TITULO='"+dato+"' WHERE Id='"+dato+"'";//crear id
                // en mysql
                SQL = "UPDATE 100mil SET TITULO='" + dato + "' WHERE Id='" + dato + "'";// crear id en mysql

            }
            if (datoAModificar == 2) {
                // SQL="UPDATE libroshptas SET AUTOR='"+dato+"' WHERE Id='"+dato+"'";//crear id
                // en mysql
                SQL = "UPDATE 100mil SET AUTOR='" + dato + "' WHERE Id='" + dato + "'";// crear id en mysql
            }
            if (datoAModificar == 3) {
                // SQL="UPDATE libroshptas SET PUBLICACION='"+dato+"' WHERE
                // Id='"+dato+"'";//crear id en mysql
                SQL = "UPDATE 100mil SET PUBLICACION='" + dato + "' WHERE Id='" + dato + "'";// crear id en mysql
            }
            if (datoAModificar == 4) {
                // SQL="UPDATE libroshptas SET NUMEROPAGINAS='"+Integer.parseInt(dato)+"' WHERE
                // Id='"+dato+"'";//crear id en mysql
                SQL = "UPDATE libroshptas SET 100mil='" + Integer.parseInt(dato) + "' WHERE Id='" + dato + "'";
            }
            if (datoAModificar == 5) {
                // SQL="UPDATE libroshptas SET IDIOMA='"+dato+"' WHERE Id='"+dato+"'";//crear id
                // en mysql
                SQL = "UPDATE 100mil SET IDIOMA='" + dato + "' WHERE Id='" + dato + "'";// crear id en mysql
            }
            if (datoAModificar == 6) {
                // SQL="UPDATE libroshptas SET TEMA='"+dato+"' WHERE Id='"+dato+"'";//crear id
                // en mysql
                SQL = "UPDATE 100mil SET TEMA='" + dato + "' WHERE Id='" + dato + "'";// crear id en mysql
            }
            PreparedStatement pst = con.prepareStatement(SQL);
            pst.executeUpdate();
            // JOptionPane.showMessageDialog(null,"Registro exitoso");

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}
