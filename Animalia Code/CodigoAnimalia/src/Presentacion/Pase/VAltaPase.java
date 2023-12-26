package Presentacion.Pase;

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
import java.sql.Time;
import java.sql.Date;

import javax.swing.JTextField;

import Negocio.Pase.TPase;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;


public class VAltaPase extends JFrame implements IGUI {

	
	public VAltaPase() {
		super("Alta Pase");
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

	    JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel("Introduzca los datos del  Pase que desea dar de alta", 1, 10, 80, 20, Color.BLACK);
	    msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(msgIntroIDCabecera);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
	    
	    // Campo para introducir la fecha del pase
	    JLabel labelFecha = new JLabel("Fecha (AAAA-MM-DD): ");
	    labelFecha.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(labelFecha);

	    JTextField textFecha = new JTextField(20);
	    textFecha.setMaximumSize(textFecha.getPreferredSize());
	    textFecha.setMaximumSize(textFecha.getPreferredSize());
	    mainPanel.add(textFecha);
	    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	    
	 // Campo para introducir el precio del pase
	    JLabel labelPrecio = new JLabel("Precio: ");
	    labelPrecio.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(labelPrecio);

	    JTextField textPrecio = new JTextField(20);
	    textPrecio.setMaximumSize(textFecha.getPreferredSize());
	    mainPanel.add(textPrecio);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	    
		 // Campo para introducir la hora del pase
	    JLabel labelHora = new JLabel("Hora (HH:MM:SS): ");
	    labelHora.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(labelHora);

	    JTextField textHora = new JTextField(20);
	    textHora.setMaximumSize(textHora.getPreferredSize());
	    mainPanel.add(textHora);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	    
	    // Campo para introducir la cantidad disponible del pase
	    JLabel labelCantidadDisponible = new JLabel("CantidadDisponible: ");
	    labelCantidadDisponible.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(labelCantidadDisponible);

	    JTextField textCantidadDisponible= new JTextField(20);
	    textCantidadDisponible.setMaximumSize(textHora.getPreferredSize());
	    mainPanel.add(textCantidadDisponible);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	    
	    // Campo para introducir el id del hábitat asociado
	    JLabel labelIdHabitat = new JLabel("ID del hábitat: ");
	    labelIdHabitat.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(labelIdHabitat);

	    JTextField textIdHabitat = new JTextField(20);
	    textIdHabitat.setMaximumSize(textIdHabitat.getPreferredSize());
	    mainPanel.add(textIdHabitat);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
	    
	    // Panel para los botones
	    JPanel panelBotones = new JPanel();
	    mainPanel.add(panelBotones);
	    
	    // BOTON ACEPTAR (GUARDAR LOS DATOS DEL ALTA)
	    JButton botonAceptar = new JButton("Aceptar");
	    botonAceptar.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	        
	           VAltaPase.this.setVisible(false);
	           try {
	            	
	            //Falta fecha y hora
	            	Time hora= Time.valueOf(textHora.getText()) ;
	            	Date fecha= Date.valueOf(textFecha.getText());
		            Double precio = Double.parseDouble(textPrecio.getText());
	            	Integer cantidadDisponible = Integer.parseInt(textCantidadDisponible.getText());
		            Integer id_habitat = Integer.parseInt(textIdHabitat.getText());
		            //Vamos a tratar los errores en caso de ingresar campos nulos
		            ApplicationController.getInstance().manageRequest(new Context(Evento.ALTA_PASE, new TPase(0, 
	            			fecha != null ? fecha: null, hora != null ? hora: null, 
	            					precio != null ? precio:0, 
	            							!textCantidadDisponible.getText().isEmpty() ? cantidadDisponible:0,
	            									!textIdHabitat.getText().isEmpty() ? id_habitat:0,
	            							true)));
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
	            VAltaPase.this.setVisible(false);
	            ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VPASE, null));
	        }
	    });
	    panelBotones.add(botonCancelar);

	    this.setVisible(true);
	    this.setResizable(true);
	}
	@Override
	public void actualizar(Context res) {
		// begin-user-code
		// TODO Auto-generated method stub
		if (res.getEvento() == Evento.ALTA_PASE_OK) {			
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_CORRECTO, (int) res.getDatos()));
		} else if (res.getEvento() == Evento.ALTA_PASE_KO) {
			//ApplicationController.getInstance().manageRequest(new Context(Evento.VALTA_ESPECIE, (int) res.getDatos()));
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) res.getDatos()));
		}
		//ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_ESPECIES)); //Mostramos pases para que se vea que se han dado de alta correctamente
		dispose(); //Liberamos recursos de la memoria
		// end-user-code
	}


	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}
}