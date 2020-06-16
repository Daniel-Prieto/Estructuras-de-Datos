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
public class Articulo extends Texto{

    String index;
    
    public Articulo(){        
    }

    @Override
    public String toString() {
        return "Articulo{" + "index=" + index + '}';
    }

    public Articulo(String index){        
        this.index=index;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
