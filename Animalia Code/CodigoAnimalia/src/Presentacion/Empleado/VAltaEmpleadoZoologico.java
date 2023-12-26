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
import Negocio.Empleado.TEmpleadoZoologico;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

public class VAltaEmpleadoZoologico extends JFrame implements IGUI {
	
	private TEmpleadoZoologico tZoologico;
	
	public VAltaEmpleadoZoologico(TEmpleadoZoologico tZoologico) {
		super("Alta Empleado Zoologico");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 1000;
		int alto = 525;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		// this.setBounds(100, 100, 1000, 525);
		// this.setBounds(100, 100, 650, 430);
		this.setLayout(null);
		
		this.tZoologico = tZoologico;
		
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI();
	}
	
	private void initGUI() {
		
		JPanel mainPanel = new JPanel();
	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	    this.setContentPane(mainPanel);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

	    JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel("Introduzca los datos del empleado de zoologico que desea dar de alta", 1, 10, 80, 20, Color.BLACK);
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
	    
	    
	 // Campo para introducir la especialidad del empleado
	    JLabel labelEspecialidad = new JLabel("Especialidad: ");
	    labelEspecialidad.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(labelEspecialidad);

	    JTextField textEspecialidad = new JTextField(20);
	    textEspecialidad.setMaximumSize(textEspecialidad.getPreferredSize());
	    mainPanel.add(textEspecialidad);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	    
	    // Campo para introducir la tasa del empleado
	    JLabel labelTasa = new JLabel("Tasa: ");
	    labelTasa.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(labelTasa);

	    JTextField textTasa = new JTextField(20);
	    textTasa.setMaximumSize(textTasa.getPreferredSize());
	    mainPanel.add(textTasa);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	    
	    // Campo para introducir la experiencia del empleado
	    JLabel labelExperiencia = new JLabel("Experiencia: ");
	    labelExperiencia.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(labelExperiencia);

	    JTextField textExperiencia = new JTextField(20);
	    textExperiencia.setMaximumSize(textExperiencia.getPreferredSize());
	    mainPanel.add(textExperiencia);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	    
	 // Panel para los botones
	    JPanel panelBotones = new JPanel();
	    mainPanel.add(panelBotones);
	    
	    // BOTON ACEPTAR (GUARDAR LOS DATOS DEL ALTA)
	    JButton botonAceptar = new JButton("Aceptar");
	    botonAceptar.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	VAltaEmpleadoZoologico.this.setVisible(false);
	            
	        	try {
	            	String nombre = textNombre.getText();
	            	String dni = textDni.getText();
		            Double sueldoBase = Double.parseDouble(textSueldoBase.getText());
		            Integer telefono = Integer.parseInt(textTelefono.getText());
	            	String especialidad = textEspecialidad.getText();
		            Double tasa = Double.parseDouble(textTasa.getText());
		            Integer experiencia = Integer.parseInt(textExperiencia.getText());
		            
		            TEmpleadoZoologico empleado = new TEmpleadoZoologico(0, 
		            		nombre != null ? nombre: "",
	            			dni != null ? dni: "",
	            			!textSueldoBase.getText().isEmpty() ? sueldoBase:0.0, 
	            			!textTelefono.getText().isEmpty() ? telefono:0, 
	            			true,
	            			especialidad != null ? especialidad: "",
	            			!textTasa.getText().isEmpty() ? tasa :0.0,
	            			!textExperiencia.getText().isEmpty() ? experiencia :0);
	        		
	        		ApplicationController.getInstance().manageRequest(new Context(Evento.ALTA_EMPLEADO_ZOOLOGICO, empleado));
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
	        	VAltaEmpleadoZoologico.this.setVisible(false);
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
				if (res.getEvento() == Evento.ALTA_EMPLEADO_ZOOLOGICO_OK) {			
					ApplicationController.getInstance().manageRequest(new Context (Evento.V_CORRECTO, (int) res.getDatos()));
				} else if (res.getEvento() == Evento.ALTA_EMPLEADO_ZOOLOGICO_KO) {
					//ApplicationController.getInstance().manageRequest(new Context(Evento.VALTA_EMPLEADO_ZOOLOGICO, (int) res.getDatos()));
					ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) res.getDatos()));
				}
				//ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_EMPLEADOS)); //Mostramos empleados para que se vea que se ha modificado correctamente
				dispose(); //Liberamos recursos de la memoria
				// end-user-code
		
	}
}
