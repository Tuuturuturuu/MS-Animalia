package Presentacion.VentaJPA;

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

import Negocio.ProductoJPA.TAlimentacion;
import Negocio.ProductoJPA.TProducto;
import Negocio.VentaJPA.TLineaVenta;
import Negocio.VentaJPA.TVenta;
import Negocio.VentaJPA.TVentaConProductos;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

public class VMostrarVentaConProductos extends JFrame implements IGUI {
	private TVentaConProductos carrito;
	
	public VMostrarVentaConProductos(TVentaConProductos carritoVenta) {
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
        this.carrito = carritoVenta;
        initGUI();
    }
	
private void initGUI() {
		
		TVenta venta = carrito.gettVenta(); 
		Set<TLineaVenta> listaCarrito = carrito.gettLineaVenta();
		Set<TProducto> productos = carrito.gettProducto();
		String msj = "Precio Total Venta: " + venta.getPrecio_total()+ "   || Fecha: " + venta.getFecha() +  "   || Metodo de Pago: "+ venta.getMetodo_pago() + 
				"   || Activo: "+ venta.getActivo();
		
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
            	VMostrarVentaConProductos.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VVENTA, null));
            }
        });
        panelBotones.add(botonCancelar);

        String[] nombreColumnas = { "ID Venta", "ID Producto","Nombre Producto ", "Stock",  "Precio ", "Tipo"};
		JTable tabla = ComponentsBuilder.createTable(listaCarrito.size(), 6, nombreColumnas);
		int i = 0;
		
		Iterator<TLineaVenta> iteratorLineaFactura = listaCarrito.iterator();
		Iterator<TProducto> producto = productos.iterator();
		while (iteratorLineaFactura.hasNext() && producto.hasNext()) {
		    TLineaVenta lvIterar = iteratorLineaFactura.next();
		    TProducto productoIterar = producto.next();
		    tabla.setValueAt(lvIterar.getIdVenta(), i, 0);
			tabla.setValueAt(lvIterar.getIdProducto(), i, 1);
			tabla.setValueAt(productoIterar.getNombre(), i, 2);
			tabla.setValueAt(productoIterar.getStock(),i, 3);
			tabla.setValueAt(productoIterar.getPrecio(), i, 4);
			if(productoIterar instanceof TAlimentacion)
				tabla.setValueAt("Alimentacion", i, 5);
			else
				tabla.setValueAt("Merchadising", i, 5);
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

	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizar(Context res) {
		// TODO Auto-generated method stub
		if (res.getDatos() != null) {
			this.carrito = (TVentaConProductos) res.getDatos();
			initGUI();
		}
		
	}
}
