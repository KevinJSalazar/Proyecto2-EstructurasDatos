/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.grupo_10_iiparcial;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author evin
 */
public class CrearArbol {
    
    public static void crearArbolDeDecisiones(ArbolBinario<String> arbol, HashMap<String, List<String>> animales) {
        for(Map.Entry<String, List<String>> entrada : animales.entrySet()){
            crearArbolDeAnimal(arbol, entrada.getValue(), entrada.getKey());
        }
//        System.out.println(arbol.recorridoPreOrden());
    }
    
    public static void crearArbolDeAnimal(ArbolBinario<String> arbol, List<String> respuestas, String animal) {
        if(respuestas.isEmpty() && arbol.esHoja()){
            arbol.raiz.contenido = animal;
            return;
        }
        String respSiNo = respuestas.remove(0);
        if(respSiNo.equals("si")){
            if(arbol.getNodoIzq()==null)
                arbol.addLeft(new ArbolBinario(""));
            crearArbolDeAnimal(arbol.getNodoIzq(), respuestas, animal);
        }
        if(respSiNo.equals("no")){
            if(arbol.getNodoDer()==null)
                arbol.addRight(new ArbolBinario(""));
            crearArbolDeAnimal(arbol.getNodoDer(), respuestas, animal);
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
        try(Scanner sc = new Scanner(new File("archivos\\preguntas.txt"))){
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
        try(Scanner sc = new Scanner(new File("archivos\\respuestas.txt"))){
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
