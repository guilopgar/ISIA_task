package tests;

import java.util.logging.Level;
import java.util.logging.Logger;
import matrices.DimensionesIncompatibles;
import matrices.Matriz;

public class Matrices {

    public static void main(String[] args) {
        Matriz m1 = new Matriz(3, 4, true);
        System.out.println(m1);
        Matriz m2 = new Matriz(3, 4, true);
        System.out.println(m2);
        try {
            System.out.println(Matriz.sumarDosMatrices(m1, m2));
        } catch (DimensionesIncompatibles ex) {
            ex.printStackTrace();
        }
        
        System.out.println("Test Multiplicar:");
        Matriz m3 = new Matriz(3, 4, true);
        System.out.println(m3);
        Matriz m4 = new Matriz(4, 3, true);
        System.out.println(m4);
        try {
            System.out.println(Matriz.multiplicarDosMatrices(m3, m4));
        } catch (DimensionesIncompatibles ex) {
            ex.printStackTrace();
        }
        System.out.println("Test inversa:");
        Matriz m5 = new Matriz(5, 5, true);
        System.out.println(m5);
        try {
            System.out.println(Matriz.matrizInversa(m5));

        } catch (DimensionesIncompatibles ex) {
            ex.printStackTrace();
        }
    }
    
}
