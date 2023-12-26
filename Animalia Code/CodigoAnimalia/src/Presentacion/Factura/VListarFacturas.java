package Presentacion.Factura;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;

import Negocio.Factura.TFactura;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

@SuppressWarnings("serial")
public class VListarFacturas extends JFrame implements IGUI {

    public VListarFacturas(Set<TFactura> listaFacturas) {
        super("Listar Todas las Facturas");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = 900;
        int alto = 450;
        int x = (pantalla.width - ancho) / 2;
        int y = (pantalla.height - alto) / 2;
        this.setBounds(x, y, ancho, alto);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initGUI(listaFacturas);
    }

	private void initGUI(Set<TFactura> listaFacturas) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.setContentPane(mainPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        JPanel panelID = new JPanel();
        mainPanel.add(panelID);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        JPanel panelBotones = new JPanel();
        mainPanel.add(panelBotones);

        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.setBounds(200, 50, 100, 100);
        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VListarFacturas.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VFACTURA, null));
            }
        });
        panelBotones.add(botonCancelar);

        String[] nombreColumnas = {"ID", "Precio Total", "Fecha de Compra", "Activo"};
        JTable tabla = ComponentsBuilder.createTable(listaFacturas.size(), 4, nombreColumnas);

        int i = 0;
        for (TFactura factura : listaFacturas) {
            tabla.setValueAt(factura.GetId(), i, 0);
            tabla.setValueAt(factura.GetPrecioTotal(), i, 1);
            tabla.setValueAt(factura.GetFechaCompra(), i, 2);
            tabla.setValueAt(factura.GetActivo(), i, 3);
            i++;
        }

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(50, 115, 800, 288);
        this.add(scroll);

        this.setVisible(true);
        this.setResizable(true);
    }

    public void actualizar() {
    }

	@Override
	public void actualizar(Context res) {
		// TODO Auto-generated method stub
		
	}
}
