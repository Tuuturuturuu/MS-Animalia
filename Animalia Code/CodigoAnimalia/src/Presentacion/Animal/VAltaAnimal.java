/**
 * 
 */
package Presentacion.Animal;

import javax.swing.JFrame;

import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;



import Negocio.Animal.TAnimal;
import Negocio.Animal.TAnimalAcuatico;
import Negocio.Animal.TAnimalNoAcuatico;

@SuppressWarnings("serial")
public class VAltaAnimal extends JFrame implements IGUI {
	
	public VAltaAnimal() {
		super("Alta Animal");
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

		JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel(
				"Seleccione el tipo de animal que desea dar de alta ",
				1, 10, 80, 20, Color.BLACK);
		msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDCabecera);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
	
		// TIPO ANIMAL
		JPanel panelTAnimal = new JPanel();
		mainPanel.add(panelTAnimal);

		JLabel labelTAnimal = ComponentsBuilder.createLabel("                Tipo de Animal: ", 10, 100, 80, 20,
				Color.BLACK);
		panelTAnimal.add(labelTAnimal);

		JComboBox<String> tipoAnimal = new JComboBox<String>();
		tipoAnimal.addItem("Acuatico");
		tipoAnimal.addItem("No Acuatico");
		tipoAnimal.setPreferredSize(new Dimension(250, 25));
		panelTAnimal.add(tipoAnimal);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		//BOTONES ACEPTAR CANCELAR
		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);
		
		// BOTON ACEPTAR (GUARDAR LOS DATOS DEL ALTA)
				JButton botonAceptar = new JButton("Aceptar");
				botonAceptar.setBounds(75, 50, 100, 100);
				botonAceptar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						VAltaAnimal.this.setVisible(false);
						int tipo = -47548;
						try {
							if(tipoAnimal.getSelectedItem() == "Acuatico"){
								tipo = 1;
								ApplicationController.getInstance().manageRequest(new Context(Evento.VALTA_ANIMAL_ACUATICO, null));

							}
							else if(tipoAnimal.getSelectedItem() == "No Acuatico"){
								tipo = 0;
								ApplicationController.getInstance().manageRequest(new Context(Evento.VALTA_ANIMAL_NO_ACUATICO, null));

							}
						} catch (Exception ex) {
								ApplicationController.getInstance().manageRequest(new Context(Evento.V_ERRORES, -4));
						}

					}
				});
				panelBotones.add(botonAceptar);
		
		//BOTON CANCELAR
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(200, 50, 100, 100);
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VAltaAnimal.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VANIMAL, null));
			}
		});
		panelBotones.add(botonCancelar);

		this.setVisible(true);
		this.setResizable(true);
	}

	public void actualizar() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	@Override
	public void actualizar(Context res) {
		// TODO Auto-generated method stub
		
	}
}