/**
 * 
 */
package Presentacion.Especie;

import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;
import javax.swing.JDialog;
import javax.swing.JTextField;

import Negocio.Especie.TEspecie;

import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VModificarEspecie extends JFrame implements IGUI {


	public VModificarEspecie() {
		super("Modificar Especie");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 630;
		int alto = 330;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI();
	}

	public void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		// ID ESPECIE
		JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel("Introduzca el ID de la Especie que quiere modificar ", 1, 10, 80, 20, Color.BLACK);
		msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDCabecera);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel panelID = new JPanel();
		mainPanel.add(panelID);

		JLabel labelID = ComponentsBuilder.createLabel("ID Especie: ", 10, 100, 80, 20, Color.BLACK);
		panelID.add(labelID);

		JTextField textIdEspecie = new JTextField();
		textIdEspecie.setPreferredSize(new Dimension(250, 30));
		textIdEspecie.setEditable(true);
		panelID.add(textIdEspecie);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		// NOMBRE ESPECIE
		JPanel panelNombre = new JPanel();
		mainPanel.add(panelNombre);

		JLabel labelNombre = ComponentsBuilder.createLabel("Nombre Especie: ", 10, 100, 80, 20, Color.BLACK);
		panelNombre.add(labelNombre);

		JTextField textNombre = new JTextField();
		textNombre.setPreferredSize(new Dimension(250, 30));
		textNombre.setEditable(true);
		panelNombre.add(textNombre);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		// ID HABITAT
		JPanel panelIdHabitat = new JPanel();
		mainPanel.add(panelIdHabitat);

		JLabel labelIdHabitat = ComponentsBuilder.createLabel("Id Habitat: ", 10, 100, 80, 20, Color.BLACK);
		panelIdHabitat.add(labelIdHabitat);

		JTextField textIdHabitat = new JTextField();
		textIdHabitat.setPreferredSize(new Dimension(250, 30));
		textIdHabitat.setEditable(true);
		panelIdHabitat.add(textIdHabitat);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));		

		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);

		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(75, 50, 100, 100);
		botonAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				           
				VModificarEspecie.this.setVisible(false);
				try {
					Integer id_especie = Integer.parseInt(textIdEspecie.getText());
	            	String nombre_especie = textNombre.getText();
		            Integer id_habitat = Integer.parseInt(textIdHabitat.getText());
		            //Vamos a tratar los errores en caso de ingresar campos nulos
	            	ApplicationController.getInstance().manageRequest(new Context(Evento.MODIFICAR_ESPECIE, new TEspecie(
	            					!textIdEspecie.getText().isEmpty()? id_especie: 0, 
	            					nombre_especie != null ? nombre_especie: "", 
	            					!textIdHabitat.getText().isEmpty() ? id_habitat:0, 
	            							true)));
				} catch (Exception ex) {
	    			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, -4));
				}

			}
		});
		panelBotones.add(botonAceptar);

		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(200, 50, 100, 100);
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VModificarEspecie.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context (Evento.CREAR_VESPECIE, null));
			}
		});
		panelBotones.add(botonCancelar);

		this.setVisible(true);
		this.setResizable(true);
	}
	
	public void actualizar(Context res) {
		// begin-user-code
		// TODO Auto-generated method stub
		if (res.getEvento() == Evento.MODIFICAR_ESPECIE_OK) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_CORRECTO, (int) res.getDatos()));
		} else if (res.getEvento() == Evento.MODIFICAR_ESPECIE_KO) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) res.getDatos()));
		}
		dispose(); //Liberamos recursos de la memoria
		// end-user-code
	}

	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}

}