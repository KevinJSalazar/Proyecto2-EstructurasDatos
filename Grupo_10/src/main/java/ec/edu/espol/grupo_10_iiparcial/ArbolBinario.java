/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.grupo_10_iiparcial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author CltControl
 */
public class ArbolBinario<E extends Comparable<E>>{
    Nodo raiz;
    public class Nodo{
        E contenido;
        ArbolBinario<E> izq;
        ArbolBinario<E> der;
        
        public Nodo(E e){
            this.contenido = e;
            izq = der = null;
        }
    }
    
    public ArbolBinario(){
        clear();
    }
    public ArbolBinario(E contenido){
        clear();
        this.raiz = new Nodo(contenido);
    }
    public void clear(){
        this.raiz = null;
    }
    public boolean isEmpty(){
        return this.raiz == null;
    }
    public ArbolBinario getNodoIzq(){
        if(this.raiz.izq != null)
            return this.raiz.izq;
        return null;
    }
    public ArbolBinario getNodoDer(){
        if(this.raiz.der != null)
            return this.raiz.der;
        return null;
    }
    
    public E getContenido(){
        if(this.raiz.contenido != null)
            return this.raiz.contenido;
        return null;
    }
    public void setContenido(E contenido){
        this.raiz = new Nodo(contenido);
    }
    
    public boolean esHoja(){
        if(isEmpty())
            return false;
        return this.raiz.der == null && this.raiz.izq == null;
    }
    public int altura(){
        if(isEmpty())
            return 0;
        if(esHoja())
            return 1;
        int alturaIzq = (this.raiz.izq!=null)? this.raiz.izq.altura() : 0;
        int alturaDer = (this.raiz.der!=null)? this.raiz.der.altura() : 0;
        return 1 + Math.max(alturaIzq, alturaDer);
    }
    public boolean addLeft(ArbolBinario ab){
        if(this.raiz.izq!=null)
            return false;
        this.raiz.izq = ab;
        return true;
    }
    public boolean addRight(ArbolBinario ab){
        if(this.raiz.der!=null)
            return false;
        this.raiz.der = ab;
        return true;
    }
    
    public ArrayList<E> recorridoPreOrden(){
        if(this.isEmpty())
            return null;
        ArrayList<E> recorrido = new ArrayList<>();
        recorrido.add(this.raiz.contenido);
        if(this.raiz.izq!=null) recorrido.addAll(this.raiz.izq.recorridoPreOrden());
        if(this.raiz.der!=null) recorrido.addAll(this.raiz.der.recorridoPreOrden());
        return recorrido;
    }
    
    public List<E> respuestasPorRecorrido(List<E> respuestas){
        ArrayList<E> posiblesRespuesta = new ArrayList<>();
        if(this.esHoja()){
            posiblesRespuesta.add(this.raiz.contenido);
            return posiblesRespuesta;
        }
        else if(respuestas.isEmpty()){
            posiblesRespuesta.addAll(this.buscarHojas());
            return posiblesRespuesta;
        }
        E respSiNo = respuestas.remove(0);
        List<E> posRes = new ArrayList<E>();
        if(respSiNo.equals("si") && this.raiz.izq!=null)
            posRes = this.raiz.izq.respuestasPorRecorrido(respuestas);
        if(respSiNo.equals("no") && this.raiz.der!=null)
            posRes = this.raiz.der.respuestasPorRecorrido(respuestas);
        if(posRes != null)
            posiblesRespuesta.addAll(posRes);
        else
            return null;
        return posiblesRespuesta;
    }
    
    public List<E> buscarHojas(){
        if(this.isEmpty())
            return null;
        ArrayList<E> hojas = new ArrayList<>();
        if(this.esHoja())
            hojas.add(this.raiz.contenido);
        if(this.raiz.izq!=null) hojas.addAll(this.raiz.izq.buscarHojas());
        if(this.raiz.der!=null) hojas.addAll(this.raiz.der.buscarHojas());
        return hojas;
    }
    
    public void añadirHojas(E hoja, List<E> recorrido){
        E camino = recorrido.remove(0);
        if(recorrido.isEmpty()){
            if(camino.equals("si"))
                this.addLeft(new ArbolBinario(hoja));
            else if(camino.equals("no"))
                this.addRight(new ArbolBinario(hoja));
        }
        else{
            if(camino.equals("si") && this.raiz.izq!=null)
                this.raiz.izq.añadirHojas(hoja, recorrido);
            else if(camino.equals("no") && this.raiz.der!=null)
                this.raiz.der.añadirHojas(hoja, recorrido);
        }
    }
}
