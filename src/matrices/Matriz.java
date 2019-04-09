/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matrices;

import java.awt.Dimension;
import java.util.Random;

/**
 *
 * @author galvez
 */
public class Matriz {
    private double[][]datos;
    private Random rnd = new Random();
    
    public Matriz(int filas, int columnas, boolean inicializarAleatorio){
        datos = new double[columnas][];
        for(int i=0; i<columnas; i++){
            datos[i] = new double[filas];
            if (inicializarAleatorio)
                for(int j=0; j<filas; j++)
                    datos[i][j] = rnd.nextInt(100);
        }
    }
    public Matriz(Dimension d, boolean inicializarAleatorio){
        this(d.height, d.width, inicializarAleatorio);
    }
    
    public Dimension getDimension(){
        return new Dimension(datos.length, datos[0].length);
    }
    
    public static Matriz sumarDosMatrices(Matriz a, Matriz b) throws DimensionesIncompatibles { 
        if(! a.getDimension().equals(b.getDimension())) throw new DimensionesIncompatibles("La suma de matrices requiere matrices de las mismas dimensiones");        
        int i, j, filasA, columnasA; 
        filasA = a.getDimension().height; 
        columnasA = a.getDimension().width; 
        Matriz matrizResultante = new Matriz(filasA, columnasA, false);
        for (j = 0; j < filasA; j++) { 
            for (i = 0; i < columnasA; i++) { 
                matrizResultante.datos[i][j] += a.datos[i][j] + b.datos[i][j]; 
            } 
        } 
        return matrizResultante; 
    }
    
    public static Matriz matrizInversa(Matriz a) throws DimensionesIncompatibles { 
        if(a.getDimension().height != a.getDimension().width || a.getDimension().height == 1) 
            throw new DimensionesIncompatibles("La inversa solo se puede calcular para matrices cuadradas de dimensión superior a 1");        
        int i, j, dimA;
        double det;
        dimA = a.getDimension().height; 
        Matriz matrizResultante = new Matriz(dimA, dimA, false);
        det = Matriz.determinante(a);
        if (det == 0) {
            System.out.println("El determinante es nulo, con lo que la matriz no es invertible");
        }
        else {
            matrizResultante = Matriz.matrizAdjuntos(a);
            double invDet = 1/det;
            for (j = 0; j < dimA; j++) {
                for (i = 0; i < dimA; i++) {
                    matrizResultante.datos[i][j] = invDet * matrizResultante.datos[i][j];
                }
            }
        } 
        return matrizResultante; 
    }
    
    public static double determinante(Matriz arr) {
        // Se asume que la matriz que se recibe como argumento es cuadrada
        int i, dim;
        double result;
        Matriz aux;
        result = 0;
        dim = arr.getDimension().height;
        
        if (dim == 1) {
            result = arr.datos[0][0];
        }
        else {
            for (i = 0; i < dim; i++) {
                aux = Matriz.subMatriz(0, i, arr);
                result += arr.datos[i][0] * Math.pow(-1, i) * Matriz.determinante(aux);
            }
        }
        return result;
    }
    
    public static Matriz matrizAdjuntos(Matriz arr) {
        // Se asume que la matriz que se recibe como argumento es cuadrada y de dimensión superior a 1
        int i, j, dim;
        dim = arr.getDimension().height;
        Matriz result, aux;
        result = new Matriz(dim, dim, false);
        for (j = 0; j < dim; j++) {
            for (i = 0; i < dim; i++) {
                aux = subMatriz(j, i, arr);
                // Trasponer el resultado, ya que la traspuesta de la matriz de cofactores es la matriz de adjuntos
                result.datos[j][i] = Math.pow(-1, i+j) * Matriz.determinante(aux);
            }
        }
        return result;
    }
    
    public static Matriz subMatriz(int fila, int col, Matriz arr) {
        // Se asume que la matriz que se recibe como argumento es cuadrada y de dimensión superior a 1
        int i, j, dim, auxFila;
        dim = arr.getDimension().height;
        Matriz result = new Matriz(dim-1, dim-1, false);
        for (j = 0; j < dim; j++) {
            if (j != fila) {
                auxFila = j < fila ? j : j - 1;
                for (i = 0; i < dim; i++) {
                    if (i < col) {
                        result.datos[i][auxFila] = arr.datos[i][j];
                    }
                    else if (i > col) {
                        result.datos[i-1][auxFila] = arr.datos[i][j];
                    }
                }
            }
        }
        return result;
    }

    @Override
    public String toString(){
        String ret = "";
        ret += "[\n";
        for (int i = 0; i < getDimension().width; i++) {
            ret += "(";
            for (int j = 0; j < getDimension().height; j++) {  
                ret += String.format("%.5f", datos[i][j]); 
                if (j != getDimension().height - 1) ret += ", ";
            } 
            ret += ")";
            if (i != getDimension().width - 1) ret += ",";
            ret += "\n";
        } 
        ret += "]\n";
        return ret;
    }
}
