package Presentacion.Factura;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Integracion.Factura.TLineaFactura;
import Negocio.Factura.TFactura;
import Negocio.Factura.TFacturaConPases;
import Negocio.Pase.TPase;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

public class VMostrarFacturaConPases extends JFrame implements IGUI {
	
	private TFacturaConPases carrito;
	public VMostrarFacturaConPases(TFacturaConPases venta) {
        super("Mostrar Factura");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = 900;
        int alto = 450;
        int x = (pantalla.width - ancho) / 2;
        int y = (pantalla.height - alto) / 2;
        this.setBounds(x, y, ancho, alto);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.carrito = venta;
        initGUI();
    }

	private void initGUI() {
		
		TFactura factura = carrito.getFactura(); 
		Set<TLineaFactura> listaCarrito = carrito.getLineasFactura();
		Set<TPase> pases = carrito.getPases();
		String msj = "Precio Total Factura: " + factura.GetPrecioTotal()+ "   || Fecha: " + factura.GetFechaCompra() + "   || Activo: "+ factura.GetActivo();
		
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
            	VMostrarFacturaConPases.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VFACTURA, null));
            }
        });
        panelBotones.add(botonCancelar);

        String[] nombreColumnas = { "ID Factura", "ID Pase","Fecha Pase ", "Hora Pase",  "Cantidad", "Precio "};
		JTable tabla = ComponentsBuilder.createTable(listaCarrito.size(), 6, nombreColumnas);
		int i = 0;
		
		Iterator<TLineaFactura> iteratorLineaFactura = listaCarrito.iterator();
		Iterator<TPase> pase = pases.iterator();
		while (iteratorLineaFactura.hasNext() && pase.hasNext()) {
		    TLineaFactura lfIterar = iteratorLineaFactura.next();
		    TPase paseIterar = pase.next();
		    tabla.setValueAt(lfIterar.GetIdFactura(), i, 0);
			tabla.setValueAt(lfIterar.GetIdPase(), i, 1);
			tabla.setValueAt(paseIterar.getFecha(), i, 2);
			tabla.setValueAt(paseIterar.getHora(), i, 3);
			tabla.setValueAt(lfIterar.GetCantidad(), i, 4);
			tabla.setValueAt(lfIterar.GetPrecio(), i, 5);
			i++;
		}
		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setBounds(50, 115, 900, 288);
		this.add(scroll);
		JPanel mensaje = new JPanel();
		mainPanel.add(mensaje);

		JLabel labelID = ComponentsBuilder.createLabel(msj, 10, 100, 80, 20, Color.BLACK);
		mensaje.add(labelID);

        this.setVisible(true);
        this.setResizable(true);
    }

    public void actualizar() {
    }

	@Override
	public void actualizar(Context res) {
		if (res.getDatos() != null) {
			this.carrito = (TFacturaConPases) res.getDatos();
			initGUI();
		}
		// TODO Auto-generated method stub
		
	}
}
