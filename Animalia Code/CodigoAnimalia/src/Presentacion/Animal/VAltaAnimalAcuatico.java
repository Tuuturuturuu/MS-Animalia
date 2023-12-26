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

import Negocio.Animal.TAnimalAcuatico;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

@SuppressWarnings("serial")
public class VAltaAnimalAcuatico extends JFrame implements IGUI {
	private JPanel j;
	private TAnimalAcuatico tAcuatico;

	public VAltaAnimalAcuatico(TAnimalAcuatico tAcuatico) {
		super("Alta Animal Acuatico");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 500;
		int alto = 400;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setLayout(null);
		j = new JPanel();
		this.tAcuatico = tAcuatico;
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
				"Introduzca la temperatura y el tipo de agua del animal acuatico que quiere dar de alta ", 1, 10, 80,
				20, Color.BLACK);
		msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDCabecera);

		// NOMBRE
		JPanel panelNombre = new JPanel();
		mainPanel.add(panelNombre);

		JLabel labelNombre = ComponentsBuilder.createLabel("Nombre: ", 10, 100, 80, 20, Color.BLACK);
		panelNombre.add(labelNombre);

		JTextField nombre = new JTextField();
		nombre.setPreferredSize(new Dimension(250, 30));
		nombre.setEditable(true);
		panelNombre.add(nombre);

		// AREA RIGIDA DE SEPARACION
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		// temperatura
		JPanel panelTemp = new JPanel();
		mainPanel.add(panelTemp);

		JLabel labelTemp = ComponentsBuilder.createLabel("Temperatura: ", 10, 100, 80, 20, Color.BLACK);
		panelTemp.add(labelTemp);

		JTextField temp = new JTextField();
		temp.setPreferredSize(new Dimension(250, 30));
		temp.setEditable(true);
		panelTemp.add(temp);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		// tipo de agua
		JPanel panelAgua = new JPanel();
		mainPanel.add(panelAgua);

		JLabel labelAgua = ComponentsBuilder.createLabel("    Tipo de agua: ", 10, 100, 80, 20, Color.BLACK);
		panelAgua.add(labelAgua);

		JTextField agua = new JTextField();
		agua.setPreferredSize(new Dimension(250, 30));
		agua.setEditable(true);
		panelAgua.add(agua);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

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

		// botones aceptar y cancelar
		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);

		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(75, 50, 100, 100);
		botonAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VAltaAnimalAcuatico.this.setVisible(false);
				try {
					String nombreT = nombre.getText();
					String tAgua = agua.getText();
					Integer tempe = Integer.parseInt(temp.getText());
					Integer id_especie = Integer.parseInt(IDEspecie.getText());
					
					TAnimalAcuatico animal = new TAnimalAcuatico(
							0, 
							nombreT != null ? nombreT : "", 
							tAgua != null ? tAgua : "", 
							tempe, 
							id_especie >= 0 ? id_especie : 0,
							true);
					
					ApplicationController.getInstance().manageRequest(
							new Context(Evento.ALTA_ANIMAL_ACUATICO, animal));

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
				VAltaAnimalAcuatico.this.setVisible(false);
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
		if (res.getEvento() == Evento.ALTA_ANIMAL_ACUATICO_OK) {
			ApplicationController.getInstance().manageRequest(new Context(Evento.V_CORRECTO, (int) res.getDatos()));
		} else if (res.getEvento() == Evento.ALTA_ANIMAL_ACUATICO_KO) {
			// ApplicationController.getInstance().manageRequest(new
			// Context(Evento.VALTA_ESPECIE, (int) res.getDatos()));
			ApplicationController.getInstance().manageRequest(new Context(Evento.V_ERRORES, (int) res.getDatos()));
		}
		// ApplicationController.getInstance().manageRequest(new
		// Context(Evento.LISTAR_ESPECIES)); //Mostramos especies para que se
		// vea que se ha modificado correctamente
		dispose(); // Liberamos recursos de la memoria
		// end-user-code

	}
}
