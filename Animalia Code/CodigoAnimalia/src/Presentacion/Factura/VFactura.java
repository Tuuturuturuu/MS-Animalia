package Presentacion.Factura;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Negocio.Factura.TCarrito;
import Negocio.Factura.TFactura;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;


@SuppressWarnings("serial")
public class VFactura extends JFrame implements IGUI{
	
	private JButton bAbrirFactura;
	private JButton bDevolverFactura;
	private JButton bModificarFactura;
	private JButton bMostrarFactura;
	private JButton bListarFacturas;
	private JButton backButton;
	private JPanel j;

	private TFactura tFactura;

	public VFactura(){
		super("Facturaia");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 1000;
		int alto = 525;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setLayout(null);
		j = new JPanel();
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initGUI();
		this.setVisible(true);
	}

	public void initGUI() {
		JLabel label = ComponentsBuilder.createLabel("Factura", 250, 30, 500, 50, Color.BLACK);
		this.add(label);
		
		//ABRIR Factura
		bAbrirFactura = ComponentsBuilder.createButton("Abrir Factura", 100, 100, 185, 100);
		bAbrirFactura.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VFactura.this.setVisible(false);
				TCarrito carrito = new TCarrito();
				ApplicationController.getInstance().manageRequest(new Context(Evento.VCERRAR_FACTURA, carrito));
			}

		});
		bAbrirFactura.setVisible(true);
		this.add(bAbrirFactura);
		
		//Devolver Factura
		bDevolverFactura = ComponentsBuilder.createButton("Devolver Factura", 300, 100, 185, 100);
		bDevolverFactura.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VFactura.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VDEVOLVER_FACTURA, null));
			}

		});
		bDevolverFactura.setVisible(true);
		this.add(bDevolverFactura);
		
		//MODIFICAR Factura
		bModificarFactura = ComponentsBuilder.createButton("Modificar Factura", 500, 100, 185, 100);
		bModificarFactura.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VFactura.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VMODIFICAR_FACTURA, null));
			}

		});
		bModificarFactura.setVisible(true);
		this.add(bModificarFactura);
		
		//MOSTRAR Factura
		bMostrarFactura = ComponentsBuilder.createButton("Mostrar Factura", 700, 100, 185, 100);
		bMostrarFactura.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VFactura.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VMOSTRAR_FACTURA, null));
			}

		});
		bMostrarFactura.setVisible(true);
		this.add(bMostrarFactura);
		
		//LISTAR Facturas
		bListarFacturas = ComponentsBuilder.createButton("Listar Facturas", 100, 250, 185, 100);
		bListarFacturas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VFactura.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_FACTURAS, null));
			}

		});
		bListarFacturas.setVisible(true);
		this.add(bListarFacturas);
		
		//BACK BUTTON
		backButton = ComponentsBuilder.createBackButton();
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VFactura.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_MAIN_VIEW,null));
				dispose();
			}
		});
		backButton.setVisible(true);
		this.add(backButton);
		
		getContentPane().add(j);


	}

	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizar(Context res) {
		// TODO Auto-generated method stub
		
	}
}
