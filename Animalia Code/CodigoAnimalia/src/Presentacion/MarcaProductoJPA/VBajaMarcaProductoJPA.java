
package Presentacion.MarcaProductoJPA;

import javax.swing.JFrame;

import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

public class VBajaMarcaProductoJPA extends JFrame implements IGUI, ActionListener {

	private static final long serialVersionUID = 1L;

	public VBajaMarcaProductoJPA(){
		super("Baja Marca Producto");
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
	
	public void initGUI(){
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel("Introduzca el ID de la Marca Producto que quiera dar de baja ",
				1, 10, 80, 20, Color.BLACK);
		msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDCabecera);
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel panelID = new JPanel();
		mainPanel.add(panelID);

		JLabel labelID = ComponentsBuilder.createLabel("ID Marca Producto: ", 10, 100, 80, 20, Color.BLACK);
		panelID.add(labelID);

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
				VBajaMarcaProductoJPA.this.setVisible(false);
				try {
					int idT = Integer.parseInt(id.getText());
					ApplicationController.getInstance().manageRequest(new Context(Evento.BAJA_MARCA,
							!id.getText().isEmpty()? idT : 0));

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
				VBajaMarcaProductoJPA.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VMARCA, null));
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
		if (res.getEvento() == Evento.BAJA_MARCA_OK) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_CORRECTO, (int) res.getDatos()));
		} else if (res.getEvento() == Evento.BAJA_MARCA_KO) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) res.getDatos()));
		}
		dispose(); //Liberamos recursos de la memoria
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}