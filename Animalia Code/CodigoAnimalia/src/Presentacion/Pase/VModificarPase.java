/**
 * 
 */
package Presentacion.Pase;

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;

import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Negocio.Pase.TPase;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

public class VModificarPase extends JFrame implements ActionListener, IGUI {

	private JDialog jDialog;
	private JPanel jPanel;
	private JButton jButton;
	private JLabel jLabel;
	private JTextField jTextField;

	public VModificarPase() {
		super("Modificar Pase");
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

	    JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel("Introduzca los datos del  Pase que desea modificar", 1, 10, 80, 20, Color.BLACK);
	    msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(msgIntroIDCabecera);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
	    
	    JLabel labelID = new JLabel("ID: ");
		labelID.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(labelID);

		JTextField textID = new JTextField(20);
		textID.setMaximumSize(textID.getPreferredSize());
		mainPanel.add(textID);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		
		// Campo para introducir la fecha del pase
		JLabel labelFecha = new JLabel("Fecha (AAAA-DD-MM): ");
		labelFecha.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(labelFecha);

		JTextField textFecha = new JTextField(20);
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

		JTextField textCantidadDisponible = new JTextField(20);
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

		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(75, 50, 100, 100);
		botonAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				VModificarPase.this.setVisible(false);
				try {
					Integer id_pase = Integer.parseInt(textID.getText());

					Time hora= Time.valueOf(textHora.getText());
	            	Date fecha= Date.valueOf(textFecha.getText());
		            Double precio = Double.parseDouble(textPrecio.getText());
	            	Integer cantidadDisponible = Integer.parseInt(textCantidadDisponible.getText());
		            Integer id_habitat = Integer.parseInt(textIdHabitat.getText());
					// Vamos a tratar los errores en caso de ingresar campos
					// nulos
					ApplicationController.getInstance().manageRequest(new Context(Evento.MODIFICAR_PASE, new TPase(id_pase != 0 ? id_pase:0, 
	            			fecha != null ? fecha: null, hora != null ? hora: null, 
	            					!textPrecio.getText().isEmpty() ? precio:0, 
	            							!textCantidadDisponible.getText().isEmpty() ? cantidadDisponible:0,
	            									!textIdHabitat.getText().isEmpty() ? id_habitat:0,
	            							true)));
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
				VModificarPase.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VPASE));
			}
		});
		panelBotones.add(botonCancelar);

		this.setVisible(true);
		this.setResizable(true);

	}

	public void actionPerformed(ActionEvent e) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	
	public void actualizar() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	@Override
	public void actualizar(Context res) {
		if (res.getEvento() == Evento.MODIFICAR_PASE_OK) {
			ApplicationController.getInstance().manageRequest(new Context(Evento.V_CORRECTO, (int) res.getDatos()));
		} else if (res.getEvento() == Evento.MODIFICAR_PASE_KO) {
			ApplicationController.getInstance().manageRequest(new Context(Evento.V_ERRORES, (int) res.getDatos()));
		}
		dispose(); // Liberamos recursos de la memoria
	}
}