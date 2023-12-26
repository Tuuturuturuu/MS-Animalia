/**
 * 
 */
package Presentacion.Empleado;

import javax.swing.JFrame;

import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Empleado.TEmpleado;
import Negocio.Empleado.TEmpleadoLimpieza;
import Negocio.Empleado.TEmpleadoZoologico;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VAltaEmpleado extends JFrame implements IGUI {


	public VAltaEmpleado() {
		super("Alta Empleado");
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

	    JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel("Seleccione el tipo de empleado que desea dar de alta", 1, 10, 80, 20, Color.BLACK);
	    msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(msgIntroIDCabecera);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
	    
	 // TIPO EMPLEADO
	 		JPanel panelTEmpleado = new JPanel();
	 		mainPanel.add(panelTEmpleado);

	 		JLabel labelTEmpleado = ComponentsBuilder.createLabel("Tipo del Empleado: ", 10, 100, 80, 20,
	 				Color.BLACK);
	 		panelTEmpleado.add(labelTEmpleado);

	 		JComboBox<String> tipoEmpleado = new JComboBox<String>();
	 		tipoEmpleado.addItem("Limpieza");
	 		tipoEmpleado.addItem("Zoologico");
	 		tipoEmpleado.setPreferredSize(new Dimension(250, 25));
	 		panelTEmpleado.add(tipoEmpleado);

	 		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
	    
	    // Panel para los botones
	    JPanel panelBotones = new JPanel();
	    mainPanel.add(panelBotones);
	    
	    // BOTON ACEPTAR (GUARDAR LOS DATOS DEL ALTA)
	    JButton botonAceptar = new JButton("Aceptar");
	    botonAceptar.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            VAltaEmpleado.this.setVisible(false);
				int tipo = -47548;
	            //Vamos a tratar los errores en caso de ingresar campos nulos
	            
				try {
					if(tipoEmpleado.getSelectedItem() == "Limpieza"){
						tipo = 0;
						ApplicationController.getInstance().manageRequest(new Context(Evento.VALTA_EMPLEADO_LIMPIEZA, null));

					}
					else if(tipoEmpleado.getSelectedItem() == "Zoologico"){
						tipo = 1;
						ApplicationController.getInstance().manageRequest(new Context(Evento.VALTA_EMPLEADO_ZOOLOGICO, null ));

					}
				} catch (Exception ex) {
					ApplicationController.getInstance().manageRequest(new Context(Evento.V_ERRORES, -4));
				}
	            
	        }
	    });
	    panelBotones.add(botonAceptar);
	    
	    //BOTON CANCELAR
	    JButton botonCancelar = new JButton("Cancelar");
	    botonCancelar.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            VAltaEmpleado.this.setVisible(false);
	            ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VEMPLEADO, null));
	        }
	    });
	    panelBotones.add(botonCancelar);

	    this.setVisible(true);
	    this.setResizable(true);
	}
	
	
	public void actualizar() {

	}
	

	@Override
	public void actualizar(Context res) {

	}
}