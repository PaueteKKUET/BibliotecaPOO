package com.fadedbytes.bibliotecapoo;

import com.fadedbytes.bibliotecapoo.api.*;
import com.fadedbytes.bibliotecapoo.database.DatabaseManager;
import com.fadedbytes.bibliotecapoo.gui.MenuPrincipal;

import javax.swing.*;

public class Start {

    public static void main(String[] args) {

        DatabaseManager.connect();
        insertarDatos();


        JFrame frame = new JFrame("MenuPrincipal");
        frame.setContentPane(new MenuPrincipal().background);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(450, 300);
        frame.setVisible(true);
    }

    private static void insertarDatos() {

        Socio[] socios = {
                new Socio("12345678N", "Juan", "Martínez Martínez", FechaRomana.fromGregoriano(10, 6, 2019)),
                new Socio("87654321H", "Ana", "García García", FechaRomana.fromGregoriano(5, 11, 2021)),
                new Socio("25814763L", "Susana", "Castillo Navarro", FechaRomana.fromGregoriano(13, 4, 2022))
        };

        Producto[] productos = {
                new Libro("Pinocho", "Carlo Lorenzini",10, 1, Producto.Generos.NOVELA, Producto.Generos.MISTERIO),
                new Libro("Java y Eclipse", "Federico cómo dices se escribe eso?", 20, 2, Producto.Generos.INFORMATICA, Producto.Generos.CIENCIA),
                new Libro("Anno Cero", "Varios", 30, 3, Producto.Generos.HISTORIA),
                new Revista("Computer Hoy", "Varios", 40, 7, Producto.Generos.INFORMATICA),
                new Revista("El ayer", "Varios", 50, 21, Producto.Generos.PARAPSICOLOGIA)
        };

        /*
        for (Producto producto : productos) {
            producto.toDatabase();
        }
        */
    }

}
