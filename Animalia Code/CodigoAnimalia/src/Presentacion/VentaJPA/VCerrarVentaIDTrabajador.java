package Presentacion.VentaJPA;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Negocio.TrabajadorJPA.TTrabajador;
import Negocio.VentaJPA.TCarritoJPA;
import Negocio.VentaJPA.TVenta;
import Presentacion.Evento;
import Presentacion.Animal.VAltaAnimal;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

public class VCerrarVentaIDTrabajador extends JFrame implements IGUI{
	
	public VCerrarVentaIDTrabajador() {
		super("Comprobar ID Trabajador");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 1000;
		int alto = 525;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		// this.setBounds(100, 100, 1000, 525);
		// this.setBounds(100, 100, 650, 430);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI();
	}

	private void initGUI() {
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		JLabel msgIntroIDTrabajador = ComponentsBuilder.createLabel(
				"Introduzca el ID del Trabajador que va a realizar la venta ", 1, 10, 80, 20, Color.BLACK);
		msgIntroIDTrabajador.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDTrabajador);

		JPanel panelIDTrabajador = new JPanel();
		panelIDTrabajador.setBorder(new EmptyBorder(10, 10, 10, 10)); // agregado
		panelIDTrabajador.setAlignmentX(CENTER_ALIGNMENT); // agregado
		mainPanel.add(panelIDTrabajador);

		JLabel labelidTrabajador = new JLabel("Id Trabajador");
		labelidTrabajador.setPreferredSize(new Dimension(100, 30));
		panelIDTrabajador.add(labelidTrabajador);

		JTextField idTrabajador = new JTextField();
		idTrabajador.setPreferredSize(new Dimension(250, 30));
		idTrabajador.setEditable(true);
		panelIDTrabajador.add(idTrabajador);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
	

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		//BOTONES ACEPTAR CANCELAR
		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);
		
		// BOTON ACEPTAR (Para comprobar la validez de ID del Trabajador)
				JButton botonAceptar = new JButton("Aceptar");
				botonAceptar.setBounds(75, 50, 100, 100);
				botonAceptar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						VCerrarVentaIDTrabajador.this.setVisible(false);
						//try {
							Integer id_Trabajador = Integer.parseInt(idTrabajador.getText());
							ApplicationController.getInstance().manageRequest(new Context(Evento.CERRAR_VENTA_IDTRABAJADOR, id_Trabajador));

							
						//} catch (Exception ex) {
							//	ApplicationController.getInstance().manageRequest(new Context(Evento.V_ERRORES, -4));
						//}

					}
				});
				panelBotones.add(botonAceptar);
		
		//BOTON CANCELAR
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(200, 50, 100, 100);
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VCerrarVentaIDTrabajador.this.setVisible(false);
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
		if (res.getEvento() == Evento.CERRAR_VENTA_IDTRABAJADOR_KO) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) res.getDatos()));
		}
	}

}
