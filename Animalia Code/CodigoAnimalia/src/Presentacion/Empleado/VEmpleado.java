package Presentacion.Empleado;

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

import Negocio.Empleado.TEmpleado;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;
import Negocio.Empleado.TEmpleadoLimpieza;
import Negocio.Empleado.TEmpleadoZoologico;


@SuppressWarnings("serial")
public class VEmpleado  extends JFrame implements IGUI{

	private JButton bAltaEmpleado;
	private JButton bBajaEmpleado;
	private JButton bModificarEmpleado;
	private JButton bMostrarEmpleado;
	private JButton bListarEmpleado;
	private JButton bListarEmpleadoLimpieza;
	private JButton bListarEmpleadoZoologico;
	private JButton bListarPorHabitatEmpleado;
	private JButton bListarEmpleadosPorEspecieEnHabitat;
	private JButton backButton;
	private JPanel j;

	private TEmpleado tEmpleado;
	private Set<TEmpleado> hsEmpleado;
	private Set<TEmpleadoLimpieza> hsEmpleadoL;
	private Set<TEmpleadoZoologico> hsEmpleadoZ;

	public VEmpleado(){
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
	
	public void initGUI() {
		tEmpleado = new TEmpleado();
		hsEmpleado = new HashSet<TEmpleado> ();
		hsEmpleadoL = new HashSet<TEmpleadoLimpieza> ();
		hsEmpleadoZ = new HashSet<TEmpleadoZoologico> ();
		JLabel label = ComponentsBuilder.createLabel("Empleado", 250, 30, 500, 50, Color.BLACK);
		this.add(label);
		
		//ALTA EMPLEADO
		bAltaEmpleado = ComponentsBuilder.createButton("Alta Empleado", 100, 100, 185, 100);
		bAltaEmpleado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VEmpleado.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VALTA_EMPLEADO, tEmpleado));
			}

		});
		bAltaEmpleado.setVisible(true);
		this.add(bAltaEmpleado);
		
		//BAJA EMPLEADO
		bBajaEmpleado = ComponentsBuilder.createButton("Baja Empleado", 407, 100, 185, 100);
		bBajaEmpleado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VEmpleado.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VBAJA_EMPLEADO, tEmpleado));
			}

		});
		bBajaEmpleado.setVisible(true);
		this.add(bBajaEmpleado);
		
		//MODIFICAR EMPLEADO
		bModificarEmpleado = ComponentsBuilder.createButton("Modificar Empleado", 715, 100, 185, 100);
		bModificarEmpleado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VEmpleado.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VMODIFICAR_EMPLEADO, tEmpleado));
			}

		});
		bModificarEmpleado.setVisible(true);
		this.add(bModificarEmpleado);
		
		//MOSTRAR EMPLEADO
		bMostrarEmpleado = ComponentsBuilder.createButton("Mostrar Empleado", 100, 220, 185, 100);
		bMostrarEmpleado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VEmpleado.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VMOSTRAR_EMPLEADO, tEmpleado));
			}

		});
		bMostrarEmpleado.setVisible(true);
		this.add(bMostrarEmpleado);
		
		//LISTAR EMPLEADOS
		bListarEmpleado = ComponentsBuilder.createButton("Listar Empleados", 407, 220, 185, 100);
		bListarEmpleado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VEmpleado.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VLISTAR_EMPLEADO, hsEmpleado));
			}

		});
		bListarEmpleado.setVisible(true);
		this.add(bListarEmpleado);
		
		//LISTAR EMPLEADOS LIMPIEZA
				bListarEmpleadoLimpieza = ComponentsBuilder.createButton("Listar Empleados Limpieza", 708, 220, 200, 100);
				bListarEmpleadoLimpieza.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						VEmpleado.this.setVisible(false);
						ApplicationController.getInstance().manageRequest(new Context(Evento.VLISTAR_EMPLEADOS_LIMPIEZA, hsEmpleadoL));
					}

				});
				bListarEmpleadoLimpieza.setVisible(true);
				this.add(bListarEmpleadoLimpieza);

		//LISTAR EMPLEADOS ZOOLOGICO
		bListarEmpleadoZoologico = ComponentsBuilder.createButton("Listar Empleados Zoologico", 100, 340, 200, 100);
		bListarEmpleadoZoologico.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VEmpleado.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VLISTAR_EMPLEADOS_ZOOLOGICO, hsEmpleadoZ));
			}

		});
		bListarEmpleadoZoologico.setVisible(true);
		this.add(bListarEmpleadoZoologico);
		
		//LISTAR EMPLEADOS POR HABITAT
		bListarPorHabitatEmpleado = ComponentsBuilder.createButton("Listar Empleados Por Habitat", 407, 340, 200, 100);
		bListarPorHabitatEmpleado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VEmpleado.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VLISTAR_EMPLEADO_POR_HABITAT, tEmpleado));
			}

		});
		bListarPorHabitatEmpleado.setVisible(true);
		this.add(bListarPorHabitatEmpleado);
		
		//LISTAR EMPLEADOS POR ESPECIE EN HABITAT 
		bListarEmpleadosPorEspecieEnHabitat = ComponentsBuilder.createButton("Empleados Por Especie En Habitat", 715, 340, 250, 100);
		bListarEmpleadosPorEspecieEnHabitat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VEmpleado.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VLISTAR_EMPLEADO_POR_ESPECIE_EN_HABITAT, tEmpleado));
			}

		});
		bListarEmpleadosPorEspecieEnHabitat.setVisible(true);
		this.add(bListarEmpleadosPorEspecieEnHabitat);
		
		//BACK BUTTON
		backButton = ComponentsBuilder.createBackButton();
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VEmpleado.this.setVisible(false);
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
