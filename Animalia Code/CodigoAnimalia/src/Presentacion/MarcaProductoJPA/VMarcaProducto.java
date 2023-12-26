package Presentacion.MarcaProductoJPA;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Negocio.MarcaProductoJPA.TMarcaProducto;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

public class VMarcaProducto extends JFrame implements IGUI {
	
	private static final long serialVersionUID = 1L;
	private JButton bAltaMarcaProducto;
	private JButton bBajaMarcaProducto;
	private JButton bModificarMarcaProducto;
	private JButton bMostrarMarcaProducto;
	private JButton bListarMarcaProductos;
	private JButton bListarMarcasPorProveedor;
	private JButton bBack;
	private JPanel j;

	private TMarcaProducto TMarcaProducto;
	private List<TMarcaProducto> hsMarcaProducto;

	public VMarcaProducto() {
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

		TMarcaProducto = new TMarcaProducto();
		hsMarcaProducto = new ArrayList<TMarcaProducto>();
		JLabel label = ComponentsBuilder.createLabel("Marca de Producto", 250, 30, 500, 50, Color.BLACK);
		this.add(label);

		// ALTA MARCA
		bAltaMarcaProducto = ComponentsBuilder.createButton("Alta Marca de Producto", 100, 100, 185, 100);
		bAltaMarcaProducto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VMarcaProducto.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VALTA_MARCA, TMarcaProducto));
			}

		});
		bAltaMarcaProducto.setVisible(true);
		this.add(bAltaMarcaProducto);

		// BAJA PRODUCTO
		bBajaMarcaProducto = ComponentsBuilder.createButton("Baja Marca de Producto", 300, 100, 185, 100);
		bBajaMarcaProducto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VMarcaProducto.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VBAJA_MARCA, TMarcaProducto));
			}

		});
		bBajaMarcaProducto.setVisible(true);
		this.add(bBajaMarcaProducto);

		// MODIFICAR PRODUCTO
		bModificarMarcaProducto = ComponentsBuilder.createButton("Modificar Marca de Producto", 500, 100, 185, 100);
		bModificarMarcaProducto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VMarcaProducto.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VMODIFICAR_MARCA, TMarcaProducto));
			}

		});
		bModificarMarcaProducto.setVisible(true);
		this.add(bModificarMarcaProducto);

		// MOSTRAR PRODUCTO
		bMostrarMarcaProducto = ComponentsBuilder.createButton("Mostrar Marca de Producto", 700, 100, 185, 100);
		bMostrarMarcaProducto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VMarcaProducto.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VMOSTRAR_MARCA, TMarcaProducto));
			}

		});
		bMostrarMarcaProducto.setVisible(true);
		this.add(bMostrarMarcaProducto);

		// LISTAR PRODUCTOS
		bListarMarcaProductos = ComponentsBuilder.createButton("Listar Marcas de Producto", 100, 250, 185, 100);
		bListarMarcaProductos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VMarcaProducto.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_MARCA, hsMarcaProducto));
			}

		});
		bListarMarcaProductos.setVisible(true);
		this.add(bListarMarcaProductos);

		// LISTAR MARCA POR PROVEEDOR
		bListarMarcasPorProveedor = ComponentsBuilder.createButton("Listar Marcas de producto por proveedor", 700, 250, 185, 100);
		bListarMarcasPorProveedor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VMarcaProducto.this.setVisible(false);
				ApplicationController.getInstance()
						.manageRequest(new Context(Evento.VLISTAR_MARCAS_POR_PROVEEDOR, TMarcaProducto));
			}

		});
		bListarMarcasPorProveedor.setVisible(true);
		this.add(bListarMarcasPorProveedor);

		// BACK BUTTON
		bBack = ComponentsBuilder.createBackButton();
		bBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VMarcaProducto.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_MAIN_VIEW, null));
				dispose();
			}
		});
		bBack.setVisible(true);
		this.add(bBack);

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
