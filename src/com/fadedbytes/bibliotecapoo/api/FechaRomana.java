package com.fadedbytes.bibliotecapoo.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * No está terminado, pero la idea es hacer un calendario de 12 días
 * que bienalmente tiene un mes más.
 */
public class FechaRomana {

    private final byte dies;
    private final MesRomano mensis;
    private final int annus;

    private FechaRomana(byte dies, MesRomano mensis, int annus) {
        this.dies = dies;
        this.mensis = mensis;
        this.annus = annus;
    }

    public int getDies() {
        return dies;
    }

    public MesRomano getMensis() {
        return mensis;
    }

    public int getAnnus() {
        return annus;
    }

    public FechaRomana sumarDias(int dias) {
        FechaRomana nuevaFecha = null;
        if (dias + this.dies > this.mensis.number()) {
            // TODO
        } else {
            return new FechaRomana((byte) (this.dies + dias), this.mensis, this.annus);
        }

        return nuevaFecha;
    }

    public boolean esAnteriorA(FechaRomana fecha) {
        if (this.annus < fecha.annus) {
            return true;
        } else if (this.annus > fecha.annus) {
            return false;
        }

        if (this.mensis.number() < fecha.mensis.number()) {
            return true;
        } else if (this.mensis.number() > fecha.mensis.number()) {
            return false;
        }

        return this.dies < fecha.dies;
    }

    public boolean esPosteriorA(FechaRomana fecha) {
        return !this.equals(fecha) && !this.esAnteriorA(fecha);
    }

    public boolean esMercedonio() {
        return this.annus % 2 == 0;
    }

    public static FechaRomana now() {
        return fromGregoriano(
                LocalDate.now().getDayOfMonth(),
                LocalDate.now().getMonthValue(),
                LocalDate.now().getYear()
        );
    }

    public static FechaRomana fromGregoriano(int dies, int mensis, int annus) {
        // TODO
        return new FechaRomana((byte) dies, MesRomano.fromNumber(mensis), annus);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FechaRomana fecha) {
            return this.annus == fecha.annus && this.mensis == fecha.mensis && this.dies == fecha.dies;
        }
        return false;
    }

    public enum MesRomano {
        MARTIUS(31),
        APRILIS(29),
        MAIUS(31),
        JUNIUS(29),
        QUINTILIS(31),
        SEXTILIS(29),
        SEPTEMBRIS(29),
        OCTOBRIS(31),
        NOVEMBRIS(29),
        DECEMBRIS(29),
        IANUARIUS(29),
        FEBRUARIUS(28),

        MERCEDONIUS(22);

        int dias;

        private MesRomano(int dias) {
            this.dias = dias;
        }

        public static MesRomano fromNumber(int mes) {
            mes --;
            if (mes < 0 || mes > MesRomano.class.getEnumConstants().length - 1) {
                return MERCEDONIUS;
            }
            return MesRomano.class.getEnumConstants()[mes - 1];
        }

        public int number() {
            ArrayList<MesRomano> meses = new ArrayList(List.of(MesRomano.class.getEnumConstants()));
            return meses.indexOf(this) + 1;
        }

        public int dias() {
            return this.dias;
        }
    }
}
