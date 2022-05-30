package com.fadedbytes.bibliotecapoo.api;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class Alquiler {

    private static final ArrayList<Alquiler> ALQUILERES = new ArrayList<>();

    private final int id;
    private final Producto PRODUCTO;
    private final Socio SOCIO;
    private final FechaRomana FECHA_ALQUILER;
    private FechaRomana fechaDevolucion;
    private boolean resuelto;

    public Alquiler(Producto producto, Socio socio) {
        if (!producto.puedeAlquilarse()) throw new IllegalArgumentException("El producto no puede alquilarse");
        this.id = ALQUILERES.size();
        this.PRODUCTO = producto;
        this.SOCIO = socio;
        this.FECHA_ALQUILER = FechaRomana.now();

        this.fechaDevolucion = FECHA_ALQUILER.sumarDias(producto.getMaxDiasReserva() + (socio.esVeterano() ? 5 : 0));

        this.resuelto = false;
        if (this.PRODUCTO instanceof Libro libro) {
            libro.ejemplaresAlquilados--;
        }
        ALQUILERES.add(this);
    }

    public boolean caducado() {
        return this.fechaDevolucion.esAnteriorA(FechaRomana.now());
    }

    public boolean estaResuelto() {
        return resuelto;
    }

    public void resolver() {
        if (this.PRODUCTO instanceof Libro libro) {
            libro.ejemplaresAlquilados--;
        }

        ALQUILERES.remove(this);
    }

    public Producto getProducto() {
        return this.PRODUCTO;
    }

    public Socio getSocio() {
        return this.SOCIO;
    }

    public static Alquiler byID(int id) {
        for (Alquiler alquiler : ALQUILERES) {
            if (alquiler.id == id) return alquiler;
        }
        return null;
    }

    public static ArrayList<Alquiler> getAlquileres() {
        return ALQUILERES;
    }

}
