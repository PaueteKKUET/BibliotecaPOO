package com.fadedbytes.bibliotecapoo.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public abstract class Producto {

    private static final Collection<Producto> PRODUCTOS = new ArrayList<>();

    protected final int id;

    private String titulo, autor;
    private int maxDiasReserva;

    private Collection<Generos> generos;

    protected Producto(@NotNull String titulo, @Nullable String autor, int maxDiasReserva, Generos... generos) {
        this.id = PRODUCTOS.size();
        this.titulo = titulo;
        this.autor = autor;
        this.maxDiasReserva = maxDiasReserva;
        this.generos = new ArrayList<>();

        this.generos.addAll(Arrays.asList(generos));

        PRODUCTOS.add(this);
    }

    public String getID() {
        return String.valueOf(getTipo()) + this.id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor == null ? "Anónimo" : autor;
    }

    public Collection<Generos> generos() {
        return this.generos;
    }

    public int getMaxDiasReserva() {
        return maxDiasReserva;
    }

    public String getGeneros() {
        StringBuilder generos = new StringBuilder();
        for (Generos genero : this.generos()) {
            generos.append(genero.name().toLowerCase());
        }

        return generos.toString();
    }

    protected abstract char getTipo();

    public abstract boolean puedeAlquilarse();

    public abstract void toDatabase();


    public static ArrayList<Producto> getProductos() {
        return new ArrayList<>(PRODUCTOS);
    }

    public static Producto getProducto(String id) {
        for (Producto producto : PRODUCTOS) {
            if (producto.getID().equals(id)) {
                return producto;
            }
        }
        return null;
    }

    public enum Generos {
        NOVELA,
        MISTERIO,
        INFORMATICA,
        CIENCIA,
        HISTORIA,
        PARAPSICOLOGIA,
        GATOS,
        CANARIOS
    }
}
