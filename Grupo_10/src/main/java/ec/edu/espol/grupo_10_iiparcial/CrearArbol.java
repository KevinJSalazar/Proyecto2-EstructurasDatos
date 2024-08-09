/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.grupo_10_iiparcial;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author evin
 */
public class CrearArbol {
    
    public static void crearArbolDeDecisiones(ArbolBinario<String> arbol, ArrayList<String> preguntas, int indice) {
        if(indice >= preguntas.size()) {
            return;
        }
        if(arbol.getNodoIzq() == null) {
            arbol.addLeft(new ArbolBinario<String>(preguntas.get(indice)));
            crearArbolDeDecisiones(arbol.getNodoIzq(), preguntas, indice + 1);
        } if(arbol.getNodoDer() == null) {
            arbol.addRight(new ArbolBinario<String>(preguntas.get(indice)));
            crearArbolDeDecisiones(arbol.getNodoDer(), preguntas, indice + 1);
        }
    }
    
    public static void añadirAnimales(ArbolBinario<String> arbol, HashMap<String, List<String>> respuestas){
        for(String animal : respuestas.keySet()){
            System.out.println(animal);
            arbol.añadirHojas(animal, respuestas.get(animal));
            System.out.println(arbol.recorridoPreOrden());
        }
    }
    
    public static int getIndicePregunta(List<String> preguntas, String pregunta){
        for(int i = 0; i < preguntas.size(); i++){
            if(preguntas.get(i).equals(pregunta))
                return i;
        }
        return -1;
    }
    
    public static List<String> crearListaRespuesta(int tamaño){
        ArrayList<String> respuestasUsuario = new ArrayList<>();
        for(int i = 0; i < tamaño; i++)
            respuestasUsuario.add("NoInfo");
        return respuestasUsuario;
    }
    
    public static List<String> leerPreguntas(){
        ArrayList<String> preguntas = new ArrayList<>();
        try(Scanner sc = new Scanner(new File("preguntas.txt"))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                preguntas.add(linea);
            }
        }
        catch(Exception e){
        }
        return preguntas;
    }
    
    public static HashMap<String, List<String>> leerRespuestas(){
    HashMap<String, List<String>> respuestas = new HashMap<String, List<String>>();
        try(Scanner sc = new Scanner(new File("respuestas.txt"))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] data = linea.split(" ");
                String animal = data[0];
                respuestas.put(animal, new ArrayList<String>());
                for(int i =1; i < data.length; i++){
                    respuestas.get(animal).add(data[i]);
                }
            }
        }
        catch(Exception e){
        }
        return respuestas;
    }
}
