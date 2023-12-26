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
public class MainView extends JFrame implements IGUI{
	
	//BOTONES
	JButton Banimal;
	JButton Bempleado;
	JButton Bespecie;
	JButton Bfactura;
	JButton Bhabitat ;
	JButton Bpase;
	JButton Btienda;
	JLabel label;
	
	public MainView(){
		super("Animalia");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 1000;
		int alto = 650;
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
	
	private void initGUI(){
		label = ComponentsBuilder.createLabel("Selecciona el modulo sobre el cual quieres trabajar", 50, 30, 900, 50, Color.BLACK);
		this.add(label);
		
		//ANIMAL
		Banimal = ComponentsBuilder.createButton("Animal", 100, 120, 185, 100);
		Banimal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainView.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VANIMAL, null));

			}
		});
		
		this.add(Banimal);
	
		//EMPLEADO
		Bempleado = ComponentsBuilder.createButton("Empleado", 407, 120, 185, 100);
		Bempleado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainView.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VEMPLEADO, null));

			}
		});

		this.add(Bempleado);
		
		//ESPECIE
		Bespecie = ComponentsBuilder.createButton("Especie", 715, 120, 185, 100);
		Bespecie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainView.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VESPECIE, null));

			}
		});

		this.add(Bespecie);
		
		//FACTURA
		Bfactura = ComponentsBuilder.createButton("Factura", 100, 290, 185, 100);
		Bfactura.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainView.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VFACTURA, null));

			}
		});

		this.add(Bfactura);
		
		//HABITAT
		Bhabitat = ComponentsBuilder.createButton("Habitat", 407, 290, 200, 100);
		Bhabitat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainView.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VHABITAT, null));

			}
		});

		this.add(Bhabitat);
		
		//PASE
		Bpase = ComponentsBuilder.createButton("Pase", 715, 290, 200, 100);
		Bpase.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainView.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VPASE, null));

			}
		});

		this.add(Bpase);
		
		//TIENDA (VISTAS JPA)
		Btienda = ComponentsBuilder.createButton("Tienda", 407, 450, 200, 100);
		Btienda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainView.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VTIENDA, null));

			}
		});

		this.add(Btienda);
		

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
