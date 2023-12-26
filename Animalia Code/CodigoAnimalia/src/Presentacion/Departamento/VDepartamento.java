package Presentacion.Departamento;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Negocio.DepartamentoJPA.TDepartamento;
import Negocio.VentaJPA.TCarritoJPA;
import Negocio.VentaJPA.TVenta;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;


public class VDepartamento extends JFrame implements IGUI{
	
	private JButton bAltaDepartamento;
	private JButton bBajaDepartamento;
	private JButton bModificarDepartamento;
	private JButton bMostrarDepartamento;
	private JButton bListarDepartamento;
	private JButton bCalcularNominaDepartamento;
	private JButton backButton;
	private JPanel j;

	private Set departamento;
	
	public VDepartamento(){
		super("Animalia");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 1000;
		int alto = 525;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setLayout(null);
		j = new JPanel();
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initGUI();
		this.setVisible(true);
	}

	private void initGUI() {
		JLabel label = ComponentsBuilder.createLabel("Departamento", 250, 30, 500, 50, Color.BLACK);
		this.add(label);
		
		departamento = new HashSet();
		//ALTA DEPARTAMENTO
		bAltaDepartamento = ComponentsBuilder.createButton("Alta Departamento", 100, 100, 185, 100);
		bAltaDepartamento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VDepartamento.this.setVisible(false);
				TCarritoJPA carrito = new TCarritoJPA();
				ApplicationController.getInstance().manageRequest(new Context(Evento.VALTA_DEPARTAMENTO, carrito));
			}

		});
		bAltaDepartamento.setVisible(true);
		this.add(bAltaDepartamento);
		
		//BAJA DEPARTAMENTO
		bBajaDepartamento = ComponentsBuilder.createButton("Baja Departamento", 300, 100, 185, 100);
		bBajaDepartamento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VDepartamento.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VBAJA_DEPARTAMENTO, null));
			}

		});
		bBajaDepartamento.setVisible(true);
		this.add(bBajaDepartamento);
		
		//MODIFICAR DEPARTAMENTO
		bModificarDepartamento = ComponentsBuilder.createButton("Modificar Departamento", 500, 100, 185, 100);
		bModificarDepartamento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VDepartamento.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VMODIFICAR_DEPARTAMENTO, null));
			}

		});
		bModificarDepartamento.setVisible(true);
		this.add(bModificarDepartamento);
		
		//MOSTRAR DEPARTAMENTO
		bMostrarDepartamento = ComponentsBuilder.createButton("Mostrar Departamento", 700, 100, 185, 100);
		bMostrarDepartamento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VDepartamento.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VMOSTRAR_DEPARTAMENTO, null));
			}

		});
		bMostrarDepartamento.setVisible(true);
		this.add(bMostrarDepartamento);
		
		//LISTAR DEPARTAMENTO
		bListarDepartamento = ComponentsBuilder.createButton("Listar Departamento", 100, 250, 185, 100);
		bListarDepartamento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VDepartamento.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_DEPARTAMENTO));
			}

		});
		bListarDepartamento.setVisible(true);
		this.add(bListarDepartamento);
		
		//CALCULAR NOMINA DEPARTAMENTO
		bCalcularNominaDepartamento = ComponentsBuilder.createButton("Calcular Nomina Departamento", 300, 250, 285, 100);
		bCalcularNominaDepartamento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VDepartamento.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VCALCULAR_NOMINA_DEPARTAMENTO, null));
			}

		});
		bCalcularNominaDepartamento.setVisible(true);
		this.add(bCalcularNominaDepartamento);
		//BACK BUTTON
		backButton = ComponentsBuilder.createBackButton();
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VDepartamento.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_MAIN_VIEW,null));
				dispose();
			}
		});
		backButton.setVisible(true);
		this.add(backButton);
		
		getContentPane().add(j);

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