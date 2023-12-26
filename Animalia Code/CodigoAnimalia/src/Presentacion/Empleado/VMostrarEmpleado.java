/**
 * 
 */
package Presentacion.Empleado;

import javax.swing.JFrame;

import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.Especie.VMostrarEspecie;
import Presentacion.FactoriaVistas.IGUI;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import Negocio.Animal.TAnimal;
import Negocio.Empleado.TEmpleado;
import Negocio.Empleado.TEmpleadoLimpieza;
import Negocio.Empleado.TEmpleadoZoologico;

import javax.swing.JLabel;
import javax.swing.JDialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VMostrarEmpleado extends JFrame implements  IGUI {
	
	public VMostrarEmpleado() {
		super("Mostar Empleado");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 430;
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

		JLabel msgIntroIDCabecera = ComponentsBuilder
				.createLabel("Introduzca el ID del Empleado que quiere que se muestre ", 1, 10, 80, 20, Color.BLACK);
		msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDCabecera);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel panelID = new JPanel();
		mainPanel.add(panelID);

		JLabel textIdEmpleado = ComponentsBuilder.createLabel("ID Empleado: ", 10, 100, 80, 20, Color.BLACK);
		panelID.add(textIdEmpleado);

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
				VMostrarEmpleado.this.setVisible(false);
				try {
					Integer id_Empleado = Integer.parseInt(id.getText());
					//Vamos a tratar el error de campos nulos
	            	ApplicationController.getInstance().manageRequest(new Context(Evento.MOSTRAR_EMPLEADO, 
	            			!id.getText().isEmpty()? id_Empleado: 0));

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
				 VMostrarEmpleado.this.setVisible(false);
		            ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VEMPLEADO, null));
		        }
		});
		panelBotones.add(botonCancelar);

		this.setVisible(true);
		this.setResizable(true);
	}
	
	public void actualizar(Context res) {
		// begin-user-code
		// TODO Auto-generated method stub
		
		TEmpleado empleado = (TEmpleado) res.getDatos();
		if (res.getEvento() == Evento.MOSTRAR_EMPLEADO_OK) {
			String msj = "ID: "+ empleado.getId() + ", Nombre Empleado: " + empleado.getNombre() + ", DNI Empleado: " + empleado.getDni() + ", Suledo Base: " + empleado.getSueldoBase() + ", Telefono: " + empleado.getTelefono() + ", Tipo: " + (empleado.getTipo() == 1 ? "Limpieza" : "Zoologico") + ", Activo: "+ empleado.getActivo();
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_MOSTRAR_UNO, msj));
		
		} else if (res.getEvento() == Evento.MOSTRAR_EMPLEADO_KO) {

			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) empleado.getId()));
		}
		dispose();
		// end-user-code
	}

	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}
}