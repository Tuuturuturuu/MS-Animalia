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
import java.util.HashMap;
import java.util.Set;
import javax.swing.JTextField;

import Negocio.Habitat.THabitat;

import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JDialog;


public class VCalcularHabitatMasIngresos extends JFrame implements IGUI {

	private Set<JTextField> jTextField;
	private Set<JLabel> jLabel;
	private Set<JButton> jButton;
	private Set<JPanel> jPanel;
	private Set<JDialog> jDialog;

	public VCalcularHabitatMasIngresos() {
		super("Calcular Habitat Con Mas Ingresos");
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
	            "Introduzca las fechas para ver el habitat con mas ingresos: ",
	            1, 10, 80, 20, Color.BLACK);
	    msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(msgIntroIDCabecera);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
	    
	    // Campo para introducir la fecha de inicio
        JLabel labelFechaInicio = new JLabel("Fecha de inicio (dd/mm/yyyy):");
        labelFechaInicio.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(labelFechaInicio);

        JTextField textFechaInicio = new JTextField(20);
        textFechaInicio.setMaximumSize(textFechaInicio.getPreferredSize());
        mainPanel.add(textFechaInicio);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Campo para introducir la fecha de fin
        JLabel labelFechaFin = new JLabel("Fecha de fin (dd/mm/yyyy):");
        labelFechaFin.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(labelFechaFin);

        JTextField textFechaFin = new JTextField(20);
        textFechaFin.setMaximumSize(textFechaFin.getPreferredSize());
        mainPanel.add(textFechaFin);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
	    
	    // Panel para los botones
	    JPanel panelBotones = new JPanel();
	    mainPanel.add(panelBotones);
	    
	    
	    // BOTON ACEPTAR (GUARDAR LOS DATOS DEL ALTA)
	    JButton botonAceptar = new JButton("Aceptar");
	    botonAceptar.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
                VCalcularHabitatMasIngresos.this.setVisible(false);
                try {
                	HashMap<String, String> params = new HashMap<>();
                	if(textFechaInicio.getText().isEmpty() || textFechaFin.getText().isEmpty()){
                    	
                    	
                    	params.put("fechaIni", "0001-01-01");
                    	params.put("fechaFin", "0001-01-01");
                	}else{
                		String fechaInicio = textFechaInicio.getText();
                        String fechaFin = textFechaFin.getText();                    	
                    	params.put("fechaIni", fechaInicio);
                    	params.put("fechaFin", fechaFin);
                	}
                	
                	
                    ApplicationController.getInstance().manageRequest(new Context(Evento.CALCULAR_HABITAT_CON_MAS_INGRESOS, params ));
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
	            VCalcularHabitatMasIngresos.this.setVisible(false);
	            ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VHABITAT, null));
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
		if (res.getEvento() == Evento.CALCULAR_HABITAT_CON_MAS_INGRESOS_OK) {
			THabitat especie = (THabitat) res.getDatos();
			String msj = "ID: "+ especie.getId() + " ,Nombre Habitat: " + especie.getNombre() + " ,Tamano: " + especie.getTamano() + " ,Activo: "+ especie.isActivo();
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_MOSTRAR_UNO, msj));
		
		} else if (res.getEvento() == Evento.CALCULAR_HABITAT_CON_MAS_INGRESOS_KO) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) res.getDatos()));
		}
		dispose(); //Liberamos recursos de la memoria
		
	}
}