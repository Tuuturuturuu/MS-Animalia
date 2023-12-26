package Presentacion;

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

import Negocio.Especie.TEspecie;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;



public class VMostrarUno extends JFrame implements IGUI{
	private static final long serialVersionUID = 1L;

	private String msj;

	public VMostrarUno(String mostrar) {
		super("Mensaje de confirmacion");
		this.msj = mostrar;
		initGUI();
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 850;
		int alto = 250;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void initGUI() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBounds(200, 200, 200, 200);
		this.setContentPane(panel);

		JPanel aux = new JPanel();
		panel.add(aux);
		JLabel info = new JLabel("Operacion exitosa");
		aux.add(info);

		panel.add(Box.createRigidArea(new Dimension(0, 10)));

		JPanel mensaje = new JPanel();
		panel.add(mensaje);

		JLabel labelID = ComponentsBuilder.createLabel(msj, 10, 100, 80, 20, Color.BLACK);
		mensaje.add(labelID);

		panel.add(Box.createRigidArea(new Dimension(0, 10)));

		JPanel botonPanel = new JPanel();
		panel.add(botonPanel);
		JButton confirmar = new JButton("OK");
		confirmar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VMostrarUno.this.setVisible(false);
	            ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VTIENDA, null));
			}

		});
		botonPanel.add(confirmar);
		pack();
	}


	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizar(Context res) {
		// TODO Auto-generated method stub
		if (res.getDatos() != null) {
			this.msj = (String) res.getDatos();
			initGUI();
		}	
	}
}
