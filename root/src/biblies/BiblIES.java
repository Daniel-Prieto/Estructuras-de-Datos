/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package biblies;

import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author deste
 */
public class BiblIES {
    //=======================
    static long TInicio, TFin,div;
    static double total=1;
   
   
    //------------   1   --------- 1000.000 ------------- 10.000.000 -------------1.000.000.000 -------------
  // tiempo:  [nano]segundos // [mili]segundos  // [cen]tecimas de segundo // [s]egundos
  //---------------------------------------------------------------------------------------------------
  
      static String medida="milisegundos";
      
      
  
  static String timestamp(){
      return "TIEMPO ["+medida+"] de operacion: "+ (TFin-TInicio)/div +" "+medida;
  }
  //==========================
 
    static ConexionSQL cc=new ConexionSQL();
    static Connection con=cc.conexion();    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int asd= 12313;
        Object s=asd;
        
        int az = (Integer) s;
        Scanner scan = new Scanner(System.in);
        ListaEnlazada listacodigo=new ListaEnlazada();
        ListaEnlazada listatitulo=new ListaEnlazada();
        ListaEnlazada listaautor=new ListaEnlazada();
        arbolAVL Acodigo= new arbolAVL("codigo");
        Acodigo.activarRepetidos();
        arbolAVL Atitulo= new arbolAVL("titulo");
        Atitulo.activarRepetidos();
        arbolAVL Aautor= new arbolAVL("autor");
        Aautor.activarRepetidos();
        Cliente cliente1=new Cliente("Pregrado", 0, 1, "Daniel", "Prieto", "chupemestepenco@unal.edu.co", "1234");
        
        Admin nuevoAdmin=new Admin(4719301, "Notch", "Rodriguez", "NotchBoss99@gmail.com", "XContraseñaX");
        
         switch(medida){
            case "nanosegundos": div=1;
            break;
            case "milisegundos": div=1000000;
            break;
            case "centecimas": div=  10000000;
            break;
            case "segundos": div=    1000000000;
            break;
                
        }
        
        
        //long TInicio, TFin, tiempo;       
        //TInicio = System.nanoTime();
        //insertarLibrosSQL(nuevoAdmin,listacodigo,listatitulo,listaautor);
        //TFin = System.nanoTime(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        //tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
        //System.out.println("Tiempo de ejecución en milisegundos: " + tiempo); //Mostramos en pantalla el tiempo de ejecución en segundos

        //=======================================================================================

        insertarLibrosSQL_AVL(nuevoAdmin, Acodigo, Atitulo, Aautor);
       //Acodigo.inOrder();
        //=======================================================================================

        nuevoAdmin.crearLibro("CABALLO DE TROYA 1", "JJ BENITEZ", "22/02/2002", 213,"ESPAÑOL", "DRAMA/POLICIACO", listacodigo, listatitulo, listaautor);
        nuevoAdmin.crearLibro("CABALLO DE TROYA 2", "JJ BENITEZ", "22/02/2002", 213,"ESPAÑOL", "DRAMA/POLICIACO", listacodigo, listatitulo, listaautor);
        nuevoAdmin.crearLibro("CABALLO DE TROYA 3", "JJ BENITEZ", "22/02/2002", 213,"ESPAÑOL", "DRAMA/POLICIACO", listacodigo, listatitulo, listaautor);
        nuevoAdmin.crearLibro("CABALLO DE TROYA 4", "JJ BENITEZ", "22/02/2002", 213,"ESPAÑOL", "DRAMA/POLICIACO", listacodigo, listatitulo, listaautor);
        nuevoAdmin.crearLibro("CABALLO DE TROYA 5", "JJ BENITEZ", "22/02/2002", 213,"ESPAÑOL", "DRAMA/POLICIACO", listacodigo, listatitulo, listaautor);
           
        Consola consola= new Consola ();
        consola.setAdmin(nuevoAdmin);
        consola.setCliente(cliente1);
        consola.insertarListas(listacodigo, listatitulo, listaautor);
        consola.insertarArboles(Acodigo, Atitulo, Aautor);
        consola.encender();



        //=======================================================================================
         
         
    }

   static void insertarLibrosSQL(Admin administrador,ListaEnlazada listaCod,ListaEnlazada listaTitulo,ListaEnlazada listaAutor){        
       long TInicio = System.nanoTime();      
       
       //String SQL="select * from millon";
        String SQL="select * from libroshptas";
        //String SQL="select * from 10mil";
        try {
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(SQL);
            while(rs.next()){
                int codigo=rs.getInt(1);
                String titulo=rs.getString(2);
                String autor=rs.getString(3);
                String publicacion=rs.getString(4);
                int numeroPagina=rs.getInt(5);
                String idioma=rs.getString(6);
                String tema=rs.getString(7);
                //Date fechapublicacion=new Date(publicacion);
               // administrador.crearLibro(codigo,titulo, autor, publicacion, numeroPagina, idioma, tema, listaCod, listaTitulo, listaAutor);
                Texto libro = new Texto(codigo,titulo, autor, publicacion, numeroPagina, idioma, tema);
               // boolean mirador=arbolBst.insertar(libro,"autor");
               // System.out.println(mirador);

                if (rs.getInt(1)%10000==0){

                    System.out.println(rs.getInt(1));
                    
                    TFin = System.nanoTime();
                    double tiempo = (TFin - TInicio)/10000000;
                    System.out.println("Tiempo de ejecución del ingreso en centesimas de segundos de "+rs.getInt(1)+" datos: " + tiempo);
                    System.out.print("=======================");

                }
            }
        } catch (Exception e) {
            System.out.println("valio verga"+e);
        }
    }




    static void insertarLibrosSQL_AVL(Admin administrador,arbolAVL acodigo,arbolAVL atitulo,arbolAVL aautor){                 
        
        //String SQL="select * from millon";
        String SQL="select * from libroshptas limit 1000000";
        //String SQL="select * from 50mil";
         //String SQL="select * from 10mil";
         try {
             TInicio = System.nanoTime();
             Statement st=con.createStatement();
             ResultSet rs=st.executeQuery(SQL); //consulta
             TFin = System.nanoTime();
             System.out.println("Duracion de la consulta SQL--> "+timestamp()+"\n\n");
              TInicio = System.nanoTime();  
              long TINICIO=System.nanoTime();
             while(rs.next()){
                 int codigo=rs.getInt(1);
                 String titulo=rs.getString(2);
                 String autor=rs.getString(3);
                 String publicacion=rs.getString(4);
                 int numeroPagina=rs.getInt(5);
                 String idioma=rs.getString(6);
                 String tema=rs.getString(7);
                 
                //creacion
                 Texto libro = new Texto(codigo,titulo, autor, publicacion, numeroPagina, idioma, tema);
                
                // INSERCCION //
                administrador.crearLibroAVL(libro, acodigo, atitulo, aautor);
                //===========//
 
                 if (rs.getInt(1)%10000==0){
 
                     //System.out.println("COUNTER: "+rs.getInt(1));
                     TFin = System.nanoTime();
                     System.out.println(""+rs.getInt(1)+" Textos ingresados: \n" + timestamp());
                     System.out.println("=======================");
                      TInicio = System.nanoTime();  
 
                 }
             }
             long TFIN= System.nanoTime();
             double t =(TFIN-TINICIO)/div;
             System.out.println("\nTIEMPO TOTAL DE INSERCCION DE TEXTOS A LOS ARBOLES: "+ t + " "+medida);
         } catch (Exception e) {
             System.out.println("valio verga"+e);
         }
     }
}
    
