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
import java.util.Set;
import javax.swing.JTextField;

import Negocio.Habitat.TTrabajo;

import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JDialog;


public class VVincularEmpleadoHabitat extends JFrame implements IGUI {

	private Set<JTextField> jTextField;
	private Set<JLabel> jLabel;
	private Set<JButton> jButton;
	private Set<JPanel> jPanel;
	private Set<JDialog> jDialog;

	public VVincularEmpleadoHabitat() {
		super("Vincular Empleado a Habitat");
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
	            "Introduzca los datos del habitat y del empleado que desea vincular ",
	            1, 10, 80, 20, Color.BLACK);
	    msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(msgIntroIDCabecera);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
	    
	    // Campo para introducir el ID del hábitat
	    JLabel labelHabitatID = new JLabel("ID del Hábitat:");
	    labelHabitatID.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(labelHabitatID);

	    JTextField textHabitatID = new JTextField(20);
	    textHabitatID.setMaximumSize(textHabitatID.getPreferredSize());
	    mainPanel.add(textHabitatID);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	    
	    // Campo para introducir el ID del empleado
	    JLabel labelEmpleadoID = new JLabel("ID del Empleado:");
	    labelEmpleadoID.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(labelEmpleadoID);

	    JTextField textEmpleadoID = new JTextField(20);
	    textEmpleadoID.setMaximumSize(textEmpleadoID.getPreferredSize());
	    mainPanel.add(textEmpleadoID);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
	    
	    // Panel para los botones
	    JPanel panelBotones = new JPanel();
	    mainPanel.add(panelBotones);
	    
	    // BOTON ACEPTAR (GUARDAR LOS DATOS DEL ALTA)
	    JButton botonAceptar = new JButton("Aceptar");
	    botonAceptar.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	VVincularEmpleadoHabitat.this.setVisible(false);
	        	try {
	                int habitatID = Integer.parseInt(textHabitatID.getText());
	                int empleadoID = Integer.parseInt(textEmpleadoID.getText());
	                ApplicationController.getInstance().manageRequest(new Context(Evento.VINCULAR_EMPLEADO_A_HABITAT, new TTrabajo(habitatID, empleadoID)));
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
	            VVincularEmpleadoHabitat.this.setVisible(false);
	            ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VHABITAT, null));
	        }
	    });
	    panelBotones.add(botonCancelar);

	    this.setVisible(true);
	    this.setResizable(true);
	}

	@Override
	public void actualizar(Context res) {
		if (res.getEvento() == Evento.VINCULAR_EMPLEADO_A_HABITAT_OK) {			
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_CORRECTO, (int) res.getDatos()));
		} else if (res.getEvento() == Evento.VINCULAR_EMPLEADO_A_HABITAT_KO) {
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