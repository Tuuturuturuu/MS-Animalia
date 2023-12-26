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

public class VAltaTrabajadorJPA extends JFrame implements IGUI {

	public VAltaTrabajadorJPA(){
		super("Alta Trabajador");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 1000;
		int alto = 625;
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

		    JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel("Seleccione el tipo de trabajador que desea dar de alta", 1, 10, 80, 20, Color.BLACK);
		    msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		    mainPanel.add(msgIntroIDCabecera);

		    mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		    
			 // TIPO EMPLEADO
	 		JPanel panelTTrabajador = new JPanel();
	 		mainPanel.add(panelTTrabajador);

	 		JLabel labelTTrabajador = ComponentsBuilder.createLabel("Tipo de Trabajador: ", 10, 100, 80, 20,
	 				Color.BLACK);
	 		panelTTrabajador.add(labelTTrabajador);

	 		JComboBox<String> tipoTrabajador = new JComboBox<String>();
	 		tipoTrabajador.addItem("Completo");
	 		tipoTrabajador.addItem("Parcial");
	 		tipoTrabajador.setPreferredSize(new Dimension(250, 25));
	 		panelTTrabajador.add(tipoTrabajador);

	 		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
	 		
	 		//DNI
	 		JPanel panelDni = new JPanel();
			mainPanel.add(panelDni);
	 		
		    JLabel labelDni = ComponentsBuilder.createLabel("DNI: ", 10, 100, 80, 20, Color.BLACK);
		    panelDni.add(labelDni);

		    JTextField dni = new JTextField(20);
		    dni.setPreferredSize(new Dimension(250, 30));
		    dni.setEditable(true);
		    panelDni.add(dni);
	 		
			//NOMBRE
			JPanel panelNombre = new JPanel();
			mainPanel.add(panelNombre);
			
			JLabel labelNombre = ComponentsBuilder.createLabel("Nombre: ", 10, 100, 80, 20, Color.BLACK);
			panelNombre.add(labelNombre);

			JTextField nombre = new JTextField();
			nombre.setPreferredSize(new Dimension(250, 30));
			nombre.setEditable(true);
			panelNombre.add(nombre);
			
			//SUELDO 
			JPanel panelSueldo = new JPanel();
			mainPanel.add(panelSueldo);

			JLabel labelSueldo = ComponentsBuilder.createLabel("Sueldo: ", 10, 100, 80, 20, Color.BLACK);
			panelSueldo.add(labelSueldo);

			JTextField sueldo = new JTextField();
			sueldo.setPreferredSize(new Dimension(250, 30));
			sueldo.setEditable(true);
			panelSueldo.add(sueldo);
			
			//TELEFONO 
			JPanel panelTelefono = new JPanel();
			mainPanel.add(panelTelefono);

			JLabel labelTelefono = ComponentsBuilder.createLabel("Telefono: ", 10, 100, 80, 20, Color.BLACK);
			panelTelefono.add(labelTelefono);

			JTextField telefono = new JTextField();
			telefono.setPreferredSize(new Dimension(250, 30));
			telefono.setEditable(true);
			panelTelefono.add(telefono);
			
			//ID DEPARTAMENTO 
			JPanel panelIdDepartamento = new JPanel();
			mainPanel.add(panelIdDepartamento);

			JLabel labelIdDepartamento = ComponentsBuilder.createLabel("Id Departamento: ", 10, 100, 80, 20, Color.BLACK);
			panelIdDepartamento.add(labelIdDepartamento);

			JTextField idDepartamento = new JTextField();
			idDepartamento.setPreferredSize(new Dimension(250, 30));
			idDepartamento.setEditable(true);
			panelIdDepartamento.add(idDepartamento);
			
			//HORAS EXTRA (SOLO PARA COMPLETO)
			JPanel panelHorasExtra = new JPanel();
			mainPanel.add(panelHorasExtra);

			JLabel labelHorasExtra = ComponentsBuilder.createLabel("Horas Extra: ", 10, 100, 80, 20, Color.BLACK);
			panelHorasExtra.add(labelHorasExtra);

			JTextField horasExtra = new JTextField();
			horasExtra.setPreferredSize(new Dimension(250, 30));
			horasExtra.setEditable(true);
			panelHorasExtra.add(horasExtra);
			
			panelHorasExtra.setVisible(true);
			
			//EUROS POR HORA EXTRA (SOLO PARA COMPLETO)
			JPanel panelEurosPorHoraExtra = new JPanel();
			mainPanel.add(panelEurosPorHoraExtra);

			JLabel labelEurosPorHoraExtra = ComponentsBuilder.createLabel("Euros / Hora Extra: ", 10, 100, 80, 20, Color.BLACK);
			panelEurosPorHoraExtra.add(labelEurosPorHoraExtra);

			JTextField eurosPorHoraExtra = new JTextField();
			eurosPorHoraExtra.setPreferredSize(new Dimension(250, 30));
			eurosPorHoraExtra.setEditable(true);
			panelEurosPorHoraExtra.add(eurosPorHoraExtra);
			
			panelEurosPorHoraExtra.setVisible(true);
			
			//HORAS (SOLO PARA PARCIAL)
			JPanel panelHoras = new JPanel();
			mainPanel.add(panelHoras);

			JLabel labelHoras = ComponentsBuilder.createLabel("Horas: ", 10, 100, 80, 20, Color.BLACK);
			panelHoras.add(labelHoras);

			JTextField horas = new JTextField();
			horas.setPreferredSize(new Dimension(250, 30));
			horas.setEditable(true);
			panelHoras.add(horas);
			
			panelHoras.setVisible(false);
			
			//EUROS POR HORA (SOLO PARA PARCIAL)
			JPanel panelEurosPorHora = new JPanel();
			mainPanel.add(panelEurosPorHora);

			JLabel labelEurosPorHora = ComponentsBuilder.createLabel("Euros / Hora: ", 10, 100, 80, 20, Color.BLACK);
			panelEurosPorHora.add(labelEurosPorHora);

			JTextField eurosPorHora = new JTextField();
			eurosPorHora.setPreferredSize(new Dimension(250, 30));
			eurosPorHora.setEditable(true);
			panelEurosPorHora.add(eurosPorHora);
			
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
			
			
		    // Panel para los botones
		    JPanel panelBotones = new JPanel();
		    mainPanel.add(panelBotones);
		    
		    // BOTON ACEPTAR (GUARDAR LOS DATOS DEL ALTA)
		    JButton botonAceptar = new JButton("Aceptar");
		    botonAceptar.addActionListener(new ActionListener() {

		        @Override
		        public void actionPerformed(ActionEvent e) {
		            VAltaTrabajadorJPA.this.setVisible(false);
					int tipo = -47548;
		            //Vamos a tratar los errores en caso de ingresar campos nulos
		            
					try {
						if(tipoTrabajador.getSelectedItem() == "Completo"){
							
							
							String dniT = dni.getText();
							String nombreT = nombre.getText();
							Double sueldoT = Double.parseDouble(sueldo.getText());
							Integer telefonoT = Integer.parseInt(telefono.getText());
							Integer idDepartamentoT = Integer.parseInt(idDepartamento.getText());
							Double horasExtraT = Double.parseDouble(horasExtra.getText());
							Double eurosPorHoraExtraT = Double.parseDouble(eurosPorHoraExtra.getText());
							
							TTrabajadorCompleto completo = new TTrabajadorCompleto(
									0, 
									dniT != null ? dniT : "",
									nombreT != null ? nombreT : "",
									sueldoT != null ? sueldoT:0,
									!telefono.getText().isEmpty() ? telefonoT:0,
									!idDepartamento.getText().isEmpty() ? idDepartamentoT:0,
									true,
									horasExtraT != null ? horasExtraT:0,
									eurosPorHoraExtraT != null ? eurosPorHoraExtraT:0);
							
							ApplicationController.getInstance().manageRequest(
									new Context(Evento.ALTA_TRABAJADOR, completo));

						}
						else if(tipoTrabajador.getSelectedItem() == "Parcial"){
							
							String dniT = dni.getText();
							String nombreT = nombre.getText();
							Double sueldoT = Double.parseDouble(sueldo.getText());
							Integer telefonoT = Integer.parseInt(telefono.getText());
							Integer idDepartamentoT = Integer.parseInt(idDepartamento.getText());
							Double horasT = Double.parseDouble(horas.getText());
							Double eurosPorHoraT = Double.parseDouble(eurosPorHora.getText());
							
							TTrabajadorParcial parcial = new TTrabajadorParcial(
									0, 
									dniT != null ? dniT : "",
									nombreT != null ? nombreT : "",
									sueldoT != null ? sueldoT:0,
									!telefono.getText().isEmpty() ? telefonoT:0,
									!idDepartamento.getText().isEmpty() ? idDepartamentoT:0,
									true,
									horasT != null ? horasT:0,
											eurosPorHoraT != null ? eurosPorHoraT:0);
							
							ApplicationController.getInstance().manageRequest(
									new Context(Evento.ALTA_TRABAJADOR, parcial));

						}
					} catch (Exception ex) {
							ApplicationController.getInstance().manageRequest(new Context(Evento.V_ERRORES, -4));
					}
		            
		        }
		    });
		    panelBotones.add(botonAceptar);
	 		
	 		
		  //BOTON CANCELAR
		    JButton botonCancelar = new JButton("Cancelar");
		    botonCancelar.addActionListener(new ActionListener() {

		        @Override
		        public void actionPerformed(ActionEvent e) {
		            VAltaTrabajadorJPA.this.setVisible(false);
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

	@Override
	public void actualizar(Context res) {
		
		if (res.getEvento() == Evento.ALTA_TRABAJADOR_OK) {			
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_CORRECTO, (int) res.getDatos()));
		} else if (res.getEvento() == Evento.ALTA_TRABAJADOR_KO) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) res.getDatos()));
		}
		
		dispose(); //Liberamos recursos de la memoria
		
	}
}