package Presentacion.VentaJPA;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Negocio.VentaJPA.TCarritoJPA;
import Negocio.VentaJPA.TVenta;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;


public class VVenta extends JFrame implements IGUI{
	
	private JButton bAbrirVenta;
	private JButton bDevolverVenta;
	private JButton bModificarVenta;
	private JButton bMostrarVenta;
	private JButton bListarVentas;
	private JButton bListarVentasPorTrabajador;
	private JButton backButton;
	private JPanel j;

	private TVenta tVenta;
	
	public VVenta(){
		super("Venta");
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

	private void initGUI() {
		JLabel label = ComponentsBuilder.createLabel("Venta", 250, 30, 500, 50, Color.BLACK);
		this.add(label);
		
		//ABRIR Venta
		bAbrirVenta = ComponentsBuilder.createButton("Abrir Venta", 100, 100, 185, 100);
		bAbrirVenta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VVenta.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VCERRAR_VENTA_IDTRABAJADOR, null));
			}

		});
		bAbrirVenta.setVisible(true);
		this.add(bAbrirVenta);
		
		//Devolver Venta
		bDevolverVenta = ComponentsBuilder.createButton("Devolver Venta", 300, 100, 185, 100);
		bDevolverVenta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VVenta.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VDEVOLVER_VENTA, null));
			}

		});
		bDevolverVenta.setVisible(true);
		this.add(bDevolverVenta);
		
		//MODIFICAR Venta
		bModificarVenta = ComponentsBuilder.createButton("Modificar Venta", 500, 100, 185, 100);
		bModificarVenta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VVenta.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VMODIFICAR_VENTA, null));
			}

		});
		bModificarVenta.setVisible(true);
		this.add(bModificarVenta);
		
		//MOSTRAR Venta
		bMostrarVenta = ComponentsBuilder.createButton("Mostrar Venta", 700, 100, 185, 100);
		bMostrarVenta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VVenta.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VMOSTRAR_VENTA, null));
			}

		});
		bMostrarVenta.setVisible(true);
		this.add(bMostrarVenta);
		
		//LISTAR Ventas
		bListarVentas = ComponentsBuilder.createButton("Listar Ventas", 100, 250, 185, 100);
		bListarVentas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VVenta.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_VENTAS, null));
			}

		});
		bListarVentas.setVisible(true);
		this.add(bListarVentas);
		
		//LISTAR Ventas Por Trabajador
		bListarVentasPorTrabajador = ComponentsBuilder.createButton("Listar Ventas por Trabajador", 700, 250, 185, 100);
		bListarVentasPorTrabajador.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						VVenta.this.setVisible(false);
						ApplicationController.getInstance().manageRequest(new Context(Evento.V_LISTAR_VENTAS_POR_TRABAJADOR, null));
					}

				});
				bListarVentasPorTrabajador.setVisible(true);
				this.add(bListarVentasPorTrabajador);
		//BACK BUTTON
		backButton = ComponentsBuilder.createBackButton();
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VVenta.this.setVisible(false);
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

	}

}
