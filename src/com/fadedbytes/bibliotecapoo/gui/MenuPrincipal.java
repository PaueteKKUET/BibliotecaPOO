package com.fadedbytes.bibliotecapoo.gui;

import com.fadedbytes.bibliotecapoo.api.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MenuPrincipal {


    private JTabbedPane tabAlquileres;
    public JPanel background;
    private JPanel tabBiblioteca;
    private JButton butNuevoProducto;
    private JButton butNuevoSocio;
    private JTable tablaProductos;
    private JButton butCargarBBDD;
    private JPanel tabProductos;
    private JButton butAlquilar;
    private JTable tablaAlquiler;
    private JButton resolverButton;
    private JButton consultarAlquilerumButton;

    public MenuPrincipal() {
        butNuevoProducto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nuevoProducto();
            }
        });
        butNuevoSocio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nuevoSocio();
            }
        });
        butCargarBBDD.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarProductos();
            }
        });
        butAlquilar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                alquilar();
            }
        });
        consultarAlquilerumButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tablaAlquileres();
            }
        });
        resolverButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                resolverAlquiler();
            }
        });
    }

    private void nuevoProducto() {

    }

    private void nuevoSocio() {
        String DNI = JOptionPane.showInputDialog("Introduce el DNI del nuevo socio");
        String nombre = JOptionPane.showInputDialog("Introduce el nombre del nuevo socio");
        String apellidos = JOptionPane.showInputDialog("Introduce los apellidos del nuevo socio");

        try {
            new Socio(DNI, nombre, apellidos, FechaRomana.now());
            JOptionPane.showMessageDialog(null, "Socio creado correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al introducir los datos");
        }
    }

    private static final String[] tableColumns = {
            "ID",
            "Título",
            "Autor",
            "Género",
            "Días de reserva",
            "Ejemplares",
            "Disponibles",
            "Num publicación"
    };
    private void cargarProductos() {
        ArrayList<Producto> productos = new ArrayList<>(Producto.getProductos());
        Object[][] data = new Object[productos.size()][];
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            if (producto instanceof Libro libro) {
                data[i] = new Object[]{
                        libro.getID(),
                        libro.getTitulo(),
                        libro.getAutor(),
                        libro.getGeneros(),
                        libro.getMaxDiasReserva(),
                        libro.ejemplaresTotales(),
                        libro.ejemplaresDisponibles(),
                        "-"
                };
            } else if (producto instanceof Revista revista) {
                data[i] = new Object[]{
                        producto.getID(),
                        producto.getTitulo(),
                        producto.getAutor(),
                        producto.getGeneros(),
                        producto.getMaxDiasReserva(),
                        "-",
                        "-",
                        revista.getNumeroPublicacion()
                };
            }
        }
        tablaProductos.setModel(new DefaultTableModel(data, tableColumns));
    }

    private void alquilar() {
        int row = tablaProductos.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona un producto primero");
            return;
        }

        Socio socio = Socio.getSocio(JOptionPane.showInputDialog("Introduce el DNI del socio"));

        if (socio == null) {
            JOptionPane.showMessageDialog(null, "No existe el socio");
            return;
        }

        new Alquiler(Producto.getProducto(tablaProductos.getValueAt(row, 0).toString()), socio);
        JOptionPane.showMessageDialog(null, "Alquiler realizado correctamente");
    }

    String[] columnasAlquiler = {
            "Producto",
            "Socio",
            "Caducado"
    };
    private void tablaAlquileres() {
        ArrayList<Alquiler> alquileres = new ArrayList<>(Alquiler.getAlquileres());
        Object[][] data = new Object[alquileres.size()][];
        for (int i = 0; i < alquileres.size(); i++) {
            Alquiler alquiler = Alquiler.byID(i);
            data[i] = new Object[]{
                    alquiler.getProducto().getTitulo() + " - " + alquiler.getProducto().getAutor(),
                    alquiler.getSocio().getNombre() + " " + alquiler.getSocio().getApellidos(),
                    alquiler.caducado() ? "Sí" : "No"
            };

        }
        tablaProductos.setModel(new DefaultTableModel(data, columnasAlquiler));
    }

    private void resolverAlquiler() {
        int row = tablaAlquiler.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona un alquiler primero");
            return;
        }

        Alquiler.byID(Integer.parseInt(tablaAlquiler.getValueAt(row, 0).toString())).resolver();
        JOptionPane.showMessageDialog(null, "Alquiler resuelto. Actualiza para verificar");
    }
}
