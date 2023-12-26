package Presentacion.Departamento;

import javax.swing.JFrame;

import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

import java.awt.event.ActionListener;
import javax.swing.JTextField;

import Negocio.DepartamentoJPA.TDepartamento;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;


public class VAltaDepartamento extends JFrame implements IGUI, ActionListener {

	private static final long serialVersionUID = 1L;


	public VAltaDepartamento(){
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 1000;
		int alto = 525;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI();
	}
	
	
	private void initGUI(){
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel(
				"Seleccione el departamento que desea dar de alta ",
				1, 10, 80, 20, Color.BLACK);
		msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDCabecera);
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		
		JPanel panelTDepartamento = new JPanel();
		mainPanel.add(panelTDepartamento);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		//NOMBRE
		JPanel panelNombre = new JPanel();
		JLabel labelNombre = ComponentsBuilder.createLabel("Nombre: ", 10, 100, 80, 20, Color.BLACK);
		panelNombre.add(labelNombre);
		mainPanel.add(panelNombre);

		JTextField nombre = new JTextField();
		nombre.setPreferredSize(new Dimension(250, 30));
		nombre.setEditable(true);
		panelNombre.add(nombre);
					
		//BOTONES ACEPTAR CANCELAR
		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);
		
		// BOTON ACEPTAR (GUARDAR LOS DATOS DEL ALTA)
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(75, 50, 100, 100);
		botonAceptar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				VAltaDepartamento.this.setVisible(false);
				try {
					
					String nombreT = nombre.getText();
					
					TDepartamento departamento = new TDepartamento(0, nombreT != null ? nombreT : "", true);
					
					ApplicationController.getInstance().manageRequest(
							new Context(Evento.ALTA_DEPARTAMENTO, departamento));

					
				} catch (Exception ex) {
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
						VAltaDepartamento.this.setVisible(false);
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

	public void actualizar(Context res){
		if(res.getEvento() == Evento.ALTA_Departamento_OK){
			ApplicationController.getInstance().manageRequest(new Context(Evento.V_CORRECTO, (int) res.getDatos()));
		}
		else if(res.getEvento() == Evento.ALTA_Departamento_KO){
			ApplicationController.getInstance().manageRequest(new Context(Evento.V_ERRORES, (int) res.getDatos()));

		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}