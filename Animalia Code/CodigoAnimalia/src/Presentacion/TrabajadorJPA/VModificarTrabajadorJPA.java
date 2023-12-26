/**
 * 
 */
package Presentacion.TrabajadorJPA;

import javax.swing.JFrame;

import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.Empleado.VModificarEmpleado;
import Presentacion.FactoriaVistas.IGUI;
import javax.swing.JTextField;

import Negocio.TrabajadorJPA.TTrabajador;
import Negocio.TrabajadorJPA.TTrabajadorCompleto;
import Negocio.TrabajadorJPA.TTrabajadorParcial;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JDialog;

public class VModificarTrabajadorJPA extends JFrame implements IGUI {
	
	public VModificarTrabajadorJPA() {
		super("Modificar Trabajador");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 1000;
		int alto = 625;
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
		
		// ID TRABAJADOR
		JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel("Introduzca el ID del Trabajador que quiere modificar ", 1, 10, 80, 20, Color.BLACK);
		msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDCabecera);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		
		JPanel panelID = new JPanel();
		mainPanel.add(panelID);

		JLabel labelID = ComponentsBuilder.createLabel("ID Trabajador: ", 10, 100, 80, 20, Color.BLACK);
		panelID.add(labelID);

		JTextField textIdEmpleado = new JTextField();
		textIdEmpleado.setPreferredSize(new Dimension(250, 30));
		textIdEmpleado.setEditable(true);
		panelID.add(textIdEmpleado);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		// TIPO TRABAJADORES
		JPanel panelTipo = new JPanel();
		mainPanel.add(panelTipo);

		JLabel labelTipo = ComponentsBuilder.createLabel("Tipo de Trabajador: ", 10, 100, 80, 20,
				Color.BLACK);
		panelTipo.add(labelTipo);

		JComboBox<String> tipoTrabajador = new JComboBox<String>();
		tipoTrabajador.addItem("Completo");
		tipoTrabajador.addItem("Parcial");
		tipoTrabajador.setPreferredSize(new Dimension(250, 25));
		panelTipo.add(tipoTrabajador);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		// DNI TRABAJADOR
		JPanel panelDni = new JPanel();
		mainPanel.add(panelDni);

		JLabel labelDni = ComponentsBuilder.createLabel("DNI Trabajador: ", 10, 100, 80, 20, Color.BLACK);
		panelDni.add(labelDni);

		JTextField textDni = new JTextField();
		textDni.setPreferredSize(new Dimension(250, 30));
		textDni.setEditable(true);
		panelDni.add(textDni);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		// NOMBRE TRABAJADOR
		JPanel panelNombre = new JPanel();
		mainPanel.add(panelNombre);

		JLabel labelNombre = ComponentsBuilder.createLabel("Nombre Trabajador: ", 10, 100, 80, 20, Color.BLACK);
		panelNombre.add(labelNombre);

		JTextField textNombre = new JTextField();
		textNombre.setPreferredSize(new Dimension(250, 30));
		textNombre.setEditable(true);
		panelNombre.add(textNombre);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		// SUELDO TRABAJADOR
		JPanel panelSueldo = new JPanel();
		mainPanel.add(panelSueldo);

		JLabel labelSueldo = ComponentsBuilder.createLabel("Sueldo Trabajador: ", 10, 100, 80, 20, Color.BLACK);
		panelSueldo.add(labelSueldo);

		JTextField textSueldo = new JTextField();
		textSueldo.setPreferredSize(new Dimension(250, 30));
		textSueldo.setEditable(true);
		panelSueldo.add(textSueldo);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		// TELEFONO TRABAJADOR
		JPanel panelTelefono = new JPanel();
		mainPanel.add(panelTelefono);

		JLabel labelTelefono = ComponentsBuilder.createLabel("Telefono Trabajador: ", 10, 100, 80, 20, Color.BLACK);
		panelTelefono.add(labelTelefono);

		JTextField textTelefono = new JTextField();
		textTelefono.setPreferredSize(new Dimension(250, 30));
		textTelefono.setEditable(true);
		panelTelefono.add(textTelefono);
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		// TELEFONO ID DEPARTAMENTO
		JPanel panelIdDepartamento = new JPanel();
		mainPanel.add(panelIdDepartamento);

		JLabel labelIdDepartamento = ComponentsBuilder.createLabel("ID Departamento: ", 10, 100, 80, 20, Color.BLACK);
		panelIdDepartamento.add(labelIdDepartamento);

		JTextField textIdDepartamento = new JTextField();
		textIdDepartamento.setPreferredSize(new Dimension(250, 30));
		textIdDepartamento.setEditable(true);
		panelIdDepartamento.add(textIdDepartamento);
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		// HORAS EXTRA (SOLO PARA COMPLETO)
		JPanel panelHorasExtra = new JPanel();
		mainPanel.add(panelHorasExtra);

		JLabel labelHorasExtra = ComponentsBuilder.createLabel("Horas Extra: ", 10, 100, 80, 20, Color.BLACK);
		panelHorasExtra.add(labelHorasExtra);

		JTextField horasExtra = new JTextField();
		horasExtra.setPreferredSize(new Dimension(250, 30));
		horasExtra.setEditable(true);
		panelHorasExtra.add(horasExtra);

		panelHorasExtra.add(Box.createRigidArea(new Dimension(0, 20)));
		
		panelHorasExtra.setVisible(true);
		
		// EUROS POR HORA EXTRA (SOLO PARA COMPLETO)
		JPanel panelEurosPorHoraExtra = new JPanel();
		mainPanel.add(panelEurosPorHoraExtra);

		JLabel labelEurosPorHoraExtra = ComponentsBuilder.createLabel("Euros / Hora Extra: ", 10, 100, 80, 20, Color.BLACK);
		panelEurosPorHoraExtra.add(labelEurosPorHoraExtra);

