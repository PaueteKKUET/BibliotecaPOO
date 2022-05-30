package com.fadedbytes.bibliotecapoo.api;

import com.fadedbytes.bibliotecapoo.database.DatabaseManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;
import java.sql.Statement;

public class Revista extends Producto {

    private final int numeroPublicacion;

    public Revista(@NotNull String titulo, @Nullable String autor, int maxDiasReserva, int numeroPublicacion, Generos... generos) {
        super(titulo, autor, maxDiasReserva, generos);
        this.numeroPublicacion = numeroPublicacion;
    }

    public int getNumeroPublicacion() {
        return numeroPublicacion;
    }


    @Override
    protected char getTipo() {
        return 'R';
    }

    @Override
    public boolean puedeAlquilarse() {
        return true;
    }

    @Override
    public void toDatabase() {
        Statement query = DatabaseManager.getStatement();

        try {
            query.execute(
                    String.format(
                            """
                                    INSERT INTO ejemplar VALUES
                                    (%s, %s, %s, %s, %s, 0, 0, %s)
                                    """,
                            getID(),
                            getTitulo(),
                            getAutor(),
                            getGeneros(),
                            String.valueOf(getMaxDiasReserva()),
                            String.valueOf(numeroPublicacion)
                    )
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
