/**
 * 
 */
package Presentacion.Departamento;

import javax.swing.JFrame;

import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import Negocio.DepartamentoJPA.TDepartamento;


public class VModificarDepartamento extends JFrame implements Presentacion.FactoriaVistas.IGUI {

	private JDialog jDialog;
	private JLabel jLabel;
	private JPanel jPanel;
	private JButton jButton;
	private JTextField jTextField;

	public VModificarDepartamento() {
		super("Modificar Departamento");
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

		JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel("Seleccione el id del Departamento que desea modificar ",
				1, 10, 80, 20, Color.BLACK);
		msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDCabecera);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		// ID DEPARTAMENTO
		JPanel panelID = new JPanel();
		mainPanel.add(panelID);

		JLabel labelID = ComponentsBuilder.createLabel("ID Departamento: ", 10, 100, 80, 20, Color.BLACK);
		panelID.add(labelID);

		JTextField idD = new JTextField();
		idD.setPreferredSize(new Dimension(250, 30));
		idD.setEditable(true);
		panelID.add(idD);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		// NOMBRE
		JPanel panelNombre = new JPanel();
		JLabel labelNombre = ComponentsBuilder.createLabel("Nombre: ", 10, 100, 80, 20, Color.BLACK);
		panelNombre.add(labelNombre);

		JTextField nombreD = new JTextField();
		nombreD.setPreferredSize(new Dimension(250, 30));
		nombreD.setEditable(true);
		panelNombre.add(nombreD);
		mainPanel.add(panelNombre);

		// BOTONES ACEPTAR CANCELAR

		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);

		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(75, 50, 100, 100);
		botonAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VModificarDepartamento.this.setVisible(false);
				try {
					
						String nombre = nombreD.getText();
						Integer id = Integer.parseInt(idD.getText());

						TDepartamento departamento = new TDepartamento(id, nombre != null ? nombre: "", true);

						ApplicationController.getInstance()
								.manageRequest(new Context(Evento.MODIFICAR_DEPARTAMENTO,departamento));


				}
				catch (Exception ex) {
					ApplicationController.getInstance().manageRequest(new Context(Evento.V_ERRORES, -4));
				}

			}
		});

		panelBotones.add(botonAceptar);
		
		//BOTON CANCELAR
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(200, 50, 100, 100);
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VModificarDepartamento.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VDEPARTAMENTO, null));
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
		
				if (res.getEvento() == Evento.MODIFICAR_DEPARTAMENTO_OK) {
					ApplicationController.getInstance().manageRequest(new Context(Evento.V_CORRECTO, (int) res.getDatos()));
				} else if (res.getEvento() == Evento.MODIFICAR_DEPARTAMENTO_KO) {
					
					ApplicationController.getInstance().manageRequest(new Context(Evento.V_ERRORES, (int) res.getDatos()));
				}
				dispose(); // Liberamos recursos de la memoria
				
	}
}