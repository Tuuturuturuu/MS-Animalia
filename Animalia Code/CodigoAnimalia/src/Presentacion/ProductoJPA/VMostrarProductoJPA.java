package Presentacion.ProductoJPA;

import javax.swing.JFrame;

import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;
import java.util.Set;
import javax.swing.JTextField;

import Negocio.Animal.TAnimal;
import Negocio.Especie.TEspecie;
import Negocio.ProductoJPA.TAlimentacion;
import Negocio.ProductoJPA.TProducto;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JDialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public class VMostrarProductoJPA extends JFrame implements  IGUI {

	private Set<JTextField> jTextField;
	private Set<JButton> jButton;
	private Set<JPanel> jPanel;
	private Set<JLabel> jLabel;
	private Set<JDialog> jDialog;

	public VMostrarProductoJPA() {
		super("Mostrar Producto");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 430;
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

		JLabel msgIntroIDCabecera = ComponentsBuilder
				.createLabel("Introduzca el ID del producto a mostrar ", 1, 10, 80, 20, Color.BLACK);
		msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDCabecera);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel panelID = new JPanel();
		mainPanel.add(panelID);

		JLabel labelID = ComponentsBuilder.createLabel("ID Producto: ", 10, 100, 80, 20, Color.BLACK);
		panelID.add(labelID);

		JTextField id = new JTextField();
		id.setPreferredSize(new Dimension(250, 30));

		id.setEditable(true);
		panelID.add(id);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);
		
		//BOTON ACEPTAR
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(75, 50, 100, 100);
		botonAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VMostrarProductoJPA.this.setVisible(false);

				try {
					Integer idT = Integer.parseInt(id.getText());
					ApplicationController.getInstance().manageRequest(new Context(Evento.MOSTRAR_PRODUCTO, !id.getText().isEmpty() ? idT: 0));

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
				VMostrarProductoJPA.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VPRODUCTO, null));

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
		// TODO Auto-generated method stub
		TProducto producto = (TProducto) res.getDatos();
		if (res.getEvento() == Evento.MOSTRAR_PRODUCTO_OK) {
			String msj = "ID: "+ producto.getID() + ", Nombre Producto: " + producto.getNombre() + "Precio: "+ producto.getPrecio() +"Stock: "+ producto.getStock() +", Id Marca de Producto: " + producto.getIdMarcaProducto() + ", Tipo: " + (producto instanceof TAlimentacion ? "Alimentacion" : "Merchadising") + ", Activo: "+ producto.getActivo();
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_MOSTRAR_UNO, msj));
		
		} else if (res.getEvento() == Evento.MOSTRAR_PRODUCTO_KO) {

			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) producto.getID()));
		}
		dispose();
		
	}
}