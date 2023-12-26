package Presentacion.Habitat;

import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;
import javax.swing.JDialog;
import javax.swing.JTextField;

import Negocio.Habitat.THabitat;

import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VModificarHabitat extends JFrame implements IGUI {

	private JDialog jDialog;
	private JTextField jTextField;
	private JPanel jPanel;
	private JButton jButton;
	private JLabel jLabel;

	public VModificarHabitat() {
		super("Modificar Habitat");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 630;
		int alto = 330;
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
		
		// ID HABITAT
		JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel("Introduzca el ID del Habitat que quiere modificar ", 1, 10, 80, 20, Color.BLACK);
		msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDCabecera);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel panelID = new JPanel();
		mainPanel.add(panelID);

		JLabel labelID = ComponentsBuilder.createLabel("ID Habitat: ", 10, 100, 80, 20, Color.BLACK);
		panelID.add(labelID);

		JTextField textIdHabitat = new JTextField();
		textIdHabitat.setPreferredSize(new Dimension(250, 30));
		textIdHabitat.setEditable(true);
		panelID.add(textIdHabitat);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

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

		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);

		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        VModificarHabitat.this.setVisible(false);
		        try {
		            Integer idHabitat = Integer.parseInt(textIdHabitat.getText());
		            String nombreHabitat = textNombre.getText();
		            Integer tamanoHabitat = Integer.parseInt(textTamaño.getText());
		            
		            Boolean activoHabitat = true;

		            THabitat habitat = new THabitat(idHabitat, activoHabitat,  nombreHabitat, tamanoHabitat);

		            // Send the request with the new THabitat object
		            ApplicationController.getInstance().manageRequest(new Context(Evento.MODIFICAR_HABITAT, habitat));
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
				VModificarHabitat.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context (Evento.CREAR_VHABITAT, null));
			}
		});
		panelBotones.add(botonCancelar);

		this.setVisible(true);
		this.setResizable(true);
	}
	
	public void actualizar(Context res) {
		// begin-user-code
		// TODO Auto-generated method stub
		if (res.getEvento() == Evento.MODIFICAR_HABITAT_OK) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_CORRECTO, (int) res.getDatos()));
		} else if (res.getEvento() == Evento.MODIFICAR_HABITAT_KO) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) res.getDatos()));
		}
		dispose(); //Liberamos recursos de la memoria
		// end-user-code
	}

	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}

}