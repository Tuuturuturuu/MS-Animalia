package Presentacion;

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

import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;


public class vConfirmDialog extends JFrame implements IGUI {

	private int id;

	public vConfirmDialog(int idCorrect) {
		super("Mensaje de confirmacion");
		this.id = idCorrect;
		initGUI();
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 300;
		int alto = 200;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void initGUI() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.setContentPane(panel);

		JPanel aux = new JPanel();
		panel.add(aux);
		JLabel info = new JLabel("Operacion exitosa");
		aux.add(info);

		panel.add(Box.createRigidArea(new Dimension(0, 10)));

		if (id != -1) {
			JPanel nuevoID = new JPanel();
			panel.add(nuevoID);
			JLabel idConf = new JLabel("ID: " + id);
			nuevoID.add(idConf);
		}

		panel.add(Box.createRigidArea(new Dimension(0, 10)));

		JPanel botonPanel = new JPanel();
		panel.add(botonPanel);
		JButton confirmar = new JButton("OK");
		confirmar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vConfirmDialog.this.setVisible(false);
	            ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VTIENDA, null));
			}

		});
		botonPanel.add(confirmar);
	}
	
	
	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizar(Context res) {
		// TODO Auto-generated method stub
		if (res.getDatos() != null) {
			Integer respuesta = (Integer) res.getDatos();
			this.id = respuesta;
			initGUI();
		}	
	}

}
