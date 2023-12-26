package Presentacion.Pase;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Negocio.Pase.TPase;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;


@SuppressWarnings("serial")
public class VPase extends JFrame implements IGUI{
	
	private JButton bAltaPase;
	private JButton bBajaPase;
	private JButton bModificarPase;
	private JButton bMostrarPase;
	private JButton bListarPases;
	private JButton bListarPasesPorHabitat;
	private JButton backButton;
	private JPanel j;

	private TPase tPase;

	public VPase(){
		super("Animalia");
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
		tPase = new TPase();
		JLabel label = ComponentsBuilder.createLabel("Pase", 250, 30, 500, 50, Color.BLACK);
		this.add(label);
		
		//ALTA PASE
		bAltaPase = ComponentsBuilder.createButton("Alta Pase", 100, 120, 185, 100);
		bAltaPase.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VPase.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VALTA_PASE, tPase));
			}

		});
		bAltaPase.setVisible(true);
		this.add(bAltaPase);
		
		//BAJA PASE
		bBajaPase= ComponentsBuilder.createButton("Baja Pase", 407, 120, 185, 100);
		bBajaPase.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VPase.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VBAJA_PASE, tPase));
			}

		});
		bBajaPase.setVisible(true);
		this.add(bBajaPase);
		
		//MODIFICAR PASE
		bModificarPase = ComponentsBuilder.createButton("Modificar Pase", 715, 120, 185, 100);
		bModificarPase.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VPase.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VMODIFICAR_PASE, tPase));
			}

		});
		bModificarPase.setVisible(true);
		this.add(bModificarPase);
		
		//MOSTRAR PASE
		bMostrarPase = ComponentsBuilder.createButton("Mostrar Pase", 100, 290, 185, 100);
		bMostrarPase.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VPase.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VMOSTRAR_PASE, tPase));
			}

		});
		bMostrarPase.setVisible(true);
		this.add(bMostrarPase);
		
		//LISTAR PASES
		bListarPases = ComponentsBuilder.createButton("Listar Pases", 407, 290, 200, 100);
		bListarPases.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VPase.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_PASES, tPase));
			}

		});
		bListarPases.setVisible(true);
		this.add(bListarPases);
		
		
		//LISTAR PASES POR HABITAT
		bListarPasesPorHabitat = ComponentsBuilder.createButton("Listar Pases Por Habitat", 715, 290,
				200, 100);
		bListarPasesPorHabitat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VPase.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VLISTAR_PASE_POR_HABITAT, tPase));
			}

		});
		bListarPasesPorHabitat.setVisible(true);
		this.add(bListarPasesPorHabitat);
		
		//BACK BUTTON
		backButton = ComponentsBuilder.createBackButton();
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VPase.this.setVisible(false);
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

