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
import javax.swing.JDialog;
import javax.swing.JTextField;

import Negocio.Animal.TAnimal;
import Negocio.Empleado.TEmpleado;
import Negocio.Empleado.TEmpleadoLimpieza;
import Negocio.Empleado.TEmpleadoZoologico;
import Negocio.Especie.TEspecie;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VModificarEmpleado extends JFrame implements  IGUI {

	private JDialog jDialog;
	private JTextField jTextField;
	private JPanel jPanel;
	private JButton jButton;
	private JLabel jLabel;

	public VModificarEmpleado() {
		super("Modificar Empleado");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 630;
		int alto = 530;
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
		JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel("Introduzca el ID del Empleado que quiere modificar ", 1, 10, 80, 20, Color.BLACK);
		msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDCabecera);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel panelID = new JPanel();
		mainPanel.add(panelID);

		JLabel labelID = ComponentsBuilder.createLabel("ID Empleado: ", 10, 100, 80, 20, Color.BLACK);
		panelID.add(labelID);

		JTextField textIdEmpleado = new JTextField();
		textIdEmpleado.setPreferredSize(new Dimension(250, 30));
		textIdEmpleado.setEditable(true);
		panelID.add(textIdEmpleado);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		// NOMBRE EMPLEADO
		JPanel panelNombre = new JPanel();
		mainPanel.add(panelNombre);

		JLabel labelNombre = ComponentsBuilder.createLabel("Nombre Empleado: ", 10, 100, 80, 20, Color.BLACK);
		panelNombre.add(labelNombre);

		JTextField textNombre = new JTextField();
		textNombre.setPreferredSize(new Dimension(250, 30));
		textNombre.setEditable(true);
		panelNombre.add(textNombre);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		// DNI EMPLEADO
		JPanel panelDni = new JPanel();
		mainPanel.add(panelDni);

		JLabel labelDni = ComponentsBuilder.createLabel("DNI Empleado: ", 10, 100, 80, 20, Color.BLACK);
		panelDni.add(labelDni);

		JTextField textDni = new JTextField();
		textDni.setPreferredSize(new Dimension(250, 30));
		textDni.setEditable(true);
		panelDni.add(textDni);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		// SUELDO BASE EMPLEADO
		JPanel panelSueldoBase = new JPanel();
		mainPanel.add(panelSueldoBase);

		JLabel labelSueldoBase = ComponentsBuilder.createLabel("Sueldo Base Empleado: ", 10, 100, 80, 20, Color.BLACK);
		panelSueldoBase.add(labelSueldoBase);

		JTextField textSueldoBase = new JTextField();
		textSueldoBase.setPreferredSize(new Dimension(250, 30));
		textSueldoBase.setEditable(true);
		panelSueldoBase.add(textSueldoBase);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		// TELEFONO EMPLEADO
		JPanel panelTelefono = new JPanel();
		mainPanel.add(panelTelefono);

		JLabel labelTelefono = ComponentsBuilder.createLabel("Telefono Empleado: ", 10, 100, 80, 20, Color.BLACK);
		panelTelefono.add(labelTelefono);

		JTextField textTelefono = new JTextField();
		textTelefono.setPreferredSize(new Dimension(250, 30));
		textTelefono.setEditable(true);
		panelTelefono.add(textTelefono);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);

		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(75, 50, 100, 100);
		botonAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				           
				VModificarEmpleado.this.setVisible(false);
				try {
					Integer id = Integer.parseInt(textIdEmpleado.getText());
	            	String nombre = textNombre.getText();
	            	String dni = textDni.getText();
		            Integer sueldoBase = Integer.parseInt(textSueldoBase.getText());
		            Integer telefono = Integer.parseInt(textTelefono.getText());
		            //Integer tipo = Integer.parseInt(textTipo.getText());
		            
		            //Vamos a tratar los errores en caso de ingresar campos nulos
		            
		            ApplicationController.getInstance().manageRequest(new Context(Evento.MODIFICAR_EMPLEADO, new TEmpleado(
		            		id, 
		            		nombre != null ? nombre: "", 
	            			dni != null ? dni: "", 
	            			!textSueldoBase.getText().isEmpty() ? sueldoBase:0.0, 
	            			!textTelefono.getText().isEmpty() ? telefono:0, 
	            			true,
	            			null)));
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
				VModificarEmpleado.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context (Evento.CREAR_VEMPLEADO, null));
			}
		});
		panelBotones.add(botonCancelar);

		this.setVisible(true);
		this.setResizable(true);
	}

	public void actualizar() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	@Override
	public void actualizar(Context res) {
		// begin-user-code
		// TODO Auto-generated method stub
		if (res.getEvento() == Evento.MODIFICAR_EMPLEADO_OK) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_CORRECTO, (int) res.getDatos()));
		} else if (res.getEvento() == Evento.MODIFICAR_EMPLEADO_KO) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) res.getDatos()));
		}
		dispose(); //Liberamos recursos de la memoria
		// end-user-code
	}
}