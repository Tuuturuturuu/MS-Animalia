package Presentacion.Animal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Negocio.Animal.TAnimal;
import Negocio.Animal.TAnimalAcuatico;
import Negocio.Animal.TAnimalNoAcuatico;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;


@SuppressWarnings("serial")
public class VAnimal extends JFrame implements IGUI{
	
	private JButton bAltaAnimal;
	private JButton bBajaAnimal;
	private JButton bModificarAnimal;
	private JButton bMostrarAnimal;
	private JButton bListarAnimales;
	private JButton bListarAnimalesNoAcuaticos;
	private JButton bListarAnimalesAcuaticos;
	private JButton bListarAnimalesPorEspecie;
	private JButton backButton;
	private JPanel j;

	private TAnimal tAnimal;
	private Set<TAnimal> hsAnimal;
	private Set<TAnimalAcuatico> hsAnimalAc;
	private Set<TAnimalNoAcuatico> hsAnimalNoAc;

	public VAnimal(){
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
		tAnimal = new TAnimal();
		hsAnimal = new HashSet<TAnimal> ();
		hsAnimalAc = new HashSet<TAnimalAcuatico> ();
		hsAnimalNoAc = new HashSet<TAnimalNoAcuatico> ();
		JLabel label = ComponentsBuilder.createLabel("Animal", 250, 30, 500, 50, Color.BLACK);
		this.add(label);
		
		//ALTA ANIMAL
		bAltaAnimal = ComponentsBuilder.createButton("Alta Animal", 100, 100, 185, 100);
		bAltaAnimal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VAnimal.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VALTA_ANIMAL, tAnimal));
			}

		});
		bAltaAnimal.setVisible(true);
		this.add(bAltaAnimal);
		
		//BAJA ANIMAL
		bBajaAnimal = ComponentsBuilder.createButton("Baja Animal", 300, 100, 185, 100);
		bBajaAnimal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VAnimal.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VBAJA_ANIMAL, tAnimal));
			}

		});
		bBajaAnimal.setVisible(true);
		this.add(bBajaAnimal);
		
		//MODIFICAR ANIMAL
		bModificarAnimal = ComponentsBuilder.createButton("Modificar Animal", 500, 100, 185, 100);
		bModificarAnimal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VAnimal.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VMODIFICAR_ANIMAL, tAnimal));
			}

		});
		bModificarAnimal.setVisible(true);
		this.add(bModificarAnimal);
		
		//MOSTRAR ANIMAL
		bMostrarAnimal = ComponentsBuilder.createButton("Mostrar Animal", 700, 100, 185, 100);
		bMostrarAnimal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VAnimal.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VMOSTRAR_ANIMAL, tAnimal));
			}

		});
		bMostrarAnimal.setVisible(true);
		this.add(bMostrarAnimal);
		
		//LISTAR ANIMALES
		bListarAnimales = ComponentsBuilder.createButton("Listar Animales", 100, 250, 185, 100);
		bListarAnimales.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VAnimal.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VLISTAR_ANIMALES, hsAnimal));
			}

		});
		bListarAnimales.setVisible(true);
		this.add(bListarAnimales);
		
		//LISTAR ANIMALES ACUATICOS
		bListarAnimalesAcuaticos = ComponentsBuilder.createButton("Listar Animales Acuaticos", 300, 250, 185, 100);
		bListarAnimalesAcuaticos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VAnimal.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VLISTAR_ANIMALES_ACUATICOS, hsAnimalAc));
			}

		});
		bListarAnimalesAcuaticos.setVisible(true);
		this.add(bListarAnimalesAcuaticos);
		
		//LISTAR ANIMALES NO ACUATICOS
		bListarAnimalesNoAcuaticos = ComponentsBuilder.createButton("Listar Animales No Acuaticos", 500, 250,
				185, 100);
		bListarAnimalesNoAcuaticos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VAnimal.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VLISTAR_ANIMALES_NO_ACUATICOS, hsAnimalNoAc));
			}

		});
		bListarAnimalesNoAcuaticos.setVisible(true);
		this.add(bListarAnimalesNoAcuaticos);
		
		//LISTAR ANIMALES POR ESPECIE
		bListarAnimalesPorEspecie = ComponentsBuilder.createButton("Listar Animales Por Especie", 700, 250,
				185, 100);
		bListarAnimalesPorEspecie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VAnimal.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VLISTAR_ANIMALES_POR_ESPECIE, tAnimal));
			}

		});
		bListarAnimalesPorEspecie.setVisible(true);
		this.add(bListarAnimalesPorEspecie);
		
		//BACK BUTTON
		backButton = ComponentsBuilder.createBackButton();
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VAnimal.this.setVisible(false);
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
