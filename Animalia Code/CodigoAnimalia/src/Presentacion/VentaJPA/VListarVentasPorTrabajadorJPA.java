package Presentacion.VentaJPA;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import Negocio.Especie.TEspecie;
import Negocio.ProveedorJPA.TProveedor;
import Negocio.VentaJPA.TVenta;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;
import Presentacion.Habitat.VListarHabitatPorEmpleado;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class VListarVentasPorTrabajadorJPA extends JFrame implements IGUI{
	
	private List<TVenta> listaventas;

	public VListarVentasPorTrabajadorJPA() {
		super("Mostrar todas las Ventas por Trabajador");
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
				"Introduzca el ID del trabajador que desea consultar sus ventas", 1, 10, 80, 20, Color.BLACK);
		msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDCabecera);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel panelID = new JPanel();
		mainPanel.add(panelID);

		JLabel textIdEmpleado = ComponentsBuilder.createLabel("ID Trabajador: ", 10, 100, 80, 20, Color.BLACK);
		panelID.add(textIdEmpleado);

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
				VListarVentasPorTrabajadorJPA.this.setVisible(false);
					try {
						Integer id_Venta = Integer.parseInt(id.getText());
						//Vamos a tratar el error de campos nulos
		            	ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_VENTAS_POR_TRABAJADOR, 
		            			!id.getText().isEmpty()? id_Venta: 0));

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
				VListarVentasPorTrabajadorJPA.this.setVisible(false);
	            ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VVENTA, null));

			}
		});
		panelBotones.add(botonCancelar);

		this.setVisible(true);
		this.setResizable(true);
		
	}
	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actualizar(Context res) {
		if (res.getEvento() == Evento.LISTAR_VENTA_POR_TRABAJADOR_OK) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_LISTAR_VENTA_POR_IDTRABAJADOR, (List<TVenta>) res.getDatos())); 

		}else if (res.getEvento() == Evento.LISTAR_VENTA_POR_TRABAJADOR_KO) {
			
			TVenta venta = (TVenta) res.getDatos();
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) venta.getId()));
		}
		dispose(); //Liberamos recursos de la memoria
		
	}
}