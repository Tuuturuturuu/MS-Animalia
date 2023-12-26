/**
 * 
 */
package Presentacion.TrabajadorJPA;

import javax.swing.JFrame;

import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

import java.awt.event.ActionListener;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import Negocio.TrabajadorJPA.TTrabajador;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

public class VListarTrabajadoresPorDepartamentoJPA extends JFrame implements IGUI, ActionListener {

	public VListarTrabajadoresPorDepartamentoJPA() {
		super("Mostrar todos los Trabajadores para dicho Departamento");
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
	
	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel(
				"Introduzca el ID del departamento  del cual desea consultar sus trabajadores", 1, 10, 80, 20, Color.BLACK);
		msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDCabecera);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel panelID = new JPanel();
		mainPanel.add(panelID);

		JLabel textId = ComponentsBuilder.createLabel("ID Departamento: ", 10, 100, 80, 20, Color.BLACK);
		panelID.add(textId);

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
				VListarTrabajadoresPorDepartamentoJPA.this.setVisible(false);
					try {
						Integer id_Departamento = Integer.parseInt(id.getText());
						//Vamos a tratar el error de campos nulos
		            	ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_TRABAJADORES_POR_DEPARTAMENTO, 
		            			!id.getText().isEmpty()? id_Departamento: 0));

					} catch (Exception ex) {
		    			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, -5));
					}

			}
		});
		panelBotones.add(botonAceptar);

		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(200, 50, 100, 100);
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VListarTrabajadoresPorDepartamentoJPA.this.setVisible(false);
	            ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VTRABAJADOR, null));

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

	public void actualizar(Context res) {
		if (res.getEvento() == Evento.LISTAR_TRABAJADORES_POR_DEPARTAMENTO_OK) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.VLISTAR_TRABAJADORES_POR_DEPARTAMENTO, (Set<TTrabajador>) res.getDatos())); 

		}else if (res.getEvento() == Evento.LISTAR_TRABAJADORES_POR_DEPARTAMENTO_KO) {
			
			TTrabajador Trabajador = (TTrabajador) res.getDatos();
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) Trabajador.getId()));
		}
		dispose(); //Liberamos recursos de la memoria
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}