package Presentacion.VentaJPA;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;


import Negocio.VentaJPA.TVenta;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class VListarVentasJPA extends JFrame implements IGUI{
	public VListarVentasJPA(List<TVenta> listaVentas) {
        super("Listar Todas las Ventas");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = 900;
        int alto = 450;
        int x = (pantalla.width - ancho) / 2;
        int y = (pantalla.height - alto) / 2;
        this.setBounds(x, y, ancho, alto);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initGUI(listaVentas);
    }

	private void initGUI(List<TVenta> listaVentas) {
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
            	VListarVentasJPA.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VVENTA, null));
            }
        });
        panelBotones.add(botonCancelar);

        String[] nombreColumnas = {"ID", "Precio Total", "Fecha de Compra","Método de Pago", "ID Trabajador","Activo"};
        JTable tabla = ComponentsBuilder.createTable(listaVentas.size(), 6, nombreColumnas);

        int i = 0;
        for (TVenta venta : listaVentas) {
            tabla.setValueAt(venta.getId(), i, 0);
            tabla.setValueAt(venta.getPrecio_total(), i, 1);
            tabla.setValueAt(venta.getFecha(), i, 2);
            tabla.setValueAt(venta.getMetodo_pago(), i, 3);
            tabla.setValueAt(venta.getIdTrabajador(), i, 4);
            tabla.setValueAt(venta.getActivo(), i, 5);
            
            i++;
        }

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(50, 115, 800, 288);
        this.add(scroll);

        this.setVisible(true);
        this.setResizable(true);
    }
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actualizar(Context res) {
		// TODO Auto-generated method stub
		
	}
}