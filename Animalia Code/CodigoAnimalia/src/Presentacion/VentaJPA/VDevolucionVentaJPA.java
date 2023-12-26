package Presentacion.VentaJPA;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import Integracion.Factura.TLineaFactura;
import Negocio.VentaJPA.TLineaVenta;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;
import Presentacion.Factura.VDevolverFactura;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class VDevolucionVentaJPA extends JFrame implements IGUI{
	
	 private JTextField idVentaField;
	 private JTextField idProductoField;
	 private JTextField unidadesField;
	 
	 public VDevolucionVentaJPA() {
	        super("Devolver venta");
	        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	        int ancho = 400;
	        int alto = 250;
	        int x = (pantalla.width - ancho) / 2;
	        int y = (pantalla.height - alto) / 2;
	        this.setBounds(x, y, ancho, alto);
	        this.setLayout(new BorderLayout());
	        this.setResizable(false);
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        initGUI();
	    }
	  
	private void initGUI() {
		 JPanel mainPanel = new JPanel();
	        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	        this.setContentPane(mainPanel);

	        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

	        // ID de la Venta
	        JPanel panelIDFactura = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        mainPanel.add(panelIDFactura);

	        JLabel labelIDFactura = ComponentsBuilder.createLabel("ID Venta: ", 10, 10, 80, 20, Color.BLACK);
	        panelIDFactura.add(labelIDFactura);

	        idVentaField = new JTextField();
	        idVentaField.setPreferredSize(new Dimension(250, 30));
	        panelIDFactura.add(idVentaField);

	        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

	        // ID del Producto
	        JPanel panelIDProducto = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        mainPanel.add(panelIDProducto);

	        JLabel labelIDPase = ComponentsBuilder.createLabel("ID Producto: ", 10, 10, 80, 20, Color.BLACK);
	        panelIDProducto.add(labelIDPase);

	        idProductoField = new JTextField();
	        idProductoField.setPreferredSize(new Dimension(250, 30));
	        panelIDProducto.add(idProductoField);

	        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

	        // Cantidad a Devolver
	        JPanel panelCantidad = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        mainPanel.add(panelCantidad);

	        JLabel labelCantidad = ComponentsBuilder.createLabel("Cantidad a Devolver: ", 10, 10, 150, 20, Color.BLACK);
	        panelCantidad.add(labelCantidad);

	        unidadesField = new JTextField();
	        unidadesField.setPreferredSize(new Dimension(250, 30));
	        panelCantidad.add(unidadesField);

	        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

	        // Botones
	        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
	        mainPanel.add(panelBotones);

	        JButton botonAceptar = new JButton("Aceptar");
	        botonAceptar.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	VDevolucionVentaJPA.this.setVisible(false);

	                try {
	                    int idVenta = Integer.parseInt(idVentaField.getText());
	                    int idProducto = Integer.parseInt(idProductoField.getText());
	                    int unidades = Integer.parseInt(unidadesField.getText());

	                    TLineaVenta lf = new TLineaVenta();
	                    lf.setIdVenta(idVenta);
	                    lf.setIdProducto(idProducto);
	                    lf.setUnidades(unidades);
	                    
	                    ApplicationController.getInstance().manageRequest(new Context(Evento.DEVOLVER_VENTA, lf));
	                } catch (NumberFormatException ex) {
		    			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, -4));
	                }
	            }
	        });
	        panelBotones.add(botonAceptar);

	        JButton botonCancelar = new JButton("Cancelar");
	        botonCancelar.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	VDevolucionVentaJPA.this.setVisible(false);
	                ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VVENTA, null));
	            }
	        });
	        panelBotones.add(botonCancelar);

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
				if (res.getEvento() == Evento.DEVOLVER_VENTA_OK) {
					ApplicationController.getInstance().manageRequest(new Context (Evento.V_DEVOLVER_VENTA_CORRECT, null));
				} else if (res.getEvento() == Evento.DEVOLVER_VENTA_KO) {
					ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) res.getDatos()));
				}
				dispose(); //Liberamos recursos de la memoria
		
	}
}