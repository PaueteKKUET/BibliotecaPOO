package com.fadedbytes.bibliotecapoo.api;

import com.fadedbytes.bibliotecapoo.database.DatabaseManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;
import java.sql.Statement;

public class Libro extends Producto {

    private int ejemplares;
    int ejemplaresAlquilados;


    public Libro(@NotNull String titulo, @Nullable String autor, int maxDiasReserva, int ejemplares, Generos... generos) {
        super(titulo, autor, maxDiasReserva, generos);
    }

    public int ejemplaresTotales() {
        return ejemplares;
    }

    public int ejemplaresDisponibles() {
        return ejemplares - ejemplaresAlquilados;
    }

    @Override
    protected char getTipo() {
        return 'L';
    }

    @Override
    public boolean puedeAlquilarse() {
        return ejemplaresDisponibles() > 0;
    }

    @Override
    public void toDatabase() {
        Statement query = DatabaseManager.getStatement();

        try {
            query.execute(
                    String.format(
                            """
                                    INSERT INTO ejemplar VALUES
                                    (%s, %s, %s, %s, %s, %s, %s, 0)
                                    """,
                            getID(),
                            getTitulo(),
                            getAutor(),
                            getGeneros(),
                            String.valueOf(getMaxDiasReserva()),
                            String.valueOf(ejemplaresTotales()),
                            String.valueOf(ejemplaresDisponibles())
                    )
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
