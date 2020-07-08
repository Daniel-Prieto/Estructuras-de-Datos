/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bibliies;

/**
 *
 * @author lizan
 */
public class Pila<T,H,I> {//PILA TRIPLE

    private final int CAP;
    private int SIZE;
    private T[] GPila;
    //NUEVO
    private H[] GPila1;
    private I[] GPila2;

    public Pila(int _CAP) {
        this.CAP = _CAP;
        SIZE = 0;
        this.GPila = (T[]) new Object[this.CAP];
        //NUEVO
        this.GPila1 = (H[]) new Object[this.CAP];
        this.GPila2 = (I[]) new Object[this.CAP];

    }

    public void push(T item,H item1,I item2) {
        if (isFull()) {
            T[] arrayT = (T[]) new Object[this.CAP * 2];
            for (int i = 0; i < this.SIZE; i++) {
                arrayT[i] = GPila[i];
            }
            this.GPila = arrayT;
            this.GPila[SIZE]=item;
           
            //------------------------------------------
            //NUEVO 1
            H[] arrayT1 = (H[]) new Object[this.CAP * 2];
            for (int i = 0; i < this.SIZE; i++) {
                arrayT1[i] = GPila1[i];
            }
            this.GPila1 = arrayT1;
            this.GPila1[SIZE]=item1;
            
            //NUEVO 2
            I[] arrayT2 = (I[]) new Object[this.CAP * 2];
            for (int i = 0; i < this.SIZE; i++) {
                arrayT2[i] = GPila2[i];
            }
            this.GPila2 = arrayT2;
            this.GPila2[SIZE]=item2;
            //----------------------------------------
            
            //viejo ultimo
            this.SIZE++;
            
            //push(item); mejor se agrega de una vez el item y se evita un bucle infinito
            
        } else {

            GPila[SIZE] = item;
            //-----------------------------------------
            //NUEVO
            GPila1[SIZE] = item1;
            GPila2[SIZE] = item2;
            //-------------------------
            this.SIZE++;
        }
    }

    public Object[] pop() {
        Object[] x=new Object[3];
        T item = null;
        //nuevo--------------------------
        H item1=null;
        I item2=null;
        //----------------------------------
        if (this.SIZE > 0) {
            item = GPila[SIZE - 1];
            GPila[SIZE - 1] = null;
            //-------------------
            //NUEVO 1
            item1 = GPila1[SIZE - 1];
            GPila1[SIZE - 1] = null;
            //NUEVO 2
            item2 = GPila2[SIZE - 1];
            GPila2[SIZE - 1] = null;
            //------------------
            this.SIZE--;

        }
        x[0]=item;
        x[1]=item1;
        x[2]=item2;
        return x;
    }

    public T peek(int index) {
        return this.GPila[index];
    }

    public T top() {
        return this.GPila[this.SIZE - 1];
    }

    public int cap() {
        return this.CAP;
    }

    public T bottom() {
        return this.GPila[0];
    }

    public boolean isFull() {
        return (this.CAP == this.SIZE);
    }

    public boolean isEmpty() {
        return (this.SIZE == 0);
    }

    public int getSize() {
        return this.SIZE;
    }

}
