package Controlador;

import java.util.Random;

public class GeneradorCodigoUnico {
    public static void main(String[] args) {
        String codigo = generarCodigoUnico();
        System.out.println("Código único: " + codigo);
    }

    public static String generarCodigoUnico() {
        Random rand = new Random();
        int min = 10000000; // El número más pequeño de 8 dígitos
        int max = 99999999; // El número más grande de 8 dígitos
        int codigoAleatorio = rand.nextInt((max - min) + 1) + min;
        return String.valueOf(codigoAleatorio);
    }
}