		JTextField eurosPorHoraExtra = new JTextField();
		eurosPorHoraExtra.setPreferredSize(new Dimension(250, 30));
		eurosPorHoraExtra.setEditable(true);
		panelEurosPorHoraExtra.add(eurosPorHoraExtra);

		panelEurosPorHoraExtra.add(Box.createRigidArea(new Dimension(0, 50)));
		
		panelEurosPorHoraExtra.setVisible(true);
		
		// HORAS (SOLO PARA PARCIAL)
		JPanel panelHoras = new JPanel();
		mainPanel.add(panelHoras);

		JLabel labelHoras = ComponentsBuilder.createLabel("Horas: ", 10, 100, 80, 20, Color.BLACK);
		panelHoras.add(labelHoras);

		JTextField horas = new JTextField();
		horas.setPreferredSize(new Dimension(250, 30));
		horas.setEditable(true);
		panelHoras.add(horas);

		panelHoras.add(Box.createRigidArea(new Dimension(0, 20)));
		
		panelHoras.setVisible(false);
		
		// EUROS POR HORA (SOLO PARA PARCIAL)
		JPanel panelEurosPorHora = new JPanel();
		mainPanel.add(panelEurosPorHora);

		JLabel labelEurosPorHora = ComponentsBuilder.createLabel("Euros / Hora: ", 10, 100, 80, 20, Color.BLACK);
		panelEurosPorHora.add(labelEurosPorHora);

		JTextField eurosPorHora = new JTextField();
		eurosPorHora.setPreferredSize(new Dimension(250, 30));
		eurosPorHora.setEditable(true);
		panelEurosPorHora.add(eurosPorHora);
		
		panelEurosPorHora.add(Box.createRigidArea(new Dimension(0, 50)));

		panelEurosPorHora.setVisible(false);
		
		
		
		tipoTrabajador.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e ){
				String option = (String) tipoTrabajador.getSelectedItem();
				if(option.equals("Completo")) {
					panelHorasExtra.setVisible(true);
					panelEurosPorHoraExtra.setVisible(true);
					panelHoras.setVisible(false);
					panelEurosPorHora.setVisible(false);
				}
				else if (option.equals("Parcial")){
					panelHorasExtra.setVisible(false);
					panelEurosPorHoraExtra.setVisible(false);
					panelHoras.setVisible(true);
					panelEurosPorHora.setVisible(true);
				}
			}
			
		});

		// BOTONES ACEPTAR CANCELAR
		
		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);

		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(75, 50, 100, 100);
		botonAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				           
				VModificarTrabajadorJPA.this.setVisible(false);
				try {
					if(tipoTrabajador.getSelectedItem() == "Completo"){
						
						Integer idT = Integer.parseInt(textIdEmpleado.getText());
						String dniT = textDni.getText();
						String nombreT = textNombre.getText();
						Double sueldoT = Double.parseDouble(textSueldo.getText());
						Integer telefonoT = Integer.parseInt(textTelefono.getText());
						Integer idDepartamentoT = Integer.parseInt(textIdDepartamento.getText());
						Double horasExtraT = Double.parseDouble(horasExtra.getText());
						Double eurosPorHoraExtraT = Double.parseDouble(eurosPorHoraExtra.getText());
						
						TTrabajadorCompleto completo = new TTrabajadorCompleto(
								idT != null ? idT:0, 
								dniT != null ? dniT : "",
								nombreT != null ? nombreT : "",
								sueldoT != null ? sueldoT:0,
								!textTelefono.getText().isEmpty() ? telefonoT:0,
								!textIdDepartamento.getText().isEmpty() ? idDepartamentoT:0,
								true,
								horasExtraT != null ? horasExtraT:0,
								eurosPorHoraExtraT != null ? eurosPorHoraExtraT:0);
						
						ApplicationController.getInstance().manageRequest(
								new Context(Evento.MODIFICAR_TRABAJADOR, completo));

					}
					else if(tipoTrabajador.getSelectedItem() == "Parcial"){
						
						Integer idT = Integer.parseInt(textIdEmpleado.getText());
						String dniT = textDni.getText();
						String nombreT = textNombre.getText();
						Double sueldoT = Double.parseDouble(textSueldo.getText());
						Integer telefonoT = Integer.parseInt(textTelefono.getText());
						Integer idDepartamentoT = Integer.parseInt(textIdDepartamento.getText());
						Double horasT = Double.parseDouble(horas.getText());
						Double eurosPorHoraT = Double.parseDouble(eurosPorHora.getText());
						
						TTrabajadorParcial parcial = new TTrabajadorParcial(
								idT != null ? idT:0, 
								dniT != null ? dniT : "",
								nombreT != null ? nombreT : "",
								sueldoT != null ? sueldoT:0,
								!textTelefono.getText().isEmpty() ? telefonoT:0,
								!textIdDepartamento.getText().isEmpty() ? idDepartamentoT:0,
								true,
								horasT != null ? horasT:0,
								eurosPorHoraT != null ? eurosPorHoraT:0);
						
						ApplicationController.getInstance().manageRequest(
								new Context(Evento.MODIFICAR_TRABAJADOR, parcial));

					}
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
				VModificarTrabajadorJPA.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context (Evento.CREAR_VTRABAJADOR, null));
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

		// begin-user-code
		// TODO Auto-generated method stub
		if (res.getEvento() == Evento.MODIFICAR_TRABAJADOR_OK) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_CORRECTO, (int) res.getDatos()));
		} else if (res.getEvento() == Evento.MODIFICAR_TRABAJADOR_KO) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) res.getDatos()));
		}
		dispose(); //Liberamos recursos de la memoria
		// end-user-code
		
	}
}