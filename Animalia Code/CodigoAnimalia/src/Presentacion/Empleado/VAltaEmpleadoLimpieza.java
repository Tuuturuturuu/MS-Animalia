package Presentacion.Empleado;

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

import Negocio.Empleado.TEmpleadoLimpieza;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

public class VAltaEmpleadoLimpieza extends JFrame implements IGUI {
	
	private TEmpleadoLimpieza tLimpieza;
	
	public VAltaEmpleadoLimpieza(TEmpleadoLimpieza tLimpieza) {
		super("Alta Empleado Limpieza");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 1000;
		int alto = 525;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		// this.setBounds(100, 100, 1000, 525);
		// this.setBounds(100, 100, 650, 430);
		this.setLayout(null);
		
		this.tLimpieza = tLimpieza;
		
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI();
	}
	
	private void initGUI() {
		
		JPanel mainPanel = new JPanel();
	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	    this.setContentPane(mainPanel);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

	    JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel("Introduzca los datos del empleado de limpieza que desea dar de alta", 1, 10, 80, 20, Color.BLACK);
	    msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(msgIntroIDCabecera);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
	    
	 // Campo para introducir el nombre del empleado
	    JLabel labelNombre = new JLabel("Nombre: ");
	    labelNombre.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(labelNombre);

	    JTextField textNombre = new JTextField(20);
	    textNombre.setMaximumSize(textNombre.getPreferredSize());
	    mainPanel.add(textNombre);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	    
	    // Campo para introducir el DNI del empleado
	    JLabel labelDni = new JLabel("DNI: ");
	    labelDni.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(labelDni);

	    JTextField textDni = new JTextField(20);
	    textDni.setMaximumSize(textDni.getPreferredSize());
	    mainPanel.add(textDni);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	    
	    // Campo para introducir el sueldo base del empleado
	    JLabel labelSueldoBase = new JLabel("Sueldo Base: ");
	    labelSueldoBase.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(labelSueldoBase);

	    JTextField textSueldoBase = new JTextField(20);
	    textSueldoBase.setMaximumSize(textSueldoBase.getPreferredSize());
	    mainPanel.add(textSueldoBase);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	    
	 // Campo para introducir el telefono del empleado
	    JLabel labelTelefono = new JLabel("Telefono: ");
	    labelTelefono.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(labelTelefono);

	    JTextField textTelefono = new JTextField(20);
	    textTelefono.setMaximumSize(textTelefono.getPreferredSize());
	    mainPanel.add(textTelefono);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	    
	    
	 // Campo para introducir el suplemento del empleado
	    JLabel labelSuplemento = new JLabel("Suplemento: ");
	    labelSuplemento.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(labelSuplemento);

	    JTextField textSuplemento = new JTextField(20);
	    textSuplemento.setMaximumSize(textSuplemento.getPreferredSize());
	    mainPanel.add(textSuplemento);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	    
	    // Campo para introducir la zona del empleado
	    JLabel labelZona = new JLabel("Zona: ");
	    labelZona.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(labelZona);

	    JTextField textZona = new JTextField(20);
	    textZona.setMaximumSize(textZona.getPreferredSize());
	    mainPanel.add(textZona);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	    
	 // Panel para los botones
	    JPanel panelBotones = new JPanel();
	    mainPanel.add(panelBotones);
	    
	    // BOTON ACEPTAR (GUARDAR LOS DATOS DEL ALTA)
	    JButton botonAceptar = new JButton("Aceptar");
	    botonAceptar.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	VAltaEmpleadoLimpieza.this.setVisible(false);
	        	
	        	try {
	            	String nombre = textNombre.getText();
	            	String dni = textDni.getText();
		            Double sueldoBase = Double.parseDouble(textSueldoBase.getText());
		            Integer telefono = Integer.parseInt(textTelefono.getText());
		            Double suplemento = Double.parseDouble(textSuplemento.getText());
	            	String zona = textZona.getText();
	            	
	            	TEmpleadoLimpieza empleado = new TEmpleadoLimpieza(0, 
	            			nombre != null ? nombre: "",
	            			dni != null ? dni: "", 
	            			!textSueldoBase.getText().isEmpty() ? sueldoBase:0.0, 
	            			!textTelefono.getText().isEmpty() ? telefono:0, 
	            			true,
	            			!textSuplemento.getText().isEmpty() ? suplemento :0,
	            			zona != null ? zona: "");
	            	
	        		ApplicationController.getInstance().manageRequest(new Context(Evento.ALTA_EMPLEADO_LIMPIEZA, empleado));
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
	        	VAltaEmpleadoLimpieza.this.setVisible(false);
	            ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VEMPLEADO, null));
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
				if (res.getEvento() == Evento.ALTA_EMPLEADO_LIMPIEZA_OK) {			
					ApplicationController.getInstance().manageRequest(new Context (Evento.V_CORRECTO, (int) res.getDatos()));
				} else if (res.getEvento() == Evento.ALTA_EMPLEADO_LIMPIEZA_KO) {
					//ApplicationController.getInstance().manageRequest(new Context(Evento.VALTA_EMPLEADO_LIMPIEZA, (int) res.getDatos()));
					ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) res.getDatos()));
				}
				//ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_EMPLEADOS)); //Mostramos empleados para que se vea que se ha modificado correctamente
				dispose(); //Liberamos recursos de la memoria
				// end-user-code
		
	}
}
