package Presentacion.Especie;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Negocio.Especie.TEspecie;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

@SuppressWarnings("serial")
public class VEspecie  extends JFrame implements IGUI{

	private JButton bAltaEspecie;
	private JButton bBajaEspecie;
	private JButton bModificarEspecie;
	private JButton bMostrarEspecie;
	private JButton bListarEspecies;
	private JButton bListarEspeciePorHabitat;
	private JButton backButton;
	private JPanel j;

	private TEspecie tEspecie;

	public VEspecie(){
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
		tEspecie = new TEspecie();
		JLabel label = ComponentsBuilder.createLabel("Especie", 250, 30, 500, 50, Color.BLACK);
		this.add(label);
		
		//ALTA ESPECIE
		bAltaEspecie = ComponentsBuilder.createButton("Alta Especie", 100, 100, 185, 100);
		bAltaEspecie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VEspecie.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VALTA_ESPECIE, tEspecie));
			}

		});
		bAltaEspecie.setVisible(true);
		this.add(bAltaEspecie);
		
		//BAJA ESPECIE
		bBajaEspecie = ComponentsBuilder.createButton("Baja Especie", 300, 100, 185, 100);
		bBajaEspecie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VEspecie.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VBAJA_ESPECIE, tEspecie));
			}

		});
		bBajaEspecie.setVisible(true);
		this.add(bBajaEspecie);
		
		//MODIFICAR ESPECIE
		bModificarEspecie = ComponentsBuilder.createButton("Modificar Especie", 500, 100, 185, 100);
		bModificarEspecie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VEspecie.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VMODIFICAR_ESPECIE, tEspecie));
			}

		});
		bModificarEspecie.setVisible(true);
		this.add(bModificarEspecie);
		
		//MOSTRAR ESPECIE
		bMostrarEspecie = ComponentsBuilder.createButton("Mostrar Especie", 700, 100, 185, 100);
		bMostrarEspecie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VEspecie.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VMOSTRAR_ESPECIE, tEspecie));
			}

		});
		bMostrarEspecie.setVisible(true);
		this.add(bMostrarEspecie);
		
		//LISTAR ESPECIES
		bListarEspecies = ComponentsBuilder.createButton("Listar Especies", 100, 250, 185, 100);
		bListarEspecies.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VEspecie.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_ESPECIES, tEspecie));
			}

		});
		bListarEspecies.setVisible(true);
		this.add(bListarEspecies);
		
		//LISTAR ESPECIE POR HABITAT
		bListarEspeciePorHabitat = ComponentsBuilder.createButton("Listar Especie Por Habitat", 700, 250, 185, 100);
		bListarEspeciePorHabitat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VEspecie.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VLISTAR_ESPECIE_POR_HABITAT, tEspecie));
			}

		});
		bListarEspeciePorHabitat.setVisible(true);
		this.add(bListarEspeciePorHabitat);
		
		//BACK BUTTON
		backButton = ComponentsBuilder.createBackButton();
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VEspecie.this.setVisible(false);
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
