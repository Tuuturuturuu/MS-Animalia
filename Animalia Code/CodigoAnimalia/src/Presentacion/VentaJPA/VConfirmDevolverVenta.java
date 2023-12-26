package Presentacion.VentaJPA;

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

import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;
import Presentacion.Factura.VConfirmDevolverFactura;

public class VConfirmDevolverVenta extends JFrame implements IGUI{
	
	public VConfirmDevolverVenta() {
		super("Mensaje de confirmacion");
		initGUI();
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 450;
		int alto = 350;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void initGUI() {
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

		JLabel labelID = ComponentsBuilder.createLabel("Devolución de Venta realizada correctamente", 10, 100, 80, 20, Color.BLACK);
		mensaje.add(labelID);

		panel.add(Box.createRigidArea(new Dimension(0, 10)));

		JPanel botonPanel = new JPanel();
		panel.add(botonPanel);
		JButton confirmar = new JButton("OK");
		confirmar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VConfirmDevolverVenta.this.setVisible(false);
	            ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_MAIN_VIEW, null));
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
		
	}

}
