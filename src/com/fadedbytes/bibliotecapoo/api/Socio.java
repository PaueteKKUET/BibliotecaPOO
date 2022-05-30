package com.fadedbytes.bibliotecapoo.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class Socio {

    private static final HashMap<String, Socio> SOCIOS = new HashMap<>();

    private final String DNI;
    private String nombre;
    private String apellidos;
    private FechaRomana fechaRegistro;

    public Socio(@NotNull String DNI, @NotNull String nombre, @Nullable String apellidos, @NotNull FechaRomana fechaRegistro) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaRegistro = fechaRegistro;

        SOCIOS.put(DNI, this);
    }

    public String getDNI() {
        return DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public FechaRomana getFechaRegistro() {
        return fechaRegistro;
    }

    public boolean esVeterano() {
        return this.getFechaRegistro().getAnnus() < 2773; // 2773 es el 2020 en el calendario romano
    }


    public static ArrayList<Socio> getSocios() {
        return new ArrayList<>(SOCIOS.values());
    }

    public static Socio getSocio(String DNI) {
        return SOCIOS.get(DNI);
    }

}
