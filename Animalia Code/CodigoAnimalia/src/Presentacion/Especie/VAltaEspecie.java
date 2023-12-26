package Presentacion.Especie;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;

import Negocio.Especie.TEspecie;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Box;
import javax.swing.BoxLayout;


public class VAltaEspecie extends JFrame implements IGUI {

	
	public VAltaEspecie() {
		super("Alta Especie");
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

	    JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel("Introduzca los datos de la especie que desea dar de alta", 1, 10, 80, 20, Color.BLACK);
	    msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(msgIntroIDCabecera);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
	    
	    // Campo para introducir el nombre de la especie
	    JLabel labelNombre = new JLabel("Nombre: ");
	    labelNombre.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(labelNombre);

	    JTextField textNombre = new JTextField(20);
	    textNombre.setMaximumSize(textNombre.getPreferredSize());
	    mainPanel.add(textNombre);

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
	            VAltaEspecie.this.setVisible(false);
	            try {
	            	String nombre = textNombre.getText();
		            Integer id_habitat = Integer.parseInt(textIdHabitat.getText());
		            //Vamos a tratar los errores en caso de ingresar campos nulos
	            	ApplicationController.getInstance().manageRequest(new Context(Evento.ALTA_ESPECIE, new TEspecie(0, 
	            			nombre != null ? nombre: "", 
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
	            VAltaEspecie.this.setVisible(false);
	            ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VESPECIE, null));
	        }
	    });
	    panelBotones.add(botonCancelar);

	    this.setVisible(true);
	    this.setResizable(true);
	}
	@Override
	public void actualizar(Context res) {
				
		if (res.getEvento() == Evento.ALTA_ESPECIE_OK) {			
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_CORRECTO, (int) res.getDatos()));
		} else if (res.getEvento() == Evento.ALTA_ESPECIE_KO) {
			//ApplicationController.getInstance().manageRequest(new Context(Evento.VALTA_ESPECIE, (int) res.getDatos()));
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) res.getDatos()));
		}
		//ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_ESPECIES)); //Mostramos especies para que se vea que se ha modificado correctamente
		dispose(); //Liberamos recursos de la memoria
		// end-user-code
	}


	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}
}