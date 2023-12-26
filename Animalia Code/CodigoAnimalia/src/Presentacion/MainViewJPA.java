package Presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

@SuppressWarnings("serial")
public class MainViewJPA extends JFrame implements IGUI{
	//BOTONES
		JButton Bproveedor;
		JButton Bmarca;
		JButton Bproducto;
		JButton Btrabajador;
		JButton Bdepartamento ;
		JButton Bventa;
		JLabel label;
		
		public MainViewJPA(){
			super("Tienda Animalia");
			Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
			int ancho = 1000;
			int alto = 540;
			int x = (pantalla.width - ancho) / 2;
			int y = (pantalla.height - alto) / 2;
			this.setBounds(x, y, ancho, alto);
			// this.setBounds(100, 100, 1000, 525);
			// this.setBounds(100, 100, 950, 500);
			this.setLayout(null);
			this.setResizable(false);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			initGUI();
			this.setVisible(true);
		}
	private void initGUI() {
		label = ComponentsBuilder.createLabel("Selecciona el modulo sobre el cual quieres trabajar", 50, 30, 900, 50, Color.BLACK);
		this.add(label);
		
		//PROVEEDOR
		Bproveedor= ComponentsBuilder.createButton("Proveedor", 100, 120, 185, 100);
		Bproveedor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainViewJPA.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VPROVEEDOR, null));

			}
		});
		
		this.add(Bproveedor);
	
		//MARCA
		Bmarca = ComponentsBuilder.createButton("Marca de Producto", 407, 120, 185, 100);
		Bmarca.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainViewJPA.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VMARCA, null));

			}
		});

		this.add(Bmarca);
		
		//PRODUCTO
		Bproducto = ComponentsBuilder.createButton("Producto", 715, 120, 185, 100);
		Bproducto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainViewJPA.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VPRODUCTO, null));

			}
		});

		this.add(Bproducto);
		
		//TRABAJAOR
		Btrabajador = ComponentsBuilder.createButton("Trabajador", 100, 290, 185, 100);
		Btrabajador.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainViewJPA.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VTRABAJADOR, null));

			}
		});

		this.add(Btrabajador);
		
		//DEPARTAMENTO
		Bdepartamento = ComponentsBuilder.createButton("Departamento", 407, 290, 200, 100);
		Bdepartamento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainViewJPA.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VDEPARTAMENTO, null));

			}
		});

		this.add(Bdepartamento);
		
		//VENTA
		Bventa = ComponentsBuilder.createButton("Venta", 715, 290, 200, 100);
		Bventa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainViewJPA.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VVENTA, null));

			}
		});

		this.add(Bventa);
		
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
