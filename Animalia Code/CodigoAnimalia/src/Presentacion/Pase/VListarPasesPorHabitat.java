package Presentacion.Pase;

import javax.swing.JFrame;

import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;
import java.util.Set;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Negocio.Pase.TPase;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VListarPasesPorHabitat extends JFrame implements IGUI {

	public VListarPasesPorHabitat() {
		super("Mostrar todos los Pases para dicho Habitat");
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

	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel(
				"Introduzca el ID del habitat que desea consultar sus pases", 1, 10, 80, 20, Color.BLACK);
		msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDCabecera);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel panelID = new JPanel();
		mainPanel.add(panelID);

		JLabel textIdHabitat = ComponentsBuilder.createLabel("ID Habitat: ", 10, 100, 80, 20, Color.BLACK);
		panelID.add(textIdHabitat);

		JTextField id = new JTextField();
		id.setPreferredSize(new Dimension(250, 30));

		id.setEditable(true);
		panelID.add(id);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);

		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(75, 50, 100, 100);
		botonAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VListarPasesPorHabitat.this.setVisible(false);
					try {
						Integer id_Habitat = Integer.parseInt(id.getText());
						//Vamos a tratar el error de campos nulos
		            	ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_PASES_POR_HABITAT, 
		            			!id.getText().isEmpty()? id_Habitat: 0));

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
				VListarPasesPorHabitat.this.setVisible(false);
	            ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VPASE, null));

			}
		});
		panelBotones.add(botonCancelar);

		this.setVisible(true);
		this.setResizable(true);
	}

	public void actualizar(Context res) {
		if (res.getEvento() == Evento.LISTAR_PASES_POR_HABITAT_OK) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_LISTAR_PASE_POR_ID_HABITAT, (Set<TPase>) res.getDatos()));

		}else if (res.getEvento() == Evento.LISTAR_PASES_POR_HABITAT_KO) {
			
			TPase Pase = (TPase) res.getDatos();
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) Pase.getID()));
		}
		dispose(); //Liberamos recursos de la memoria
		
	}

	public void actionPerformed(ActionEvent e) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}

	
}