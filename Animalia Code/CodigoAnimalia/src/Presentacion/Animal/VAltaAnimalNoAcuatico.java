package Presentacion.Animal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Animal.TAnimalNoAcuatico;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

@SuppressWarnings("serial")
public class VAltaAnimalNoAcuatico extends JFrame implements IGUI {

	private JPanel j;
	private TAnimalNoAcuatico tNoAcuatico;
	
	public VAltaAnimalNoAcuatico(TAnimalNoAcuatico tNoAcuatico) {
		super("Alta Animal NO aquatico");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 500;
		int alto = 400;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setLayout(null);
		j = new JPanel();
		this.tNoAcuatico = tNoAcuatico;
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI();
	}

	public void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel(
				"Introduzca el numero de patas del animal no acuatico que quiere dar de alta ", 1, 10, 80,
				20, Color.BLACK);
		msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDCabecera);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		
		//NOMBRE
		JPanel panelNombre = new JPanel();
		mainPanel.add(panelNombre);

		JLabel labelNombre = ComponentsBuilder.createLabel("Nombre: ", 10, 100, 80, 20, Color.BLACK);
		panelNombre.add(labelNombre);

		JTextField nombre = new JTextField();
		nombre.setPreferredSize(new Dimension(250, 30));
		nombre.setEditable(true);
		panelNombre.add(nombre);
		
		//AREA RIGIDA DE SEPARACION
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		//numero de patas
		JPanel panelPatas = new JPanel();
		mainPanel.add(panelPatas);

		JLabel labelPatas = ComponentsBuilder.createLabel("Numero de patas: ", 10, 100, 80, 20, Color.BLACK);
		panelPatas.add(labelPatas);

		JTextField patas = new JTextField();
		patas.setPreferredSize(new Dimension(250, 30));
		patas.setEditable(true);
		panelPatas.add(patas);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		// id Especie
		JPanel panelIDEspecie = new JPanel();
		mainPanel.add(panelIDEspecie);

		JLabel labelIDEspecie = ComponentsBuilder.createLabel("ID Especie: ", 10, 100, 80, 20, Color.BLACK);
		panelIDEspecie.add(labelIDEspecie);

		JTextField IDEspecie = new JTextField();
		IDEspecie.setPreferredSize(new Dimension(250, 30));
		IDEspecie.setEditable(true);
		panelIDEspecie.add(IDEspecie);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		//botones aceptar y cancelar
		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);

		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(75, 50, 100, 100);
		botonAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VAltaAnimalNoAcuatico.this.setVisible(false);
				try {
					String nombreT = nombre.getText();
					Integer patasT = Integer.parseInt(patas.getText());
					Integer id_especie = Integer.parseInt(IDEspecie.getText());
					
					TAnimalNoAcuatico animal = new TAnimalNoAcuatico(
							0, 
							nombreT != null ? nombreT : "", 
							patasT >= 0 ? patasT : 0, 
							id_especie >= 0 ? id_especie : 0, 
							true
							);
		
					ApplicationController.getInstance().manageRequest(new Context(Evento.ALTA_ANIMAL_NO_ACUATICO, animal));

				} catch (Exception ex) {
					ApplicationController.getInstance().manageRequest(new Context(Evento.V_ERRORES, -4));
				}

			}
		});
		panelBotones.add(botonAceptar);

		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(200, 50, 100, 100);
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VAltaAnimalNoAcuatico.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VANIMAL, null));
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
		// begin-user-code
		// TODO Auto-generated method stub
		if (res.getEvento() == Evento.ALTA_ANIMAL_NO_ACUATICO_OK) {			
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_CORRECTO, (int) res.getDatos()));
		} else if (res.getEvento() == Evento.ALTA_ANIMAL_NO_ACUATICO_KO) {
			//ApplicationController.getInstance().manageRequest(new Context(Evento.VALTA_ESPECIE, (int) res.getDatos()));
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) res.getDatos()));
		}
		//ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_ESPECIES)); //Mostramos especies para que se vea que se ha modificado correctamente
		dispose(); //Liberamos recursos de la memoria
		// end-user-code
		
	}

}
