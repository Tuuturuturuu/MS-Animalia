package Presentacion.Habitat;

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
import javax.swing.JTextField;

import Negocio.Habitat.THabitat;

import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;



public class VAltaHabitat extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;
	
	public VAltaHabitat() {
		super("Alta habitat");
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
	            "Introduzca los datos del habitat que desea dar de alta ",
	            1, 10, 80, 20, Color.BLACK);
	    msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(msgIntroIDCabecera);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
	    
	    // Campo para introducir el nombre del hábitat
	    JLabel labelNombre = new JLabel("Nombre:");
	    labelNombre.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(labelNombre);

	    JTextField textNombre = new JTextField(20);
	    textNombre.setMaximumSize(textNombre.getPreferredSize());
	    mainPanel.add(textNombre);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	    
	    // Campo para introducir el tamaño del hábitat
	    JLabel labelTamaño = new JLabel("Tamaño:");
	    labelTamaño.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(labelTamaño);

	    JTextField textTamaño = new JTextField(20);
	    textTamaño.setMaximumSize(textTamaño.getPreferredSize());
	    mainPanel.add(textTamaño);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
	    
	    // Panel para los botones
	    JPanel panelBotones = new JPanel();
	    mainPanel.add(panelBotones);
	    
	    // BOTON ACEPTAR (GUARDAR LOS DATOS DEL ALTA)
	    JButton botonAceptar = new JButton("Aceptar");
	    botonAceptar.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	VAltaHabitat.this.setVisible(false);
	            try {
	            	String nombre = textNombre.getText();
	 	            Integer tamano = Integer.parseInt(textTamaño.getText());
	                ApplicationController.getInstance().manageRequest(new Context(Evento.ALTA_HABITAT, new THabitat(0, true, nombre != null ? nombre: "", tamano != null ? tamano:0)));
	            } catch (Exception ex) {
	            	ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, -4));
	            }
	        }
	    });
	    panelBotones.add(botonAceptar);
	    
	    //BOTON CANCELAR
	    JButton botonCancelar = new JButton("Cancelar");
	    botonCancelar.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            VAltaHabitat.this.setVisible(false);
	            ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VHABITAT, null));
	        }
	    });
	    panelBotones.add(botonCancelar);

	    this.setVisible(true);
	    this.setResizable(true);
	}

	@Override
	public void actualizar(Context res) {
		if (res.getEvento() == Evento.ALTA_HABITAT_OK) {			
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_CORRECTO, (int) res.getDatos()));
		} else if (res.getEvento() == Evento.ALTA_HABITAT_KO) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) res.getDatos()));
		}
		dispose(); 
	}
	public void actualizar() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}


}